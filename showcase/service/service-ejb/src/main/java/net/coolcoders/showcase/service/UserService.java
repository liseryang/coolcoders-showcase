/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.UserDao;
import net.coolcoders.showcase.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author andreas
 */
@Stateless
public class UserService extends AbstractGenericService<User, String> {

    @EJB
    private UserDao userDao;

    public UserService() {
    }

    @PostConstruct
    public void init() {
        abstractGenericDao = userDao;
    }

    public Long count(String userId) {
        return userDao.count(userId);
    }
}
