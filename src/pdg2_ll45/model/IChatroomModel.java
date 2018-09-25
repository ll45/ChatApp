package pdg2_ll45.model;

import common.api.team.TeamDataPacket;
import common.api.team.ITeam;
import common.api.team.IMsgReceiver;
import common.api.message.team.ITeamMsg;

/**
 * The Team model interface
 */
public interface ITeamModel {
	
	/**
	 * Start the Team model
	 */
	public void start();

	/**
	 * Send message to remote user
	 * @param message		String to be sent
	 */
	public void sendMessage(String message);
	
	/**
	 * Quit the Team
	 */
	public void quitRoom();
	
	/**
	 * Gets the message receiver stub
	 * @return a message receiver stub
	 */
	public IMsgReceiver getReceiverStub();
	
	/**
	 * Gets the Team
	 * @return a Team
	 */
	public ITeam getTeam();
	
	/**
	 * Executes a message
	 * @param message		message to be executed
	 */
	public void executeMessage(TeamDataPacket<? extends ITeamMsg> message);
	
	/**
	 * Sends an image
	 */
	public void sendImage();
}
