package net.coolcoders.smartgwt.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.layout.HLayout;
import grails.plugins.gwt.client.GwtActionService;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.smartgwt.components.ShowcaseBaseView;
import net.coolcoders.smartgwt.views.LoginView;
import net.coolcoders.smartgwt.views.MessagesView;
import net.coolcoders.smartgwt.views.RegisterView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class ShowCaseUi extends HLayout {
    private GwtActionServiceAsync actionService;
    private LoginView loginView;
    private RegisterView registerView;
    private MessagesView messagesView;
    private List<ShowcaseBaseView> views = new ArrayList<ShowcaseBaseView>();

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
                loginView.show();
            }
        });
    }

    /**
     * Init all the views the "Old School DI-Way" ;) .
     */
    private void initViews(LoadLookupDataResponse lookupData) {
        messagesView = new MessagesView(actionService, this);
        messagesView.draw();
        loginView = new LoginView(actionService, this);
        loginView.draw();
        registerView = new RegisterView(actionService, this, lookupData.getCategoriesMap());
        registerView.draw();
        views.add(loginView);
        views.add(registerView);
        views.add(messagesView);
        hideAllViews();
    }

    public void loginSuccessful() {
        hideAllViews();
        messagesView.show();
    }

    public void showRegisterView() {
        hideAllViews();
        registerView.show();
    }

    public void showLoginView() {
        hideAllViews();
        loginView.show();
    }

    private void hideAllViews() {
        for (ShowcaseBaseView view : views) {
            view.hide();
        }
    }
}
