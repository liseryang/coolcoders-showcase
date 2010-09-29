package net.coolcoders.smartgwt

import net.coolcoders.showcase.AppUser
import net.coolcoders.showcase.client.client.LoginAction
import net.coolcoders.showcase.client.client.LoginResponse

class LoginActionHandler {
  LoginResponse execute(LoginAction action) {
    println "LoginActionHandler.execute (username:${action.username} password:${action.password}"
    def theUser = AppUser.findByUsername(action.username)
    if (theUser?.password.equals(action.password)) {
      return new LoginResponse(successful: true, userId: theUser.id)
    }
    return new LoginResponse(successful: false, message: 'Username / Password wrong !');
  }
}
