package net.coolcoders.showcase

class Category {

  String name

  static belongsTo = User
  static hasMany = [users: User]

  static constraints = {
    name(blank: false, unique: true)
  }

  public String toString() {
    "$name"
  }
}
