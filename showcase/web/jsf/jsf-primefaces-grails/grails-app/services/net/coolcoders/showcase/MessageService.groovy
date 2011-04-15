package net.coolcoders.showcase

class MessageService {

  static transactional = true
  private static final String LOAD_ALL_MESSAGES_BASE_HQL = "FROM Message as message, AppUser as user LEFT JOIN user.following as followedUser WHERE user.id=:userId AND (message.creator.id=:userId OR message.creator = followedUser )"

  def findAllMessagesOfFollowing(AppUser userInstance, int offset, int pageSize) {
    log.debug("Loading all messages of  users followed by user $userInstance")
    def result = Message.executeQuery("SELECT DISTINCT message " + LOAD_ALL_MESSAGES_BASE_HQL + " ORDER BY message.created DESC", ["userId": userInstance.id, max: pageSize, offset: offset])
    return result
  }

  def countAllMessagesOfFollowing(AppUser userInstance) {
    def result = Message.executeQuery("SELECT COUNT(DISTINCT message.id) " + LOAD_ALL_MESSAGES_BASE_HQL, ["userId": userInstance.id])
    return result[0]
  }
}
