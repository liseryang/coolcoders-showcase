package net.coolcoders.showcase

class UserService {

  static transactional = true


  def searchForUsers(String searchText) {
    return User.findByUsernameIlike("%$searchText%")
  }
}
