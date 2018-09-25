package pdg2_ll45.controller;

import pdg2_ll45.model.*;
import pdg2_ll45.view.*;

/**
 * Interface for the Team Controller
 */
public interface ITeamController {

	/**
	 * Start the controller
	 */
	public void start();

	/**
	 * Get the model
	 * @return	an ITeamModel
	 */
	public ITeamModel getModel();

	/**
	 * Get the view
	 * @return an ITeamView
	 */
	public ITeamView getView();
}
