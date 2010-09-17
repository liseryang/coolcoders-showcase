/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.dao;

import net.coolcoders.showcase.model.User;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Map;

/**
 *
 * @author andreas
 */
@Stateless
public class UserDaoBean extends GenericDao<User, Long> {

    public UserDaoBean() {
        super(User.class);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public List<User> getAll() {
        return super.getAll();    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public List<User> find(String attribute, Object value) {
        return super.find(attribute, value);
    }

    @Override
    public List<User> find(Map<String, Object> restrictions) {
        return super.find(restrictions);
    }

    @Override
    public User find(Long id) {
        return super.find(id);
    }

    @Override
    public User findSingleResult(String attribute, Object value) {
        return super.findSingleResult(attribute, value);
    }

    @Override
    public User findSingleResult(Map<String, Object> restrictions) {
        return super.findSingleResult(restrictions);
    }

    @Override
    public List<User> getNamedQueryListResult(String name, Map<String, Object> parameters) {
        return super.getNamedQueryListResult(name, parameters);
    }

    @Override
    public User getNamedQuerySingleResult(String name, Map<String, Object> parameters) {
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
    public void saveAll(List<User> users) {
        super.saveAll(users);    //To change body of overridden methods use File | Settings | File Templates.
    }

    @Override
    public User persistOrMerge(User entity) {
        return super.persistOrMerge(entity);
    }

    @Override
    public void remove(User entity) {
        super.remove(entity);
    }


}
