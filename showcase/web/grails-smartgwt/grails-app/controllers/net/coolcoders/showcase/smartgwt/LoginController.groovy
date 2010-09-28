package net.coolcoders.showcase.smartgwt

class LoginController {

  def userService

  def index = {
  }

  def login = {
    def username = params.username
    def password = params.password
    def loginData = userService.login(username, password)
    if (loginData.success) {
      log.info "logged in !"
      redirect(controller: 'messages', action: 'index', params: [userId: loginData.user.id])
      return
    }
    redirect action: 'index', params: [error: true]
  }
}
