/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.generic.AbstractGenericDao;
import net.coolcoders.showcase.dao.generic.QueryOrder;
import net.coolcoders.showcase.dao.generic.QueryParameter;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author andreas
 */
public abstract class AbstractGenericService<T, PK extends Serializable> {

    protected AbstractGenericDao<T, PK> abstractGenericDao;

    public T findNamedQueryResult(String queryName, QueryParameter queryParameter) {
        return abstractGenericDao.findNamedQueryResult(queryName, queryParameter);
    }

    public List<T> getNamedQueryResult(String queryName, QueryParameter queryParameter) {
        return abstractGenericDao.getNamedQueryResult(queryName, queryParameter);
    }

    public List<T> get() {
        return abstractGenericDao.get();
    }

    public List<T> get(QueryParameter queryParameter, QueryOrder queryOrder) {
        return abstractGenericDao.get(queryParameter, queryOrder);
    }

    public T find(PK id) {
        return abstractGenericDao.find(id);
    }

    public T find(String attribute, Object value) {
        return abstractGenericDao.find(attribute, value);
    }

    public T find(QueryParameter queryParameter) {
        return abstractGenericDao.find(queryParameter);
    }

    public T find(QueryParameter queryParameter, QueryOrder queryOrder) {
        return abstractGenericDao.find(queryParameter, queryOrder);
    }

    public void persist(Object entity) {
        abstractGenericDao.persist(entity);
    }

    public <T> T merge(T entity) {
        return abstractGenericDao.merge(entity);
    }

    public T persistOrMerge(T entity) {
        return abstractGenericDao.persistOrMerge(entity);
    }

    public void persistAll(List<T> ts) {
        abstractGenericDao.persistAll(ts);
    }

    public void remove(T entity) {
        abstractGenericDao.remove(entity);
    }

    public void delete(PK id) {
        abstractGenericDao.delete(id);
    }
}
