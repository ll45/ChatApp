package common.api.message.team;

/**
 * Represents a string (text) message that is sent to a Team. It should be 
 * displayed directly on the view of the Team. StringMessage is a well-known
 * message type and should be implemented.
 * 
 * @author ChatApp Design Group E
 *
 */
public interface IStringMsg extends ITeamMsg {
	
	/**
	 * Gets the string contents of the message. 
	 * 
	 * @return string contents of the message
	 */
	public String getContent();

}
