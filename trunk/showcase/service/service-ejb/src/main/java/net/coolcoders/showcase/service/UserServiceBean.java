/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.UserDaoBean;
import net.coolcoders.showcase.model.User;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author andreas
 */
@Stateless
public class UserServiceBean extends AbstractGenericService<User, String> {

    @EJB
    private UserDaoBean userDaoBean;

    public UserServiceBean() {
    }

    @PostConstruct
    public void init() {
        abstractGenericDao = userDaoBean;
    }

        

}
