/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web.primefaces;

import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.service.MessageService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Date;
import java.util.List;

/**
 *
 * @author andreas
 */
@Named
@RequestScoped
public class TweetsBean {

    @EJB
    private MessageService messageService;

    @Inject
    private SessionBean sessionBean;

    @Inject
    private TweetsSessionBean tweetsSessionBean;

    private Message message;

    private List<Message> messages;

    public Message getMessage() {
        if(message == null) {
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

    public List<Message> getMessages() {
        if(messages == null) {
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
