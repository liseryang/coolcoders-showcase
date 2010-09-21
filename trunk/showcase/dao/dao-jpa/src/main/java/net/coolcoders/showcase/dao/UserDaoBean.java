/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.dao;

import net.coolcoders.showcase.dao.generic.AbstractGenericDao;
import net.coolcoders.showcase.model.User;

import javax.ejb.Stateless;

/**
 *
 * @author andreas
 */
@Stateless
public class UserDaoBean extends AbstractGenericDao<User, Long> {

    public UserDaoBean() {
        super(User.class);
    }

}
