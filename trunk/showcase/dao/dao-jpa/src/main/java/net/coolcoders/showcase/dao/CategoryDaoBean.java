/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.dao;

import net.coolcoders.showcase.dao.generic.AbstractGenericDao;
import net.coolcoders.showcase.model.Category;

import javax.ejb.Stateless;

/**
 *
 * @author andreas
 */
@Stateless
public class CategoryDaoBean extends AbstractGenericDao<Category, Long> {

    public CategoryDaoBean() {
        super(Category.class);
    }

}
