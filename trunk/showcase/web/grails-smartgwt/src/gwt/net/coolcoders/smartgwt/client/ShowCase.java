package net.coolcoders.smartgwt.client;

import com.google.gwt.core.client.EntryPoint;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.smartgwt.action.LoginCallback;
import net.coolcoders.smartgwt.views.LoginView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ShowCase implements EntryPoint {
    private GwtActionServiceAsync actionService;
    private LoginView loginView;
    private LoginCallback loginCallback;

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        new ShowCaseUi();
    }
}
