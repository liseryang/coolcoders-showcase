package net.coolcoders.showcase.smartgwt

import grails.test.GrailsUnitTestCase
import net.coolcoders.showcase.smartgwt.Message
import net.coolcoders.showcase.smartgwt.User

class MessageTests extends GrailsUnitTestCase {

  void testConstraints() {
    //empty message
    def message = new Message()
    mockForConstraintsTests Message, [message]
    assertFalse 'valid empty message?', message.validate()
    assert 'content not missing?', message.errors['content']
    assert 'user not missing?', message.errors['user']
    //user missing
    message = new Message(content: 'message')
    assertFalse 'valid message whitout user?', message.validate()
    assert 'user not missing?', message.errors['user']
    //message blank
    message = new Message(content: '', user: new User())
    assertFalse 'valid blank message?', message.validate()
    assert 'content not blank?', message.errors['content']
    //message too long
    message = new Message(content: 'this message is way to long as only 140 chars are allowed...this message is way to long as only 140 chars are allowed...this message is way to long as only 140 chars are allowed...', user: new User())
    assertFalse 'valid message big content?', message.validate()
    assert 'content not too long?', message.errors['content']
    //valid message
    message = new Message(content: 'message', user: new User())
    assertTrue 'message not valid?', message.validate()
  }
}
