package pdg2_ll45.model;

import java.rmi.RemoteException;

import common.api.team.ATeamDPAlgoCmd;
import common.api.team.TeamDPAlgo;
import common.api.team.TeamDataPacket;
import common.api.team.IMsgReceiver;
import common.api.message.team.ITeamMsg;
import common.api.message.team.IGetCmd;
import pdg2_ll45.messages.Team.GetCmd;

public class StandardDPAlgo extends TeamDPAlgo {

	public StandardDPAlgo() {
		super(null);
		//		super(new ATeamDPAlgoCmd<ITeamMsg>() {
		//			private static final long serialVersionUID = -1762773499162340299L;
		//
		//			// Default method sends an IGetCmd request. 
		//			@Override
		//			public Void apply(Class<?> index, TeamDataPacket<ITeamMsg> host, Void... params) {
		//				IMsgReceiver sender = host.getSender();
		//				try {
		//					sender.receive(new TeamDataPacket<IGetCmd>(
		//							IGetCmd.class, new GetCmd(host.getData().getClass()), ownReceiver));
		//				} catch (RemoteException e) {
		//					// TODO Auto-generated catch block
		//					e.printStackTrace();
		//				}
		//				return null;
		//			}
		//			
		//		});
		//		init();
	}

	private void init() {

	}

}
