package common.api.team;

import java.util.function.Supplier;

import javax.swing.JComponent;

/**
 * Adapter used by the visitor pattern to send a message to the view.
 * 
 * @author ChatApp Design Group E
 *
 */
public interface ICmd2ModelAdapter {

	/**
	 * Uses a factory (supplier) to generate a JComponent that can be displayed on the view.
	 * 
	 * @param component
	 * 		new component used to display the message to the view
	 */
	public void buildComponent(Supplier<JComponent> component);
	
	
	/**
	 * Appends a string to the view console of the Team.
	 * 
	 * @param message
	 * 		string that will be sent to the Team's view
	 */
	public void appendString(String message);
	
}
