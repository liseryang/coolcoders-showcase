package net.coolcoders.showcase.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.FormMethod;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.showcase.client.ShowcaseConstants;
import net.coolcoders.showcase.client.ViewConstants;
import net.coolcoders.showcase.components.ShowcaseBaseButton;
import net.coolcoders.showcase.components.ShowcaseBaseView;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class LoginView extends ShowcaseBaseView implements ClickHandler {
    private ShowcaseConstants constants = GWT.create(ShowcaseConstants.class);
    private DynamicForm loginForm;
    private Button login, register;

    public LoginView(GwtActionServiceAsync actionServiceAsync) {
        super(actionServiceAsync);
        String error = Window.Location.getParameter("error");
        initWidgets();
        layout();
        if (error != null) {
            SC.say(constants.login_failed());
        }
    }

    private void initWidgets() {
        //a login form
        loginForm = new DynamicForm();
        TextItem username = new TextItem("username", constants.username_label());
        username.setRequired(true);
        username.setRequiredMessage(constants.loginCommand_username_blank());
        username.setTooltip(constants.username_label());
        PasswordItem password = new PasswordItem("password", constants.password_label());
        password.setRequired(true);
        password.setRequiredMessage(constants.loginCommand_password_blank());
        password.setTooltip(constants.password_label());
        loginForm.setFields(username, password);
        loginForm.setAutoWidth();
        loginForm.setAlign(Alignment.CENTER);
        loginForm.setMethod(FormMethod.POST);
        loginForm.setAction("/smartgwtsc/login/login");
        //login and register-button
        login = new ShowcaseBaseButton(constants.login_label());
        login.addClickHandler(this);
        register = new ShowcaseBaseButton(constants.register_label());
        register.addClickHandler(this);
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
            if (loginForm.validate()) {
                loginForm.submitForm();
            }
        } else if (register.equals(source)) {
            Window.Location.assign("/smartgwtsc/register/index");
        }
    }
}
