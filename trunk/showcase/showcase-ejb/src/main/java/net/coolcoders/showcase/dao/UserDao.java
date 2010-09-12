/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.dao;

import net.coolcoders.showcase.model.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author andreas
 */
@Stateless
@LocalBean
public class UserDao {

    @PersistenceUnit
    private EntityManager em;

    public User getByFirstName(String firstName) {
        return new User();
    }

    
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
 
}
