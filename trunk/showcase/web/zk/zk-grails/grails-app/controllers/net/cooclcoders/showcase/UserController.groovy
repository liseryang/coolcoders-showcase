package net.cooclcoders.showcase

import net.coolcoders.showcase.AppUser

class UserController {

    def following = {    }

    def search = {  
      
    }

  def follow = {
    def userToFollow = AppUser.get(params.id)
    def currentUser = AppUser.get(session.currentUser.id)
    currentUser.addToFollowing(userToFollow)
    flash.message = g.message(code: 'user.follow.executed', args: [userToFollow.username])
    redirect(action: "following")
  }

  def unfollow = {
    def userToUnfollow = AppUser.get(params.id)
    def currentUser = AppUser.get(session.currentUser.id)
    currentUser.removeFromFollowing(userToUnfollow)
    flash.message = g.message(code: 'user.unfollow.executed', args: [userToUnfollow.username])
    redirect(action: "following")
  }
}
