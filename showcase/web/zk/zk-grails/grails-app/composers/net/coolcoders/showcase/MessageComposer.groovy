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
  def noMessagesErrorLabel

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
    if (totalSize) {
      noMessagesErrorLabel.visible = false
      messageVBox.visible = true
      def childElements = []
      childElements.addAll messageVBox.getChildren()
      Message.withTransaction {
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
      }

      togglePagingVisibility()
    }
    else {
      messageVBox.visible = false
      nextButton.visible = false
      prevButton.visible = false
      if (currentUser.following) {
        noMessagesErrorLabel.value = "Your friends are very layzy and have not written a single post yet!"
      }
      else {
        noMessagesErrorLabel.value = "Currently you have no friends!"
      }
    }

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
    if (currentOffset > totalSize) {
      currentOffset = totalSize - pageSize
    }
    redraw()
  }

  def onClick_prevButton(MouseEvent me) {
    currentOffset = currentOffset - pageSize
    if (currentOffset < 0) {
      currentOffset = 0
    }
    redraw()
  }

  def onClick_createNewPostButton(MouseEvent me) {
    Message messageInstance = new Message()
    AppUser currentUser = AppUser.get(Executions.getCurrent().getSession().getAt("currentUser").id)
    messageInstance.creator = currentUser
    messageInstance.content = messageText.value
    if (messageInstance.validate()) {
      messageInstance.save(flush: true)
      messageText.value = ""
      currentOffset = 0
      redraw()
    }

  }

}
