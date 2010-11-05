package net.coolcoders.showcase.panel

import net.coolcoders.showcase.ShowcaseSession
import net.coolcoders.showcase.pages.HomePage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.panel.Panel
import net.coolcoders.showcase.pages.ProfilePage

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class UserInfoPanel extends Panel {

  public UserInfoPanel(String id) {
    super(id)
    Link profileLink = new Link("profileLink") {
      void onClick() {
        setResponsePage(ProfilePage.class)
      }
    }
    profileLink.add(new Label("user_fullname", ShowcaseSession.get().getFullname()))
    add(profileLink)
    add(new Link("logoutLink") {
      void onClick() {
        ShowcaseSession.get().invalidateNow()
        setResponsePage(HomePage.class)
      }
    })
  }

  def boolean isVisible() {
    return ShowcaseSession.get().isLoggedIn();
  }
}
