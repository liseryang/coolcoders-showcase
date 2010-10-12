package net.coolcoders.showcase

import org.zkoss.zkgrails.*
import org.zkoss.zk.ui.event.MouseEvent

class FindfriendsComposer extends GrailsComposer {

  def userService

  def searchText
  def searchButton
  def lstUsers

  def afterCompose = { window ->
    redraw()
  }

  def redraw() {
    def resultList = []
    if(searchText.value) {
      resultList = userService.searchForUsers(searchText.value)
    }

    lstUsers.clear()
    lstUsers.append {
      resultList.each { userInstance ->
        listitem(value: userInstance) {
          listcell(label: userInstance.username)
          listcell(label: userInstance.messages.size())
          listcell{
            a(href:"/user/follow/${userInstance.id}",label:"Follow")
          }
        }
      }
    }
  }

  def onClick_searchButton(MouseEvent me) {
      redraw()
  }
}
