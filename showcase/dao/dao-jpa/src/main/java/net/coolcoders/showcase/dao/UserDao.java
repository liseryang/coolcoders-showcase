/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.dao;

import net.coolcoders.showcase.dao.generic.AbstractGenericDao;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.model.User_;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import java.util.List;

/**
 * @author andreas
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class UserDao extends AbstractGenericDao<User, String> {

    public UserDao() {
        super(User.class);
    }

    public Long countUsersYouFollow(String userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<User> root = query.from(User.class);
        SetJoin<User, User> followingJoin = root.join(User_.following);

        query.select(cb.count(followingJoin));
        query.where(cb.equal(root.get(User_.id), userId));

        return em.createQuery(query).getSingleResult();
    }

    public List<User> listUsersYouFollow(String userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> root = query.from(User.class);
        SetJoin<User, User> followingJoin = root.join(User_.following);

        query.select(followingJoin);
        query.where(cb.equal(root.get(User_.id), userId));

        return em.createQuery(query).getResultList();
    }
}
