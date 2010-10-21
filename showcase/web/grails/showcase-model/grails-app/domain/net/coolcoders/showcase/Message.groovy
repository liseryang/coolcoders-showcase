package net.coolcoders.showcase

class Message implements Serializable  {

  String id
  String content
  Date created = new Date()


  static belongsTo = [creator: AppUser]

  static constraints = {
    content(blank: false, maxSize: 140)
  }

  String toString() {
    "${creator?.username} ($created): $content"
  }

  static mapping = {
    id generator: 'uuid'
  }

}
