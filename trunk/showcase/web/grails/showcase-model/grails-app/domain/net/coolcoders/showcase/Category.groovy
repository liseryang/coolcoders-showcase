package net.coolcoders.showcase

class Category implements Serializable {

  String id
  String name

  static belongsTo = AppUser
  static hasMany = [users: AppUser]

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
