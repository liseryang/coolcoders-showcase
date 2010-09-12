package net.coolcoders.showcase

class UserController {

  private static final String SORTORDER_ASC = "asc"
  private static final String SORTORDER_DESC = "desc"

  def userService

  def following = {
    log.debug("Entering following with params $params")
    def currentSortOrder = params.order
    if (!currentSortOrder) {
      currentSortOrder = SORTORDER_ASC
    }
    def nextSortOrder = SORTORDER_DESC
    if (currentSortOrder == SORTORDER_DESC) {
      nextSortOrder = SORTORDER_ASC
    }
    def sortParameter = params.sort
    if (!sortParameter) {
      sortParameter = "username"
    }
    User userInstance = User.get(session.currentUser.id)
    def followingUsers = userService.findAllFollwingUsers(userInstance, sortParameter, currentSortOrder)
    [followingUsers: followingUsers, nextSortOrder: nextSortOrder]
  }


  def search = {
    log.debug("Entering search with params $params")
    def currentUser = User.get(session.currentUser.id)
    def queryText = params.queryText
    def result = []
    if (queryText) {
      result = userService.searchForUsers(queryText)
    }
    [userResultList: result, queryText: queryText, currentUser: currentUser]
  }

  def follow = {
    def userToFollow = User.get(params.id as Long)
    def currentUser = User.get(session.currentUser.id)
    currentUser.addToFollowing(userToFollow)
    flash.message = g.message(code: 'user.follow.executed', args: [userToFollow.username])
    redirect(action: "following")
  }

  def unfollow = {
    def userToUnfollow = User.get(params.id as Long)
    def currentUser = User.get(session.currentUser.id)
    currentUser.removeFromFollowing(userToUnfollow)
    flash.message = g.message(code: 'user.unfollow.executed', args: [userToUnfollow.username])
    redirect(action: "following")
  }
}
