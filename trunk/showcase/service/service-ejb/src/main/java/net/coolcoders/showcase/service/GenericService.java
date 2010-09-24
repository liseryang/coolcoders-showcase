package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.generic.GenericDao;
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
public class GenericService<T> {

    @EJB
    private GenericDao<T> genericDao;

    public T findNamedQueryResult(Class clazz, String queryName, Map<String, Object> parameters) {
        return genericDao.findNamedQueryResult(clazz, queryName, parameters);
    }

    public List<T> getNamedQueryResult(Class clazz, String queryName, Map<String, Object> parameters) {
        return genericDao.getNamedQueryResult(clazz, queryName, parameters);
    }

    public List<T> get(Class clazz) {
        return genericDao.get(clazz);
    }

    public List<T> get(Class clazz, QueryParameter queryParameter, QueryOrder queryOrder) {
        return genericDao.get(clazz, queryParameter, queryOrder);
    }

    public T find(Class clazz, Object id) {
        return genericDao.find(clazz, id);
    }

    public T find(Class clazz, QueryParameter queryParameter) {
        return genericDao.find(clazz, queryParameter);
    }

    public T find(Class clazz, QueryParameter queryParameter, QueryOrder queryOrder) {
        return genericDao.find(clazz, queryParameter, queryOrder);
    }

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

    public void delete(Class clazz, Object id) {
        genericDao.delete(clazz, id);
    }
}
