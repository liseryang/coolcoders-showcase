package net.cooclcoders.showcase

class LogoutController {

  def index = {
    session.invalidate()
    redirect(uri:"/")
   }

}


