package net.coolcoders.showcase.grails.smartgwt

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
