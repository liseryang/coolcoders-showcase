/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web;

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

    private Message message;

    public List<Message> getMessages() {
        List<Message> messages = messageService.listAll();
        return messages;
    }

    public Message getMessage() {
        if(message == null) {
            message = new Message();
            message.setCreated(new Date());
            message.setAuthor(sessionBean.getCurrentUser());
        }
        return message;
    }

    public String saveMessage() {
        messageService.persist(message);
        return null;
    }
}