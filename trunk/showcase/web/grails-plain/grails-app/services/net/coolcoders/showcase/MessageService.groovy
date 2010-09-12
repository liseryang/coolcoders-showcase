package net.coolcoders.showcase

class MessageService {

  static transactional = true

  def findAllMessagesOfFollowing(User userInstance, int offset) {
    log.debug("Loading all messages of  users followed by user $userInstance")
    return Message.executeQuery("SELECT DISTINCT message FROM User as user LEFT JOIN user.following as followingUser LEFT JOIN followingUser.messages as message WHERE user.id=:userId OR message.user.id=:userId ORDER BY message.created DESC", ["userId": userInstance.id,max:10, offset:offset])

  }
}
