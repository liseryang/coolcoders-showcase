package net.coolcoders.showcase.panel

import net.coolcoders.showcase.AppUser
import net.coolcoders.showcase.Category
import net.coolcoders.showcase.Gender
import net.coolcoders.showcase.pages.HomePage
import net.coolcoders.showcase.pages.MessagesPage
import org.apache.wicket.extensions.markup.html.form.DateTextField
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.model.CompoundPropertyModel
import org.apache.wicket.model.Model
import org.apache.wicket.markup.html.form.*

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
abstract class ProfileFormPanel extends Panel {
  private ProfileData profileData = new ProfileData();
  private WebMarkupContainer pwdContainer = new WebMarkupContainer("pwdContainer") {

    @Override
    def boolean isVisible() {
      return getUser() == null
    }

  }
  private AppUser appUser;

  public ProfileFormPanel(String id) {
    super(id)
    CompoundPropertyModel<ProfileData> formModel = new CompoundPropertyModel<ProfileData>(profileData)
    Form<ProfileData> form = new Form<ProfileData>("registerForm", formModel) {
      @Override
      protected void onSubmit() {
        onDataSubmitted()
      }
    }
    form.add(new RequiredTextField<String>("username"))
    form.add(new RequiredTextField<String>("email"))
    form.add(new RequiredTextField<String>("fullname"))
    pwdContainer.add(new PasswordTextField("password"))
    pwdContainer.add(new PasswordTextField("passwordRep"))
    form.add(pwdContainer)
    RadioGroup<Gender> radioGroup = new RadioGroup<Gender>("gender")
    radioGroup.add(new Radio<Gender>("male", new Model(Gender.MALE)))
    radioGroup.add(new Radio<Gender>("female", new Model(Gender.FEMALE)))
    radioGroup.setRequired(true)
    form.add(radioGroup)
    form.add(new DateTextField("birthday"))
    CheckGroup<Category> categoryGroup = new CheckGroup<Category>("categories", profileData.getCategories())
    ListView<Category> categoryList = new ListView<Category>("categoryList", Category.findAll()) {
      protected void populateItem(ListItem<Category> item) {
        Category category = item.getModelObject();
        item.add(new Label("categoryName", category.name))
        item.add(new Check("category", item.getModel()))
      }
    }
    categoryGroup.add(categoryList)
    form.add(categoryGroup)
    Button cancelButton = new Button("cancelButton") {
      def void onSubmit() {
        if (getUser() != null) {
          setResponsePage(MessagesPage.class)
        }
        else {
          setResponsePage(HomePage.class)
        }
      }
    }
    cancelButton.setDefaultFormProcessing(false)
    form.add(cancelButton)
    form.add(new Button("registerButton") {
      @Override
      def boolean isVisible() {
        return getUser() == null
      }
    })
    form.add(new Button("updateButton") {
      @Override
      def boolean isVisible() {
        return getUser() != null
      }
    })
    add(form)
  }

  public final ProfileData getProfileData() {
    return profileData
  }

  public final void setUser(AppUser user) {
    this.appUser = user
    profileData.setUsername(appUser.username)
    profileData.setFullname(appUser.fullname)
    profileData.setEmail(appUser.email)
    profileData.setGender(appUser.gender)
    profileData.setBirthday(appUser.birthday)
    profileData.setCategories(appUser.categories.asList())
  }

  private AppUser getUser() {
    return appUser
  }

  public void onDataSubmitted() {}


  class ProfileData implements Serializable {
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
