import net.coolcoders.showcase.BootstrapData
import net.coolcoders.smartgwt.BaseActionHandler

class BootStrap {

  def init = { servletContext ->
    environments {
      production {
        new BootstrapData().insertData()
      }
      development {
        new BootstrapData().insertData()
      }
      test {
        new BootstrapData().insertData()
      }
    }
  }

  def destroy = {
  }
}
