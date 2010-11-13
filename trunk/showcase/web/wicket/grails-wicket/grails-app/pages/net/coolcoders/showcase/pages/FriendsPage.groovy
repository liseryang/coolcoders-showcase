package net.coolcoders.showcase.pages

import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider
import net.coolcoders.showcase.AppUser
import org.apache.wicket.model.IModel

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class FriendsPage extends BasePage{

  public FriendsPage(){

  }

  class FriendsDataProvider extends SortableDataProvider<AppUser>{

    Iterator<? extends AppUser> iterator(int i, int i1) {
      return null;
    }

    int size() {
      return 0;
    }

    IModel<AppUser> model(AppUser t) {
      return null;
    }
  }
}
