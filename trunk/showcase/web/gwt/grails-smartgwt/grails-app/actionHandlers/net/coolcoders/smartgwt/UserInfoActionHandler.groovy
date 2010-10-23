package net.coolcoders.smartgwt

import net.coolcoders.showcase.client.UserInfoAction
import net.coolcoders.showcase.client.UserInfoResponse

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class UserInfoActionHandler extends BaseActionHandler {
  def userService

  UserInfoResponse execute(UserInfoAction action) {
    String userId = session["userId"]
    return userService.getUserInfo(userId)
  }
}
