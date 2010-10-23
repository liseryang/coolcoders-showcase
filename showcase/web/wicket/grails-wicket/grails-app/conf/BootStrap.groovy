import org.codehaus.groovy.grails.commons.ApplicationHolder
import net.coolcoders.showcase.BootstrapData

class BootStrap {

    def init = { servletContext ->
      new BootstrapData().insertData()
    }
    def destroy = {
    }
}
