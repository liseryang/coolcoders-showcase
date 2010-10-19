package net.coolcoders.showcase

import org.apache.wicket.Request
import org.apache.wicket.protocol.http.WebSession

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class ShowcaseSession extends WebSession {
  String userId;
  String fullname;

  public ShowcaseSession(Request request) {
    super(request)
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public static ShowcaseSession get() {
    return (ShowcaseSession) WebSession.get();
  }

  public String getFullname() {
    return fullname
  }

  public void setFullname(String fullname) {
    this.fullname = fullname;
  }


  public boolean isLoggedIn() {
    return getUserId() != null
  }
}
