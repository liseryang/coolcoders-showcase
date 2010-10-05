package net.coolcoders.showcase.web.primefaces;

import net.coolcoders.showcase.dao.generic.QueryParameter;
import net.coolcoders.showcase.dao.generic.QueryParameterEntry;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.model.User_;
import net.coolcoders.showcase.service.UserService;
import net.coolcoders.showcase.web.scope.ViewScoped;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 * Date: 05.10.2010
 * Time: 17:48:27
 */
@Named
@ViewScoped
public class UserSearchBean implements Serializable {

    @Inject
    private SessionBean sessionBean;

    @EJB
    private UserService userService;

    private String searchString;

    private List<User> users = new ArrayList<User>();

    private User selectedUser;

    public User getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(User selectedUser) {
        this.selectedUser = selectedUser;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String search() {
        // TODO move this logic into a query        
        users = userService.list(QueryParameter.with(User_.username, QueryParameterEntry.Operator.STARTS, searchString),
                null);
        users.remove(sessionBean.getCurrentUser());
        users.removeAll(sessionBean.getCurrentUser().getFollowing());
        selectedUser = null;
        return null;
    }

    public List<User> getUsers() {
        return users;
    }

    public String follow() {
        if(selectedUser != null) {
            User user = sessionBean.getCurrentUser();
            user.getFollowing().add(selectedUser);
            userService.merge(user);
            users.remove(selectedUser);
        }
        return null;
    }
}
