package net.coolcoders.showcase.panel

import net.coolcoders.showcase.ShowcaseSession
import net.coolcoders.showcase.pages.HomePage
import net.coolcoders.showcase.pages.ProfilePage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.panel.Panel

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class UserInfoPanel extends Panel {

  public UserInfoPanel(String id) {
    super(id)
    Link profileLink = new BookmarkablePageLink("profileLink", ProfilePage.class)
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
