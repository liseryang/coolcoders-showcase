package net.coolcoders.showcase.smartgwt

import net.coolcoders.showcase.AppUser
import net.coolcoders.showcase.Message

class MessageService {

  static transactional = true

  def findAllMessagesOfFollowing(AppUser user, int offset, int pageSize) {
    log.info "findAllMessagesOfFollowing( appUser=${user}, offset=${offset}, pageSize=${pageSize} )"
    def criteria = Message.createCriteria()
    def messages = criteria.list(max: pageSize, offset: offset) {
      'in'('creator', user.following)
      order('created', 'asc')
    }
    messages
  }
}
