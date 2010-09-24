/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package net.coolcoders.showcase.dao.generic;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author andreas
 */
@Stateless
public class GenericDao<T> {

    @PersistenceContext
    protected EntityManager em;

    public GenericDao() {

    }

    // Named Query Methods
    public T findNamedQueryResult(Class clazz, String queryName, Map<String, Object> parameters) {
        TypedQuery<T> query = createTypedNamedQuery(clazz, queryName, parameters);
        return query.getSingleResult();
    }

    public List<T> getNamedQueryResult(Class clazz, String queryName, Map<String, Object> parameters) {
        TypedQuery<T> query = createTypedNamedQuery(clazz, queryName, parameters);
        return query.getResultList();
    }

    private TypedQuery<T> createTypedNamedQuery(Class clazz, String name, Map<String, Object> parameters) {
        TypedQuery<T> query = em.createNamedQuery(name, clazz);
        for (String paramName : parameters.keySet()) {
            query.setParameter(paramName, parameters.get(paramName));
        }
        return query;
    }

    // Get Methods; returns List<T>

    public List<T> get(Class clazz) {
        return get(clazz, null, null);
    }

    public List<T> get(Class clazz, QueryParameter queryParameter, QueryOrder queryOrder) {
        CriteriaQuery<T> query = createCriteriaQuery(clazz, queryParameter, queryOrder);
        return getCriteriaQueryResult(query);
    }

    // find Methods; returns T

    public T find(Class clazz, Object id) {
        return (T) em.find(clazz, id);
    }

    public T find(Class clazz, QueryParameter queryParameter) {
        CriteriaQuery<T> query = createCriteriaQuery(clazz, queryParameter, null);
        return findCriteriaQueryResult(query);
    }

    public T find(Class clazz, QueryParameter queryParameter, QueryOrder queryOrder) {
        CriteriaQuery<T> query = createCriteriaQuery(clazz, queryParameter, queryOrder);
        return findCriteriaQueryResult(query);
    }


    private CriteriaQuery<T> createCriteriaQuery(Class clazz, QueryParameter queryParameter, QueryOrder queryOrder) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> query = cb.createQuery(clazz);
        Root<T> root = query.from(clazz);

        if (queryParameter != null) {
            List<Predicate> predicates = new ArrayList<Predicate>();
            for (QueryParameterEntry entry : queryParameter.parameters()) {
                Expression path = root.get(entry.getAttribute());

                Object value = entry.getValue();
                Predicate predicate = null;
                if (QueryParameterEntry.Operator.EQ.equals(entry.getOperator())) {
                    predicate = cb.equal(path, value);
                } else if (value instanceof Number) {
                    Number number = (Number) value;
                    if (QueryParameterEntry.Operator.GT.equals(entry.getOperator())) {
                        predicate = cb.gt(path, number);
                    } else if (QueryParameterEntry.Operator.GE.equals(entry.getOperator())) {
                        predicate = cb.ge(path, number);
                    } else if (QueryParameterEntry.Operator.LT.equals(entry.getOperator())) {
                        predicate = cb.lt(path, number);
                    } else if (QueryParameterEntry.Operator.LE.equals(entry.getOperator())) {
                        predicate = cb.le(path, number);
                    }
                } else if (value instanceof Comparable) {
                    Comparable comp = (Comparable) value;
                    if (QueryParameterEntry.Operator.GT.equals(entry.getOperator())) {
                        predicate = cb.greaterThan(path, comp);
                    } else if (QueryParameterEntry.Operator.GE.equals(entry.getOperator())) {
                        predicate = cb.greaterThanOrEqualTo(path, comp);
                    } else if (QueryParameterEntry.Operator.LT.equals(entry.getOperator())) {
                        predicate = cb.lessThan(path, comp);
                    } else if (QueryParameterEntry.Operator.LE.equals(entry.getOperator())) {
                        predicate = cb.lessThanOrEqualTo(path, comp);
                    }
                }
                if (predicate != null) {
                    predicates.add(predicate);
                }
            }
            query.where(predicates.toArray(new Predicate[predicates.size()]));
        }

        if (queryOrder != null) {
            List<Order> orders = new ArrayList<Order>();
            for (SingularAttribute attribute : queryOrder.statements().keySet()) {
                QueryOrder.OrderDirection direction = queryOrder.statements().get(attribute);
                if (QueryOrder.OrderDirection.ASC.equals(direction)) {
                    orders.add(cb.asc(root.get(attribute)));
                } else if (QueryOrder.OrderDirection.DESC.equals(direction)) {
                    orders.add(cb.desc(root.get(attribute)));
                }
            }
            query.orderBy(orders);
        }

        return query;
    }

    private T findCriteriaQueryResult(CriteriaQuery<T> criteriaQuery) {
        T result = null;
        try {
            result = em.createQuery(criteriaQuery).getSingleResult();
        } catch (NoResultException e) {
            // Nothing found; return null
        }
        return result;
    }

    private List<T> getCriteriaQueryResult(CriteriaQuery<T> criteriaQuery) {
        return em.createQuery(criteriaQuery).getResultList();
    }


    public void persist(Object entity) {
        em.persist(entity);
    }

    public T merge(T entity) {
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

    public void delete(Class clazz, Object id) {
        em.remove(em.getReference(clazz, id));
    }

}
