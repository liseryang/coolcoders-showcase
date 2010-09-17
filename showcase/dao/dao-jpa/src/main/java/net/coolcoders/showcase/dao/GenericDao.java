/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coolcoders.showcase.dao;

import net.coolcoders.showcase.model.User;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author andreas
 */
public abstract class GenericDao<T, PK extends Serializable> {

    private Class<T> persistentClass;
    @PersistenceContext
    protected EntityManager em;
    protected CriteriaBuilder cb;

    @PostConstruct
    public void init() {
//        cb = em.getCriteriaBuilder();
    }

    public GenericDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    public T getNamedQuerySingleResult(String name, Map<String, Object> parameters) {
        TypedQuery<T> query = createTypedNamedQuery(name, parameters);
        return query.getSingleResult();
    }

    public List<T> getNamedQueryListResult(String name, Map<String, Object> parameters) {
        TypedQuery<T> query = createTypedNamedQuery(name, parameters);
        return query.getResultList();
    }

    private TypedQuery<T> createTypedNamedQuery(String name, Map<String, Object> parameters) {
        TypedQuery<T> query = em.createNamedQuery(name, persistentClass);
        for (String paramName : parameters.keySet()) {
            query.setParameter(paramName, parameters.get(paramName));
        }
        return query;
    }

    public List<T> find(String attribute, Object value) {
        Map<String, Object> restrictions = new HashMap<String, Object>();
        restrictions.put(attribute, value);
        return find(restrictions);
    }

    public List<T> getAll() {
        return find(new HashMap<String, Object>());
    }

    public T findSingleResult(String attribute, Object value) {
        Map<String, Object> restrictions = new HashMap<String, Object>();
        restrictions.put(attribute, value);
        return findSingleResult(restrictions);
    }

    public List<T> find(Map<String, Object> restrictions) {
        CriteriaQuery<T> query = createCriteriaQuery(restrictions);
        return getCriteriaQueryListResult(query);
    }

    public T findSingleResult(Map<String, Object> restrictions) {
        CriteriaQuery<T> query = createCriteriaQuery(restrictions);
        return getCriteriaQuerySingleResult(query);
    }

    private CriteriaQuery<T> createCriteriaQuery(Map<String, Object> restrictions) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(persistentClass);
        Root<T> root = query.from(persistentClass);
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (String restriction : restrictions.keySet()) {
            predicates.add(cb.equal(root.get(restriction), restrictions.get(restriction)));
        }
        query.where(predicates.toArray(new Predicate[predicates.size()]));
        return query;
    }

    private T getCriteriaQuerySingleResult(CriteriaQuery<T> criteriaQuery) {
        T result = null;
        try {
            result = em.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            // Nothing found; return null
        }
        return result;
    }

    private List<T> getCriteriaQueryListResult(CriteriaQuery<T> criteriaQuery) {
        return em.createQuery(criteriaQuery).getResultList();
    }

    public T find(PK id) {
        return em.find(persistentClass, id);
    }

    public void remove(T entity) {
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.remove(entity);
    }

    public void delete(PK id) {
        em.remove(em.getReference(persistentClass, id));
    }

    public T persistOrMerge(T entity) {
        if (em.contains(entity)) {
            entity = em.merge(entity);
        } else {
            em.persist(entity);
        }
        return entity;
    }

    public void persist(Object entity) {
        em.persist(entity);
    }

    public void saveAll(List<User> users) {
        for (User user : users) {
            persist(user);
        }
    }

    public <T> T merge(T entity) {
        return em.merge(entity);
    }
}
