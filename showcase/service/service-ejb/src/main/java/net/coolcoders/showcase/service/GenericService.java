/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.GenericDao;
import net.coolcoders.showcase.model.User;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @author andreas
 */
public abstract class GenericService<T, PK extends Serializable> {

    protected GenericDao<T, PK> genericDao;

    public T getNamedQuerySingleResult(String name, Map<String, Object> parameters) {
        return genericDao.getNamedQuerySingleResult(name, parameters);
    }

    public List<T> getNamedQueryListResult(String name, Map<String, Object> parameters) {
        return genericDao.getNamedQueryListResult(name, parameters);
    }

    public T findSingleResult(Map<String, Object> restrictions) {
        return genericDao.findSingleResult(restrictions);
    }

    public T findSingleResult(String attribute, Object value) {
        return genericDao.findSingleResult(attribute, value);
    }

    public T find(PK id) {
        return genericDao.find(id);
    }

    public List<T> getAll() {
        return genericDao.getAll();
    }

    public List<T> find(Map<String, Object> restrictions) {
        return genericDao.find(restrictions);
    }

    public List<T> find(String attribute, Object value) {
        return genericDao.find(attribute, value);
    }

    public void remove(T entity) {
        genericDao.remove(entity);
    }

    public T persistOrMerge(T entity) {
        return genericDao.persistOrMerge(entity);
    }

    public void persist(Object entity) {
        genericDao.persist(entity);
    }

    public void saveAll(List<User> users) {
        genericDao.saveAll(users);
    }

    public <T> T merge(T entity) {
        return genericDao.merge(entity);
    }


}
