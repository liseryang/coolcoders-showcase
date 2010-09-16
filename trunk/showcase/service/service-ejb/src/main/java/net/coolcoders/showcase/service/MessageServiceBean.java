/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.MessageDaoBean;
import net.coolcoders.showcase.model.Message;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

/**
 *
 * @author andreas
 */
@Stateless
public class MessageServiceBean extends GenericService<Message, Long>  {

    @Inject
    private MessageDaoBean messageDaoBean ;

    public MessageServiceBean() {
    }

    @PostConstruct
    public void init() {
        genericDao = messageDaoBean;
    }

    @Override
    public Message find(Long id) {
        return super.find(id);
    }

    @Override
    public List<Message> find(Map<String, Object> restrictions) {
        return super.find(restrictions);
    }

    @Override
    public List<Message> find(String attribute, Object value) {
        return super.find(attribute, value);
    }

    @Override
    public Message findSingleResult(Map<String, Object> restrictions) {
        return super.findSingleResult(restrictions);
    }

    @Override
    public Message findSingleResult(String attribute, Object value) {
        return super.findSingleResult(attribute, value);
    }

    @Override
    public List<Message> getNamedQueryListResult(String name, Map<String, Object> parameters) {
        return super.getNamedQueryListResult(name, parameters);
    }

    @Override
    public Message getNamedQuerySingleResult(String name, Map<String, Object> parameters) {
        return super.getNamedQuerySingleResult(name, parameters);
    }

    @Override
    public <T> T merge(T entity) {
        return super.merge(entity);
    }

    @Override
    public void persist(Object entity) {
        super.persist(entity);
    }

    @Override
    public Message persistOrMerge(Message entity) {
        return super.persistOrMerge(entity);
    }

    @Override
    public void remove(Message entity) {
        super.remove(entity);
    }

}
