package net.coolcoders.showcase

class LoginController {

  def index = {
    if (!session.currentUser) {
    }
    else {
      redirect(controller: "message") 
    } 
  }         

} 


