package net.coolcoders.showcase.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import net.coolcoders.showcase.components.ShowcaseBaseView;
import net.coolcoders.showcase.data.MessagesDataSource;
import net.coolcoders.showcase.event.ShowViewEvent;
import net.coolcoders.showcase.event.ShowViewEventHandler;
import net.coolcoders.showcase.views.MessagesView;
import net.coolcoders.showcase.views.ProfileView;
import net.coolcoders.showcase.views.UserListView;

import java.util.HashMap;
import java.util.Map;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Showcase extends BaseEntryPoint implements EntryPoint, AsyncCallback<UserInfoResponse> {
    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        super.onModuleLoad();
        getUserInfo();
    }

    private void getUserInfo() {
        UserInfoAction action = new UserInfoAction();
        actionService.execute(action, this);
    }

    public void onFailure(Throwable caught) {
        SC.say("Oh fuck !" + caught.getMessage());
    }

    public void onSuccess(UserInfoResponse result) {
        MessagesDataSource ds = MessagesDataSource.getInstance();
        MessagesView messagesView = new MessagesView(actionService, result, ds);
        messagesView.draw();
        ProfileView profileView = new ProfileView(actionService, result);
        profileView.draw();
        profileView.hide();
        UserListView userListView = new UserListView(actionService, result);
        userListView.draw();
        userListView.hide();

        Map<ShowViewEvent.ViewToShow, ShowcaseBaseView> theViews = new HashMap<ShowViewEvent.ViewToShow, ShowcaseBaseView>();
        theViews.put(ShowViewEvent.ViewToShow.MESSAGES, messagesView);
        theViews.put(ShowViewEvent.ViewToShow.PROFILE, profileView);
        theViews.put(ShowViewEvent.ViewToShow.USERLIST, userListView);
        new ShowViewEventHandler(theViews);

    }
}
