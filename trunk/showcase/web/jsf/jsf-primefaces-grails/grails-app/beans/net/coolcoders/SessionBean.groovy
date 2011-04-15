package net.coolcoders

import net.coolcoders.showcase.AppUser
import javax.faces.context.FacesContext

class SessionBean {

    def static scope = 'session'

    AppUser currentUser;
    String theme = "redmond"


    void init() {
    }

    void dispose() {
    }

    def getTheme() {
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (params.containsKey("theme")) {
            theme = params.get("theme");
        }
        return theme;
    }

    def setTheme(String theme) {
        this.theme = theme;
    }



    def logout() {
        currentUser = null;
        redirect "/login"
    }


}
