# Overview #

## Corresponding pages ##
  * [Show messages](PageShowMessages.md)

# Details #

## Normal Flow ##
  1. User enters a message and clicks "Send"
  1. If all constraints of the [message](DomainModelMessage.md) are valid, create a new message and ad the message to `currentUser.messages`
  1. Reload the [Show message page](PageShowMessages.md)

## Alternative flows ##

### At least 1 contraint is not valid ###
  1. Redisplay the [Show message page](PageShowMessages.md)
  1. Redisplay the provided message
  1. Show an error message for the validation error to the user