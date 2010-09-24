package net.coolcoders.showcase.web.converter;

import net.coolcoders.showcase.service.GenericService;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.servlet.ServletContext;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 22.09.2010
 * Time: 00:47:41
 * To change this template use File | Settings | File Templates.
 */
public class AbstractConverter<T> implements Converter {

    private Class clazz;

    private GenericService<T> genericService;

    public AbstractConverter(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if(genericService == null) {
            BeanManager bm = getBeanManager(facesContext);
            Bean<GenericService<T>> bean = (Bean<GenericService<T>>) bm.getBeans(GenericService.class).iterator().next();
            CreationalContext<GenericService<T>> ctx = bm.createCreationalContext(bean);
            genericService = (GenericService<T>) bm.getReference(bean, GenericService.class, ctx);
        }
        return genericService.find(clazz, s);
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return (String) o;
    }

    private BeanManager getBeanManager(FacesContext facesContext)
    {
        return (BeanManager)
              ((ServletContext) facesContext.getExternalContext().getContext())
                   .getAttribute("javax.enterprise.inject.spi.BeanManager");
    }

}
