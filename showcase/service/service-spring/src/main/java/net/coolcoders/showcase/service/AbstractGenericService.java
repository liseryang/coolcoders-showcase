/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.generic.AbstractGenericDao;
import net.coolcoders.showcase.dao.generic.QueryFetch;
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

    public List<T> listNamedQueryResult(String queryName, QueryParameter queryParameter) {
        return abstractGenericDao.listNamedQueryResult(queryName, queryParameter);
    }

    public List<T> listAll(QueryFetch... queryFetches) {
        return abstractGenericDao.listAll(queryFetches);
    }

    public List<T> list(QueryParameter queryParameter, QueryOrder queryOrder, QueryFetch... queryFetches) {
        return abstractGenericDao.list(queryParameter, queryOrder, queryFetches);
    }

    public T find(PK id) {
        return abstractGenericDao.find(id);
    }

    public T find(QueryParameter queryParameter, QueryFetch... queryFetches) {
        return abstractGenericDao.find(queryParameter, queryFetches);
    }

    public T find(QueryParameter queryParameter, QueryOrder queryOrder, QueryFetch... queryFetches) {
        return abstractGenericDao.find(queryParameter, queryOrder, queryFetches);
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
