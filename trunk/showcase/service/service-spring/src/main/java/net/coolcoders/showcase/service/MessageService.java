/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.MessageDao;
import net.coolcoders.showcase.model.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author andreas
 */
@Service
@Transactional
public class MessageService extends AbstractGenericService<Message, String> {

    @Resource
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
