package pdg2_ll45.view;

import common.api.team.ITeam;
import common.api.user.IUser;

/**
 * Interface for the over-arching ChatApp (the main view)
 */
public interface IMainView {

	/**
	 * Start the GUI
	 */
	public void start();

	/**
	 * Adds Team view
	 * @param view		an ITeam view
	 */
	public void addTeamView(ITeamView view);

	/**
	 * Inserts the user to the drop down of available users
	 * @param user		an IUser to add
	 */
	public void addUser(IUser user);

	/**
	 * Inserts the Team to the drop down of already joined Teams
	 * @param Team		an ITeam to add
	 */
	public void addJoinedTeam(ITeam Team);

	/**
	 * Inserts the Team to the drop down of available users
	 * @param Team		an ITeam to add
	 */
	public void addAvailableTeam(ITeam Team);

	/**
	 * Remove a Team view from the list of current Team views.
	 * @param TeamView the Team view to remove
	 */
	public void removeCurrentTeam(ITeamView TeamView);

	/**
	 * Remove Team from drop down of joined Teams upon leaving Team
	 * @param Team		ITeam that user left
	 */
	public void removeTeamCbx(ITeam Team);
}
