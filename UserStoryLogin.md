# Overview #

As a registered User i want to login to the system

## Corresponding pages ##
  * [Login](PageLogin.md)

# Details #

## Normal Flow ##
  1. User enters his username and his password
  1. User clicks "Login"
  1. User with this username / password combination gets logged in

## Alternative flows ##

### No  username provided ###
  1. User gets redirected to the login page
  1. A warning message "Username required!" is displayed

### No  password provided ###
  1. User gets redirected to the login page
  1. A warning message "Password required!" is displayed

### No User with matching username / password found ###
  1. User gets redirected to the login page
  1. A warning message "Login failed" is displayed