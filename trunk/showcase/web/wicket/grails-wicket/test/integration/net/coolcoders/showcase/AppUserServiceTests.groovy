package net.coolcoders.showcase

import grails.test.GrailsUnitTestCase

class AppUserServiceTests extends GrailsUnitTestCase {

  def appUserService

  void testFindFollowing() {
    String userId = AppUser.findByUsername('jmihelko').id
    List<AppUser> following = appUserService.findFollowing(userId, 'username', true, 0, 100)
    assertNotNull("null-following", following)
    assertFalse("emtpy following", following.isEmpty())
    assertEquals("expected 1 following!, got ${following.size()}!", 1, following.size())
    assertEquals("expected 'anerlich' following!, got ${following.get(0).username}!", 'anerlich', following.get(0).username)
  }

  void testNumberOfFollowing() {
    String userId = AppUser.findByUsername('jmihelko').id
    Integer numberOfFollowing = appUserService.numberOfFollowing(userId)
    assertEquals("Expected 1 following, got ${numberOfFollowing}", 1, numberOfFollowing)
    userId = AppUser.findByUsername('user1').id
    numberOfFollowing = appUserService.numberOfFollowing(userId)
    assertEquals("Expected 0 following, got ${numberOfFollowing}", 0, numberOfFollowing)
  }

  void testRegisterUser() {
    ProfileData data = new ProfileData()
    assertFalse(appUserService.registerUser(data).success)
    data = new ProfileData(
            username: 'josip',
            fullname: 'guiseppe Mihelkovic',
            email: 'somemail@somedomain.com',
            password: 'test123',
            gender: Gender.MALE,
            birthday: new Date()
    )
    def result = appUserService.registerUser(data)
    assertTrue("not successfull?", result.success)
    assertNotNull("no user id generated", result.user.id)
  }

  void testUpdateUser() {
    AppUser theUser = AppUser.findByUsername('jmihelko')
    String userId = theUser.id
    ProfileData data = new ProfileData(theUser)
    data.fullname = "Barack Obama"
    def result = appUserService.updateUser(userId, data)
    assertTrue("not successfull?", result.success)
    assertEquals("Name not updated", 'Barack Obama', AppUser.get(userId).fullname)
  }

  void testFollowUser() {
    AppUser josip = AppUser.findByUsername("jmihelko")
    AppUser abaumgartner = AppUser.findByUsername("abaumgartner")
    assertFalse("already following abaumgartner!", josip.following.contains(abaumgartner))
    appUserService.followUser(josip.id, abaumgartner.id)
    josip = AppUser.findByUsername("jmihelko")
    assertTrue("not following abaumgartner!", josip.following.contains(abaumgartner))
  }

  void testUnfollowUser() {
    AppUser josip = AppUser.findByUsername("jmihelko")
    AppUser anerlich = AppUser.findByUsername("anerlich")
    assertTrue("not following anerlich!", josip.following.contains(anerlich))
    appUserService.unfollowUser(josip.id, anerlich.id)
    josip = AppUser.findByUsername("jmihelko")
    assertFalse("not following abaumgartner!", josip.following.contains(anerlich))
  }

  void testIsFollowing() {
    AppUser josip = AppUser.findByUsername("jmihelko")
    AppUser anerlich = AppUser.findByUsername("anerlich")
    AppUser abaumgartner = AppUser.findByUsername("abaumgartner")
    assertTrue("not following anerlich?", appUserService.isFollowing(josip.id, anerlich.id))
    assertFalse("following abaumgartner?", appUserService.isFollowing(josip.id, abaumgartner.id))
  }
}
