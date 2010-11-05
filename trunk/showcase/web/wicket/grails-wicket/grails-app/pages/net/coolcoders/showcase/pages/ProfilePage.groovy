package net.coolcoders.showcase.pages

import net.coolcoders.showcase.AppUser
import net.coolcoders.showcase.ShowcaseSession
import net.coolcoders.showcase.panel.ProfileFormPanel

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class ProfilePage extends BasePage {
  private ProfileFormPanel profileForm;

  public ProfilePage() {
    profileForm = new ProfileFormPanel("profileForm") {
      def void onDataSubmitted() {
        update();
      }
    }
    AppUser theUser = AppUser.get(ShowcaseSession.get().getUserId())
    profileForm.setUser(theUser)
    add(profileForm)
  }

  private void update() {
    println "${profileForm.getProfileData()}"
  }
}
