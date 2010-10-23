package net.coolcoders.showcase.pages

import net.coolcoders.showcase.panel.RegisterPanel
import net.coolcoders.showcase.panel.ShowcaseFeedbackPanel
import net.coolcoders.showcase.panel.UserInfoPanel
import org.apache.wicket.markup.html.WebPage

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class BasePage extends WebPage {

  public BasePage() {
    add(new UserInfoPanel("userInfo"))
    add(new RegisterPanel("register"))
    add(new ShowcaseFeedbackPanel("feedback"))
  }
}
