package net.coolcoders.showcase

import net.coolcoders.showcase.panel.ProfileFormPanel.ProfileData

class AppUserService {

  static transactional = true

  def registerUser(ProfileData profileData) {
    log.debug("registerUser( ${profileData} )")
    AppUser newUser = new AppUser()
    newUser.properties = profileData.properties
//    profileData.getCategories().each {
//      it = Category.get(it.id)
//      newUser.addToCategories(it)
//    }
    log.info("${newUser.categories}")
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
//    profileData.getCategories().each {
//      Category theCategory = Category.get(it.id)
//      theUser.addToCategories(theCategory)
//    }

    if (theUser.validate() && theUser.save()) {
      return [success: true, user: theUser]
    }
    return [success: false, user: theUser]
  }
}
