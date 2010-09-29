package net.coolcoders.smartgwt

import net.coolcoders.showcase.Category
import net.coolcoders.showcase.client.client.LoadLookupDataAction
import net.coolcoders.showcase.client.client.LoadLookupDataResponse

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
