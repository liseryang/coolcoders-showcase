package net.coolcoders.showcase.pages

import net.coolcoders.showcase.AppUser
import net.coolcoders.showcase.ShowcaseSession
import net.coolcoders.showcase.panel.ProfileFormPanel
import org.apache.wicket.spring.injection.annot.SpringBean

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class RegisterPage extends BasePage {
  @SpringBean(name = "appUserService")
  def transient appUserService
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
      def map = appUserService.registerUser(profileForm.getProfileData())
      AppUser newUser = map.user
      println"success: ${map.success}"
      if (map.success) {
        ShowcaseSession.get().setUserId(newUser.getId())
        ShowcaseSession.get().setFullname(newUser.getFullname())
        setResponsePage(MessagesPage.class)
      }
      else {
        newUser.errors.each {
          info(getErrorMessage(it.fieldError, newUser))
        }
      }
    }
  }

  private void addDefaultFollowing(AppUser user) {
  }
}
