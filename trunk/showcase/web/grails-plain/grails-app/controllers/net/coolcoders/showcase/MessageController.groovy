package net.coolcoders.showcase

class MessageController {

  def messageService
  private static int PAGE_SIZE = 10

  def index = {
    int offset = 0
    if (params.offset) {
      offset = params.offset as int
    }
    if (offset < 0) {
      offset = 0
    }
    def currentUser = User.get(session.currentUser.id)
    def messages = messageService.findAllMessagesOfFollowing(currentUser, offset,PAGE_SIZE)
    def messageCount = messageService.countAllMessagesOfFollowing(currentUser)
    def nextPageAvailable = PAGE_SIZE + offset < messageCount
    def prevPageAvailable = offset > 0
    [messages: messages, currentUser: currentUser, pageSize: PAGE_SIZE, offset: offset, nextPageAvailable: nextPageAvailable, prevPageAvailable: prevPageAvailable]
  }


  def create = {
    Message message = new Message()
    message.properties = params
    def currentUser = User.get(session.currentUser.id)
    message.user = currentUser
    if (!message.validate()) {
      log.debug("Could not validate message! $message.errors")
      flash.error = g.message(code: 'message.create.failed')

    }
    else {
      log.debug("Created message!")
      message.save()
      currentUser.addToMessages(message)
    }
    redirect(action: "index")
  }


}
