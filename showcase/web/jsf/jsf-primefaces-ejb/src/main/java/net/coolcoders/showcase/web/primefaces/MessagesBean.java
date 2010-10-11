/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web.primefaces;

import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.service.MessageService;
import net.coolcoders.showcase.service.UserService;
import net.coolcoders.showcase.web.scope.ViewScoped;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author andreas
 */
@Named
@ViewScoped
public class MessagesBean implements Serializable {

    @EJB
    private MessageService messageService;

    @EJB
    private UserService userService;

    @Inject
    private SessionBean sessionBean;

    private Message message;

    private List<Message> messages;

    private int firstPage = 0;

    private int stepSize = 5;

    private Long messageCount = 0L;

    public Long getFriendsCount() {
        Long count = 0L;
        if (sessionBean.getCurrentUser() != null) {
            count = userService.countUsersYouFollow(sessionBean.getCurrentUser().getId());
        }
        return count;
    }

    public Message getMessage() {
        if (message == null) {
            message = new Message();
            message.setAuthor(sessionBean.getCurrentUser());
            message.setContent("");
        }
        return message;
    }

    public String saveMessage() {
        message.setCreated(new Date());
        messageService.persist(message);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sent message:", message.getContent()));
        message = null;
        return null;
    }

    public Long getMessageCount() {
        if (sessionBean.getCurrentUser() != null) {
            messageCount = messageService.count(sessionBean.getCurrentUser().getId());
        }
        return messageCount;
    }

    public List<Message> getMessages() {
        if (sessionBean.getCurrentUser() != null && messages == null) {
            messages = messageService.list(sessionBean.getCurrentUser().getId(),
                    getFirstPage(),
                    getStepSize());
        }
        return messages;
    }

    public String nextMessages() {
        nextPage();
        messages = null;
        return null;
    }

    public String prevMessages() {
        prevPage();
        messages = null;
        return null;
    }

    public int getStepSize() {
        return stepSize;
    }

    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void nextPage() {
        firstPage += stepSize;
    }

    public void prevPage() {
        if(firstPage - stepSize <= 0) {
            firstPage = 0;
        } else {
            firstPage -= stepSize;
        }
    }

    public boolean getRenderNext() {
        return messageCount > firstPage + stepSize;
    }

    public boolean getRenderPrev() {
        return firstPage > 0;
    }
}
