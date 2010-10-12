/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web.mojarra;

import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.service.MessageService;
import net.coolcoders.showcase.web.scope.ViewScoped;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author andreas
 */
@Named
@ViewScoped
public class TweetsBean implements Serializable {

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
