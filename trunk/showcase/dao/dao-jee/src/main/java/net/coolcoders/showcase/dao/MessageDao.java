/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.dao;

import net.coolcoders.showcase.dao.generic.AbstractGenericDao;
import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.model.Message_;
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
 *
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class MessageDao extends AbstractGenericDao<Message, String> {

    public MessageDao() {
        super(Message.class);
    }

    public List<Message> list(String userId, int first, int max) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Message> query = cb.createQuery(Message.class);
        Root<User> root = query.from(User.class);
        SetJoin<User,User> followingJoin = root.join(User_.following);
        SetJoin<User, Message> messagesJoin = followingJoin.join(User_.messages);

        query.select(messagesJoin);
        query.where(cb.equal(root.get(User_.id), userId));
        query.orderBy(cb.desc(messagesJoin.get(Message_.created)));

        return listCriteriaQueryResult(query, first, max);
    }

    public Long count(String userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<User> root = query.from(User.class);
        SetJoin<User,User> followingJoin = root.join(User_.following);
        SetJoin<User, Message> messagesJoin = followingJoin.join(User_.messages);

        query.select(cb.count(messagesJoin));
        query.where(cb.equal(root.get(User_.id), userId));

        return em.createQuery(query).getSingleResult();
    }
}
