package common.api.message.user;

import java.util.List;

import common.api.team.ITeam;

/**
 * This message is sent as a response to the receipt of an IRequestTeams message.
 * It contains a list of serialized ITeam objects. The user may then choose one 
 * to join locally.
 * 
 * @author ChatApp Design Group E
 *
 */
public interface IAvailableTeams extends IUserMsg {
	
	/**
	 * Gets the list of Team objects
	 * 
	 * @return list of Team objects
	 * 
	 */
	public List<ITeam> getTeams();

}
