package net.coolcoders

import net.coolcoders.showcase.Message
import javax.faces.context.FacesContext
import javax.faces.application.FacesMessage
import net.coolcoders.showcase.AppUser

class MessagesBean {

    //Leave scope commented for Application Scope
    def static scope = 'view'

    def messageService

    def userService

    def sessionBean

    def currentMessage = new Message()

    int firstPage = 0;

    int stepSize = 5;

    //['view','request','session','flash','globalSession' (portlet),'conversation'(swf),'flow'(swf)]

    // def property

    void init() {
        if(!sessionBean.currentUser){
            redirect "/login"
        }
    }

    def saveMessage() {
        currentMessage.setCreated(new Date());
        currentMessage.creator = sessionBean.currentUser
        currentMessage.save()
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sent message:", currentMessage.getContent()));
        currentMessage = new Message();
        return null;
    }

    def getMessageCount() {
        int messageCount = 0
        if (sessionBean.getCurrentUser() != null) {
            messageCount = messageService.countAllMessagesOfFollowing(sessionBean.getCurrentUser());
        }
        return messageCount;
    }

    def getUserFollowsSomebody() {
        if (sessionBean.currentUser) {
            AppUser currentUser = AppUser.get(sessionBean.currentUser.id)
            return currentUser.following.size() > 0
        }
        return false

    }

    def getMessages() {
        def messages = []
        if (sessionBean.getCurrentUser() != null) {
            messages = messageService.findAllMessagesOfFollowing(sessionBean.getCurrentUser(),
                    firstPage * stepSize,
                    stepSize);
        }
        return messages;
    }

    def nextMessages() {
        nextPage();
        return null;
    }

    def prevMessages() {
        prevPage();
        return null;
    }


    public void nextPage() {
        firstPage += stepSize;
    }

    public void prevPage() {
        if (firstPage - stepSize <= 0) {
            firstPage = 0;
        } else {
            firstPage -= stepSize;
        }
    }

    def getRenderNext() {
        return messageCount > firstPage + stepSize;
    }

    def getRenderPrev() {
        return firstPage > 0;
    }

}
