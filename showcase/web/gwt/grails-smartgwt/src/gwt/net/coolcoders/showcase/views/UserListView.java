package net.coolcoders.showcase.views;

import com.google.gwt.user.client.ui.Button;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.showcase.client.UserInfoResponse;
import net.coolcoders.showcase.components.ShowcaseLoggedInView;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class UserListView extends ShowcaseLoggedInView {

    public UserListView(GwtActionServiceAsync actionServiceAsync, UserInfoResponse userInfo) {
        super(actionServiceAsync, userInfo);
        addMember(new Button("User List View !"));
    }
}
