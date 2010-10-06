package net.coolcoders.showcase.components;

import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.showcase.client.UserInfoResponse;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class ShowcaseLoggedInView extends ShowcaseBaseView {

    private final UserInfoResponse userInfo;

    public ShowcaseLoggedInView(GwtActionServiceAsync actionServiceAsync, UserInfoResponse userInfo) {
        super(actionServiceAsync);
        this.userInfo = userInfo;
    }
}
