package net.coolcoders.showcase

class User {

  String username
  String fullname
  String password
  String email
  Gender gender = Gender.MALE
  Date birthday


  static belongsTo = Category
  static hasMany = [categories: Category, messages: Message,following:User]

  static constraints = {
    fullname(nullable:true)
    birthday(nullable:true)
    username(blank: false, unique: true, maxSize: 32)
    password(blank: false,matches:'^.*(?=.{6,})(?=.*\\d)(?=.*[a-zA-Z]).*$')
    email(blank: false, email: true, unique: true) 
  }

  public String toString() {
    "username: $username, email: $email"
  }
}
