/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coolcoders.showcase.dao.generic;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.SingularAttribute;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *
 */
public abstract class AbstractGenericDao<T, PK extends Serializable> {

    private Class<T> persistentClass;

    @PersistenceContext
    protected EntityManager em;

    public AbstractGenericDao(Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    // Named Query Methods
    public T findNamedQueryResult(String queryName, QueryParameter queryParameter) {
        TypedQuery<T> query = createTypedNamedQuery(queryName, queryParameter);
        return query.getSingleResult();
    }

    public List<T> listNamedQueryResult(String queryName, QueryParameter queryParameter) {
        TypedQuery<T> query = createTypedNamedQuery(queryName, queryParameter);
        return query.getResultList();
    }

    private TypedQuery<T> createTypedNamedQuery(String name, QueryParameter queryParameter) {
        TypedQuery<T> query = em.createNamedQuery(name, persistentClass);
        for (QueryParameterEntry paramName : queryParameter.parameters()) {
            query.setParameter(paramName.getAttribute().getName(), paramName.getValue());
        }
        return query;
    }

    // List Methods; returns List<T>
    public List<T> listAll(QueryFetch... queryFetches) {
        return list(null, null, queryFetches);
    }

    public List<T> list(QueryParameter queryParameter, QueryOrder queryOrder, QueryFetch... queryFetches) {
        CriteriaQuery<T> query = createCriteriaQuery(queryParameter, queryOrder, queryFetches);
        return listCriteriaQueryResult(query, null, null);
    }

    // find Methods; returns T
    public T find(PK id) {
        return em.find(persistentClass, id);
    }

    public T find(QueryParameter queryParameter, QueryFetch... queryFetches) {
        CriteriaQuery<T> query = createCriteriaQuery(queryParameter, null);
        return findCriteriaQueryResult(query);
    }

    public T find(QueryParameter queryParameter, QueryOrder queryOrder, QueryFetch... queryFetches) {
        CriteriaQuery<T> query = createCriteriaQuery(queryParameter, queryOrder, queryFetches);
        return findCriteriaQueryResult(query);
    }


    private CriteriaQuery<T> createCriteriaQuery(QueryParameter queryParameter, QueryOrder queryOrder, QueryFetch... queryFetches) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(persistentClass);
        Root<T> root = query.from(persistentClass);

        applyQueryFetches(root, queryFetches);

        if (queryParameter != null) {
            List<Predicate> predicates = createPredicates(cb, root, queryParameter);
            query.where(predicates.toArray(new Predicate[predicates.size()]));
        }

        if (queryOrder != null) {
            List<Order> orders = createOrders(cb, root, queryOrder);
            query.orderBy(orders);
        }

        query.distinct(true);

        return query;
    }

    private <T> void applyQueryFetches(Root<T> root, QueryFetch[] queryFetches) {
        for (QueryFetch queryFetch : queryFetches) {
            Fetch<Object, Object> recentFetch = null;
            for (Attribute attribute : queryFetch.getAttributeNames().keySet()) {
                JoinType joinType = queryFetch.getAttributeNames().get(attribute);
                if(recentFetch == null) {
                    recentFetch = root.fetch(attribute.getName(), joinType);
                } else {
                    recentFetch = recentFetch.fetch(attribute.getName(), joinType);
                }
            }
        }
    }

    private <T> List<Order> createOrders(CriteriaBuilder cb, Root<T> root, QueryOrder queryOrder) {
        List<Order> orders = new ArrayList<Order>();
        for (SingularAttribute attribute : queryOrder.statements().keySet()) {
            QueryOrder.OrderDirection direction = queryOrder.statements().get(attribute);
            if (QueryOrder.OrderDirection.ASC.equals(direction)) {
                orders.add(cb.asc(root.get(attribute)));
            } else if (QueryOrder.OrderDirection.DESC.equals(direction)) {
                orders.add(cb.desc(root.get(attribute)));
            }
        }
        return orders;
    }

    private <T> List<Predicate> createPredicates(CriteriaBuilder cb, Root<T> root, QueryParameter queryParameter) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        for (QueryParameterEntry entry : queryParameter.parameters()) {
            Expression path = root.get(entry.getAttribute());

            Object value = entry.getValue();
            Predicate predicate = null;
            if (QueryParameterEntry.Operator.EQ.equals(entry.getOperator())) {
                predicate = cb.equal(path, value);
            } else if (value instanceof Number) {
                predicate = createPredicateForNumber(cb, entry, path, (Number) value);
            } else if (value instanceof String) {
                predicate = createPredicateForString(cb, entry, path, (String) value);
            } else if (value instanceof Comparable) {
                predicate = createPredicateForComparable(cb, entry, path, (Comparable) value);
            }
            if (predicate != null) {
                predicates.add(predicate);
            }
        }
        return predicates;
    }

    private Predicate createPredicateForNumber(CriteriaBuilder cb, QueryParameterEntry entry, Expression<? extends Number> path, Number number) {
        Predicate predicate = null;
        if (QueryParameterEntry.Operator.GT.equals(entry.getOperator())) {
            predicate = cb.gt(path, number);
        } else if (QueryParameterEntry.Operator.GE.equals(entry.getOperator())) {
            predicate = cb.ge(path, number);
        } else if (QueryParameterEntry.Operator.LT.equals(entry.getOperator())) {
            predicate = cb.lt(path, number);
        } else if (QueryParameterEntry.Operator.LE.equals(entry.getOperator())) {
            predicate = cb.le(path, number);
        }
        return predicate;
    }

    private Predicate createPredicateForString(CriteriaBuilder cb, QueryParameterEntry entry, Expression<String> path, String string) {
        Predicate predicate = null;
        if (QueryParameterEntry.Operator.STARTS.equals(entry.getOperator())) {
            predicate = cb.like(path, string + "%");
        } else if (QueryParameterEntry.Operator.CONTAINS.equals(entry.getOperator())) {
            predicate = cb.like(path, "%" + string + "%");
        } else if (QueryParameterEntry.Operator.ENDS.equals(entry.getOperator())) {
            predicate = cb.like(path, "%" + string);
        } else {
            predicate = createPredicateForComparable(cb, entry, path, string);
        }
        return predicate;
    }

    private Predicate createPredicateForComparable(CriteriaBuilder cb, QueryParameterEntry entry, Expression<? extends Comparable> path, Comparable comp) {
        Predicate predicate = null;
        if (QueryParameterEntry.Operator.GT.equals(entry.getOperator())) {
            predicate = cb.greaterThan(path, comp);
        } else if (QueryParameterEntry.Operator.GE.equals(entry.getOperator())) {
            predicate = cb.greaterThanOrEqualTo(path, comp);
        } else if (QueryParameterEntry.Operator.LT.equals(entry.getOperator())) {
            predicate = cb.lessThan(path, comp);
        } else if (QueryParameterEntry.Operator.LE.equals(entry.getOperator())) {
            predicate = cb.lessThanOrEqualTo(path, comp);
        }
        return predicate;
    }

    protected T findCriteriaQueryResult(CriteriaQuery<T> criteriaQuery) {
        T result = null;
        try {
            result = em.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            // Nothing found; return null
        }
        return result;
    }

    protected List<T> listCriteriaQueryResult(CriteriaQuery<T> criteriaQuery, Integer first, Integer max) {
        TypedQuery<T> typedQuery = em.createQuery(criteriaQuery);
        if(first != null) {
            typedQuery.setFirstResult(first);
        }
        if(max != null) {
            typedQuery.setMaxResults(max);
        }
        return typedQuery.getResultList();
    }


    public void persist(Object entity) {
        em.persist(entity);
    }

    public <T> T merge(T entity) {
        return em.merge(entity);
    }

    public T persistOrMerge(T entity) {
        if (em.contains(entity)) {
            entity = em.merge(entity);
        } else {
            em.persist(entity);
        }
        return entity;
    }

    public void persistAll(List<T> list) {
        for (T item : list) {
            persist(item);
        }
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

}
