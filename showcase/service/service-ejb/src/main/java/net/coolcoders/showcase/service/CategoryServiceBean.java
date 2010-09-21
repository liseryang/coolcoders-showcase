/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.CategoryDaoBean;
import net.coolcoders.showcase.model.Category;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author andreas
 */
@Stateless
public class CategoryServiceBean extends AbstractGenericService<Category, Long> {

    @EJB
    private CategoryDaoBean categoryDaoBean;

    public CategoryServiceBean() {
    }

    @PostConstruct
    public void init() {
        abstractGenericDao = categoryDaoBean;
    }

}
