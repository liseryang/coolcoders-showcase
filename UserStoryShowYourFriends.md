# Overview #

## Corresponding pages ##
  * [Show friends](PageShowFriends.md)

# Details #

## Normal Flow ##
  1. Detect the attribute the user list should be sorted by
    * If no sort attribute can be detected, set `username` as sort attribute
  1. Detect the current sort order
    * If no sort order can be found, set sort order to `username`
  1. Detect the current page size
    * If no page size can be found, set page size to 10
  1. Detect the current offset
    * If no `offset` can be found, set `offset = 0`
    * If `offset<0`, set `offset = 0`
  1. Load all [users](DomainModelUser.md) the current user is follwing according to sort attribute, sort order, pagesize and offset
  1. Display following attributes for each user:
    1. Username
    1. Count of all messages written by this user
    1. Button to Unfollow the user
  1. A click on the header of the table should
    1. Change the sort order, if the current attribute was already selected
    1. Change the sprt attribute to the corresponding attribute and set the sort order to "asc"

## Alternative flows ##