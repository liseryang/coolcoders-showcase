/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.dao;

import net.coolcoders.showcase.dao.generic.AbstractGenericDao;
import net.coolcoders.showcase.model.Category;
import org.springframework.stereotype.Repository;


/**
 *
 * @author andreas
 */
@Repository
public class CategoryDao extends AbstractGenericDao<Category, String> {

    public CategoryDao() {
        super(Category.class);
    }

}
