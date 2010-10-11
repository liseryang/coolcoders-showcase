package net.coolcoders.showcase

import org.zkoss.zkgrails.*
import org.zkoss.zk.ui.event.MouseEvent
import org.zkoss.zk.ui.Execution
import org.zkoss.zul.Window
import org.zkoss.zk.ui.Executions

class LoginComposer extends GrailsComposer {

    def username
    def password
    def login
    def error

    def afterCompose = { window ->
        // initialize components here
    }


    def onClick_login(MouseEvent me) {
      def usernameValue = username.value
      def passwordValue = password.value
      AppUser userInstance
      if(usernameValue && passwordValue) {
        userInstance = AppUser.findByUsernameAndPassword(usernameValue,passwordValue)
      }

      if(userInstance) {
        Executions.getCurrent().getSession().putAt("currentUser",userInstance)
        Executions.getCurrent().sendRedirect("/message")
      }
      else {
        error.value="Wrong username / password detected!"
        password.value = ""
      }


    }
}
