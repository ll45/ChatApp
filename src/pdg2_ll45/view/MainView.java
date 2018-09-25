package pdg2_ll45.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JButton;
import common.api.team.ITeam;
import common.api.user.IUser;

import javax.swing.JTabbedPane;
import javax.swing.JComboBox;
import java.awt.GridLayout;
import javax.swing.JLabel;

import pdg2_ll45.view.TeamView;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * ChatApp GUI. The main view for the overarching chat application.
 */
public class MainView extends JFrame implements IMainView {

	/**
	 * View2ModelAdapter for main MVC
	 */
	private IMainView2ModelAdapter view2ModelAdpt;

	/**
	 * List of Team views
	 */
	private List<ITeamView> TeamViews;

	/**
	 * Text field to enter IP address of remote user
	 */
	private JTextField txtIpAddress;

	/**
	 * Text field to enter name of Team to create
	 */
	private JTextField txtCreate;

	/**
	 * Tab panel to toggle between Teams
	 */
	private JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);

	/**
	 * Drop down of available users
	 */
	private JComboBox<IUser> cbxAvailUsers = new JComboBox<IUser>();

	/**
	 * Drop down of available Teams
	 */
	private JComboBox<ITeam> cbxAvailTeams = new JComboBox<ITeam>();

	/**
	 * Drop down of joined Teams
	 */
	private JComboBox cbxJoinedTeams = new JComboBox();

	/**
	 * Constructor for ChatApp GUI
	 * @param view2ModelAdpt		the view to model adapter
	 */
	public MainView(IMainView2ModelAdapter view2ModelAdpt) {
		this.view2ModelAdpt = view2ModelAdpt;
		this.TeamViews = new ArrayList<ITeamView>();
		addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                view2ModelAdpt.stopChatApp();
            }
        });
		initGUI();
	}

	/**
	 * Initialize the GUI
	 */
	public void initGUI() {
		JPanel panel = new JPanel();
		panel.setToolTipText("North panel of utilities");
		getContentPane().add(panel, BorderLayout.NORTH);
		tabbedPane.setToolTipText("Tabbed panel of current Teams");
		getContentPane().add(tabbedPane, BorderLayout.CENTER);
		JPanel panelConnect = new JPanel();
		panelConnect.setToolTipText("Panel involving connections");
		JPanel panelCreate = new JPanel();
		panelCreate.setToolTipText("Panel of Team creation ability");
		panelCreate.setLayout(new GridLayout(0, 1, 0, 0));

		txtCreate = new JTextField();
		txtCreate.setToolTipText("Enter Team name to create");
		txtCreate.setText("Team Name");
		panelCreate.add(txtCreate);
		txtCreate.setColumns(10);

		JButton btnCreateTeam = new JButton("Create Team");
		btnCreateTeam.setToolTipText("Click to create Team");
		panelCreate.add(btnCreateTeam);
		btnCreateTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ITeam newRoom = view2ModelAdpt.addTeam(txtCreate.getText());
				addJoinedTeam(newRoom);
			}
		});

		JLabel lblJoinedChats = new JLabel("Joined Teams");
		lblJoinedChats.setToolTipText("Label for drop down of joined Teams ");
		panelCreate.add(lblJoinedChats);
		cbxJoinedTeams.setToolTipText("Drop down of joined Teams");

		panelCreate.add(cbxJoinedTeams);

		JButton btnQuit = new JButton("Quit");
		btnQuit.setToolTipText("Click to quit ChatApp");
		panelConnect.add(btnQuit);

		txtIpAddress = new JTextField();
		txtIpAddress.setToolTipText("Enter remote user IP");
		panelConnect.add(txtIpAddress);
		txtIpAddress.setText("Enter User IP");
		txtIpAddress.setColumns(10);

		JButton btnConnect = new JButton("Connect");
		btnConnect.setToolTipText("Click to connect");
		panelConnect.add(btnConnect);
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view2ModelAdpt.connectToUser(txtIpAddress.getText());
			}
		});
		panel.setLayout(new GridLayout(0, 4, 0, 0));
		panel.add(panelConnect);
		panel.add(panelCreate);

		JPanel panelAvailable = new JPanel();
		panelAvailable.setToolTipText("Panel displaying availabilities");
		panel.add(panelAvailable);
		panelAvailable.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panelAvailUsers = new JPanel();
		panelAvailUsers.setToolTipText("Panel displaying available users");
		panelAvailable.add(panelAvailUsers);
		panelAvailUsers.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblConnectedUsers = new JLabel("Connected Users");
		lblConnectedUsers.setToolTipText("Label for drop down of available users");
		panelAvailUsers.add(lblConnectedUsers);

		cbxAvailUsers.setToolTipText("Dropdown of all connected users.");
		panelAvailUsers.add(cbxAvailUsers);

		JPanel panelAvailTeams = new JPanel();
		panelAvailable.add(panelAvailTeams);
		panelAvailTeams.setLayout(new GridLayout(2, 1, 0, 0));

		JLabel lblAvailableTeams = new JLabel("Available Teams");
		lblAvailableTeams.setToolTipText("Label for drop down of available Teams");
		panelAvailTeams.add(lblAvailableTeams);
		cbxAvailTeams.setToolTipText("Dropdown for available Teams");

		panelAvailTeams.add(cbxAvailTeams);

		JPanel panelJoinAdd = new JPanel();
		panelJoinAdd.setToolTipText("Panel 4");
		panel.add(panelJoinAdd);
		panelJoinAdd.setLayout(new GridLayout(4, 1, 0, 0));

		JButton btnJoin = new JButton("Join");
		btnJoin.setToolTipText("Click to join Team");
		panelJoinAdd.add(btnJoin);
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view2ModelAdpt.joinTeam((ITeam) cbxAvailTeams.getSelectedItem());
				//				addTeam(TeamView.getSplitPane(), txtCreate.getText());
				addJoinedTeam((ITeam) cbxAvailTeams.getSelectedItem());
			}
		});

		JButton btnInvite = new JButton("Invite");
		btnInvite.setToolTipText("Click to invite user");
		panelJoinAdd.add(btnInvite);
		btnInvite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view2ModelAdpt.inviteUser((IUser) cbxAvailUsers.getSelectedItem(),
						(ITeam) cbxJoinedTeams.getSelectedItem());
			}
		});

		JButton btnRequest = new JButton("Request Avail. Teams");
		btnRequest.setToolTipText("Click to request available Teams");
		panelJoinAdd.add(btnRequest);
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view2ModelAdpt.requestUserTeams((IUser) cbxAvailUsers.getSelectedItem());
			}
		});

	}

	/**
	 * Start the GUI
	 */
	public void start() {
		this.setVisible(true);
	}

	/**
	 * Adds new Team to the tabbed pane
	 * @param content		the panel to be added to the tabbed pane
	 * @param TeamName		name of the Team to be displayed on the tab
	 */
	public void addNewTab(Container content, String TeamName) {
		tabbedPane.addTab(TeamName, content);
	}

	/**
	 * Adds Team view
	 * @param view		an ITeam view
	 */
	public void addTeamView(ITeamView view) {
		TeamViews.add(view);
		addNewTab(view.getSplitPane(), view.toString());
	}

	/**
	 * Inserts the user to the drop down of available users
	 * @param user		an IUser to add
	 */
	@Override
	public void addUser(IUser user) {
		cbxAvailUsers.insertItemAt(user, 0);

	}

	/**
	 * Inserts the Team to the drop down of available Teams
	 * @param Team		an ITeam to add
	 */
	@Override
	public void addAvailableTeam(ITeam Team) {
		cbxAvailTeams.insertItemAt(Team, 0);
	}

	/**];
	 * 
	 * Inserts the Team to the drop down of already joined Teams
	 * @param Team		an ITeam to add
	 */
	public void addJoinedTeam(ITeam Team) {
		cbxJoinedTeams.insertItemAt(Team, 0);
	}

	/**
	 * Removes the current Team tabs and Team view
	 * @param Teamview 		ITeamView to remove
	 */
	@Override
	public void removeCurrentTeam(ITeamView TeamView) {
		tabbedPane.remove(tabbedPane.getSelectedIndex());
		TeamViews.remove(TeamView);
	}

	/**
	 * Removes Team from joined Teams drop down list upon leaving a Team
	 * @param Team 			ITeam user left 
	 */
	@Override
	public void removeTeamCbx(ITeam Team) {
		cbxJoinedTeams.removeItem(Team);
	}
}
