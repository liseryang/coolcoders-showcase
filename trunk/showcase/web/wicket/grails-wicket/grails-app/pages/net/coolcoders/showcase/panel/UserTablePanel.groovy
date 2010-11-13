package net.coolcoders.showcase.panel

import net.coolcoders.showcase.AppUser
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.markup.repeater.Item
import org.apache.wicket.markup.repeater.data.DataView
import org.apache.wicket.markup.html.basic.Label

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class UserTablePanel extends Panel {

  public UserTablePanel(String id, SortableDataProvider<AppUser> dataProvider) {
    super(id)
    addUserList(dataProvider)
  }

  def addUserList(SortableDataProvider<AppUser> dataProvider) {
    DataView<AppUser> theList = new DataView<AppUser>("userList", dataProvider) {
      protected void populateItem(Item<AppUser> dataItem) {
        AppUser theUser = dataItem.getModelObject()
        Label username = new Label("username", theUser.username)
        Label fullame = new Label("fullname", theUser.fullname)
      }
    }
  }
}
