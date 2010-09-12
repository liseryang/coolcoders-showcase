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


  void testFindAllMessagesOfFollowing() {
    def username = "pschneider-manzell"
    User userInstance = User.findByUsername(username)
    assertNotNull("No user with username $username found", userInstance)
    def offset = 0
    def messages = messageService.findAllMessagesOfFollowing(userInstance, offset)
    assertNotNull("NULL instead of empty list detected!", messages)
    assertTrue("Expected empty list, but list had entries: $messages",messages.isEmpty())

  }


}
