package net.coolcoders.showcase

class UserService {

  static transactional = true


  def searchForUsers(String searchText) {
    return AppUser.findAllByUsernameIlike("%$searchText%")
  }


  def findAllFollwingUsers(AppUser userInstance, net.coolcoders.showcase.UserSortAttribute sortAttribute, net.coolcoders.showcase.SortOrder order, int pageSize, int offset) {
    String hqlQueryString = "SELECT followingUser from AppUser as user JOIN user.following as followingUser WHERE user.id=:userId"
    if (sortAttribute == net.coolcoders.showcase.UserSortAttribute.USERNAME) {
      hqlQueryString = hqlQueryString + " ORDER BY followingUser.username " + order.hqlString
    }
    else if (sortAttribute == net.coolcoders.showcase.UserSortAttribute.MESSAGE_COUNT) {
      hqlQueryString = hqlQueryString + " ORDER BY size(followingUser.messages) " + order.hqlString
    }
    return AppUser.executeQuery(hqlQueryString, ["userId": userInstance.id], [max: pageSize, offset: offset])
  }
}
