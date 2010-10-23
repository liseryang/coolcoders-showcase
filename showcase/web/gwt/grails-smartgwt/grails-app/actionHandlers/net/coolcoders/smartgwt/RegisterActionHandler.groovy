package net.coolcoders.smartgwt

import net.coolcoders.showcase.AppUser
import net.coolcoders.showcase.Category
import net.coolcoders.showcase.client.RegisterAction
import net.coolcoders.showcase.client.RegisterResponse

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class RegisterActionHandler {

  def messageSource

  RegisterResponse execute(RegisterAction action) {
    log.info("RegisterHandler.execute( ${action.toString()} )")
    AppUser theUser = new AppUser(action.properties)
    action.categoryIds.each {
      Category category = Category.get(it)
      if (category) {
        theUser.addToCategories(category)
      }
    }
    RegisterResponse response = new RegisterResponse()
    response.setSuccessful(true)
    if (!(theUser.validate() && theUser.save())) {
      response.setSuccessful(false)
      theUser.errors.each {
        log.info it
        response.getErrors().put(it.fieldError.field, it.fieldError.code)
      }
    }
    else {
      response.setUserId theUser.id
    }
    log.info "${theUser}"
    return response
  }
}
