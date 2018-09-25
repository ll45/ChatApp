package pdg2_ll45.model;

import java.util.List;

import common.api.team.ITeam;
import common.api.user.IUser;

/**
 * Interface for the main model in the over-arching ChatApp MVC
 */
public interface IMainModel {
	
	/**
	 * Start the model
	 */
	public void start();
	
	/**
	 * Connect to remote user
	 * @param ip		IP address of remote user
	 * @return String regarding connection
	 */
	public String connect(String ip);

	/**
	 * Installs a new Team
	 * @param name		name of the new Team
	 * @return a new Team
	 */
	public ITeam installNewTeam(String name);

	/**
	 * Adds a new Team
	 * @param Team		the Team to be added
	 * @return a Team model
	 */
	public ITeamModel addTeam(ITeam Team);

	/**
	 * Quits a Team 
	 * @param TeamModel 		model of the Team to be left
	 */
	public void quitRoom(ITeamModel TeamModel);

	/**
	 * Adds a new user
	 * @param user		the user to be added
	 */
	public void addUser(IUser user);

	/**
	 * Requests the available Teams of a user
	 * @param user		user that is being requested from
	 */
	public void requestUserTeams(IUser user);

	/**
	 * Removes a Team
	 */
	public void removeTeam();

	/**
	 * Joins a Team
	 * @param Team		Team that user intends to join
	 */
	public void joinTeam(ITeam Team);

	/**
	 * Invite a remote user
	 * @param user		remote user to send invite to
	 * @param Team		the Team to invite user to 
	 */
	public void inviteUser(IUser user, ITeam Team);

	/**
	 * Stops Team
	 */
	public void stop();

}
