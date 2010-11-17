import org.apache.wicket.protocol.http.WebApplication;

import grails.util.*
import org.apache.wicket.Application
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.apache.wicket.Session
import org.apache.wicket.Request
import org.apache.wicket.Response
import net.coolcoders.showcase.ShowcaseSession
import org.apache.wicket.authorization.IAuthorizationStrategy
import org.apache.wicket.Component
import org.apache.wicket.authorization.Action
import net.coolcoders.showcase.pages.BaseLoggedinPage
import org.apache.wicket.authorization.IUnauthorizedComponentInstantiationListener
import org.apache.wicket.request.target.coding.BookmarkablePageRequestTargetUrlCodingStrategy
import net.coolcoders.showcase.pages.MessagesPage
import net.coolcoders.showcase.pages.ProfilePage
import net.coolcoders.showcase.pages.FriendsPage
import net.coolcoders.showcase.pages.SearchUserPage
import net.coolcoders.showcase.pages.RegisterPage

public class WicketApplication extends WebApplication implements IAuthorizationStrategy, IUnauthorizedComponentInstantiationListener {

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
    getMarkupSettings().setStripWicketTags(true)
    getSecuritySettings().setAuthorizationStrategy(this)
    mount(new BookmarkablePageRequestTargetUrlCodingStrategy("messages", MessagesPage.class, null));
    mount(new BookmarkablePageRequestTargetUrlCodingStrategy("profile", ProfilePage.class, null));
    mount(new BookmarkablePageRequestTargetUrlCodingStrategy("friends", FriendsPage.class, null));
    mount(new BookmarkablePageRequestTargetUrlCodingStrategy("search", SearchUserPage.class, null));
    mount(new BookmarkablePageRequestTargetUrlCodingStrategy("register", RegisterPage.class, null));
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

  @Override
  void onUnauthorizedInstantiation(Component component) {
    component.getRequestCycle().setResponsePage(net.coolcoders.showcase.pages.HomePage.class)
  }

  @Override
  boolean isInstantiationAuthorized(Class tClass) {
    return true;
  }

  @Override
  boolean isActionAuthorized(Component component, Action action) {
    if (BaseLoggedinPage.class.isAssignableFrom(component.class)) {
      ShowcaseSession session = ShowcaseSession.get();
      return session.getUserId() != null;
    }
    return true;
  }


}