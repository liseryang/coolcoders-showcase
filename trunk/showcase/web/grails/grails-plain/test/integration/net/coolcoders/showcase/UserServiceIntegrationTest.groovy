package net.coolcoders.showcase

import grails.test.GrailsUnitTestCase

/**
 * Peter Schneider-Manzell
 */
class UserServiceIntegrationTest extends GrailsUnitTestCase {

  def userService

  protected void setUp() {
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }

  void testFindAllFollowingUsersOrderByUsernameNormalPageSizeAndOffset() {
    AppUser userInstance = AppUser.findByUsername("pschneider-manzell")
    def sortAttribute =  UserSortAttribute.USERNAME
    def sortOrder = SortOrder.ASC
    def pageSize = 10
    def offset = 0
    def result = userService.findAllFollwingUsers(userInstance,sortAttribute,sortOrder,pageSize,offset)
    assertNotNull("NULL for following users loaded!",result)
  }

  void testFindAllFollowingUsersOrderByMessageCountNormalPageSizeAndOffset() {
    AppUser userInstance = AppUser.findByUsername("pschneider-manzell")
    def sortAttribute = UserSortAttribute.MESSAGE_COUNT
    def sortOrder = SortOrder.ASC
    def pageSize = 10
    def offset = 0
    def result = userService.findAllFollwingUsers(userInstance,sortAttribute,sortOrder,pageSize,offset)
    assertNotNull("NULL for following users loaded!",result)
  }

  void testFindAllFollowingUsersOrderByUsernameBigPageSizeAndOffset() {
    AppUser userInstance = AppUser.findByUsername("pschneider-manzell")
    def sortAttribute = UserSortAttribute.USERNAME
    def sortOrder = SortOrder.ASC
    def pageSize = 1000
    def offset = 0
    def result = userService.findAllFollwingUsers(userInstance,sortAttribute,sortOrder,pageSize,offset)
    assertNotNull("NULL for following users loaded!",result)
  }

  void testFindAllFollowingUsersOrderByUsernameNormalPageSizeAndBigOffset() {
    AppUser userInstance = AppUser.findByUsername("pschneider-manzell")
    def sortAttribute = UserSortAttribute.USERNAME
    def sortOrder = SortOrder.ASC
    def pageSize = 10
    def offset = 1000
    def result = userService.findAllFollwingUsers(userInstance,sortAttribute,sortOrder,pageSize,offset)
    assertNotNull("NULL for following users loaded!",result)
  }

}
