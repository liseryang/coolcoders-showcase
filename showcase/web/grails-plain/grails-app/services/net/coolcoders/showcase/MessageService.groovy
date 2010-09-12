package net.coolcoders.showcase

class MessageService {

  static transactional = true

  def findAllMessagesOfFollowing(User userInstance, int offset) {
    log.debug("Loading all messages of  users followed by user $userInstance")
    if (userInstance.following) {
      def result = Message.executeQuery("SELECT DISTINCT message FROM Message as message WHERE user.id=:userId AND (message.user.id=:userId OR message.user in user.following) ORDER BY message.created DESC", ["userId": userInstance.id, max: 10, offset: offset])
      return result
    }
    else {
      def result = Message.executeQuery("SELECT DISTINCT message FROM Message as message WHERE user.id=:userId AND message.user.id=:userId ORDER BY message.created DESC", ["userId": userInstance.id, max: 10, offset: offset])
      return result
    }
  }
}
