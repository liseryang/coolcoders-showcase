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

    private int currentFirstMessage = 0;

    public List<Message> getMessages() {
        List<Message> messages = messageService.list(sessionBean.getCurrentUser().getId(), currentFirstMessage, currentFirstMessage + sessionBean.getMaxMessageCount());
        return messages;
    }



}
