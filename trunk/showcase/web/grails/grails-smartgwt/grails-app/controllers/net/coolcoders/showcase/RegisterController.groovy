package net.coolcoders.showcase

class RegisterController {

  def index = { }

  def register = { RegisterCommand registerCommand ->
    log.info("Register: ${registerCommand}")
    if (registerCommand.hasErrors()) {
      registerCommand.errors.each {
        log.info it
      }
      return
    }
    AppUser user = new AppUser()
    user.properties = registerCommand.properties
    Category.findAll().each {
      if (params["ctaegory_${it.id}"]) {
        user.addToCategories(it)
      }
    }
    user.save(failOnError: true, flush: true)
  }
}

class RegisterCommand {
  String username
  String password
  String fullname
  String email
  Gender gender
  Date birthday

  static constraints = {
    username(blank: false)
    password(blank: false)
    fullname(blank: false)
    email(blank: false, email: true)
    gender(nullable: false)
    birthday(nullable: false)
  }

  def String toString() {
    return "${username} ${fullname} ${email}";
  }


}
