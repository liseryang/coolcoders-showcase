package net.coolcoders.showcase.pages

import net.coolcoders.showcase.Gender
import net.coolcoders.showcase.RegisterData
import org.apache.wicket.extensions.markup.html.form.DateTextField
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.PasswordTextField
import org.apache.wicket.markup.html.form.RequiredTextField
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.model.PropertyModel

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class RegisterPage extends BasePage {
  private RegisterData registerData = new RegisterData()

  public RegisterPage() {
    CompoundPropertyModel<RegisterCommand> formModel = new CompoundPropertyModel<RegisterCommand>(registerData)
    Form<RegisterData> form = new Form<RegisterData>("registerForm", formModel)
    form.add(new RequiredTextField<String>("username"))
    form.add(new RequiredTextField<String>("email"))
    form.add(new RequiredTextField<String>("fullname"))
    form.add(new PasswordTextField<String>("password"))
    form.add(new PasswordTextField<String>("passwordRep"))
    println("Birthday: " + registerData.getBirthday())
    form.add(new DateTextField("birthday"));
    add(form);
  }

  class RegisterCommand implements Serializable {
    private String username
    private String fullname
    private String email
    private String password
    private String passwordRep
    private Gender gender
    private Date birthday = new Date()

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getFullname() {
      return fullname;
    }

    public void setFullname(String fullname) {
      this.fullname = fullname;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }

    public String getPasswordRep() {
      return passwordRep;
    }

    public void setPasswordRep(String passwordRep) {
      this.passwordRep = passwordRep;
    }

    public Gender getGender() {
      return gender;
    }

    public void setGender(Gender gender) {
      this.gender = gender;
    }

    public Date getBirthday() {
      return birthday;
    }

    public void setBirthday(Date birthday) {
      this.birthday = birthday;
    }
  }
}
