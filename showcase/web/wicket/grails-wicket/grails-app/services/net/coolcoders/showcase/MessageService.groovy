package net.coolcoders.showcase

class MessageService {

  static transactional = true

  public List<AppUser> findAllMessagesOfFollowing(String userId, int offset, int pageSize) {
    log.debug "findAllMessagesOfFollowing( appUser=${userId}, offset=${offset}, pageSize=${pageSize} )"
    AppUser theUser = AppUser.get(userId)
    List<AppUser> following = []
    if (theUser.following) {
      following.addAll(theUser.following)
    }
    following.add(theUser)
    def criteria = Message.createCriteria()
    def messages = criteria.list(max: pageSize, offset: offset) {
      'in'('creator', following)
      order('created', 'desc')
    }
    messages
  }

  public Long numberOfAllMessagesOfFollowing(String userId) {
    log.debug("numberOfAllMessagesOfFollowing( ${userId} )")
    AppUser theUser = AppUser.get(userId)
    List<AppUser> following = []
    if (theUser.following) {
      following.addAll(theUser.following)
    }
    following.add(theUser)
    def criteria = Message.createCriteria()
    def number = criteria.get() {
      'in'('creator', following)
      projections {
        countDistinct("id")
      }
    }
    number
  }

  public Message addMessage(String userId, String newMessage) {
    log.debug("addMessage( ${userId}, ${newMessage} )")
    AppUser theUser = AppUser.get(userId)
    Message theMessage = new Message(creator: theUser, content: newMessage, created: new Date()).save()
    theMessage
  }
}
