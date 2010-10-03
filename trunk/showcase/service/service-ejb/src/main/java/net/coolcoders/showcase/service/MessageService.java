/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.MessageDao;
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
public class MessageService extends AbstractGenericService<Message, String> {

    @EJB
    private MessageDao messageDao;

    public MessageService() {
    }

    @PostConstruct
    public void init() {
        abstractGenericDao = messageDao;
    }

    public List<Message> list(String userId, int start, int max) {
        return messageDao.list(userId, start, max);
    }

    public Long count(String userId) {
        return messageDao.count(userId);
    }
}
