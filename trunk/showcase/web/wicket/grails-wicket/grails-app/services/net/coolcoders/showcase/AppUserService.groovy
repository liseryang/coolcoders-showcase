package net.coolcoders.showcase

class AppUserService {

  static transactional = true

  def registerUser(ProfileData profileData) {
    log.debug("registerUser( ${profileData} )")
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
    log.debug("updateUser( ${userId}, ${profileData} )")
    AppUser theUser = AppUser.get(userId)
    theUser.properties = profileData.properties
    theUser.categories.clear()
    if (theUser.validate() && theUser.save()) {
      return [success: true, user: theUser]
    }
    return [success: false, user: theUser]
  }

  List<AppUser> findFollowing(String userId, String sortAttribute, boolean ascending) {
    AppUser theUser = AppUser.get(userId)
    def following = AppUser.createCriteria().list() {
      'in'('id', theUser.following.id)
      if (ascending) {
        order(sortAttribute, 'asc')
      }
      else {
        order(sortAttribute, 'desc')
      }
    }
    log.info "${theUser} is following:  ${following}"
    following
  }

  public Long numberOfFollowing(String userId) {
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
}
