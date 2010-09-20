package net.coolcoders.smartgwt.views;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.smartgwt.client.LoginAction;
import net.coolcoders.smartgwt.client.LoginResponse;
import net.coolcoders.smartgwt.client.ShowCaseUi;
import net.coolcoders.smartgwt.client.ViewConstants;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class LoginView extends ShowcaseBaseView implements ClickHandler {
    private ShowCaseUi ui;
    DynamicForm loginForm;
    private Button login, register;

    public LoginView(GwtActionServiceAsync actionServiceAsync, ShowCaseUi showCaseUi) {
        super(actionServiceAsync);
        this.ui = showCaseUi;
        initWidgets();
        layout();
    }

    private void initWidgets() {
        //a login form
        loginForm = new DynamicForm();
        TextItem username = new TextItem("Username", "Username");
        PasswordItem password = new PasswordItem("Password", "Password");
        loginForm.setFields(username, password);
        loginForm.setAutoWidth();
        loginForm.setAlign(Alignment.CENTER);
        //login and register-button
        login = new Button("Login");
        login.addClickHandler(this);
        login.setAlign(Alignment.CENTER);
        login.setWidth(120);
        register = new Button("Register");
        register.addClickHandler(this);
        register.setAlign(Alignment.CENTER);
        register.setWidth(120);
    }

    private void layout() {
        HLayout buttonLayout = new HLayout(ViewConstants.STD_MEMBERS_MARGIN);
        buttonLayout.addMember(register);
        buttonLayout.addMember(login);
        buttonLayout.setAlign(Alignment.CENTER);
        HLayout formLayout = new HLayout(ViewConstants.STD_MEMBERS_MARGIN);
        formLayout.addMember(loginForm);
        formLayout.setAlign(Alignment.CENTER);
        VLayout layout = new VLayout(ViewConstants.STD_MEMBERS_MARGIN);
        layout.setHeight100();
        layout.setAlign(Alignment.CENTER);
        layout.addMember(formLayout);
        layout.addMember(buttonLayout);
        addMember(layout);

    }

    public void onClick(ClickEvent clickEvent) {
        Button source = (Button) clickEvent.getSource();
        if (login.equals(source)) {
            doLogin();
        } else if (register.equals(source)) {
            ui.showRegisterView();
        }
    }

    private void doLogin() {
        String username = (String) loginForm.getItem("Username").getValue();
        String passwd = (String) loginForm.getItem("Password").getValue();
        if (username == null || passwd == null) {
            SC.say("Username AND Password needed !");
            return;
        }
        LoginAction action = new LoginAction(username, passwd);
        actionService.execute(action, new AsyncCallback<LoginResponse>() {
            public void onFailure(Throwable caught) {
                SC.say("An error occured !" + caught.getMessage());
            }

            public void onSuccess(LoginResponse result) {
                if (result.isSuccessful()) {
                    ui.loginSuccessful();
                } else {
                    SC.say(result.getMessage());
                }
            }
        });
    }

}
