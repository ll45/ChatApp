package common.api.message.team;

import common.api.team.ATeamDPAlgoCmd;

/**
 * This message is sent as a response to the receipt of an IGetCmd message. It contains
 * a serialized copy of a specific command that may be installed into the remote Visitor 
 * and used to process the unknown datatype.
 * 
 * @author ChatApp Design Group E
 *
 */
public interface IAddCmd extends ITeamMsg {
	
	/**
	 * Gets the serialized copy of the command to be installed into the Visitor.
	 * 
	 * @return command to be installed into the Visitor
	 */
	public ATeamDPAlgoCmd<? extends ITeamMsg> getCmd();
	
	/**
	 * @return
	 * 		Class of the command
	 */
	public Class<? extends ITeamMsg> getClassIndex();
}
