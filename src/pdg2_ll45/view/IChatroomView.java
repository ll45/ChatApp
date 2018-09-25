package pdg2_ll45.view;

import java.awt.Component;
import java.util.function.Supplier;

import javax.swing.JComponent;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;

/**
 * Interface for the Team GUI
 */
public interface ITeamView {

	/**
	 * Start the Team GUI
	 */
	public void start();

	/**
	 * Appends string
	 * @param text		String to append
	 */
	public void appendText(String text);

	/**
	 * Gets the split pane of the Team GUI
	 * @return	a JSplitPane
	 */
	public JSplitPane getSplitPane();

	/**
	 * Gets the name of the Team
	 * @return	a String representation of the name of the Team
	 */
	public String getName();

	/**
	 * Uses a factory (supplier) to generate a JComponent that can be displayed on the view.
	 * 
	 * @param component
	 * 		new component used to display the message to the view
	 */
	public void buildComponent(Supplier<JComponent> component);

	/**
	 * Calls on the main view to remove this Team from the view.
	 */
	public void removeSelf();
}
