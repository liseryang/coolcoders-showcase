/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web;

import net.coolcoders.showcase.dao.generic.QueryParameter;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.service.DbInitBean;
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
public class LoginBean {

    @EJB
    private UserServiceBean userServiceBean;

    @Inject
    private SessionBean sessionBean;

    @EJB
    private DbInitBean dbInitBean;

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
        User user = userServiceBean.find(QueryParameter.with("username", username).and("password", password));
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

    public String initDb() {
        dbInitBean.initDb();
        return null;
    }

}
