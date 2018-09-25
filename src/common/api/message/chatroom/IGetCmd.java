package common.api.message.team;

/**
 * This message is sent when the user's Visitor doesn't have a case for the incoming
 * datatype. It contains the original sent IUserMsg. Receipt of an IGetCmd message
 * by a user should cause that user to send an IAddCmd message back.
 * 
 * @author ChatApp Design Group E
 *
 */
public interface IGetCmd extends ITeamMsg {
	
	/**
	 * Gets the type of the message that was unable to be processed by the user.
	 * 
	 * @return the type of the unknown ITeamMsg that was sent.
	 * 
	 */
	public Class<? extends ITeamMsg> getClassIndex();
}
