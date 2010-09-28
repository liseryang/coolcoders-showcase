package net.coolcoders.showcase

class UserService {

  static transactional = true


  def searchForUsers(String searchText) {
    return AppUser.findAllByUsernameIlike("%$searchText%")
  }


  def findAllFollwingUsers(AppUser userInstance, UserSortAttribute sortAttribute, SortOrder order, int pageSize, int offset) {
    String hqlQueryString = "SELECT followingUser from AppUser as user JOIN user.following as followingUser WHERE user.id=:userId"
    if (sortAttribute == UserSortAttribute.USERNAME) {
      hqlQueryString = hqlQueryString + " ORDER BY followingUser.username " + order.hqlString
    }
    else if (sortAttribute == UserSortAttribute.MESSAGE_COUNT) {
      hqlQueryString = hqlQueryString + " ORDER BY size(followingUser.messages) " + order.hqlString
    }
    return AppUser.executeQuery(hqlQueryString, ["userId": userInstance.id], [max: pageSize, offset: offset])
  }
}
