/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web.primefaces;

import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.service.MessageService;
import net.coolcoders.showcase.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

/**
 * @author andreas
 */
@Named
@RequestScoped
public class TweetsBean {

    @EJB
    private MessageService messageService;

    @EJB
    private UserService userService;

    @Inject
    private SessionBean sessionBean;

    @Inject
    private TweetsSessionBean tweetsSessionBean;

    private Message message;

    private List<Message> messages;

    public Long getFriendsCount() {
        Long count = 0L;
        if (sessionBean.getCurrentUser() != null) {
            count = userService.count(sessionBean.getCurrentUser().getId());
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
        message = null;
        return null;
    }

    public Long getMessageCount() {
        Long count = 0L;
        if (sessionBean.getCurrentUser() != null) {
            count = messageService.count(sessionBean.getCurrentUser().getId());
            tweetsSessionBean.setMessageCount(count);
        }
        return count;
    }

    public List<Message> getMessages() {
        if (sessionBean.getCurrentUser() != null && messages == null) {
            messages = messageService.list(sessionBean.getCurrentUser().getId(),
                    tweetsSessionBean.getFirstPage(),
                    tweetsSessionBean.getStepSize());
        }
        return messages;
    }

    public String nextMessages() {
        tweetsSessionBean.nextPage();
        messages = null;
        return null;
    }

    public String prevMessages() {
        tweetsSessionBean.prevPage();
        messages = null;
        return null;
    }

    public String showUsersYouFollow() {
        return "showUsersYouFollow";
    }
}
