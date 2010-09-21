/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.MessageDaoBean;
import net.coolcoders.showcase.model.Message;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author andreas
 */
@Stateless
public class MessageServiceBean extends AbstractGenericService<Message, Long> {

    @EJB
    private MessageDaoBean messageDaoBean ;

    public MessageServiceBean() {
    }

    @PostConstruct
    public void init() {
        abstractGenericDao = messageDaoBean;
    }

}
