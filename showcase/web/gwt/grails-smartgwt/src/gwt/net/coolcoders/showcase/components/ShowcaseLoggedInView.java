package net.coolcoders.showcase.components;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.LinkItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.showcase.client.UserInfoResponse;
import net.coolcoders.showcase.event.ShowViewEvent;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class ShowcaseLoggedInView extends ShowcaseBaseView {

    private final UserInfoResponse userInfo;

    public ShowcaseLoggedInView(GwtActionServiceAsync actionServiceAsync, UserInfoResponse userInfo) {
        super(actionServiceAsync);
        this.userInfo = userInfo;
        addLogoutPanel();
    }

    private void addLogoutPanel() {
        DynamicForm form = new DynamicForm();
        form.setMargin(20);
        form.setNumCols(1);
        LinkItem profileLink = new LinkItem();
        profileLink.setShowTitle(false);
        profileLink.setLinkTitle(userInfo.getFullname());
        profileLink.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                fireEvent(new ShowViewEvent(ShowViewEvent.ViewToShow.PROFILE));
            }
        });
        LinkItem logoutLink = new LinkItem();
        logoutLink.setLinkTitle("Logout");
        logoutLink.setShowTitle(false);
        logoutLink.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent clickEvent) {
                Window.Location.assign("/smartgwtsc/logout");
            }
        });
        form.setFields(profileLink, logoutLink);
        form.setAlign(Alignment.RIGHT);
        headerPanel.addMember(form);
    }
}
