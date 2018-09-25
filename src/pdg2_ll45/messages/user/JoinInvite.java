package pdg2_ll45.messages.user;

import common.api.team.ITeam;
import common.api.message.user.IJoinInvite;

/**
 * Class involving join invites. Implements IJoinInvite.
 */
public class JoinInvite implements IJoinInvite {
	
	/**
	 * Serializable ID
	 */
	private static final long serialVersionUID = -5602064447472709396L;
	
	/**
	 * The Team to invite to
	 */
	private ITeam Team; 
	
	/**
	 * Constructor for a join invite
	 * @param Team		Team to invite to
	 */
	public JoinInvite(ITeam Team) {
		this.Team = Team;
	}
	
	/**
	 * Gets Team to invite to
	 * @return Team		A Team to be invited to
	 */
	@Override
	public ITeam getTeam() {
		return Team;
	}

}
