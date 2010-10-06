package net.coolcoders.showcase

class LoginController {

  def userService

  def index = {
  }

  def login = {
    def username = params.username
    def password = params.password
    def loginData = userService.login(username, password)
    if (loginData.success) {
      session.setAttribute("userId", loginData.user.id)
      redirect(controller: 'showcase', action: 'index')
      return
    }
    redirect action: 'index', params: [error: true]
  }
}
