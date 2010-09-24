package net.coolcoders.showcase

class UserService {

  static transactional = true


  def searchForUsers(String searchText) {
    return AppUser.findByUsernameIlike("%$searchText%")
  }


  def findAllFollwingUsers(AppUser userInstance, String sortAttribute, String order,int pageSize,int offset) {
    String hqlQueryString = "SELECT followingUser from AppUser as user JOIN user.following as followingUser WHERE user.id=:userId"
    if (sortAttribute == "username") {
      hqlQueryString = hqlQueryString + " ORDER BY followingUser.username " + order
    }
    else if (sortAttribute == "messageCount") {
      hqlQueryString = hqlQueryString + " ORDER BY size(followingUser.messages) " + order
    }
    return AppUser.executeQuery(hqlQueryString, ["userId": userInstance.id],[max:pageSize, offset:offset])
  }
}
