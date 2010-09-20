package net.coolcoders.smartgwt

import net.coolcoders.showcase.grails.smartgwt.Category
import net.coolcoders.smartgwt.client.LoadLookupDataAction
import net.coolcoders.smartgwt.client.LoadLookupDataResponse

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class LoadLookupDataActionHandler {
  LoadLookupDataResponse execute(LoadLookupDataAction action) {
    println "LoadLookupDataActionHandler.execute ( )"
    def categories = new LinkedHashMap();
    Category.getAll().each {
      categories.put(it.id, it.name)
    }
    return new LoadLookupDataResponse(categoriesMap: categories)
  }
}
