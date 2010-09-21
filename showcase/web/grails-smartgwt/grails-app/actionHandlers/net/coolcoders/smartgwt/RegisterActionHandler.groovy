package net.coolcoders.smartgwt

import net.coolcoders.showcase.grails.smartgwt.Gender
import net.coolcoders.showcase.grails.smartgwt.User
import net.coolcoders.smartgwt.client.RegisterAction
import net.coolcoders.smartgwt.client.RegisterResponse

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class RegisterActionHandler {
  RegisterResponse execute(RegisterAction action) {
    log.info("RegisterActionHandler.execute(" + action + ")")
    def newUser = new User(
            username: action.username,
            fullname: action.fullname,
            email: action.email,
            gender: Gender.valueOf(action.gender),
            birthday: action.birthday
    )
    action.categoryIds.each {
      newUser.addToCategories(Category.get(it))
    }
    if (!newUser.validate() && newUser.save()) {
      RegisterResponse registerResponse = new RegisterResponse(successful: false);
      newUser.errors.each {
        registerResponse.errors.put(it.field, it.codes[0])
      }
    }
    return new RegisterResponse(successful: true);
  }
}
