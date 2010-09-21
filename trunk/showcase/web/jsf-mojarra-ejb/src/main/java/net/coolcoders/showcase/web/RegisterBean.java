/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web;

import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.service.UserServiceBean;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author andreas
 */
@Named
@RequestScoped
public class RegisterBean {

    @EJB
    private UserServiceBean userServiceBean;

    @Inject
    private SessionBean sessionBean;

    private User user = new User();

    public User getUser() {
        return user;
    }

    public String save() {
        userServiceBean.persist(user);
        sessionBean.setCurrentUser(user);
        return "home";
    }

}
