package common.api.message.team;

import common.api.message.shared.IFailure;

/**
 * The message that denotes a failed ITeamMsg. A typed version of the IFailure message.
 * 
 * @author Chatapp Design Group E
 *
 */
public interface IFailedTeamMsg extends IFailure<ITeamMsg>, ITeamMsg {
}