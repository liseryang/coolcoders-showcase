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

    public List<Message> getMessages() {
        List<Message> messages = messageServiceBean.get();
        return messages;
    }



}
