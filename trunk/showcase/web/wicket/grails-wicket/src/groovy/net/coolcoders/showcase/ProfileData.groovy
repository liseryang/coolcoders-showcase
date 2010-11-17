package net.coolcoders.showcase

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class ProfileData implements Serializable {
  private String username;
  private String fullname;
  private String email;
  private String password;
  private String passwordRep;
  private Gender gender;
  private Date birthday = new Date();
  private List<Category> categories = new ArrayList<Category>();

  public ProfileData() {
  }

  public ProfileData(AppUser user) {
    this.username = user.username
    this.fullname = user.fullname
    this.email = user.email
    this.password = user.password
    this.gender = user.gender
    this.birthday = user.birthday
    this.categories = user.categories.asList()
  }

  public void setDataFromUser(AppUser user) {
    this.username = user.username
    this.fullname = user.fullname
    this.email = user.email
    this.password = user.password
    this.gender = user.gender
    this.birthday = user.birthday
    this.categories = user.categories?.asList()
  }

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
    return categories;
  }

  public void setCategories(List<Category> categories) {
    this.categories = categories;
  }

  public String toString() {
    return "ProfileData[username=${getUsername()} email=${getEmail()} categories=${getCategories()}]"
  }
}
