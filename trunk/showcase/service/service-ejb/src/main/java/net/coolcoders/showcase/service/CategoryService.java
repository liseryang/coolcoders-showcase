/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.CategoryDao;
import net.coolcoders.showcase.model.Category;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author andreas
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class CategoryService extends AbstractGenericService<Category, String> {

    @EJB
    private CategoryDao categoryDao;

    public CategoryService() {
    }

    @PostConstruct
    public void init() {
        abstractGenericDao = categoryDao;
    }

}
