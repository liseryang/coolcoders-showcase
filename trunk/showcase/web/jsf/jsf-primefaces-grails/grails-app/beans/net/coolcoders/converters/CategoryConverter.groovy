package net.coolcoders.converters

import javax.faces.convert.Converter
import javax.faces.context.FacesContext
import javax.faces.component.UIComponent
import javax.faces.convert.FacesConverter
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * Peter Schneider-Manzell
 */
class CategoryConverter implements Converter {
    Logger log = LoggerFactory.getLogger(CategoryConverter.class);

    Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        log.error("Trying to convert value $s")
        return net.coolcoders.showcase.Category.get(s)
    }

    String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        log.error("Trying to convert value $o")
        return o
    }
}
