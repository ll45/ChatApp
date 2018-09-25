package pdg2_ll45.model;

import common.api.team.ITeam;
import common.api.user.IUser;
import pdg2_ll45.view.*;

/**
 * Interface for the main model to view adapter
 */
public interface IMainModel2ViewAdapter {
	
	/**
	 * Adds text
	 * @param text		String text to add
	 */
	public void addText(String text);

	/**
	 * Adds new Team view
	 * @param view		view to be added
	 */
	public void addNewTeamView(ITeamView view);

	/**
	 * Add Team to available Teams drop down
	 * @param Team		Team to be added to available Team drop down
	 */
	public void addAvailableTeam(ITeam Team);

	/**
	 * Add Team to joined Teams drop down
	 * @param Team		Team to be added to joined Teams drop down
	 */
	public void addJoinedTeam(ITeam Team);

	/**
	 * Removes Team from the view
	 * @param view		Team to be removed
	 */
	public void removeTeam(ITeamView view);

	/**
	 * Adds a remote user
	 * @param user		remote user to be added
	 */
	public void addUser(IUser user);

	/**
	 * Remove a Team from the Team drop down 
	 * @param Team		Team to be removed
	 */
	public void removeTeamCbx(ITeam Team);
}
