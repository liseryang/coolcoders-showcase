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
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Showcase extends BaseEntryPoint implements EntryPoint, AsyncCallback<LoadLookupDataResponse> {

    private LinkedHashMap<String, String> categoriesMap;

    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        super.onModuleLoad();
        loadLookupData();
    }

    private void loadLookupData() {
        LoadLookupDataAction loadLookupDataAction = new LoadLookupDataAction();
        actionService.execute(loadLookupDataAction, this);

    }

    public void onFailure(Throwable caught) {
        SC.say("Oh fuck !" + caught.getMessage());
    }

    public void onSuccess(LoadLookupDataResponse loadLookupDataResponse) {
        categoriesMap = loadLookupDataResponse.getCategoriesMap();

        UserInfoAction action = new UserInfoAction();
        actionService.execute(action, new AsyncCallback<UserInfoResponse>() {
            public void onFailure(Throwable caught) {
                SC.say("Oh fuck !" + caught.getMessage());
            }

            public void onSuccess(UserInfoResponse userInfoResponse) {
                MessagesDataSource ds = MessagesDataSource.getInstance();
                MessagesView messagesView = new MessagesView(actionService, userInfoResponse, ds);
                messagesView.draw();
                ProfileView profileView = new ProfileView(actionService, userInfoResponse, categoriesMap);
                profileView.draw();
                profileView.hide();
                UserListView userListView = new UserListView(actionService, userInfoResponse);
                userListView.draw();
                userListView.hide();

                Map<ShowViewEvent.ViewToShow, ShowcaseBaseView> theViews = new HashMap<ShowViewEvent.ViewToShow, ShowcaseBaseView>();
                theViews.put(ShowViewEvent.ViewToShow.MESSAGES, messagesView);
                theViews.put(ShowViewEvent.ViewToShow.PROFILE, profileView);
                theViews.put(ShowViewEvent.ViewToShow.USERLIST, userListView);
                new ShowViewEventHandler(theViews);
            }
        });
    }
}
