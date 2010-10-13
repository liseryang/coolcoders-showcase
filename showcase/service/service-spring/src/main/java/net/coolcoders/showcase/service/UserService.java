/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.UserDao;
import net.coolcoders.showcase.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 *
 * @author andreas
 */
@Service
@Transactional
public class UserService extends AbstractGenericService<User, String> {

    @Resource
    private UserDao userDao;

    public UserService() {
    }

    @PostConstruct
    public void init() {
        abstractGenericDao = userDao;
    }

    public Long countUsersYouFollow(String userId) {
        return userDao.countUsersYouFollow(userId);
    }

    public List<User> listUsersYouFollow(String userId) {
        return userDao.listUsersYouFollow(userId);
    }

}
