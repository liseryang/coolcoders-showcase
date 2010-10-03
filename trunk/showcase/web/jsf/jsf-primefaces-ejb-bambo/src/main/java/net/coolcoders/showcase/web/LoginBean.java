/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web;

import net.coolcoders.showcase.dao.generic.QueryParameter;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.model.User_;
import net.coolcoders.showcase.service.UserService;

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
public class LoginBean {

    @EJB
    private UserService userService;

    @Inject
    private SessionBean sessionBean;

    private String username = null;

    private String password;

    private String message;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public String login() {
        User user = userService.find(QueryParameter.with(User_.username, username).and(User_.password, password));
        if(user == null) {
            message = "Login failed!";
            return null;
        } else {
            sessionBean.setCurrentUser(user);
            return "home";
        }
    }

    public String logout() {
        sessionBean.setCurrentUser(null);
        return "login";
    }

}
