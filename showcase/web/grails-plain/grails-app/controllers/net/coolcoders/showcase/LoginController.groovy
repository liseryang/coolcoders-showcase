package net.coolcoders.showcase

class LoginController {

  def index = {
    if (!session.currentUser) {
      def loginCommandInstance = new LoginCommand()
      [loginCommandInstance: loginCommandInstance]
    }
    else {
      redirect(controller: "message")
    }

  }


  def login = {LoginCommand loginCommandInstance ->
    log.debug("Entered login with loginCommandInstance $loginCommandInstance")
    if (loginCommandInstance.hasErrors()) {
      render(view: "index", model: [loginCommandInstance: loginCommandInstance])
    }
    else {
      User user = User.findByUsernameAndPassword(loginCommandInstance.username, loginCommandInstance.password)
      if (user) {
        session.currentUser = user
        redirect(controller: "message")
      }
      else {
        flash.error = g.message(code: "login.failed")
        render(view: "index", model: [loginCommandInstance: loginCommandInstance])
      }
    }

  }
}


class LoginCommand {
  String username
  String password

  static constraints = {
    username(blank: false)
    password(blank: false)
  }

  String toString() {
    "username: $username, password: .........."
  }
}
