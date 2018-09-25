package pdg2_ll45.messages.user;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

import common.api.team.TeamDPAlgo;
import common.api.team.TeamDataPacket;
import common.api.team.IMsgReceiver;
import common.api.message.team.ITeamMsg;

/**
 * Class of message receiver. Implements IMsgReceiver.
 */
public class MsgReceiver implements IMsgReceiver {
	
	/**
	 * A message receiver to Team adapter
	 */
	MsgReceiver2TeamModelAdapter adapter;
	
	/**
	 * Constructor for a message receiver
	 * @param adapter		the message receiver to Team adapter
	 */
	public MsgReceiver(MsgReceiver2TeamModelAdapter adapter) {
		this.adapter = adapter;
	}
	
	/**
	 * Received a message
	 * @param message		A Team data packet to be received
	 */
	@Override
	public void receive(TeamDataPacket<? extends ITeamMsg> message) throws RemoteException {
		adapter.executeMessage(message);
	}

}
