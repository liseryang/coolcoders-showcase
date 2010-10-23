package net.coolcoders.showcase.pages

import net.coolcoders.showcase.AppUser
import net.coolcoders.showcase.ShowcaseSession
import org.apache.log4j.Logger
import org.apache.wicket.PageParameters
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.PasswordTextField
import org.apache.wicket.markup.html.form.RequiredTextField
import org.apache.wicket.model.CompoundPropertyModel

/**
 * Homepage
 */
public class HomePage extends BasePage {
  private static final long serialVersionUID = 1L;
  protected static Logger logger = Logger.getLogger(getClass());
  private String username
  private String password

  public String getUsername() {
    return username
  }

  public void setUname(String username) {
    this.username = username
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Constructor that is invoked when page is invoked without a session.
   *
   * @param parameters
   *            Page parameters
   */
  public HomePage(final PageParameters parameters) {
    CompoundPropertyModel formModel = new CompoundPropertyModel(this)
    Form form = new Form("loginForm", formModel) {
      protected void onSubmit() {
        AppUser theUser = AppUser.findByUsername(getUsername())
        if (theUser && theUser.password == getPassword()) {
          ShowcaseSession.get().setUserId(theUser.getId())
          ShowcaseSession.get().setFullname(theUser.getFullname())
          setResponsePage(new MessagesPage(theUser.id))
        }
        else {
          info("Login failed !")
        }
      }
    };
    form.add(new RequiredTextField<String>("username"))
    form.add(new PasswordTextField<String>("password"))
    add(form)
  }
}
