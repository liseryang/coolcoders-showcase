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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

/**
 *
 * @author andreas
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class MessageServiceBean extends AbstractGenericService<Message, String> {

    @EJB
    private MessageDaoBean messageDaoBean ;

    public MessageServiceBean() {
    }

    @PostConstruct
    public void init() {
        abstractGenericDao = messageDaoBean;
    }

    public List<Message> get(String userId, int start, int max) {
        return messageDaoBean.list(userId, start, max);
    }

}
