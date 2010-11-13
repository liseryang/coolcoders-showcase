package net.coolcoders.showcase

import grails.test.GrailsUnitTestCase

class AppUserServiceTests extends GrailsUnitTestCase {

  def appUserService

  void testFindFollowing() {
    String userId = AppUser.findByUsername('jmihelko').id
    List<AppUser> following = appUserService.findFollowing(userId, 'username', true)
    assertNotNull("null-following", following)
    assertFalse("emtpy following", following.isEmpty())
    assertEquals("expected 1 following!, got ${following.size()}!", 1, following.size())
    assertEquals("expected 'anerlich' following!, got ${following.get(0).username}!", 'anerlich', following.get(0).username)
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

}
