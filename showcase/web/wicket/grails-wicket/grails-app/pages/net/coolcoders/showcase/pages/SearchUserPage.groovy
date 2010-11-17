package net.coolcoders.showcase.pages

import net.coolcoders.showcase.AppUser
import net.coolcoders.showcase.ShowcaseSession
import net.coolcoders.showcase.panel.UserTablePanel
import org.apache.wicket.extensions.markup.html.repeater.util.SortParam
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.IModel
import org.apache.wicket.model.Model
import org.apache.wicket.model.PropertyModel
import org.apache.wicket.spring.injection.annot.SpringBean

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class SearchUserPage extends BaseLoggedinPage {
  @SpringBean(name = "appUserService")
  def transient appUserService
  private String searchString

  public SearchUserPage() {
    PropertyModel<String> searchModel = new PropertyModel<String>(this, 'searchString');
    Form form = new Form('searchForm', searchModel);
    form.add(new TextField<String>('searchString', searchModel))
    UserTablePanel panel = new UserTablePanel('usersTable', new UserDataProvider(), appUserService)
    add(form, panel)
  }

  public String getSearchString() {
    return searchString
  }

  public void setSearchString(String searchString) {
    this.searchString = searchString
  }

  class UserDataProvider extends SortableDataProvider<AppUser> {

    public UserDataProvider() {
      setSort(new SortParam('username', true))
    }

    Iterator<? extends AppUser> iterator(int first, int count) {
      String currentUserId = ShowcaseSession.get().getUserId()
      String sortParam = getSort().getProperty()
      boolean isAscending = getSort().isAscending()
      return appUserService.findUsers(getSearchString(), sortParam, isAscending, first, count).iterator()
    }

    int size() {
      return AppUser.count()
    }

    IModel<AppUser> model(AppUser t) {
      t = AppUser.get(t.id)
      return new Model(t);
    }
  }
}
