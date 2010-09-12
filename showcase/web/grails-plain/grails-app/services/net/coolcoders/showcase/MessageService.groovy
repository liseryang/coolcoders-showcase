package net.coolcoders.showcase

class MessageService {

  static transactional = true

  def findAllMessagesOfFollowing(User userInstance, int offset) {
    log.debug("Loading all messages of  users followed by user $userInstance")
    if (userInstance.following) {
      def c = Message.createCriteria()
      def results = c.list {
        'in'('user', userInstance.following)
        maxResults(offset)
        order("created", "desc")
      }
      return results
    }
    else {
      return []
    }

  }
}
