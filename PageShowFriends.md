# User stories #
  * [Show your friends](UserStoryShowYourFriends.md)
  * [Remove existing friend](UserStoryRemoveFriend.md)

# Layout #

## Header ##
  * Cool coders logo (left aligned)
  * Link to [Edit profile](PageEditProfile.md) with username of current user (right aligned)
  * Link to [Logout](UserStoryLogout.md) (right aligned)

## Bread crumb ##
  * Link "Home" to [show message page](PageShowMessages.md)
  * Link "Your friends" with link to [PageShowFriends](PageShowFriends.md)

## Body ##
  1. Table with entries of type [user](DomainModelUser.md)
    1. Sortable column "Username"
    1. Sortable column "No. of posts" (Containing the post count of the user)
    1. Column with the action [Unfollow](UserStoryRemoveFriend.md)
  1. A paging component displaying the amount of available pages, the current page and the number of all users the current user is following
  1. A link "Search new friends" to page [Search new friends](PageSearchNewFriends.md)


# Examples #

> ## Grails plain ##

![http://coolcoders-showcase.googlecode.com/svn/wiki/images/showcase_viewfriends_v1.png](http://coolcoders-showcase.googlecode.com/svn/wiki/images/showcase_viewfriends_v1.png)