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
public class GenericService extends GenericDao {

    @EJB
    private GenericDao genericDao;

    @Override
    public <T> T findNamedQueryResult(Class<T> clazz, String queryName, Map<String, Object> parameters) {
        return genericDao.findNamedQueryResult(clazz, queryName, parameters);
    }

    @Override
    public <T> List<T> listNamedQueryResult(Class<T> clazz, String queryName, Map<String, Object> parameters) {
        return genericDao.listNamedQueryResult(clazz, queryName, parameters);
    }

    @Override
    public <T> List<T> listAll(Class<T> clazz, QueryFetch... queryFetches) {
        return genericDao.listAll(clazz, queryFetches);
    }

    @Override
    public <T> List<T> list(Class<T> clazz, QueryParameter queryParameter, QueryOrder queryOrder, QueryFetch... queryFetches) {
        return genericDao.list(clazz, queryParameter, queryOrder, queryFetches);
    }

    @Override
    public <T> T findById(Class<T> clazz, Object id) {
        return genericDao.findById(clazz, id);
    }

    @Override
    public <T> T find(Class<T> clazz, QueryParameter queryParameter, QueryFetch... queryFetches) {
        return genericDao.find(clazz, queryParameter, queryFetches);
    }

    @Override
    public <T> T find(Class<T> clazz, QueryParameter queryParameter, QueryOrder queryOrder, QueryFetch... queryFetches) {
        return genericDao.find(clazz, queryParameter, queryOrder, queryFetches);
    }

    @Override
    public void persist(Object entity) {
        genericDao.persist(entity);
    }

    @Override
    public <T> T merge(T entity) {
        return genericDao.merge(entity);
    }

    @Override
    public <T> T persistOrMerge(T entity) {
        return genericDao.persistOrMerge(entity);
    }

    @Override
    public <T> void persistAll(List<T> list) {
        genericDao.persistAll(list);
    }

    @Override
    public <T> void remove(T entity) {
        genericDao.remove(entity);
    }

    @Override
    public <T> void delete(Class<T> clazz, Object id) {
        genericDao.delete(clazz, id);
    }

    @Override
    public void flushAndClear() {
        genericDao.flushAndClear();
    }
}
