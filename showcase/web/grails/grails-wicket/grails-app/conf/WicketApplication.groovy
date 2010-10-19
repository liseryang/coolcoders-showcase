import org.apache.wicket.protocol.http.WebApplication;

import grails.util.*
import org.apache.wicket.Application
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.wicket.Session
import org.apache.wicket.Request
import org.apache.wicket.Response
import net.coolcoders.showcase.ShowcaseSession

public class WicketApplication extends WebApplication {

  /**
   * @see org.apache.wicket.Application#getHomePage()
   */
  Class getHomePage() {
    net.coolcoders.showcase.pages.HomePage.class
  }

  /**
   * Configures Grails' application context to be used for @SpringBean injection
   */
  protected void init() {
    super.init()
    addComponentInstantiationListener(new SpringComponentInjector(this, ApplicationHolder.getApplication().getMainContext(), false));
    getMarkupSettings().setStripWicketTags true
  }

  /**
   * If we're running in Grails development environment use Wicket development environment
   */
  public String getConfigurationType() {
    if (GrailsUtil.isDevelopmentEnv()) {
      return Application.DEVELOPMENT
    }
    return Application.DEPLOYMENT
  }

  def Session newSession(Request request, Response response) {
    return new ShowcaseSession(request);
  }
}