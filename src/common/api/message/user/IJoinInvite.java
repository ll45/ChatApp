package common.api.message.user;

import common.api.team.ITeam;

/**
 * This message is sent to a user who is to be invited to join a specific Team. The 
 * message contains a serialized copy of the Team object that the user may join. To 
 * join the Team, the receiving user must send an IAdd message to each IMsgReceiver
 * in the Team.
 * 
 * @author ChatApp Design Group E
 *
 */
public interface IJoinInvite extends IUserMsg {
	
	/**
	 * Gets the serialized Team object from the message.
	 * 
	 * @return serialized Team object
	 * 
	 */
	public ITeam getTeam();

}
