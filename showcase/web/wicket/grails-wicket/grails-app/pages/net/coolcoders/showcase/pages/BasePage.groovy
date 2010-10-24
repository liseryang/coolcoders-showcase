package net.coolcoders.showcase.pages

import net.coolcoders.showcase.panel.RegisterPanel
import net.coolcoders.showcase.panel.ShowcaseFeedbackPanel
import net.coolcoders.showcase.panel.UserInfoPanel
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.model.Model
import org.apache.wicket.model.StringResourceModel
import org.springframework.validation.FieldError

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class BasePage extends WebPage {

  public BasePage() {
    add(new UserInfoPanel("userInfo"))
    add(new RegisterPanel("register"))
    add(new ShowcaseFeedbackPanel("feedback"))
  }

  public String getMessage(String resourceKey) {
    return new StringResourceModel(resourceKey, this, null).getString()
  }

  public String getErrorMessage(FieldError error) {
    new StringResourceModel('${objectName}.${field}.${code}', this, new Model(error)).getString()
  }

  public String getErrorMessage(FieldError error, Object... params) {
    new StringResourceModel('${objectName}.${field}.${code}', this, new Model(error), params).getString()
  }
}
