package net.coolcoders.smartgwt.client;

import com.google.gwt.core.client.EntryPoint;
import net.coolcoders.smartgwt.views.LoginView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Login extends BaseEntryPoint implements EntryPoint {
    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        super.onModuleLoad();
        LoginView loginView = new LoginView(actionService);
        loginView.draw();
    }
}
