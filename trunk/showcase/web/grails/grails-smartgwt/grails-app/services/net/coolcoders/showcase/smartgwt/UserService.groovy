package net.coolcoders.showcase.smartgwt

import net.coolcoders.showcase.AppUser

class UserService {

  static transactional = true

  def login(String username, String password) {
    def theUser = AppUser.findByUsername(username)
    if (theUser?.password.equals(password)) {
      return [user: theUser, success: true]
    }
    return [success: false, user: null]
  }
}
