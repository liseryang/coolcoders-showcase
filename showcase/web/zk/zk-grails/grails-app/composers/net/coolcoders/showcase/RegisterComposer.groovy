package net.coolcoders.showcase

import org.zkoss.zkgrails.*
import org.zkoss.zkplus.databind.DataBinder
import org.zkoss.zk.ui.event.MouseEvent
import org.zkoss.zk.ui.Executions

class RegisterComposer extends GrailsComposer {

  DataBinder binder = new DataBinder()
  String beanBindingName = "newUser"

  def username
  def fullname
  def email
  def gender
  def password
  def repassword
  def birthday

  def errorMessages

  def register
  

  def afterCompose = { window ->
    binder.addBinding(username,"value","${beanBindingName}.username")
    binder.addBinding(fullname,"value","${beanBindingName}.fullname")
    binder.addBinding(email,"value","${beanBindingName}.email")
    binder.addBinding(gender,"selectedItem.value","${beanBindingName}.gender")
    binder.addBinding(password,"value","${beanBindingName}.password")
    binder.addBinding(repassword,"value","${beanBindingName}.repassword")
    binder.addBinding(birthday,"value","${beanBindingName}.birthday")
  }


  def onClick_register(MouseEvent me) {
    AppUser userInstance = new AppUser()
    binder.bindBean(beanBindingName,userInstance)
    binder.saveAll()
    if(!userInstance.validate()) {
      def errorMessageString = ""
      userInstance.errors.allErrors.each { errorInstance  ->
         errorMessageString = errorMessageString +"\n"+ message(error:errorInstance)
      }
      errorMessages.value = errorMessageString
    }
    else {
      userInstance.save()
      Executions.getCurrent().sendRedirect("/")
    }
  }

}
