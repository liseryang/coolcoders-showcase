package net.coolcoders.showcase

import grails.web.JSONBuilder

class MessagesController {

  def index = {

  }

  def list = {
    def userId = params.userId
    def messages = Message.findByUser(User.get(userId));
    def builder = new JSONBuilder()

    render(builder: 'json') {
      data {
        record {
          messages.each {
            record {
              content(it.content)
              created(it.created)
            }
          }
        }
      }
    }
  }
}
