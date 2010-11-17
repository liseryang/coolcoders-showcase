package net.coolcoders.showcase.panel

import net.coolcoders.showcase.AppUser
import net.coolcoders.showcase.AppUserService
import net.coolcoders.showcase.ShowcaseSession
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator
import org.apache.wicket.markup.html.panel.Panel
import org.apache.wicket.markup.repeater.Item
import org.apache.wicket.markup.repeater.data.DataView

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
class UserTablePanel extends Panel {

  private final transient AppUserService appUserService

  public UserTablePanel(String id, SortableDataProvider<AppUser> dataProvider, AppUserService userService) {
    super(id)
    this.appUserService = userService;
    addUserList(dataProvider)
  }

  def addUserList(SortableDataProvider<AppUser> dataProvider) {
    DataView<AppUser> theList = new DataView<AppUser>("userList", dataProvider, 10) {
      protected void populateItem(Item<AppUser> dataItem) {
        AppUser theUser = dataItem.getModelObject()
        Label username = new Label("username", theUser.username)
        Label fullname = new Label("fullname", theUser.fullname)
        Label messageCount = new Label("messageCount", "${theUser.messages?.size()}")
        Link unfollowLink = new Link("unfollowLink") {
          void onClick() {
            unfollow(theUser.id)
          }

          @Override
          def boolean isVisible() {
            return isUnfollowLinkVisible(theUser.id);
          }
        }
        Link followLink = new Link("followLink") {
          void onClick() {
            follow(theUser.id)
          }

          @Override
          def boolean isVisible() {
            return isFollowLinkVisible(theUser.id)
          }
        }
        dataItem.add(username, fullname, messageCount, unfollowLink, followLink)
      }
    }
    add(new OrderByBorder("orderByUsername", "username", dataProvider))
    add(new OrderByBorder("orderByFullname", "fullname", dataProvider))
    add(theList)
    add(new PagingNavigator("paginator", theList))
  }

  private boolean isFollowLinkVisible(String theUserId) {
    String currentUserId = ShowcaseSession.get().getUserId()
    return !appUserService.isFollowing(currentUserId, theUserId);
  }

  private boolean isUnfollowLinkVisible(String theUserId) {
    String currentUserId = ShowcaseSession.get().getUserId()
    return appUserService.isFollowing(currentUserId, theUserId);
  }

  private void follow(String theUserId) {
    String currentUserId = ShowcaseSession.get().getUserId()
    appUserService.followUser(currentUserId, theUserId)
  }

  private void unfollow(String theUserId) {
    String currentUserId = ShowcaseSession.get().getUserId()
    appUserService.unfollowUser(currentUserId, theUserId)
  }
}
