package net.coolcoders.showcase.smartgwt

class UserService {

  static transactional = true

  def login(String username, String password) {
    def theUser = User.findByUsername(username)
    if (theUser?.password.equals(password)) {
      return [user: theUser, success: true]
    }
    return [success: false, user: null]
  }
}
