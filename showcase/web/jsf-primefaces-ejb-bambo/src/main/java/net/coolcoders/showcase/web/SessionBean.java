/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/

package net.coolcoders.showcase.web;

import net.coolcoders.showcase.model.User;

import javax.ejb.TimerService;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author andreas
 */
@Named
@SessionScoped
public class SessionBean implements java.io.Serializable {

    private String themePath = "./xmlhttp/css/rime/rime.css";
//    private String themePath = "./xmlhttp/css/xp/xp.css";

    @Inject
    TimerBean timerBean;

    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public String getThemePath() {
        return themePath;
    }

    public void setThemePath(String themePath) {
        this.themePath = themePath;
    }

    public SelectItem[] getThemePaths() {
        SelectItem[] items = new SelectItem[3];
        items[0] = new SelectItem("./xmlhttp/css/rime/rime.css", "Rime");
        items[1] = new SelectItem("./xmlhttp/css/xp/xp.css", "XP");
        items[2] = new SelectItem("./xmlhttp/css/royale/royale.css", "Royale");
        return items;
    }

    public void changeStyle(ValueChangeEvent e) throws java.io.IOException {
        String tempStyle = (String) e.getNewValue();
        if (!themePath.equalsIgnoreCase(tempStyle)) {
            themePath = tempStyle;
            FacesContext.getCurrentInstance().getExternalContext().redirect("./");
        }
    }

    public String logout() {
        currentUser = null;
        return "login";
    }

    public String startTimer() {
        timerBean.startTimer();
        return null;
    }

}
