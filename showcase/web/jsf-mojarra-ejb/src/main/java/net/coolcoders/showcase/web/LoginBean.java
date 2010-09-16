/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web;

import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.service.DbInitBean;
import net.coolcoders.showcase.service.UserServiceBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author andreas
 */
@Named
@RequestScoped
public class LoginBean {

    @Inject
    private UserServiceBean userServiceBean;

    @Inject
    private SessionBean sessionBean;

    @Inject
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
        Map<String, Object> restrictions = new HashMap<String, Object>();
        restrictions.put("username", username);
        restrictions.put("password", password);
        
        User user = userServiceBean.findSingleResult(restrictions);
        if(user == null) {
            message = "Login failed!";
            return null;
        } else {
            sessionBean.setCurrentUser(user);
            return "home";
        }
    }

    public String initDb() {
        dbInitBean.initDb();
        return null;
    }

}
