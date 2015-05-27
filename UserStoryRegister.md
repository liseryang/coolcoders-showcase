# Overview #

## Corresponding pages ##
  * [Register](PageRegister.md)

# Details #

## Normal Flow ##

  1. User provides following informations:
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
    1. Special validation: Password must match transient attribute Repassword
  1. If all attributes provided are valid, a new User is created with the given informations
  1. User is redirected to the login page

## Alternative flows ##

### At least one attribute does not pass validation ###

  1. Redisplay register page with all informations provided by the user
  1. Display an error message for each failed contraint