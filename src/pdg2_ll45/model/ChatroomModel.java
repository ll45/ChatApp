package pdg2_ll45.model;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.UnknownHostException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

import common.api.team.*;
import common.api.message.team.IAdd;
import common.api.message.team.ITeamMsg;
import common.api.message.team.IFailedTeamMsg;
import common.api.message.team.IAddCmd;
import common.api.message.team.IGetCmd;
import common.api.message.team.IQuit;
import common.api.message.team.IStringMsg;
import common.api.message.user.IUserMsg;
import common.api.user.IUser;
import common.api.user.UserDataPacket;
import pdg2_ll45.messages.Team.AddCmd;
import pdg2_ll45.messages.Team.GetCmd;
import pdg2_ll45.messages.Team.ImageMsg;
import pdg2_ll45.messages.Team.ImageMsgCmd;
import pdg2_ll45.messages.Team.StringMsg;
import provided.datapacket.ADataPacket;
import provided.datapacket.DataPacket;
import provided.datapacket.DataPacketAlgo;
import provided.extvisitor.IExtVisitorCmd;
import provided.extvisitor.IExtVisitorHost;
import provided.rmiUtils.IRMIUtils;
import provided.rmiUtils.IRMI_Defs;
import provided.rmiUtils.RMIUtils;

/**
 * The class for Team model. Implements ITeamModel
 */
public class TeamModel implements ITeamModel {
	
	/**
	 * A model to view adapter
	 */
	private ITeamModel2ViewAdapter model2ViewAdapter;
	
	/**
	 * A model to MAIN model adapter
	 */
	private ITeamModel2MainModelAdapter Team2MainAdapter;
	
	/**
	 * A Team
	 */
	private ITeam Team;
	
	/**
	 * The Team's unique ID
	 */
	private UUID TeamID;
	
	/**
	 * Team's name
	 */
	private String chatName;
	
	/**
	 * Local address
	 */
	private String localAddress;
	
	/**
	 * The user of Team
	 */
	private IUser user;
	
	/**
	 * The message receiver stub of the user
	 */
	private IMsgReceiver ownReceiver;
	
	/**
	 * The message receiver of the Team stub
	 */
	private IMsgReceiver TeamStub;
	
	/**
	 * RMIUtils
	 */
	private IRMIUtils rmiUtils;
	
	/**
	 * Hashmap to handle unknown message types
	 */
	private HashMap<Class<? extends ITeamMsg>, TeamDataPacket<ITeamMsg>> unknownMessages = new HashMap<Class<? extends ITeamMsg>, TeamDataPacket<ITeamMsg>>();

	/**
	 * The command to model adapter
	 */
	private ICmd2ModelAdapter cmd2ModelAdapter = new ICmd2ModelAdapter() {

		@Override
		public void buildComponent(Supplier<JComponent> component) {
			model2ViewAdapter.buildComponent(component);
		}

		@Override
		public void appendString(String message) {
			model2ViewAdapter.appendString(message);
		}

	};
	
	/**
	 * A data packet algorithm
	 */
	private TeamDPAlgo dataPacketAlgo = new TeamDPAlgo(new ATeamDPAlgoCmd<ITeamMsg>() {

		// Default method sends an IGetCmd request. 
		@Override
		public Void apply(Class<?> index, TeamDataPacket<ITeamMsg> host, Void... params) {
			IMsgReceiver sender = host.getSender();
			try {

				unknownMessages.put(host.getData().getClass(), host);
				System.out.println("unknown type: " + host.getData().getClass());
				sender.receive(new TeamDataPacket<IGetCmd>(IGetCmd.class, new GetCmd(host.getData().getClass()),
						TeamStub));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

	});
	
	/**
	 * Constructor for the Team model
	 * @param Team		the Team object
	 * @param model2ViewAdapter			the model to view adapter
	 * @param Team2MainAdapter		the Team to main model adapter
	 * @param user		the user
	 */
	public TeamModel(ITeam Team, ITeamModel2ViewAdapter model2ViewAdapter,
			ITeamModel2MainModelAdapter Team2MainAdapter, IUser user) {
		this.Team = Team;
		this.model2ViewAdapter = model2ViewAdapter;
		this.Team2MainAdapter = Team2MainAdapter;
		this.user = user;

		//Initialize DataPacketAlgo
		initDataPacketAlgo();
	}

	/**
	 * Initialize the data packet algorithm
	 */
	public void initDataPacketAlgo() {
		dataPacketAlgo.setCmd(IStringMsg.class, new ATeamDPAlgoCmd<IStringMsg>() {

			@Override
			public Void apply(Class<?> index, TeamDataPacket<IStringMsg> host, Void... params) {
				IStringMsg msg = host.getData();
				model2ViewAdapter.addText(msg.getContent());
				return null;
			}

		});
		dataPacketAlgo.setCmd(IFailedTeamMsg.class, new ATeamDPAlgoCmd<IFailedTeamMsg>() {

			@Override
			public Void apply(Class<?> index, TeamDataPacket<IFailedTeamMsg> host, Void... params) {
				model2ViewAdapter.addText("Failed Team message: " + host.getData().getErrorMessage());
				return null;
			}

		});
		dataPacketAlgo.setCmd(IAdd.class, new ATeamDPAlgoCmd<IAdd>() {

			@Override
			public Void apply(Class<?> index, TeamDataPacket<IAdd> host, Void... params) {
				IMsgReceiver sender = host.getSender();
				IUser userStub = host.getData().getUserStub();
				if (!Team.getMsgReceivers().contains(sender)) {
					Team.addReceiver(sender);
				}
				Team2MainAdapter.addUser(userStub);
				System.out.println("added user to Team, users: " + Team.getMsgReceivers().toString());
				return null;
			}

		});
		dataPacketAlgo.setCmd(IGetCmd.class, new ATeamDPAlgoCmd<IGetCmd>() {

			@Override
			public Void apply(Class<?> index, TeamDataPacket<IGetCmd> host, Void... params) {
				IMsgReceiver sender = host.getSender();
				Class<? extends ITeamMsg> unknownClass = host.getData().getClassIndex();
				@SuppressWarnings("unchecked")
				AddCmd cmd = new AddCmd(
						(ATeamDPAlgoCmd<? extends ITeamMsg>) dataPacketAlgo.getCmd(unknownClass), unknownClass);
				TeamDataPacket<IAddCmd> message = new TeamDataPacket<IAddCmd>(IAddCmd.class, cmd, TeamStub);
				try {
					sender.receive(message);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

		});
		dataPacketAlgo.setCmd(IAddCmd.class, new ATeamDPAlgoCmd<IAddCmd>() {

			@Override
			public Void apply(Class<?> index, TeamDataPacket<IAddCmd> host, Void... params) {
				dataPacketAlgo.setCmd(host.getData().getClassIndex(), host.getData().getCmd());
				host.getData().getCmd().setCmd2ModelAdpt(cmd2ModelAdapter);
				TeamDataPacket<? extends ITeamMsg> unknownMsg = unknownMessages
						.get(host.getData().getClassIndex());
				if (unknownMsg == null)
					return null;
				System.out.println("Processing for type: " + unknownMsg.getData().getClass());
				unknownMsg.execute(dataPacketAlgo, (Void[]) null);
				return null;
			}

		});
		dataPacketAlgo.setCmd(IQuit.class, new ATeamDPAlgoCmd<IQuit>() {

			@Override
			public Void apply(Class<?> index, TeamDataPacket<IQuit> host, Void... params) {
				System.out.println("in quit command");
				System.out.println("before remove, num receivers: " + Team.getMsgReceivers().size());
				System.out.println("receivers contains us: " + Team.getMsgReceivers().contains(host.getSender()));
				Team.removeReceiver(host.getSender());
				System.out.println("after remove, num receivers: " + Team.getMsgReceivers().size());
				return null;
			}
		});
		dataPacketAlgo.setCmd(ImageMsg.class, new ImageMsgCmd(cmd2ModelAdapter));
	}

	/**
	 * Start the model
	 */
	public void start() {

		ownReceiver = new IMsgReceiver() {
			public void receive(TeamDataPacket<? extends ITeamMsg> message) throws RemoteException {
				message.execute(dataPacketAlgo, (Void[]) null);
				System.out.println("Received packet: " + message.toString());
			}
		};

		try {
			TeamStub = (IMsgReceiver) UnicastRemoteObject.exportObject(this.ownReceiver, IRMI_Defs.REGISTRY_PORT);
		} catch (RemoteException e) {
			System.out.println("Error creating & binding user stub: " + e.getClass());
			e.printStackTrace();
		}

	}

	/**
	 * Sends message to remote user
	 * @param message 		the String message to be sent to remote user
	 */
	public void sendMessage(String message) {
		try {
			Team.notifyAllReceivers(
					new TeamDataPacket<IStringMsg>(IStringMsg.class, new StringMsg(message), TeamStub));
		} catch (RemoteException e) {
			System.out.println("Error handling string message.");
			e.printStackTrace();
		}
	}

	/**
	 * Sends image to remote user
	 */
	public void sendImage() {
		try {
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File("calabiyau4C.png"));
			} catch (IOException e) {
			}
			Team.notifyAllReceivers(
					new TeamDataPacket<ImageMsg>(ImageMsg.class, new ImageMsg(img), TeamStub));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the message receiver stub
	 * @return the message receiver stub
	 */
	public IMsgReceiver getReceiverStub() {
		return TeamStub;
	}
	
	/**
	 * Gets the Team 
	 * @return the Team
	 */
	public ITeam getTeam() {
		return Team;
	}
	
	/**
	 * Quits the Team
	 */
	@Override
	public void quitRoom() {
		Team2MainAdapter.quitRoom();
	}

	/**
	 * Executes a message
	 */
	@Override
	public void executeMessage(TeamDataPacket<? extends ITeamMsg> message) {
		message.execute(dataPacketAlgo, (Void[]) null);
	}
}
