# Overview #

## Corresponding pages ##
  * [Edit profile](PageEditProfile.md)

# Details #

## Normal Flow ##

  1. User can change one of the following informations:
    * Username
    * Password
    * Repassword
    * Fullname
    * Email
    * Gender
    * Birthday
    * Set of categories
  1. User submits the form
  1. All attributes are validated according to the constraints (See [here](DomainModelUser.md) for more details)
  1. If all attributes provided are valid, informations of the current user are updated according to the provided informations
  1. User is redirected to the [show message page](PageShowMessages.md)

## Alternative flows ##

### At least one attribute does not pass validation ###

  1. Redisplay [edit profile page](PageEditProfile.md) with all informations provided by the user
  1. Display an error message for each failed contraint