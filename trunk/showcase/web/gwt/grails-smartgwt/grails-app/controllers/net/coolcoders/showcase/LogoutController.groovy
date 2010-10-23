package net.coolcoders.showcase

class LogoutController {

  def index = {
    session.invalidate()
    redirect(controller: 'login')
  }
}
