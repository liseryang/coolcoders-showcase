/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.dao;

import net.coolcoders.showcase.model.Message;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;

/**
 *
 * @author andreas
 */
@Stateless
public class MessageDaoBean extends GenericDao<Message, Long> {

    public MessageDaoBean() {
        super(Message.class);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public List<Message> find(String attribute, Object value) {
        return super.find(attribute, value);
    }

    @Override
    public List<Message> find(Map<String, Object> restrictions) {
        return super.find(restrictions);
    }

    @Override
    public Message find(Long id) {
        return super.find(id);
    }

    @Override
    public Message findSingleResult(String attribute, Object value) {
        return super.findSingleResult(attribute, value);
    }

    @Override
    public Message findSingleResult(Map<String, Object> restrictions) {
        return super.findSingleResult(restrictions);
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
    public void init() {
        super.init();
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
