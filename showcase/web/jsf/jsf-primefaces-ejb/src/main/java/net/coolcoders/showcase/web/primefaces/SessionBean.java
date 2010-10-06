/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

package net.coolcoders.showcase.web.primefaces;

import net.coolcoders.showcase.dao.generic.QueryParameter;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.model.User_;
import net.coolcoders.showcase.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * @author andreas
 */
@Named
@SessionScoped
public class SessionBean implements Serializable {

    private User currentUser;

    @EJB
    private UserService userService;

    private String theme = "redmond"; //default

    public String getTheme() {
//        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//        if(params.containsKey("theme")) {
//            theme = params.get("theme");
//        }

        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }


    public User getCurrentUser() {
        if(currentUser == null) {
            currentUser = userService.find(QueryParameter.with(User_.username, "anerlich"));
        }
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String logout() {
        currentUser = null;
        return "login";
    }
}
