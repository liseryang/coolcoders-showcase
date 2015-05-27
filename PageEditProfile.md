# User stories #
  * [Edit profile](UserStoryEditProfile.md)

# Layout #

## Header ##
  * Cool coders logo (left aligned)
  * Link to [Edit profile](PageEditProfile.md) with username of current user (right aligned)
  * Link to [Logout](UserStoryLogout.md) (right aligned)

## Bread crumb ##
  * Link "Home" to [show message page](PageShowMessages.md)
  * Link "Your profile" with link to [PageEditProfile](PageEditProfile.md)

## Body ##
Form with following elements:
  * Error message box (Only visible if validation errors are present)
  * Label "Username" with textfield for the username
  * Label "Full name" with textfield for the full name
  * Label "Email" with textfield for the email
  * Label "Password" with password field for the password
  * Label "Repassword" with password field for the repassword
  * Label "Gender" with a radio button group for all values of Enum `Gender` (Male preselected)
    * For each value in Enum Gender display a radio button followed with the label e.g. `Gender.MALE.getI18NKey()`
  * Label "Gender" with input field for attribute birthday
  * Label "Categories"
    * For each [category](DomainModelCategory.md): 1 checkbox with the label `currentCategory.getName()`
  * Button "Update" to submit the form

# Examples #

## Grails plain ##

![http://coolcoders-showcase.googlecode.com/svn/wiki/images/showcase_editprofile_v1.png](http://coolcoders-showcase.googlecode.com/svn/wiki/images/showcase_editprofile_v1.png)