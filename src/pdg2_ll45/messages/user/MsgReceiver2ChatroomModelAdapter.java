package pdg2_ll45.messages.user;

import java.io.Serializable;

import common.api.team.TeamDataPacket;
import common.api.message.team.ITeamMsg;
import pdg2_ll45.model.ITeamModel;

/**
 * Class of message recevier to model adapter
 */
public class MsgReceiver2TeamModelAdapter {
	
	/**
	 * The Team model
	 */
	private ITeamModel model;
	
	/**
	 * Constructor for message receiver to Team model adapter
	 * @param model		the Team model
	 */
	public MsgReceiver2TeamModelAdapter(ITeamModel model) {
		this.model = model;
	}

	/**
	 * Execute a message
	 * @param message		a Team data packet to exeute
	 */
	public void executeMessage(TeamDataPacket<? extends ITeamMsg> message) {
		model.executeMessage(message);
	}
}
