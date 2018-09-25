package common.api.team;

import common.api.message.team.ITeamMsg;

import provided.datapacket.ADataPacketAlgoCmd;

/**
 * @author ChatApp Design Group E
 * 
 * The interface to be used for all commands installed into the visitor that processes ITeamMsgs.
 *
 * @param <D>
 * 		Type of message that should be processed by this command. This message needs to be a subclass
 * 		of ITeamMsg. 
 */
public abstract class ATeamDPAlgoCmd<D extends ITeamMsg> 
	extends ADataPacketAlgoCmd<Void, D, Void, ICmd2ModelAdapter, TeamDataPacket<D>> {
	
	/**
	 * Unique serial version UID.
	 */
	private static final long serialVersionUID = -7717296348586410093L;
	
	/**
	 * Command to model adapter that allows the command to communicate with the model as necessary.
	 */
	private transient ICmd2ModelAdapter cmd2ModelAdpt;
	
	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}
	
	/**
	 * Gets the command-to-model adapter associated with the given command.
	 * 
	 * @return command-to-model adapter associated with the given command
	 */
	protected ICmd2ModelAdapter getCmd2ModelAdpt() {
		return this.cmd2ModelAdpt;
	}

}