package net.coolcoders.showcase.pages

import net.coolcoders.showcase.AppUser
import net.coolcoders.showcase.ShowcaseSession
import net.coolcoders.showcase.panel.UserTablePanel
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider
import org.apache.wicket.model.IModel
import org.apache.wicket.model.Model
import org.apache.wicket.spring.injection.annot.SpringBean

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class FriendsPage extends BaseLoggedinPage {
  @SpringBean(name = "appUserService")
  def transient appUserService

  public FriendsPage() {
    UserTablePanel panel = new UserTablePanel("friendsTable", new FriendsDataProvider(), appUserService)
    add(panel)
  }

  class FriendsDataProvider extends SortableDataProvider<AppUser> {

    public FriendsDataProvider() {
      setSort(new SortParam("username", true))
    }

    Iterator<? extends AppUser> iterator(int first, int count) {
      String currentUserId = ShowcaseSession.get().getUserId()
      String sortParam = getSort().getProperty()
      boolean isAscending = getSort().isAscending()
      return appUserService.findFollowing(currentUserId, sortParam, isAscending, first, count).iterator()
    }

    int size() {
      String currentUserId = ShowcaseSession.get().getUserId()
      return appUserService.numberOfFollowing(currentUserId)
    }

    IModel<AppUser> model(AppUser t) {
      t = AppUser.get(t.id)
      return new Model(t);
    }
  }
}
