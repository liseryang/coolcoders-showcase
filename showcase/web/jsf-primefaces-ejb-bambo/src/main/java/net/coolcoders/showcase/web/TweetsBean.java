/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web;

import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.service.MessageServiceBean;
import net.coolcoders.showcase.service.UserServiceBean;

import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author andreas
 */
@Named
@RequestScoped
public class TweetsBean {

    @Inject
    private MessageServiceBean messageServiceBean;

    @Inject
    private UserServiceBean userServiceBean;

    @Inject
    private SessionBean sessionBean;

    private Message message;

    private Message selectedMessage;

    public List<Message> getMessages() {
        List<Message> messages = messageServiceBean.find(new HashMap());
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
        messageServiceBean.persist(message);
        return null;
    }

    public SelectItem[] getAuthorOptions() {
        List<User> users = userServiceBean.getAll();
        List<SelectItem> items = new ArrayList<SelectItem>();
        items.add(new SelectItem(null, ""));
        for (User user : users) {
            items.add(new SelectItem(user, user.getUsername()));
        }
        return items.toArray(new SelectItem[items.size()]);
    }

    public Message getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(Message selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public String deleteSelectedMessage() {
        if(selectedMessage != null)  {
            messageServiceBean.remove(selectedMessage);
            selectedMessage = null;
        }
        return null;
    }
}
