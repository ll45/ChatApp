package pdg2_ll45.messages.user;

import java.io.Serializable;
import java.util.List;

import common.api.team.ITeam;
import common.api.message.user.IAvailableTeams;

/**
 * Class of serializable available Teams. Implements IAvailableTeams.
 */
public class AvailableTeams implements IAvailableTeams, Serializable {
	
	/**
	 * List of Teams
	 */
	private List<ITeam> Teams;
	
	/**
	 * Constructor for available Teams
	 * @param Teams		a list of Teams that are available
	 */
	public AvailableTeams(List<ITeam> Teams) {
		this.Teams = Teams;
	}
	
	/**
	 * Gets available Teams
	 * @return Teams		List of available Teams
	 */
	@Override
	public List<ITeam> getTeams() {
		return Teams;
	}

}
