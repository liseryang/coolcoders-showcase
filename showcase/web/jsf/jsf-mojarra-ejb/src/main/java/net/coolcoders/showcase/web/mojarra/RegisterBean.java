/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web.mojarra;

import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.service.UserService;
import net.coolcoders.showcase.web.scope.ViewScoped;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 *
 * @author andreas
 */
@Named
@ViewScoped
public class RegisterBean implements Serializable {

    @EJB
    private UserService userService;

    @Inject
    private SessionBean sessionBean;

    private User user = new User();

    public User getUser() {
        return user;
    }

    public String save() {
        userService.persist(user);
        sessionBean.setCurrentUser(user);
        return "home";
    }

}
