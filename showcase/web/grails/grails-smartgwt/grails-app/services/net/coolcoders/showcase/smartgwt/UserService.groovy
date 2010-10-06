package net.coolcoders.showcase.smartgwt

import net.coolcoders.showcase.AppUser
import net.coolcoders.showcase.client.UserInfoResponse

class UserService {

  static transactional = true

  def login(String username, String password) {
    AppUser theUser = AppUser.findByUsername(username)
    if (theUser?.password.equals(password)) {
      return [user: theUser, success: true]
    }
    return [success: false, user: null]
  }

  UserInfoResponse getUserInfo(String userId) {
    AppUser theUser = AppUser.get(userId)
    UserInfoResponse response = new UserInfoResponse(
            username: theUser.username,
            fullname: theUser.fullname,
            email: theUser.email)
    response
  }
}
