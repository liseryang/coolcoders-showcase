    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web;

    import net.coolcoders.showcase.model.User;

    import javax.enterprise.context.SessionScoped;
    import javax.inject.Named;

/**
 *
 * @author andreas
 */
@Named
@SessionScoped
public class SessionBean implements java.io.Serializable {

    private User currentUser;

    private int maxMessageCount = 10;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public int getMaxMessageCount() {
        return maxMessageCount;
    }

    public void setMaxMessageCount(int maxMessageCount) {
        this.maxMessageCount = maxMessageCount;
    }
}
