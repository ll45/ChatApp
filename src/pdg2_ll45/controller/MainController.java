package pdg2_ll45.controller;

import java.awt.EventQueue;
import java.util.List;

import common.api.team.ITeam;
import common.api.user.IUser;
import pdg2_ll45.model.*;
import pdg2_ll45.view.*;

/**
 * Controller for the over-arching ChatApp (controller of the main MVC in the ChatApp)
 */
public class MainController implements IMainController {

	/**
	 * The model for the main MVC
	 */
	private IMainModel mainModel;

	/**
	 * The view for the main MVC
	 */
	private IMainView mainView;

	/**
	 * Constructor for the main controller
	 */
	public MainController() {
		mainModel = new MainModel(new IMainModel2ViewAdapter() {

			public void addText(String text) {
				System.out.println(text);
			}

			public void addNewTeamView(ITeamView view) {
				mainView.addTeamView(view);
			}

			@Override
			public void removeTeam(ITeamView view) {
				mainView.removeCurrentTeam(view);
			}

			@Override
			public void addAvailableTeam(ITeam Team) {
				mainView.addAvailableTeam(Team);
			}

			@Override
			public void addUser(IUser user) {
				mainView.addUser(user);
			}

			@Override
			public void addJoinedTeam(ITeam Team) {
				mainView.addJoinedTeam(Team);
			}

			@Override
			public void removeTeamCbx(ITeam Team) {
				mainView.removeTeamCbx(Team);
			}

		});

		mainView = new MainView(new IMainView2ModelAdapter() {

			@Override
			public void connectToUser(String ip) {
				mainModel.connect(ip);
			}

			@Override
			public ITeam addTeam(String name) {
				return mainModel.installNewTeam(name);
			}

			@Override
			public void requestUserTeams(IUser user) {
				mainModel.requestUserTeams(user);
			}

			@Override
			public void joinTeam(ITeam Team) {
				System.out.println("joining Team " + Team.toString());
				mainModel.joinTeam(Team);

			}

			@Override
			public void inviteUser(IUser user, ITeam Team) {
				mainModel.inviteUser(user, Team);
				mainView.addJoinedTeam(Team);
			}

			@Override
			public void stopChatApp() {
				mainModel.stop();
			}

		});
	}

	/**
	 * Start the main controller
	 */
	public void start() {
		mainModel.start();
		mainView.start();
	}

	/**
	 * Main method for the main controller
	 * @param args		args for the main method
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				final MainController controller = new MainController(); // instantiate the system
				controller.start(); // start the system
			} catch (final Exception e) {
				e.printStackTrace();
			}
		});
	}

}
