package net.coolcoders.showcase

class MessageController {

  def messageService

  def index = {
    int offset = 0
    if(params.offset) {
      offset = params.offset as int
    }
    def currentUser = User.get(session.currentUser.id)
    def messages = messageService.findAllMessagesOfFollowings(currentUser,offset)
    [messages: messages,currentUser:currentUser]
  }
}
