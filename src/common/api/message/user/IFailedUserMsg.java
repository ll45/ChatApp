package common.api.message.user;

import common.api.message.shared.IFailure;

/**
 * @author ChatApp Design Group E
 * 
 * The interface representing a failed IUserMsg.
 *
 */
public interface IFailedUserMsg extends IFailure<IUserMsg>, IUserMsg {
}