package net.coolcoders.showcase.pages

import net.coolcoders.showcase.AppUser
import net.coolcoders.showcase.Category
import net.coolcoders.showcase.Gender
import net.coolcoders.showcase.ShowcaseSession
import org.apache.wicket.extensions.markup.html.form.DateTextField
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.model.Model
import org.apache.wicket.markup.html.form.*

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class RegisterPage extends BasePage {
  private RegisterCommand registerData = new RegisterCommand()

  public RegisterPage() {
    CompoundPropertyModel<RegisterCommand> formModel = new CompoundPropertyModel<RegisterCommand>(registerData)
    Form<RegisterCommand> form = new Form<RegisterCommand>("registerForm", formModel) {
      protected void onSubmit() {
        register()
      }
    }
    form.add(new RequiredTextField<String>("username"))
    form.add(new RequiredTextField<String>("email"))
    form.add(new RequiredTextField<String>("fullname"))
    form.add(new PasswordTextField<String>("password"))
    form.add(new PasswordTextField<String>("passwordRep"))
    RadioGroup<Gender> radioGroup = new RadioGroup<Gender>("gender")
    radioGroup.add(new Radio<Gender>("male", new Model(Gender.MALE)))
    radioGroup.add(new Radio<Gender>("female", new Model(Gender.FEMALE)))
    form.add(radioGroup)
    form.add(new DateTextField("birthday"))
    CheckGroup<Category> categoryGroup = new CheckGroup<Category>("categories", registerData.getCategories())

    ListView<Category> categoryList = new ListView<Category>("categoryList", Category.findAll()) {
      protected void populateItem(ListItem<Category> item) {
        Category category = item.getModelObject();
        item.add(new Label("categoryName", category.name))
        item.add(new Check("category", item.getModel()))
      }
    }
    categoryGroup.add(categoryList)
    form.add(categoryGroup)
    add(form)
  }

  private void register() {
    println "${getRegisterData().getPassword()} / ${getRegisterData().getPasswordRep()}"
    if (!(getRegisterData().getPassword() == getRegisterData().getPasswordRep())) {
      info(getMessage('user.password.nomatch'))
      return
    }
    else {
      AppUser newUser = new AppUser()
      newUser.properties = getRegisterData().properties
      if (newUser.validate() && newUser.save(flush: true)) {
        addDefaultFollowing(newUser)
        ShowcaseSession.get().setUserId(newUser.id)
        setResponsePage(new MessagesPage(newUser.id))
      }
      else {
        newUser.errors.each {
          info(getErrorMessage(it.fieldError, newUser))
        }
      }
    }
  }

  private void addDefaultFollowing(AppUser user) {
    AppUser abaumgartner = AppUser.findByUsername("abaumgartner")
    AppUser anerlich = AppUser.findByUsername("anerlich")
    AppUser jmihelko = AppUser.findByUsername("jmihelko")
    AppUser pschneidermanzell = AppUser.findByUsername("pschneider-manzell")
    user.addToFollowing(abaumgartner).addToFollowing(anerlich).addToFollowing(jmihelko).addToFollowing(pschneidermanzell)
  }

  public RegisterCommand getRegisterData() {
    return registerData
  }

  public void setRegisterData(RegisterCommand data) {
    this.registerData = registerData
  }

  class RegisterCommand implements Serializable {
    private String username
    private String fullname
    private String email
    private String password
    private String passwordRep
    private Gender gender
    private Date birthday = new Date()
    private List<Category> categories = new ArrayList<Category>();

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

    public List<Category> getCategories() {
      return categories
    }

    public List<Category> setCategories(List<Category> categories) {
      this.categories = categories
    }
  }
}
