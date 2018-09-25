package pdg2_ll45.messages.Team;

import java.io.Serializable;

import common.api.message.team.IStringMsg;

/**
 * Class representing a string (text) message that is sent to a Team.
 * Implements IStringMsg
 */
public class StringMsg implements IStringMsg, Serializable {
	
	/**
	 * The serializable ID
	 */
	private static final long serialVersionUID = 732300470191282771L;
	
	/**
	 * The string content
	 */
	String content; 
	
	/**
	 * The string message
	 * @param content		the message
	 */
	public StringMsg(String content) {
		this.content = content;
	}
	
	/**
	 * Gets the content
	 * @return String content
	 */
	@Override
	public String getContent() {
		return content;
	}

}
