/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package net.coolcoders.showcase.service;

import net.coolcoders.showcase.dao.CategoryDao;
import net.coolcoders.showcase.model.Category;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 *
 * @author andreas
 */
@Service
@Transactional
public class CategoryService extends AbstractGenericService<Category, String> {

    @Resource
    private CategoryDao categoryDao;

    public CategoryService() {
    }

    @PostConstruct
    public void init() {
        abstractGenericDao = categoryDao;
    }

}
