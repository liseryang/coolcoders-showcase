package net.coolcoders.showcase.pages

import net.coolcoders.showcase.AppUser
import net.coolcoders.showcase.ProfileData
import net.coolcoders.showcase.ShowcaseSession
import net.coolcoders.showcase.panel.ProfileFormPanel
import org.apache.wicket.spring.injection.annot.SpringBean

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class ProfilePage extends BasePage {
  @SpringBean(name = "appUserService")
  def transient appUserService
  private ProfileFormPanel profileForm;

  public ProfilePage() {
    profileForm = new ProfileFormPanel("profileForm") {
      def void onDataSubmitted() {
        update();
      }
    }
    AppUser theUser = AppUser.get(ShowcaseSession.get().getUserId())
    println "User.categories: ${theUser.categories}"
    profileForm.setUser(theUser)
    add(profileForm)
  }

  private void update() {
    ProfileData data = profileForm.getProfileData();
    String userId = ShowcaseSession.get().getUserId()
    def map = appUserService.updateUser(userId, data)
    if (map.success) {
      setResponsePage(MessagesPage.class)
    }
    else {
      AppUser theUser = map.user
      theUser.errors.each {
        info(getErrorMessage(it.fieldError, theUser))
      }
    }
  }
}
