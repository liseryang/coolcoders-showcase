package net.coolcoders.showcase

import org.zkoss.zkgrails.*
import org.zkoss.zk.ui.Executions
import org.zkoss.zk.ui.event.ForwardEvent

class FollowingComposer extends GrailsComposer {

  def userService

  def lstFollowing
  def pagFollowing


  def afterCompose = { window ->
    AppUser currentUser = AppUser.get(Executions.getCurrent().getSession().getAt("currentUser").id)
    pagFollowing.totalSize = currentUser.following.size()
    redraw()
  }

  def onPaging_pagFollowing(ForwardEvent fe) {
    def e = fe.origin
    redraw(e.activePage)
  }

  def redraw(page = 0) {
    AppUser currentUser = AppUser.get(Executions.getCurrent().getSession().getAt("currentUser").id)
    UserSortAttribute sortAttribute = UserSortAttribute.USERNAME
    SortOrder sortOrder = SortOrder.ASC
    int max = pagFollowing.pageSize
    int offset = page * pagFollowing.pageSize
    def followingUsers = userService.findAllFollwingUsers(currentUser, sortAttribute, sortOrder, max, offset)

    lstFollowing.clear()
    lstFollowing.append {
      followingUsers.each { userInstance ->
        listitem(value: userInstance) {
          listcell(label: userInstance.username)
          listcell(label: userInstance.messages.size())
          listcell(label: "Follow!")
        }
      }
    }
  }
}
