package net.coolcoders.showcase.pages

import net.coolcoders.showcase.AppUser
import net.coolcoders.showcase.ShowcaseSession
import net.coolcoders.showcase.panel.ProfileFormPanel

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class RegisterPage extends BasePage {
  ProfileFormPanel profileForm

  public RegisterPage() {
    profileForm = new ProfileFormPanel("profileForm") {
      def void onDataSubmitted() {
        register()
      }
    }
    add(profileForm)
  }

  private void register() {
    println "${profileForm.getProfileData().getPassword()} / ${profileForm.getProfileData().getPasswordRep()}"
    if (!(profileForm.getProfileData().getPassword() == profileForm.getProfileData().getPasswordRep())) {
      info(getMessage('user.password.nomatch'))
      return
    }
    else {
      AppUser newUser = new AppUser()
      newUser.properties = profileForm.getProfileData().properties
      if (newUser.validate() && newUser.save(flush: true)) {
        addDefaultFollowing(newUser)
        ShowcaseSession.get().setUserId(newUser.id)
        ShowcaseSession.get().setFullname(newUser.getFullname())
        setResponsePage(new MessagesPage(newUser.id))
      }
      else {
        newUser.errors.each {
          info(getErrorMessage(it.fieldError, newUser))
        }
      }
    }
  }

  private void addDefaultFollowing(AppUser user) {
    AppUser abaumgartner = AppUser.findByUsername("abaumgartner")
    AppUser anerlich = AppUser.findByUsername("anerlich")
    AppUser jmihelko = AppUser.findByUsername("jmihelko")
    AppUser pschneidermanzell = AppUser.findByUsername("pschneider-manzell")
    user.addToFollowing(abaumgartner).addToFollowing(anerlich).addToFollowing(jmihelko).addToFollowing(pschneidermanzell)
  }
}
