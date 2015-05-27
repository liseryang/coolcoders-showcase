# User stories #
  * [Search user](UserStorySearchUser.md)
  * [Add new friend](UserStoryAddNewFriend.md)

# Layout #

## Header ##
  * Cool coders logo (left aligned)
  * Link to [Edit profile](PageEditProfile.md) with username of current user (right aligned)
  * Link to [Logout](UserStoryLogout.md) (right aligned)

## Bread crumb ##

## Body ##

### Search form ###
  1. Label "Search by username:"
  1. Textfield for search string
  1. Button "Search" to start the search

### Search result ###
  1. Table with with search result entries
    1. Column "Username"
    1. Column "Last post" (Date of newest post)
    1. Column with link "Follow" which triggers the user stors [Add new friend](UserStoryAddNewFriend.md)

# Examples #

## Grails plain ##

![http://coolcoders-showcase.googlecode.com/svn/wiki/images/showcase_searchfriends_v1.png](http://coolcoders-showcase.googlecode.com/svn/wiki/images/showcase_searchfriends_v1.png)