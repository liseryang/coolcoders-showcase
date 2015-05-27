# Overview #

## Corresponding pages ##
  * [Show messages](PageShowMessages.md)

# Details #

## Normal Flow ##
  1. Detect the current `offset` for the message list selected by the user.
    * If no offset can be found, set `offset` to 0
    * If `offset<0` set `offset = 0`
  1. Display the 10 newest [message](DomainModelMessage.md) created by the current user or an user the current [user](DomainModelUser.md) is following starting with number `offset`
  1. If there are more than 10 messages, display a link "Next" in order change the offset to `offset + 10`
  1. If the `offset` is bigger 0, display a link "Prev" wich loads the page again with a `offset = currentOffset -10`

## Alternative flows ##

### User follows no other user ###
  1. Display a message box with a warning text (See [Show Friends](PageShowFriends.md))

### No new messages for current user can be found ###
  1. Display a message box with a warning message  (See [Show Friends](PageShowFriends.md))