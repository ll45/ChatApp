package pdg2_ll45.model;

import java.util.function.Supplier;

import javax.swing.JComponent;

/**
 * Interface for a Team model to view adapter in the Team MVC
 */
public interface ITeamModel2ViewAdapter {
	
	/**
	 * adds text to Team display
	 * @param text		String text to be displayed
	 */
	public void addText(String text);

	/**
	 * Builds the component to display unknown message type
	 * @param component		the supplier component
	 */
	public void buildComponent(Supplier<JComponent> component);
	
	/**
	 * Appends a string
	 * @param message		String message to be appended
	 */
	public void appendString(String message);
	
	/**
	 * Removes the view
	 */
	public void removeView();
}
