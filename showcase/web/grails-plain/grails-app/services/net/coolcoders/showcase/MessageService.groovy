package net.coolcoders.showcase

class MessageService {

  static transactional = true

  def findAllMessagesOfFollowings(User userInstance, int offset) {
    log.debug("Loading all messages of users followed by user $userInstance")
    if (userInstance.follows) {
      def c = Message.createCriteria()
      def results = c.list {
        'in'('user', userInstance.follows)
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
