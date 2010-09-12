package net.coolcoders.showcase

class Message {

  String content
  Date created = new Date()


  static belongsTo = [user: User]

  static constraints = {
    content(blank: false, maxSize: 140)
  }

  String toString() {
    "${user?.username} ($created): $content"
  }

}
