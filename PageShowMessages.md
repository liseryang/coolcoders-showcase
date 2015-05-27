# User stories #

  * [Show recent messages](UserStoryShowRecentMessages.md)
  * [Create new message](UserStoryCreateMessage.md)

# Layout #

## Header ##
  * Cool coders logo (left aligned)
  * Link to [Edit profile](PageEditProfile.md) with username of current user (right aligned)
  * Link to [Logout](UserStoryLogout.md) (right aligned)

## Bread crumb ##
  * Link to [Home](PageShowMessages.md)

## Body ##
The body contains of 2 sections:

### Create new message section ###
  1. Form with following elements:
    1. Area for validation error messages
    1. Textblock `What cool code are you hacking right now?`
    1. Textarea to enter a new message
    1. Submit button to submit the message (See [User story create message](UserStoryCreateMessage.md))

### Show all messages (Only if user has friends and messages are available) ###

  1. List of all messages, for each message a single row containing
    1. Creation date of the message
    1. Username of the user created this message
    1. The content of the message
  1. Below the list simple paging buttons:
    1. Button "Prev", if newer messages are available
    1. Button "Next", if older messages are available
  1. Bellow the list and paging elements, a link to the Page [Show Friends](PageShowFriends.md) with the Text "View your friends"

### No friends warning (Only if user has no friends) ###
  1. Box with the text content "Currently you have no friends!"

### No messages warning (Only if there are no messages to display) ###
  1. Box with the text content "Your friends are very layzy and have not written a single post yet!"

# Examples #

## Grails plain ##

![http://coolcoders-showcase.googlecode.com/svn/wiki/images/showcase_messages_v1.png](http://coolcoders-showcase.googlecode.com/svn/wiki/images/showcase_messages_v1.png)