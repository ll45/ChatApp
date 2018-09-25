package common.api.user;

import common.api.message.user.IUserMsg;
import provided.datapacket.DataPacketAlgo;

/**
 * @author ChatApp Design Group E
 * 
 * The visitor that should be used for processing all datapackets that contain IUserMsgs.
 *
 */
public class UserDPAlgo extends DataPacketAlgo<Void, Void>{

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -7168869552420953088L;

	/**
	 * Constructor for the visitor.
	 * 
	 * @param defaultCmd
	 * 		default command that the visitor should execute
	 */
	public UserDPAlgo(AUserDPAlgoCmd<? extends IUserMsg> defaultCmd) {
		super(defaultCmd);
	}
}
