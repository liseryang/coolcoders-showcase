package net.coolcoders.showcase

import grails.test.GrailsUnitTestCase

class MessageServiceTests extends GrailsUnitTestCase {

  def messageService

  void testFindAllMessagesOfFollowing() {
    AppUser theUser = AppUser.findByUsername('jmihelko')
    String userId = theUser.id
    List<Message> messages = messageService.findAllMessagesOfFollowing(userId, 1, 10)
    assertNotNull("no messages found", messages)
    assertFalse("emtpy messages", messages.isEmpty())
    assertEquals("expected 10 messages, got ${messages.size()}", 10, messages.size())
    messages = messageService.findAllMessagesOfFollowing(userId, 0, 1000)
    assertNotNull("no messages found", messages)
    assertFalse("emtpy messages", messages.isEmpty())
    assertEquals("expected 60 messages, got ${messages.size()}", 60, messages.size())
  }

  void testNumberOfAllMessagesOfFollowing() {
    AppUser theUser = AppUser.findByUsername('jmihelko')
    String userId = theUser.id
    def numberOfMessages = messageService.numberOfAllMessagesOfFollowing(userId)
    assertEquals("Expected 60, got ${numberOfMessages}", 60, numberOfMessages)
  }

  void testAddMessage() {
    AppUser theUser = AppUser.findByUsername('jmihelko')
    String userId = theUser.id
    Message newMessage = messageService.addMessage(userId, 'Content of new Message!')
    assertNotNull("no new message genereated!", newMessage.id)
    assertNotNull("ne creation date set!", newMessage.created)
    assertEquals("wrong user, expected ${userId}, got ${newMessage.creator.id}", userId, newMessage.creator.id)
  }
}
