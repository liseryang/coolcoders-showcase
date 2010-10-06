package net.coolcoders.showcase.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import net.coolcoders.showcase.data.MessagesDataSource;
import net.coolcoders.showcase.views.MessagesView;

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
    }
}
