package pdg2_ll45.view;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.function.Supplier;

import javax.swing.JButton;
import javax.swing.JComponent;

import net.miginfocom.swing.MigLayout;
import javax.swing.JSplitPane;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;

/**
 * Team GUI. The mini view for the overarching chat application.
 */
public class TeamView extends JFrame implements ITeamView {

	/**
	 * View2ModelAdapter for mini MVC
	 */
	private ITeamView2ModelAdapter view2ModelAdpt;
	/**
	 * Adapter to allow this view to communicate with the main view.
	 */
	private ITeamView2MainViewAdapter mainViewAdpt;

	/**
	 * Text box to enter intended message user wants to send
	 */
	private JTextField txtEnterMessageHere;

	/**
	 * Split pane for the displayed messages and test field to enter messages
	 */
	private JSplitPane splitPane = new JSplitPane();

	/**
	 * Button to leave room
	 */
	private final JButton btnLeaveTeam = new JButton("Leave Team");

	/**
	 * Button to send an image
	 */
	private final JButton btnSendImage = new JButton("Send Image");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTextArea txtrTeamMessages = new JTextArea();
	private final JScrollPane scrollPanelComponent = new JScrollPane();
	private final JPanel panelComponent = new JPanel();

	/**
	 * Constructor for Team GUI
	 * @param view2ModelAdpt		the view to model adapter
	 */
	public TeamView(ITeamView2ModelAdapter view2ModelAdpt, ITeamView2MainViewAdapter mainViewAdpt) {
		this.view2ModelAdpt = view2ModelAdpt;
		this.mainViewAdpt = mainViewAdpt;
		initGUI();
	}

	/**
	 * Initialize the GUI
	 */
	public void initGUI() {
		splitPane.setToolTipText("Split view of chat display and text field to type messages");

		getSplitPane().setOrientation(JSplitPane.VERTICAL_SPLIT);
		getContentPane().add(getSplitPane(), BorderLayout.CENTER);

		JPanel panelTextBox = new JPanel();
		getSplitPane().setRightComponent(panelTextBox);

		panelTextBox.setLayout(new MigLayout("", "[565px,grow]", "[][41px][grow]"));

		JButton btnSend = new JButton("Send");
		btnSend.setToolTipText("Click to send message.");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view2ModelAdpt.sendMessage(txtEnterMessageHere.getText());
			}
		});

		txtEnterMessageHere = new JTextField();
		txtEnterMessageHere.setToolTipText("Enter message you wish to send in Team.");
		txtEnterMessageHere.setText("Enter Message Here!");
		txtEnterMessageHere.setColumns(10);
		panelTextBox.add(txtEnterMessageHere, "cell 0 0,grow");
		panelTextBox.add(btnSend, "flowx,cell 0 1,alignx left,aligny top");
		btnLeaveTeam.setToolTipText("Click to leave Team");

		btnLeaveTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainViewAdpt.removeTeamView(TeamView.this);
				view2ModelAdpt.quitRoom();
			}
		});
		btnSendImage.setToolTipText("Click to send image");
		btnSendImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view2ModelAdpt.sendImage();
			}
		});

		panelTextBox.add(btnSendImage, "cell 0 1");
		panelTextBox.add(btnLeaveTeam, "cell 0 1");
		scrollPanelComponent.setToolTipText("Component to display unknown types");

		panelTextBox.add(scrollPanelComponent, "cell 0 2,grow");

		scrollPanelComponent.setViewportView(panelComponent);
		scrollPane.setToolTipText("Team Messages");

		splitPane.setLeftComponent(scrollPane);
		txtrTeamMessages.setToolTipText("Display for Team messages");
		txtrTeamMessages.setText("Team Messages");

		scrollPane.setViewportView(txtrTeamMessages);

	}

	/**
	 * Start the GUI
	 */
	public void start() {
	}

	/**
	 * Append text to the GUI
	 * @param text		the text to append
	 */
	public void appendText(String text) {
		this.txtrTeamMessages.append(text + '\n');
	}

	/**
	 * Get the Split Pane
	 * @return JSplitPane		the split pane of Team GUI
	 */
	public JSplitPane getSplitPane() {
		return splitPane;
	}

	/**
	 * Set the Split Pane
	 * @param splitPane		splitPane to set
	 */
	public void setSplitPane(JSplitPane splitPane) {
		this.splitPane = splitPane;
	}

	/**
	 * Uses a factory (supplier) to generate a JComponent that can be displayed on the view.
	 * 
	 * @param component
	 * 		new component used to display the message to the view
	 */
	public void buildComponent(Supplier<JComponent> component) {
		panelComponent.add(component.get());
	}

	/**
	 * Calls on the main view to remove this Team from the view. 
	 */
	@Override
	public void removeSelf() {
		mainViewAdpt.removeTeamView(this);
	}
}
