/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web;

import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.service.MessageServiceBean;

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
    private MessageServiceBean messageServiceBean;

    @Inject
    private SessionBean sessionBean;

    private Message message;

    public List<Message> getMessages() {
        List<Message> messages = messageServiceBean.get();
        return messages;
    }

    public Message getMessage() {
        if(message == null) {
            message = new Message();
            message.setAuthor(sessionBean.getCurrentUser());
        }
        return message;
    }

    public String saveMessage() {
        message.setCreated(new Date());
        messageServiceBean.persist(message);
        message = null;
        return null;
    }
}
