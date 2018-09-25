package pdg2_ll45.model;

import common.api.user.IUser;

/**
 * Interface for the Team model to main model adapter
 */
public interface ITeamModel2MainModelAdapter {
	
	/**
	 * Adds new user
	 * @param user		user to be added
	 */
	public void addUser(IUser user);
	
	/**
	 * Quits a Team
	 */
	public void quitRoom();
}
