package net.coolcoders.showcase.panel

import net.coolcoders.showcase.ShowcaseSession
import net.coolcoders.showcase.pages.RegisterPage
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.panel.Panel

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class RegisterPanel extends Panel {

  public RegisterPanel(String id) {
    super(id)
    add(new Link("registerLink") {
      void onClick() {
        setResponsePage(RegisterPage.class)
      }
    })
  }

  def boolean isVisible() {
    return !ShowcaseSession.get().isLoggedIn();
  }
}
