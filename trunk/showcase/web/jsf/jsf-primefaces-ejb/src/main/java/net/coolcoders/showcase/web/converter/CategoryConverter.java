package net.coolcoders.showcase.web.converter;

import net.coolcoders.showcase.model.Category;

import javax.faces.convert.FacesConverter;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 22.09.2010
 * Time: 00:17:19
 * To change this template use File | Settings | File Templates.
 */
//@FacesConverter(value = "categoryConverter")
@FacesConverter(forClass = Category.class)
public class CategoryConverter extends AbstractConverter<Category> {

    public CategoryConverter() {
        super(Category.class);
    }
}
