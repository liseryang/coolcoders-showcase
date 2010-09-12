package net.coolcoders.showcase

class UserService {

  static transactional = true


  def searchForUsers(String searchText) {
    return User.findByUsernameIlike("%$searchText%")
  }


  def findAllFollwingUsers(User userInstance, String sortAttribute, String order) {
    String hqlQueryString = "SELECT followingUser from User as user JOIN user.following as followingUser WHERE user.id=:userId"
    if (sortAttribute == "username") {
      hqlQueryString = hqlQueryString + " ORDER BY followingUser.username " + order
    }
    else if (sortAttribute == "messageCount") {
      hqlQueryString = hqlQueryString + " ORDER BY size(followingUser.messages) " + order
    }
    return User.executeQuery(hqlQueryString, ["userId": userInstance.id])
  }
}
