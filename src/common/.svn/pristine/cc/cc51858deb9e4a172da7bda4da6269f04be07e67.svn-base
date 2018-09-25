package common.api.user;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.api.message.user.IUserMsg;

/**
 * The interface that represents a single user on the network. An IUser object has a non-unique 
 * username. 
 * 
 * An IUser has the ability to accept an ADataPacket containing an IUserMsg (the specific IUserMsg
 * is handled using a Visitor). 
 * 
 * @author ChatApp Design Group E
 *
 */
public interface IUser extends Remote {
	
	/**
	 * The name the IUser object will be bound to in the RMI Registry.
	 */
	public static final String BOUND_NAME = "User";
	
	/**
	 * Gets the (non-unique) name of the user. 
	 * <p>
	 * 
	 * Each user has a username that users will see. This name is NOT unique.
	 * 
	 * @return name of the user
	 * 
	 * @throws RemoteException 
	 * 		in case there's an error during network transmission
	 */
	public String getName() throws RemoteException;
	
	/**
	 * Method that allows an IUser object to accept an incoming message and process
	 * it using a visitor.
	 * 
	 * @param message
	 * 		the incoming message to be sent to the user.
	 * @throws RemoteException 
	 * 		in case an error occurs during network transmission
	 */
	public void accept(UserDataPacket<? extends IUserMsg> message) throws RemoteException;
	
	/**
	 * Method that tells this IUser to connect back to the sender IUser.
	 * 
	 * @param user the IUser stub to "connect" to.
	 * 
	 * @throws RemoteException 
	 * 		in case an error occurs during network transmission
	 */
	public void registerUser(IUser user) throws RemoteException;

}
