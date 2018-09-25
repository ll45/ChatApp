package pdg2_ll45.view;

/**
 * Interface for the Team view to model adapter
 */
public interface ITeamView2ModelAdapter {

	/**
	 * Sends message
	 * @param message		String to send
	 */
	public void sendMessage(String message);

	/**
	 * Sends a default image to everyone in the chat.
	 */
	public void sendImage();

	/**
	 * Quit the Team
	 */
	public void quitRoom();

}
