package net.coolcoders

import net.coolcoders.showcase.AppUser
import javax.faces.context.FacesContext
import javax.faces.application.FacesMessage

class LoginBean {

    def static scope = 'view'
    SessionBean sessionBean;

    String username = null;

    String password;

    String message;

    void init() {
    }

    void dispose() {
    }

    def login() {
        AppUser user = AppUser.findByUsernameAndPassword(username, password)
        if (user == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", "Login failed!"));
            message = "Login failed!";
            return null
        } else {
            sessionBean.setCurrentUser(user)
            redirect "/showMessages"
        }
    }



}
