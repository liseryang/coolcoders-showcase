/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web.icefaces;

import net.coolcoders.showcase.model.Category;
import net.coolcoders.showcase.model.Gender;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.service.CategoryService;
import net.coolcoders.showcase.service.UserService;
import net.coolcoders.showcase.web.common.i18n.MessageBundleLoader;
import net.coolcoders.showcase.web.scope.ViewScoped;

import javax.ejb.EJB;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andreas
 */
@Named
@ViewScoped
public class RegisterBean implements Serializable {

    @EJB
    private UserService userService;

    @EJB
    private CategoryService categoryService;

    @Inject
    private SessionBean sessionBean;

    private User user = new User();

    public User getUser() {
        if(sessionBean.getCurrentUser() != null) {
            user = sessionBean.getCurrentUser();
        }
        return user;
    }

    public String save() {
        if(sessionBean.getCurrentUser() == null) {
            userService.persist(user);
        } else {
            userService.merge(user);
        }
        sessionBean.setCurrentUser(user);
        return "showMessages";
    }

    public SelectItem[] getGenderItems() {
        SelectItem[] item = new SelectItem[2];

        item[0] = new SelectItem(Gender.MALE, MessageBundleLoader.getMessage(Gender.MALE.getI18nKey()));
        item[1] = new SelectItem(Gender.FEMALE, MessageBundleLoader.getMessage(Gender.FEMALE.getI18nKey()));
        return item;
    }

    public SelectItem[] getCategoryItems() {
        List<Category> categories = categoryService.listAll();
        List<SelectItem> items = new ArrayList<SelectItem>(categories.size());
        for (Category category : categories) {
            items.add(new SelectItem(category.getId(), category.getName()));
        }
        return items.toArray(new SelectItem[items.size()]);
    }
}
