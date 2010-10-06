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
    log.info "Foun messages: ${messages}"
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

  private void flushMessage(MarkupBuilder xml, Message message) {
    xml.record(id: message.id, content: message.content, created: message.created)
  }
}
