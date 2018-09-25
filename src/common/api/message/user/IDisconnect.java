package common.api.message.user;


/**
 * This message is sent to a User by a user who wishes to leave the network. 
 * The users who receive this message should remove the user from the network
 * (by deleting that user's user stub from their local collection of stubs).
 * Since no information aside from the sender of this message is required, this
 * interface has no methods.
 * 
 * @author ChatApp Design Group E
 *
 */
public interface IDisconnect extends IUserMsg {
	
}
