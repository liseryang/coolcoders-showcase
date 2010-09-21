/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.web;

import net.coolcoders.showcase.model.Category;
import net.coolcoders.showcase.model.Gender;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.service.CategoryServiceBean;
import net.coolcoders.showcase.service.DbInitBean;
import net.coolcoders.showcase.service.UserServiceBean;
import net.coolcoders.showcase.web.util.MessageBundleLoader;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andreas
 */
@Named
@RequestScoped
public class RegisterBean {

    @EJB
    private UserServiceBean userServiceBean;

    @EJB
    private CategoryServiceBean categoryServiceBean;

    @Inject
    private SessionBean sessionBean;

    @EJB
    private DbInitBean dbInitBean;

    private User user = new User();

    public User getUser() {
        return user;
    }

    public String save() {
        userServiceBean.persist(user);
        sessionBean.setCurrentUser(user);
        return "home";
    }

    public SelectItem[] getGenderItems() {
        SelectItem[] item = new SelectItem[2];

        item[0] = new SelectItem(Gender.MALE, MessageBundleLoader.getMessage(Gender.MALE.getI18nKey()));
        item[1] = new SelectItem(Gender.FEMALE, MessageBundleLoader.getMessage(Gender.FEMALE.getI18nKey()));
        return item;
    }

    public SelectItem[] getCategoryItems() {
        List<Category> categories = categoryServiceBean.get();
        if(categories.size() == 0) {
            dbInitBean.initDb();
        }
        List<SelectItem> items = new ArrayList<SelectItem>(categories.size());
        for (Category category : categories) {
            items.add(new SelectItem(category, category.getName()));
        }
        return items.toArray(new SelectItem[items.size()]);
    }
}
