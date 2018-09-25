package pdg2_ll45.messages.Team;

import common.api.message.team.ITeamMsg;
import common.api.message.team.IGetCmd;

/**
 * Class of message sent when the user's Visitor doesn't have a case for the incomingdatatype.
 * Implements IGetCmd
 */
public class GetCmd implements IGetCmd {
	
	/**
	 * A message class
	 */
	private Class<? extends ITeamMsg> msgClass;
	
	/**
	 * Get command
	 * @param msgClass		a message class
	 */
	public GetCmd(Class<? extends ITeamMsg> msgClass) {
		this.msgClass = msgClass;
	}
	
	/**
	 * Gets class index
	 * @return a Class
	 */
	@Override
	public Class<? extends ITeamMsg> getClassIndex() {
		return msgClass;
	}

}
