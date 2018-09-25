package pdg2_ll45.view;

import java.util.List;

import common.api.team.*;
import common.api.user.IUser;

/**
 * Interface for the Main view to model adapter
 */
public interface IMainView2ModelAdapter {

	/**
	 * Connect to remote user
	 * @param ip		String representation of the IP of remote user
	 */
	public void connectToUser(String ip);

	/**
	 * Adds Team
	 * @param name		String name of Team to add
	 * @return	an ITeam
	 */
	public ITeam addTeam(String name);

	/**
	 * Request remote user's Teams
	 * @param user		IUser to request from
	 */
	public void requestUserTeams(IUser user);

	/**
	 * Join a Team
	 * @param Team		ITeam to join
	 */
	public void joinTeam(ITeam Team);

	/**
	 * Invite a user to a specified Team. 
	 * @param user		user to invite 
	 * @param Team 		Team to invite them to
	 */
	public void inviteUser(IUser user, ITeam Team);

	/** 
	 * Disconnects the user from all other users, leaves all Teams, and terminates the chat app. 
	 */
	public void stopChatApp();
}
