package pdg2_ll45.messages.Team;

import common.api.message.team.IAdd;
import common.api.user.IUser;

/**
 * Class for add messages
 */
public class Add implements IAdd {
	
	/**
	 * The user to add
	 */
	private IUser user;
	
	/**
	 * Add user
	 * @param user		an IUser to add
	 */
	public Add(IUser user) {
		this.user = user;
	}
	
	/**
	 * Get the user's stub
	 * @return an IUser
	 */
	@Override
	public IUser getUserStub() {
		return user;
	}

}
