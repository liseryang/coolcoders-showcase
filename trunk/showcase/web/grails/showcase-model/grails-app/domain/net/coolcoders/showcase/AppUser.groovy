package net.coolcoders.showcase

class AppUser implements Serializable {

  String id
  String username
  String fullname
  String password
  String repassword
  String email
  Gender gender = Gender.MALE
  Date birthday


  static hasMany = [categories: net.coolcoders.showcase.Category, messages: net.coolcoders.showcase.Message, following: AppUser]

  static constraints = {
    fullname(nullable: true)
    birthday(nullable: true)
    username(blank: false, unique: true, maxSize: 32)
    password(blank: false, matches: '^.*(?=.{6,})(?=.*\\d)(?=.*[a-zA-Z]).*$')
    email(blank: false, email: true, unique: true)
  }

  static transients = ['repassword']

  public String toString() {
    "username: $username, email: $email"
  }

  static mapping = {
    id generator: 'uuid'
  }
}
