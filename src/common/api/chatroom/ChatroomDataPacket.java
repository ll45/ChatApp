package common.api.team;

import common.api.message.team.ITeamMsg;

import provided.datapacket.DataPacket;

/**
 * @author ChatApp Design Group E
 * 
 * Type-narrowed version of a datapacket that can only contain ITeamMsgs.
 *
 * @param <T> the type of message sent through TeamDataPackets
 * 		Type parameter that extends ITeamMsg
 */
public class TeamDataPacket<T extends ITeamMsg> extends DataPacket<T, IMsgReceiver> {

	/**
	 * Unique serial version UID.
	 */
	private static final long serialVersionUID = -8211117048701378374L;

	/**
	 * Constructor for the Team datapacket.
	 * 
	 * @param c
	 * 		class of the message that's being sent
	 * @param data
	 * 		message that's being sent
	 * @param sender
	 * 		sender of the datapacket (should be an IMsgReceiver stub) 
	 */
	public TeamDataPacket(Class<T> c, T data, IMsgReceiver sender) {
		super(c, data, sender);
	}

}
