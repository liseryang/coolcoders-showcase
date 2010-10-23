package net.coolcoders.showcase

import groovy.xml.MarkupBuilder

class MessageController {

  def messageService

  def list = {
    log.info "MessageController.list( ${params} )"
    def userId = session['userId']
    Integer start = Integer.valueOf(params['_startRow'])
    Integer end = Integer.valueOf(params['_endRow'])
    AppUser theUser = AppUser.get(userId)
    def messages = messageService.findAllMessagesOfFollowing(theUser, start, (end - start))
    def xml = new MarkupBuilder(response.writer)
    xml.response() {
      status(0)
      startRow(start)
      endRow(end)
      totalRows(messages.totalCount)
      data {
        messages.each {
          flushMessage xml, it
        }
      }
    }
  }

  def add = {
    log.info "MessageController.list( ${params} )"
    def userId = session['userId']
    AppUser theUser = AppUser.get(userId)
    Message newMessage = new Message(created: new Date(), content: params.content, creator: theUser).save();
    log.info "Added message: ${newMessage} - ID: ${newMessage.id}"
    def xml = new MarkupBuilder(response.writer)
    xml.response() {
      status(0)
      data() {
        flushMessage xml, newMessage
      }
    }
  }

  private void flushMessage(MarkupBuilder xml, Message message) {
    xml.record(
            id: message.id,
            content: message.content,
            created: message.created,
            creator: message.creator.username
    )
  }
}
