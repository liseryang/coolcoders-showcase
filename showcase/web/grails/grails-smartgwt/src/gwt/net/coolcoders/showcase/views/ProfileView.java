package net.coolcoders.showcase.views;

import com.smartgwt.client.widgets.Label;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.showcase.client.UserInfoResponse;
import net.coolcoders.showcase.components.ShowcaseLoggedInView;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class ProfileView extends ShowcaseLoggedInView {

    public ProfileView(GwtActionServiceAsync actionServiceAsync, UserInfoResponse userInfo) {
        super(actionServiceAsync, userInfo);
        addMember(new Label("ProfileView !"));
    }
}
