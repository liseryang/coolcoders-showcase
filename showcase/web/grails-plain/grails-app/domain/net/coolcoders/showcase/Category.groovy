package net.coolcoders.showcase

class Category {

  String name

  static hasMany = [users: User]

  static constraints = {
    name(blank: false, unique: true)
  }

  public String toString() {
    "$name"
  }
}
