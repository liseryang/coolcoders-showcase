package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.generic.GenericDao;
import net.coolcoders.showcase.dao.generic.QueryFetch;
import net.coolcoders.showcase.dao.generic.QueryOrder;
import net.coolcoders.showcase.dao.generic.QueryParameter;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 20.09.2010
 * Time: 11:29:24
 * To change this template use File | Settings | File Templates.
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class GenericService<T> extends GenericDao<T> {

    @EJB
    private GenericDao<T> genericDao;

    public T findNamedQueryResult(Class<T> clazz, String queryName, Map<String, Object> parameters) {
        return genericDao.findNamedQueryResult(clazz, queryName, parameters);
    }

    public List<T> getNamedQueryResult(Class<T> clazz, String queryName, Map<String, Object> parameters) {
        return genericDao.getNamedQueryResult(clazz, queryName, parameters);
    }

    public List<T> get(Class<T> clazz) {
        return genericDao.get(clazz);
    }

    public List<T> get(Class<T> clazz, QueryParameter queryParameter, QueryOrder queryOrder, QueryFetch... queryFetches) {
        return genericDao.get(clazz, queryParameter, queryOrder, queryFetches);
    }

    public T find(Class<T> clazz, Object id) {
        return genericDao.find(clazz, id);
    }

    public T find(Class<T> clazz, QueryParameter queryParameter, QueryFetch... queryFetches) {
        return genericDao.find(clazz, queryParameter, queryFetches);
    }

    public T find(Class<T> clazz, QueryParameter queryParameter, QueryOrder queryOrder, QueryFetch... queryFetches) {
        return genericDao.find(clazz, queryParameter, queryOrder, queryFetches);
    }

    @Override
    public void persist(Object entity) {
        genericDao.persist(entity);
    }

    public T merge(T entity) {
        return genericDao.merge(entity);
    }

    public T persistOrMerge(T entity) {
        return genericDao.persistOrMerge(entity);
    }

    public void persistAll(List<T> ts) {
        genericDao.persistAll(ts);
    }

    public void remove(T entity) {
        genericDao.remove(entity);
    }

    public void delete(Class<T> clazz, Object id) {
        genericDao.delete(clazz, id);
    }

    @Override
    public void flushAndClear() {
        genericDao.flushAndClear();
    }
}
