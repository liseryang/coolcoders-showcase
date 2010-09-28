package net.coolcoders.showcase

class UserController {

  private static final int PAGESIZE_DEFAULT = 10

  def userService

  def following = {
    log.debug("Entering following with params $params")
    def sortOrder = SortOrder.ASC
    def sortAttribute = UserSortAttribute.USERNAME

    if (params.order) {
      sortOrder = SortOrder.valueOf(params.order)
    }
    def nextSortOrder = SortOrder.DESC
    if (sortOrder == SortOrder.DESC) {
      nextSortOrder = SortOrder.ASC
    }
    if (params.sort) {
      sortAttribute = UserSortAttribute.valueOf(params.sort)
    }

    if (!params.max) {
      params.max = PAGESIZE_DEFAULT
    }

    if (!params.offset) {
      params.offset = 0
    }

    AppUser userInstance = AppUser.get(session.currentUser.id)
    def followingUsers = userService.findAllFollwingUsers(userInstance, sortAttribute, sortOrder, params.max as int, params.offset as int)
    def totalCount = userInstance.following.size()
    [followingUsers: followingUsers, nextSortOrder: nextSortOrder.name(), totalCount: totalCount, pageSize: params.max]
  }


  def search = {
    log.debug("Entering search with params $params")
    def currentUser = AppUser.get(session.currentUser.id)
    def queryText = params.queryText
    def result = []
    if (queryText) {
      result = userService.searchForUsers(queryText)
    }
    [userResultList: result, queryText: queryText, currentUser: currentUser]
  }

  def follow = {
    def userToFollow = AppUser.get(params.id)
    def currentUser = AppUser.get(session.currentUser.id)
    currentUser.addToFollowing(userToFollow)
    flash.message = g.message(code: 'user.follow.executed', args: [userToFollow.username])
    redirect(action: "following")
  }

  def unfollow = {
    def userToUnfollow = AppUser.get(params.id)
    def currentUser = AppUser.get(session.currentUser.id)
    currentUser.removeFromFollowing(userToUnfollow)
    flash.message = g.message(code: 'user.unfollow.executed', args: [userToUnfollow.username])
    redirect(action: "following")
  }
}
