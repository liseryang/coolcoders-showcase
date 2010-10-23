package net.coolcoders.showcase.web.vaadin.panel;

import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;
import net.coolcoders.showcase.dao.generic.QueryParameter;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.model.User_;
import net.coolcoders.showcase.web.vaadin.ShowcaseApplication;

import java.util.Arrays;

/**
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *         Date: 23.10.2010
 *         Time: 18:01:11
 */
public class LoginPanel extends VerticalLayout {

    public LoginPanel(final ShowcaseApplication application) {
        final User currentUser = application.getCurrentUser();
        final Form loginForm = new Form();
        loginForm.setStyleName("marginTop");
        loginForm.setCaption("Login");
        loginForm.setWidth(260, Sizeable.UNITS_PIXELS);

        BeanItem<User> userItem = new BeanItem<User>(currentUser);
        loginForm.setItemDataSource(userItem);
        loginForm.setFormFieldFactory(application.getUserFieldFactory());
        loginForm.setVisibleItemProperties(Arrays.asList("username", "password"));

        this.addComponent(loginForm);
        this.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

        // The cancel / apply btnPanel
        HorizontalLayout btnPanel = new HorizontalLayout();
        btnPanel.setStyleName("marginBottom");
        btnPanel.setWidth(160, Sizeable.UNITS_PIXELS);
        btnPanel.setSpacing(true);
        Button loginBtn = new Button("Login", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                loginForm.commit();
                User loggedInUser = application.getUserService().find(
                        QueryParameter.with(User_.username, currentUser.getUsername()).
                                and(User_.password, currentUser.getPassword()));
                if(loggedInUser != null) {
                    application.setUser(loggedInUser);
                    application.goToMessagesPanel();
                }
            }
        });
        btnPanel.addComponent(loginBtn);
        Button registerBtn = new Button("Register", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                application.goToRegisterPanel();
            }
        });
        btnPanel.addComponent(registerBtn);
        this.addComponent(btnPanel);
        this.setComponentAlignment(btnPanel, Alignment.MIDDLE_CENTER);
    }
}
