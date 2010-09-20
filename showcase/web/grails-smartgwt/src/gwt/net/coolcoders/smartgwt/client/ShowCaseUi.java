package net.coolcoders.smartgwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.HLayout;
import grails.plugins.gwt.client.GwtActionService;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.smartgwt.views.LoginView;
import net.coolcoders.smartgwt.views.MessagesView;
import net.coolcoders.smartgwt.views.RegisterView;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class ShowCaseUi extends HLayout {
    private GwtActionServiceAsync actionService;
    private LoginView loginView;
    private RegisterView registerView;
    private MessagesView messagesView;

    public ShowCaseUi() {
        initActionService();
        initLookupData();
    }


    /**
     * Initialize any needed async services.
     */
    private void initActionService() {
        actionService = GWT.create(GwtActionService.class);
        ((ServiceDefTarget) actionService).setServiceEntryPoint(GWT.getModuleBaseURL() + "rpc");
    }

    /**
     * Fetch lookup data needed by the app.
     */
    private void initLookupData() {
        actionService.execute(new LoadLookupDataAction(), new AsyncCallback<LoadLookupDataResponse>() {
            public void onFailure(Throwable caught) {
                //this error would suck quite a bit!
                SC.say("Error loading required lookup data! " + caught.getMessage());
            }

            public void onSuccess(LoadLookupDataResponse result) {
                //required lookup data loaded -> build the ui
                initViews(result);
                loginView.draw();
            }
        });
    }

    /**
     * Init all the views.
     */
    private void initViews(LoadLookupDataResponse lookupData) {
        messagesView = new MessagesView();
        loginView = new LoginView(actionService, this);
        registerView = new RegisterView(actionService, lookupData.getCategoriesMap());

    }

    public void loginSuccessful() {
        loginView.hide();
        messagesView.show();
    }

    public void showRegisterView() {
        loginView.hide();
        registerView.draw();
    }
}
