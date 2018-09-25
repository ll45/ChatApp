package pdg2_ll45.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import common.api.team.TeamDataPacket;
import common.api.team.ITeam;
import common.api.team.IMsgReceiver;
import common.api.message.team.ITeamMsg;

/**
 * Class of basic Team. Implements ITeam.
 */
public class BasicTeam implements ITeam {

	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = -6945717527437278919L;

	/**
	 * UUID of Team
	 */
	private UUID uuid;

	/**
	 * List of message receiver stubs
	 */
	private List<IMsgReceiver> receivers = new ArrayList<IMsgReceiver>();

	/**
	 * Name of Team
	 */
	private String name;

	/**
	 * Constructor for a basic Team 
	 * @param name		String name of the Team
	 */
	public BasicTeam(String name) {
		this.name = name;
		this.uuid = UUID.randomUUID();
	}

	/**
	 * Get name of Team 
	 * @return name of Team
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Gets UUID of Team
	 * @return UUID of Team
	 */
	@Override
	public UUID getUUID() {
		return uuid;
	}

	/**
	 * Gets message receivers
	 * @return List of message receiver stubs
	 */
	@Override
	public List<IMsgReceiver> getMsgReceivers() {
		return receivers;
	}

	/**
	 * Adds a message receiver to list of message receivers
	 */
	public void addReceiver(IMsgReceiver receiver) {
		receivers.add(receiver);
	}

	/**
	 * Removes a message recevier from the list of message receivers
	 */
	public void removeReceiver(IMsgReceiver receiver) {
		receivers.remove(receiver);
	}

	/**
	 * Notifies all receivers with message
	 * @param message of Team data packet type
	 */
	@Override
	public void notifyAllReceivers(TeamDataPacket<? extends ITeamMsg> message) throws RemoteException {
		System.out.println("Sending the following message: " + message);
		System.out.println("Receivers who will receive: " + receivers.toString());
		for (IMsgReceiver receiver : receivers) {
			receiver.receive(message);
		}
	}

}
