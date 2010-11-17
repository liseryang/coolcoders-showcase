package net.coolcoders.showcase

class AppUserService {

  static transactional = true

  def registerUser(ProfileData profileData) {
    log.debug("registerUser( profileData=[${profileData}] )")
    AppUser newUser = new AppUser()
    newUser.properties = profileData.properties
    if (newUser.validate() && newUser.save(flush: true)) {
      //add standard followings
      AppUser following = AppUser.findByUsername("abaumgartner")
      newUser.addToFollowing(following)
      following = AppUser.findByUsername("anerlich")
      newUser.addToFollowing(following)
      following = AppUser.findByUsername("jmihelko")
      newUser.addToFollowing(following)
      following = AppUser.findByUsername("pschneider-manzell")
      newUser.addToFollowing(following)
      return [success: true, user: newUser]
    }
    else {
      return [success: false, user: newUser]
    }
  }

  def updateUser(String userId, ProfileData profileData) {
    log.debug("updateUser( userId=[${userId}], profileData=[${profileData}] )")
    AppUser theUser = AppUser.get(userId)
    theUser.properties = profileData.properties
    theUser.categories.clear()
    if (theUser.validate() && theUser.save()) {
      return [success: true, user: theUser]
    }
    return [success: false, user: theUser]
  }

  List<AppUser> findFollowing(String userId, String sortAttribute, boolean ascending, int offset, int pageSize) {
    log.info("findFollowing( userId=[${userId}], sortAttribute=[${sortAttribute}], ascending=[${ascending}], offset=[${offset}], pageSize=[${pageSize}] )")
    AppUser theUser = AppUser.get(userId)
    def following = AppUser.createCriteria().list(max: pageSize, offset: offset) {
      'in'('id', theUser.following.id)
      if (ascending) {
        order(sortAttribute, 'asc')
      }
      else {
        order(sortAttribute, 'desc')
      }
    }
    following
  }

  List<AppUser> findUsers(String searchString, String sortAttribute, boolean ascending, int offset, int pageSize) {
    log.info("findUsers( searchString=[${searchString}] sortAttribute=[${sortAttribute}], ascending=[${ascending}], offset=[${offset}], pageSize=[${pageSize}] )")
    def users = AppUser.createCriteria().list(max: pageSize, offset: offset) {
      if (searchString) {
        like("username", "%${searchString}%")
      }
      if (ascending) {
        order(sortAttribute, 'asc')
      }
      else {
        order(sortAttribute, 'desc')
      }
    }
    users
  }

  public Long numberOfFollowing(String userId) {
    log.debug("numberOfFollowing( userId=[${userId}] )")
    AppUser theUser = AppUser.get(userId)
    if (!theUser.following) {
      return 0
    }
    def numberOfFollowing = AppUser.createCriteria().get() {
      'in'('id', theUser.following.id)
      projections {
        countDistinct('id')
      }
    }
    numberOfFollowing
  }

  public void unfollowUser(String currentUserId, String userIdToUnfollow) {
    log.debug("unfollowUser( currentUserId=[${currentUserId}], userIdToUnfollow=[${userIdToUnfollow}]")
    AppUser currentUser = AppUser.get(currentUserId)
    AppUser toUnfollow = AppUser.get(userIdToUnfollow)
    currentUser.removeFromFollowing(toUnfollow)
    currentUser.save(flush: true)
    log.info(Following: "${currentUser.following}")
  }

  public void followUser(String currentUserId, String userIdToFollow) {
    log.debug("folowUser( currentUserId=[${currentUserId}], userIdToFollow=[${userIdToFollow}] )")
    AppUser currentUser = AppUser.get(currentUserId)
    AppUser toFollow = AppUser.get(userIdToFollow)
    currentUser.addToFollowing(toFollow)
    currentUser.save(flush: true)
    log.info(Following: "${currentUser.following}")
  }

  public Boolean isFollowing(String currentUserId, String otherUserId) {
    log.debug("isFollowing( currentUserId=[${currentUserId}], otherUserId=[${otherUserId}] )")
    AppUser current = AppUser.get(currentUserId)
    current?.following?.id?.contains(otherUserId)
  }
}
