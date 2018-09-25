package common.api.message.team;

/**
 * This message is sent to a Team by a user who wishes to leave the Team. The Team
 * and all its remote instances should delete that user's IMsgReceiver stub from their copy of
 * the Team. Since no information aside from the sender of this message is required, this
 * interface has no methods.
 * 
 * @author ChatApp Design Group E
 *
 */
public interface IQuit extends ITeamMsg {
}