package common.api.team;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;
import java.util.UUID;

import common.api.message.team.ITeamMsg;

/**
 * An interface that represents a single Team object.
 * 
 * A Team object is simply a collection of IMsgReceiver stubs. Any IMsgReceiver stub
 * is tied to the mini-MVC containing that Team object on a remote user's system, and
 * the IMsgReceiver may be used by the Team object on the local user's system to 
 * communicate with the Team object on the remote user's system.
 * 
 * <p>
 * 1. CREATING a Team
 * <p>
 *    > A Team object is created locally and tied to a unique identifier (UID). This
 *      object is serializable.
 * <p>
 *    > This action is NOT PART OF THE API! It is merely noted here for reference 
 *      purposes.
 * <p>
 *    
 * 2. JOINING a Team
 * <p>
 * 	  > There are two possible methods for joining a Team:
 * 
 * 		- INVITING a user to join
 * 			The local user inviting the remote user to join a Team they're part of must
 * 			send the remote user an IJoinInvite message. This message contains a serialized copy
 * 			of the Team object that the remote user can then create a mini-MVC for. The 
 * 			remote user may choose to decline this invitation.
 * 		
 * 		- REQUESTING to join
 * 			The user wishing to join must send an IJoinRequest message to any user already in the
 * 			Team. This IJoinRequest is tied to the Team's UID. If the receiver chooses
 * 			to accept the join request, they respond with an IJoinInvite message containing the serialized
 * 			Team object. The join request may be declined.
 * 		
 * <p>
 * 	  > Upon receipt of the IJoinInvite message, a user may send an IAddUser message to all IMsgReceivers in the
 * 		Team. The IAddUser message contains an IMsgReceiver stub tied to the local mini-MVC representing that
 * 		Team. All users then simply add this IMsgReceiver stub to their instance of the serialized Team.
 * 
 *    > After joining, the user automatically exchanges IUser stubs with all other users in that Team.
 *      This ensures all users are directly connected to each other, and one user leaving the Team will
 *      not disconnect anyone else.
 * <p>
 *    
 * 3. LEAVING a Team
 * <p>
 *    > Any user who wishes to leave this Team simply sends an IQuit message to all IMsgReceivers
 *      in the Team. This deletes the user's IMsgReceiver stub from all copies of the Team object. 
 *      The user can then delete their copy of the Team object.
 * <p>
 * 
 * 4. DELETING a Team
 * <p>
 *    > A Team should be automatically deleted once all users have left that Team (there are no more IMsgReceivers
 *      still in the Team).
 *    > This action is NOT part of the API! It is noted here merely for reference purposes.
 *    
 * @author ChatApp Design Group E
 * 
 */
public interface ITeam extends Serializable {
	
	/**
	 * Gets the (non-unique) name of the Team. 
	 * <p>
	 * 
	 * Each Team has a name that users will see when finding Teams to connect
	 * to. This name is NOT unique.
	 * <p>
	 * 
	 * @return name of the Team
	 * 
	 */
	public String getName();
	
	/**
	 * Gets the (unique) identifying ID of the Team.
	 * <p>
	 * 
	 * Each Team has a unique identifying ID that will be used for all internal
	 * communication. This UID will also be seen by the user when finding Teams to 
	 * connect to.
	 * <p>
	 * 
	 * @return UID of the Team
	 * 
	 */
	public UUID getUUID();
	
	/**
	 * Gets the list of current message receivers in the Team.
	 * <p>
	 * 
	 * Each Team maintains a list of current IMsgReceivers that any remote user can query
	 * at any time.
	 * <p>
	 * 
	 * @return A list of IMsgReceiver objects representing the receivers to this Team.
	 */
	public List<IMsgReceiver> getMsgReceivers();
	
	
	/** Notifies and forwards the message to all receivers.  Will call receive() on each IMsgReceiver.
	 * 
	 * @param message 
	 * 		the Message to send to all receivers.
	 * 
	 * @throws RemoteException 
	 * 		in case an error occurs during network transmission
	 */
	public void notifyAllReceivers(TeamDataPacket<? extends ITeamMsg> message) throws RemoteException;

	/** Adds a specific IMsgReceiver to the internal collections of IMsgReceivers to notify.
	 * @param receiver the Receiver to add to the list of message receivers.
	 */
	public void addReceiver(IMsgReceiver receiver);

	/** Deletes a specific IMsgReceiver from the internal collections of IMsgReceivers to notify.
	 * @param receiver the Receiver to remove from the list of message receivers.
	 */
	public void removeReceiver(IMsgReceiver receiver);
}
