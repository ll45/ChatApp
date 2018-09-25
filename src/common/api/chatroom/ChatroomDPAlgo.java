package common.api.team;

import common.api.message.team.ITeamMsg;
import provided.datapacket.DataPacketAlgo;

/**
 * @author ChatApp Design Group E
 * 
 * An abstract class for the Team-level visitor that accepts and processes datapackets
 * containing ITeamMsgs. 
 *
 */
public class TeamDPAlgo extends DataPacketAlgo<Void, Void> {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -7168869552420953088L;

	/**
	 * Constructor for the visitor.
	 * 
	 * @param defaultCmd
	 * 		default command to execute for the visitor
	 */
	public TeamDPAlgo(ATeamDPAlgoCmd<? extends ITeamMsg> defaultCmd) {
		super(defaultCmd);
	}

}
