package net.coolcoders.showcase.web.converter;

import net.coolcoders.showcase.model.Category;
import net.coolcoders.showcase.service.CategoryServiceBean;
import net.coolcoders.showcase.service.GenericService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

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
