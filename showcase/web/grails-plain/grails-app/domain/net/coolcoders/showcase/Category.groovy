package net.coolcoders.showcase

class Category {

  String id
  String name

  static belongsTo = User
  static hasMany = [users: User]

  static constraints = {
    name(blank: false, unique: true)
  }

  public String toString() {
    "$name"
  }

  static mapping = {
    id generator: 'uuid'
  }
}
