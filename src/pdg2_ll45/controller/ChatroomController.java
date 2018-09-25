package pdg2_ll45.controller;

import java.util.function.Supplier;

import javax.swing.JComponent;

import common.api.team.ITeam;
import common.api.user.*;
import pdg2_ll45.model.*;
import pdg2_ll45.view.*;

/**
 * Controller for the Team (controller of the mini MVC in the ChatApp)
 */
public class TeamController implements ITeamController {

	/**
	 * The Team model for the Team MVC
	 */
	private ITeamModel TeamModel;

	/**
	 * the Team view for the Team MVC
	 */
	private ITeamView TeamView;

	/**
	 * Constructor for the Team controller
	 * @param Team		an ITeam
	 * @param user		an IUser
	 * @param mainModel		the main TeamModel 
	 */
	public TeamController(ITeam Team, IUser user, IMainModel mainModel,
			IMainModel2ViewAdapter mainViewAdpt) {
		TeamModel = new TeamModel(Team, new ITeamModel2ViewAdapter() {

			public void addText(String text) {
				TeamView.appendText(text);
				System.out.println(text);
			}

			@Override
			public void buildComponent(Supplier<JComponent> component) {
				TeamView.buildComponent(component);

			}

			@Override
			public void appendString(String message) {
				TeamView.appendText(message);

			}

			@Override
			public void removeView() {
				System.out.println("removing self");
				TeamView.removeSelf();
			}

		}, new ITeamModel2MainModelAdapter() {

			@Override
			public void addUser(IUser _user) {
				mainModel.addUser(_user);
			}

			@Override
			public void quitRoom() {
				mainModel.quitRoom(TeamModel);
			}

		}, user);
		TeamView = new TeamView(new ITeamView2ModelAdapter() {

			@Override
			public void sendMessage(String message) {
				TeamModel.sendMessage(message);
			}

			@Override
			public void quitRoom() {
				TeamModel.quitRoom();
			}

			@Override
			public void sendImage() {
				TeamModel.sendImage();
			}

		}, new ITeamView2MainViewAdapter() {

			@Override
			public void removeTeamView(ITeamView TeamView) {
				mainViewAdpt.removeTeam(TeamView);
			}

		});
	}

	/**
	 * Start the controller
	 */
	public void start() {
		TeamModel.start();
		TeamView.start();
	}

	/**
	 * Get the Team TeamModel
	 * @return the ITeamModel
	 */
	@Override
	public ITeamModel getModel() {
		return TeamModel;
	}

	/**
	 * Get the Team TeamView
	 * @return the ITeamView
	 */
	@Override
	public ITeamView getView() {
		return TeamView;
	}
}
