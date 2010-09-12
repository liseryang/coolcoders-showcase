package net.coolcoders.showcase

class MessageController {

  def messageService

  def index = {
    int offset = 0
    if (params.offset) {
      offset = params.offset as int
    }
    def currentUser = User.get(session.currentUser.id)
    def messages = messageService.findAllMessagesOfFollowing(currentUser, offset)
    [messages: messages, currentUser: currentUser]
  }


  def create = {
    Message message = new Message()
    message.properties = params
    def currentUser = User.get(session.currentUser.id)
    message.user = currentUser
    if (!message.validate()) {
      log.debug("Could not validate message! $message.errors")
      flash.error = g.message(code:'message.create.failed')

    }
    else {
      log.debug("Created message!")
      message.save()
      currentUser.addToMessages(message)
    }
    redirect(action: "index")
  }


}
