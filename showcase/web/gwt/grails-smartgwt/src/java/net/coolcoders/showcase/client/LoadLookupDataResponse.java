package net.coolcoders.showcase.client;

import grails.plugins.gwt.shared.Response;

import java.util.LinkedHashMap;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class LoadLookupDataResponse implements Response {
    private static final long serialVersionUID = 1L;
    private LinkedHashMap<String, String> categoriesMap;

    public LinkedHashMap<String, String> getCategoriesMap() {
        return categoriesMap;
    }

    public void setCategoriesMap(LinkedHashMap<String, String> categoriesMap) {
        this.categoriesMap = categoriesMap;
    }
}
