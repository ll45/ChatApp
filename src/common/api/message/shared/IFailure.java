package common.api.message.shared;

import common.api.message.IMessage;

/**
 * Generic interface for a failing message. Allows for a parameterized (IUserMsg or ITeamMsg) error message to be sent.
 *
 * @param <Message>
 * 		the failed message's type
 * @author ChatApp Group E
 */
public abstract interface IFailure<Message extends IMessage> extends IMessage {
	/** 
	 * Gets the string denoting the failure.
	 * 
	 * @return String the string error message.
	 * 
	 */
	public String getErrorMessage();
	
	/**
	 * Gets the message that caused a failure.
	 * 
	 * @return Message the message that caused a failure.
	 * 
	 */
	public Message getFailingMessage();
}
