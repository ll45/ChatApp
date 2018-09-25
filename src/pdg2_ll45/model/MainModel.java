package pdg2_ll45.model;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import common.api.team.TeamDataPacket;
import common.api.team.ITeam;
import common.api.team.IMsgReceiver;
import common.api.message.team.IAdd;
import common.api.message.team.ITeamMsg;
import common.api.message.team.IQuit;
import common.api.message.user.IAvailableTeams;
import common.api.message.user.IDisconnect;
import common.api.message.user.IFailedUserMsg;
import common.api.message.user.IJoinInvite;
import common.api.message.user.IRequestTeams;
import common.api.message.user.IUserMsg;
import common.api.user.AUserDPAlgoCmd;
import common.api.user.IUser;
import common.api.user.UserDPAlgo;
import common.api.user.UserDataPacket;
import pdg2_ll45.controller.*;
import pdg2_ll45.messages.Team.Add;
import pdg2_ll45.messages.Team.Quit;
import pdg2_ll45.messages.user.*;
import provided.datapacket.DataPacket;
import provided.datapacket.DataPacketAlgo;
import provided.rmiUtils.IRMIUtils;
import provided.rmiUtils.IRMI_Defs;
import provided.rmiUtils.RMIUtils;

/**
 * Class for the over-arching model of the ChatApp MVC
 */
public class MainModel implements IMainModel {

	/**
	 * An RMIUtil
	 */
	private IRMIUtils rmiUtils;
	
	/**
	 * The local address of user
	 */
	private String localAddress;
	
	/**
	 * The user
	 */
	private IUser user;
	
	/**
	 * User's stub
	 */
	private IUser userStub;
	
	/**
	 * User's message visitor
	 */
	private UserDPAlgo userMsgVisitor;

	/**
	 * Connected users
	 */
	private List<IUser> connectedUsers;
	
	/**
	 * A model to view adapter
	 */
	private IMainModel2ViewAdapter model2ViewAdpt;
	
	/**
	 * List of Team models
	 */
	private List<ITeamModel> TeamModels;
	
	/**
	 * List of available Teams
	 */
	private List<ITeam> availableTeams;

	/**
	 * Constructor for main model
	 * @param model2ViewAdpt		a model to view adapter
	 */
	public MainModel(IMainModel2ViewAdapter model2ViewAdpt) {
		this.model2ViewAdpt = model2ViewAdpt;
		final class DefaultAlgoCmd extends AUserDPAlgoCmd<IUserMsg> {

			@Override
			public Void apply(Class<?> index, UserDataPacket<IUserMsg> host, Void... params) {
				// TODO Auto-generated method stub
				return null;
			}

		}
		this.userMsgVisitor = new UserDPAlgo(new DefaultAlgoCmd());
		connectedUsers = new ArrayList<IUser>();
		TeamModels = new ArrayList<ITeamModel>();
		availableTeams = new ArrayList<ITeam>();
		initUserMsgVisitor();
	}

	/**
	 * Initialize visitors
	 */
	private void initUserMsgVisitor() {
		userMsgVisitor.setCmd(IDisconnect.class, new AUserDPAlgoCmd<IDisconnect>() {
			private static final long serialVersionUID = -2750048585996495692L;

			@Override
			public Void apply(Class<?> index, UserDataPacket<IDisconnect> host, Void... params) {
				connectedUsers.remove(host.getSender());
				return null;
			}

		});
		userMsgVisitor.setCmd(IFailedUserMsg.class, new AUserDPAlgoCmd<IFailedUserMsg>() {
			private static final long serialVersionUID = 743442274260469872L;

			@Override
			public Void apply(Class<?> index, UserDataPacket<IFailedUserMsg> host, Void... params) {
				System.out.println("Failed user message: " + host.getData().getErrorMessage());
				return null;
			}

		});
		userMsgVisitor.setCmd(IJoinInvite.class, new AUserDPAlgoCmd<IJoinInvite>() {
			private static final long serialVersionUID = -5447183781973234634L;

			@Override
			public Void apply(Class<?> index, UserDataPacket<IJoinInvite> host, Void... params) {
				ITeam Team = host.getData().getTeam();
				joinTeam(Team);
				model2ViewAdpt.addJoinedTeam(Team);
				return null;
			}

		});
		userMsgVisitor.setCmd(IRequestTeams.class, new AUserDPAlgoCmd<IRequestTeams>() {
			private static final long serialVersionUID = -2401267101607709387L;

			@Override
			public Void apply(Class<?> index, UserDataPacket<IRequestTeams> host, Void... params) {
				List<ITeam> Teams = TeamModels.stream().map(TeamModel -> TeamModel.getTeam())
						.collect(Collectors.toList());
				IUser asker = host.getSender();
				try {
					IAvailableTeams msg = new AvailableTeams(Teams);
					asker.accept(new UserDataPacket<IAvailableTeams>(IAvailableTeams.class, msg, userStub));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}

		});
		userMsgVisitor.setCmd(IAvailableTeams.class, new AUserDPAlgoCmd<IAvailableTeams>() {
			private static final long serialVersionUID = 7331490826497854042L;

			@Override
			public Void apply(Class<?> index, UserDataPacket<IAvailableTeams> host, Void... params) {
				for (ITeam Team : host.getData().getTeams()) {
					if (!(containsTeam(availableTeams, Team))) {
						availableTeams.add(Team);
						model2ViewAdpt.addAvailableTeam(Team);
					}

				}
				return null;
			}

		});

	}
	
	/**
	 * Check whether Team has certain Team
	 * @param Teams		List of Teams
	 * @param Team		Team to be found
	 * @return	boolean value, true if Team is in Teams
	 */
	private boolean containsTeam(List<ITeam> Teams, ITeam Team) {
		for (ITeam room : Teams) {
			if (room.getUUID().equals(Team.getUUID())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Connect to remote user
	 * @param IP of remote user
	 */
	public String connect(String _ip) {
		String ip = _ip.trim();
		Registry registry = rmiUtils.getRemoteRegistry(ip);
		System.out.println(registry.toString());
		try {
			IUser connect = (IUser) registry.lookup(IUser.BOUND_NAME);
			addUser(connect);
			System.out.println("Connected to " + ip + " successfully.");
			return "Connected to " + ip + " successfully.";
		} catch (Exception e) {
			e.printStackTrace();
			return "Exception connecting to " + ip + ": " + e + "\n";
		}
	}

	/**
	 * Stops model
	 */
	public void stop() {
		try {
			// Leave all current Teams
			for (ITeamModel TeamModel : TeamModels) {
				ITeam Team = TeamModel.getTeam();
				Team.notifyAllReceivers(
						new TeamDataPacket<Quit>(Quit.class, new Quit(), TeamModel.getReceiverStub()));
			}
			// Disconnect from all users
			for (IUser connectedUser : connectedUsers) {
				connectedUser.accept(new UserDataPacket<IDisconnect>(IDisconnect.class, new Disconnect(), userStub));
			}
			// Kill the application
			rmiUtils.stopRMI();
			System.exit(0);
		} catch (Exception e) {
			System.exit(-1);
		}
	}

	/**
	 * Starts model
	 */
	public void start() {
		this.rmiUtils = new RMIUtils(model2ViewAdpt::addText);
		rmiUtils.startRMI(IRMI_Defs.CLASS_SERVER_PORT_CLIENT);

		final class User implements IUser {
			@Override
			public String getName() throws RemoteException {
				return "pdg2_ll45";
			}

			@Override
			public void registerUser(IUser user) throws RemoteException {
				addUser(user);
				System.out.println("Registered user: " + user.getName());
			}

			@Override
			public void accept(UserDataPacket<? extends IUserMsg> message) throws RemoteException {
				message.execute(userMsgVisitor, (Void[]) null);
			}
		}

		this.user = new User();
		try {
			localAddress = rmiUtils.getLocalAddress();
		} catch (SocketException | UnknownHostException e) {
			System.out.println("Error getting local address: " + e.getClass());
			e.printStackTrace();
		}

		try {
			userStub = (IUser) UnicastRemoteObject.exportObject(this.user, IRMI_Defs.REGISTRY_PORT);
			rmiUtils.getLocalRegistry().bind(IUser.BOUND_NAME, userStub);
		} catch (RemoteException | AlreadyBoundException e) {
			System.out.println("Error creating & binding user stub: " + e.getClass());
			e.printStackTrace();
		}
	}

	/**
	 * Instals a new Team with name
	 * @param name		String name of new Team
	 */
	public ITeam installNewTeam(String name) {
		ITeam newRoom = new BasicTeam(name);
		ITeamModel newModel = addTeam(newRoom);

		try {
			IMsgReceiver recvStub = newModel.getReceiverStub();
			newModel.getTeam().addReceiver(recvStub);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newRoom;
	}
	
	/**
	 * Add a Team 
	 * @param Team 		the Team to add
	 */
	public ITeamModel addTeam(ITeam Team) {
		ITeamController newTeamController = new TeamController(Team, userStub, this,
				this.model2ViewAdpt);
		newTeamController.start();
		TeamModels.add(newTeamController.getModel());
		model2ViewAdpt.addNewTeamView(newTeamController.getView());
		return newTeamController.getModel();
	}
	
	/**
	 * Join a Team
	 * @param Team 		the Team to join
	 */
	public void joinTeam(ITeam Team) {
		System.out.println("in join Team");
		ITeamModel newModel = addTeam(Team);
		IMsgReceiver recv = new MsgReceiver(new MsgReceiver2TeamModelAdapter(newModel));
		try {
			IMsgReceiver recvStub = (IMsgReceiver) UnicastRemoteObject.exportObject(recv, IRMI_Defs.STUB_PORT_CLIENT);
			newModel.getTeam().addReceiver(recvStub);
			Team.notifyAllReceivers(new TeamDataPacket<IAdd>(IAdd.class, new Add(userStub), recv));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Adds user
	 * @param user 		user to add
	 */
	public void addUser(IUser _user) {
		if (!(connectedUsers.contains(_user))) {
			connectedUsers.add(_user);
			try {
				_user.registerUser(userStub);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
			model2ViewAdpt.addUser(_user);
		}
	}

	/**
	 * Requests user's available Teams
	 * @param user		user to request from
	 */
	public void requestUserTeams(IUser user) {
		try {
			user.accept(new UserDataPacket<IRequestTeams>(IRequestTeams.class, new RequestTeams(),
					this.userStub));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void removeTeam() {
		// TODO Auto-generated method stub

	}

	@Override
	public void quitRoom(ITeamModel TeamModel) {
		System.out.println("in quitroom in mainmodel");
		try {
			TeamModel.getReceiverStub()
					.receive(new TeamDataPacket<IQuit>(IQuit.class, new Quit(), TeamModel.getReceiverStub()));
			TeamModel.getTeam().notifyAllReceivers(
					new TeamDataPacket<IQuit>(IQuit.class, new Quit(), TeamModel.getReceiverStub()));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		TeamModels.remove(TeamModel);
		model2ViewAdpt.removeTeamCbx(TeamModel.getTeam());
	}

	@Override
	public void inviteUser(IUser user, ITeam Team) {
		try {
			user.accept(new UserDataPacket<IJoinInvite>(IJoinInvite.class, new JoinInvite(Team), this.userStub));
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
