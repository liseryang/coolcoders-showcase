package net.coolcoders.smartgwt.action;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import net.coolcoders.smartgwt.client.LoginResponse;
import net.coolcoders.smartgwt.views.LoginView;
import net.coolcoders.smartgwt.views.MessagesView;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class LoginCallback implements AsyncCallback<LoginResponse> {
    private LoginView loginView;
    private MessagesView messagesView;

    public void onFailure(Throwable caught) {
        SC.say("An error occurred ! " + caught.getMessage());
    }

    public void onSuccess(LoginResponse result) {
        if (result.isSuccessful()) {
            loginView.hide();
            messagesView.draw();
        } else {
            SC.say(result.getMessage());
        }
    }
}
