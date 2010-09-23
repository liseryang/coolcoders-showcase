package net.coolcoders.showcase

class UserController {

  private static final String SORTORDER_ASC = "asc"
  private static final String SORTORDER_DESC = "desc"
  private static final int PAGESIZE_DEFAULT = 10

  def userService

  def following = {
    log.debug("Entering following with params $params")
    if (!params.order) {
      params.order = SORTORDER_ASC
    }
    def nextSortOrder = SORTORDER_DESC
    if (params.order == SORTORDER_DESC) {
      nextSortOrder = SORTORDER_ASC
    }
    if (!params.sort) {
      params.sort = "username"
    }

    if(!params.max) {
      params.max = PAGESIZE_DEFAULT
    }

    if(!params.offset) {
      params.offset = 0
    }

    User userInstance = User.get(session.currentUser.id)
    def followingUsers = userService.findAllFollwingUsers(userInstance, params.sort, params.order, params.max as int, params.offset as int)
    def totalCount = userInstance.following.size()
    [followingUsers: followingUsers, nextSortOrder: nextSortOrder,totalCount:totalCount,pageSize:params.max]
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
    def userToFollow = User.get(params.id)
    def currentUser = User.get(session.currentUser.id)
    currentUser.addToFollowing(userToFollow)
    flash.message = g.message(code: 'user.follow.executed', args: [userToFollow.username])
    redirect(action: "following")
  }

  def unfollow = {
    def userToUnfollow = User.get(params.id )
    def currentUser = User.get(session.currentUser.id)
    currentUser.removeFromFollowing(userToUnfollow)
    flash.message = g.message(code: 'user.unfollow.executed', args: [userToUnfollow.username])
    redirect(action: "following")
  }
}
