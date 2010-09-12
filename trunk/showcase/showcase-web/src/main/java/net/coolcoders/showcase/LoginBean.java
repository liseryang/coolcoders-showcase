/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author andreas
 */
@Named
@RequestScoped
public class LoginBean {

    private String userName;

    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



}
