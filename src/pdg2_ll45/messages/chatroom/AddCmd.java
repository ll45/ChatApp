package pdg2_ll45.messages.Team;

import common.api.team.ATeamDPAlgoCmd;
import common.api.message.team.IAddCmd;
import common.api.message.team.ITeamMsg;

/**
 * Class of messages is sent as a response to the receipt of an IGetCmd message
 * Implements IAddCmd
 */
public class AddCmd implements IAddCmd {
	
	/**
	 * A Team data packet algorithm
	 */
	private ATeamDPAlgoCmd<? extends ITeamMsg> cmd;
	
	/**
	 * A ITeamMsg class
	 */
	private Class<? extends ITeamMsg> msgClass;
	
	/**
	 * Adds the command
	 * @param cmd		the command 
	 * @param msgClass		the message class
	 */
	public AddCmd(ATeamDPAlgoCmd<? extends ITeamMsg> cmd, Class<? extends ITeamMsg> msgClass) {
		this.cmd = cmd;
		this.msgClass = msgClass;
	}
	
	/**
	 * Gets the command
	 * @return an ATeamDPAlgoCmd
	 */
	@Override
	public ATeamDPAlgoCmd<? extends ITeamMsg> getCmd() {
		return cmd;
	}

	/**
	 * Gets the class index
	 * @return class of messages
	 */
	@Override
	public Class<? extends ITeamMsg> getClassIndex() {
		return msgClass;
	}

}
