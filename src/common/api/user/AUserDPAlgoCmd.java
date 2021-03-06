package common.api.user;

import common.api.team.ICmd2ModelAdapter;
import common.api.message.user.IUserMsg;
import provided.datapacket.ADataPacketAlgoCmd;

/**
 * @author ChatApp Design Group E
 * 
 * The interface to be used for all commands installed into the visitor that processes IUserMsgs.
 *
 * @param <D>
 * 		Type of message that should be processed by this command. This message needs to be a subclass
 * 		of IUserMsg. 
 */
public abstract class AUserDPAlgoCmd<D extends IUserMsg> 
	extends ADataPacketAlgoCmd<Void, D, Void, ICmd2ModelAdapter, UserDataPacket<D>> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 897149519557015998L;
	
	/**
	 * Command-to-model adapter that allows the command to communicate with the local model.
	 */
	transient private ICmd2ModelAdapter cmd2ModelAdpt;
	
	@Override
	public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}
	
	/**
	 * Gets the command-to-model adapter of the command.
	 * 
	 * @return command-to-model adapter
	 */
	protected ICmd2ModelAdapter getCmd2ModelAdpt() {
		return this.cmd2ModelAdpt;
	}

}