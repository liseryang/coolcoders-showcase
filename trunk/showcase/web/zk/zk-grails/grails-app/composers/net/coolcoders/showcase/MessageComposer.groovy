package net.coolcoders.showcase

import org.zkoss.zkgrails.*
import org.zkoss.zk.ui.Executions
import java.text.SimpleDateFormat
import java.text.DateFormat
import org.zkoss.zk.ui.event.MouseEvent

class MessageComposer extends GrailsComposer {

  def messageService
  DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);


  def messageVBox
  def messageText
  def createNewPostButton
  def nextButton
  def prevButton

  int currentOffset = 0
  int pageSize = 10
  int totalSize = -1



  def afterCompose = { window ->
    AppUser currentUser = AppUser.get(Executions.getCurrent().getSession().getAt("currentUser").id)
    totalSize = messageService.countAllMessagesOfFollowing(currentUser)
    redraw()
  }

  def redraw() {
    AppUser currentUser = AppUser.get(Executions.getCurrent().getSession().getAt("currentUser").id)

    def childElements = []
    childElements.addAll messageVBox.getChildren()
    def messageInstanceList = messageService.findAllMessagesOfFollowing(currentUser, currentOffset, pageSize)
    childElements.each {
      messageVBox.removeChild(it)
    }
    messageVBox.append {
      messageInstanceList.each {messageInstance ->
        div(class: "messageEntry clearfix") {
          div(class: "messageDate") {
            label(value: df.format(messageInstance.created))
          }
          div(class: "messageUser") {
            label(value: messageInstance.creator.username)
          }
          div(class: "messageContent") {
            label(value: messageInstance.content)
          }
        }
      }
    }
    togglePagingVisibility()
  }

  def togglePagingVisibility() {
    if (currentOffset < pageSize) {
      prevButton.visible = false
    }
    else {
      prevButton.visible = true
    }
    
    if (currentOffset + pageSize > totalSize) {
      nextButton.visible = false
    }
    else {
      nextButton.visible = true
    }
  }

  def onClick_nextButton(MouseEvent me) {
    currentOffset = currentOffset + pageSize
    if(currentOffset>totalSize) {
      currentOffset = totalSize - pageSize
    }
    redraw(currentOffset)
  }

  def onClick_prevButton(MouseEvent me) {
    currentOffset = currentOffset - pageSize
    if(currentOffset<0) {
      currentOffset = 0
    }
    redraw()
  }

}
