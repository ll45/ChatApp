package common.api.message.team;

import common.api.user.IUser;

/**
 * This message is sent to a Team by a user who wishes to join that Team. The datapacket 
 * containing an IAdd will contain the message receiver stub of the sender in the datapacket's 
 * sender field. The receiver should get this message receiver and add it to their local copy of 
 * the Team.
 * 
 * The message contains a serialized user stub for the sender.
 * Thus, the user should call getUserStub() on this message and get the user stub of the 
 * sender. If they don't already have that user stub, they should call registerUser() on that stub
 * so they can connect to them.
 * 
 * @author ChatApp Design Group E
 *
 */
public interface IAdd extends ITeamMsg {
	
	/**
	 * @return user stub of the sender
	 */
	public IUser getUserStub(); 
	
}
