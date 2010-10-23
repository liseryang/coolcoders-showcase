package net.coolcoders.showcase

class MessageService {

  static transactional = true

  def findAllMessagesOfFollowing(String userId, int offset, int pageSize) {
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
}
