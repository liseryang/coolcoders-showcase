package net.coolcoders.showcase

import grails.test.GrailsUnitTestCase

/**
 * Peter Schneider-Manzell
 */
class MessageServiceIntegrationTest extends GrailsUnitTestCase {

  def messageService

  protected void setUp() {
    super.setUp()
  }

  protected void tearDown() {
    super.tearDown()
  }


  void testFindAllMessagesOfFollowingNoFollowingUsers() {
    def username = "pschneider-manzell"
    User userInstance = User.findByUsername(username)
    assertNotNull("No user with username $username found", userInstance)
    def offset = 0
    def pageSize = 10
    def messages = messageService.findAllMessagesOfFollowing(userInstance, offset, pageSize)
    assertNotNull("NULL instead of empty list detected!", messages)
    assertTrue("Expected empty list, but list had entries: $messages", messages.isEmpty())

  }

  void testFindAllMessagesOfFollowingWithFollowingUsers() {
    def username = "anerlich"
    User userInstance = User.findByUsername(username)
    assertNotNull("No user with username $username found", userInstance)
    def offset = 0
    def pageSize = 10
    def messages = messageService.findAllMessagesOfFollowing(userInstance, offset, 10)
    assertNotNull("NULL instead of empty list detected!", messages)
    assertFalse("Expected NOT empty list, but list had no entries: $messages", messages.isEmpty())

  }

  void testCountAllMessagesOfFollowingWithoutFollowingUsers() {
    String username = "pschneider-manzell"
    User userInstance = User.findByUsername(username)
    assertNotNull("No user with username $username found", userInstance)
    def result = messageService.countAllMessagesOfFollowing(userInstance)
    assertNotNull("NULL instead of int value detected!", result)
    assertEquals("Expected different message count", 0, result)
  }

  void testCountAllMessagesOfFollowingWithFollowingUsers() {
    String username = "anerlich"
    User userInstance = User.findByUsername(username)
    assertNotNull("No user with username $username found", userInstance)
    def result = messageService.countAllMessagesOfFollowing(userInstance)
    assertNotNull("NULL instead of int value detected!", result)
    assertEquals("Expected different message count", 110, result)
  }


}
