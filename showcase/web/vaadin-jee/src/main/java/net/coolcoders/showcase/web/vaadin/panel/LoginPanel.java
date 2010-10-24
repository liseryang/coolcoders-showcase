package net.coolcoders.showcase.web.vaadin.panel;

import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;
import net.coolcoders.showcase.dao.generic.QueryParameter;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.model.User_;
import net.coolcoders.showcase.web.vaadin.ShowcaseApplication;
import net.coolcoders.showcase.web.vaadin.UiConstants;

import java.util.Arrays;

/**
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *         Date: 23.10.2010
 *         Time: 18:01:11
 */
public class LoginPanel extends VerticalLayout {

    final private ShowcaseApplication application;

    private Form loginForm;

    public LoginPanel(final ShowcaseApplication application) {
        this.application = application;
        addLoginForm();
        addUsersPanel();
    }

    private void addLoginForm() {
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
                    application.setCurrentUser(loggedInUser);
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

    private void addUsersPanel() {
        VerticalLayout usersLayout = new VerticalLayout();
        usersLayout.setStyleName(UiConstants.CSS_CONTENT_PANEL + "marginBottom");
        usersLayout.setWidth(270, Sizeable.UNITS_PIXELS);

        Label lblUsersCaption = new Label("<h3>Following Users exists by default</h3>");
        lblUsersCaption.setSizeUndefined();
        lblUsersCaption.setContentMode(Label.CONTENT_XHTML);
        usersLayout.addComponent(lblUsersCaption);
        usersLayout.setComponentAlignment(lblUsersCaption, Alignment.MIDDLE_CENTER);

        Label lblUser1 = new Label("abaumgartner / test123");
        lblUser1.setSizeUndefined();
        usersLayout.addComponent(lblUser1);
        usersLayout.setComponentAlignment(lblUser1, Alignment.MIDDLE_CENTER);

        Label lblUser2 = new Label("anerlich / test123");
        lblUser2.setSizeUndefined();
        usersLayout.addComponent(lblUser2);
        usersLayout.setComponentAlignment(lblUser2, Alignment.MIDDLE_CENTER);

        Label lblUser3 = new Label("jmihelko / test123");
        lblUser3.setSizeUndefined();
        usersLayout.addComponent(lblUser3);
        usersLayout.setComponentAlignment(lblUser3, Alignment.MIDDLE_CENTER);

        Label lblUser4 = new Label("pschneider-manzell / test123 ");
        lblUser4.setSizeUndefined();
        usersLayout.addComponent(lblUser4);
        usersLayout.setComponentAlignment(lblUser4, Alignment.MIDDLE_CENTER);

        this.addComponent(usersLayout);
        this.setComponentAlignment(usersLayout, Alignment.MIDDLE_CENTER);

    }

}
