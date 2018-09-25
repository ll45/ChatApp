package common.api.user;

import common.api.message.user.IUserMsg;
import provided.datapacket.DataPacket;

/**
 * @author ChatApp Design Group E
 * 
 * Type-narrowed version of a datapacket that can only contain IUserMsgs.
 *
 * @param <T>
 * 		Type parameter that extends IUserMsg
 */
public class UserDataPacket<T extends IUserMsg> extends DataPacket<T, IUser> {

	/**
	 * Serial version UID.
	 */
	private static final long serialVersionUID = 8316357706870001477L;

	/**
	 * Constructor for the user datapacket.
	 * 
	 * @param c
	 * 		Class of the message to send
	 * @param data
	 * 		message to send
	 * @param sender
	 * 		sender of the message (should be an IUser stub)
	 */
	public UserDataPacket(Class<T> c, T data, IUser sender) {
		super(c, data, sender);
	}

}

