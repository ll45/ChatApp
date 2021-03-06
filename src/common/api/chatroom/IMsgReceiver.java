package common.api.team;

import java.rmi.Remote;
import java.rmi.RemoteException;

import common.api.message.team.ITeamMsg;

/**
 * The interface that represents all adapters that handle communication between a
 * remote user and the mini-MVC tied to a Team object. The adapter only has one 
 * function:
 * <p>
 * 
 * RECEIVING a message:
 * <p>
 *   > All messages sent between users and Teams are represented as a data packet.
 * <p>
 *   > The receive() method includes a Visitor that has cases for both known and
 *     unknown data types.
 *     
 *       - Well-known message types (IAdd, ILeave, IDisplayableMsg, etc.) are handled by the Visitor.
 * <p>
 *       - For unknown data types, a message is sent to the sender of the unknown
 *         data packet requesting a handler command. This command is then installed 
 *         into the Visitor.
 * <p>
 *         
 * <p>
 *   > A data packet sent to an IMsgAdpt is simply a host. Upon reaching the 'accept' 
 *     method, the host calls host.execute(Visitor). Whatever data type the message might
 *     be is handled by the Visitor appropriately.
 * <p>
 * 
 */
public interface IMsgReceiver extends Remote {
	
	/**
	 * Accepts a message sent to the Team object and handles the data packet 
	 * appropriately according to the type of the packet. Includes a Visitor that
	 * the host data packet can call its execute() method on.
	 * <p>
	 * 
	 * For unknown data types, a message is sent to the sender of the unknown
	 * data packet requesting a handler command. This command is then installed 
	 * into the Visitor. Well-known message types (of type ITeamMsg) are 
	 * handled by the appropriate Visitor case.
	 * <p>
	 * 
	 * @param message
	 * 		the message sent to the Team
	 * 
	 * @throws RemoteException 
	 * 		in case something goes wrong with the network transmission
	 * 
	 * @author ChatApp Design Group E
	 * 		
	 */
	public void receive(TeamDataPacket<? extends ITeamMsg> message) throws RemoteException;
}
