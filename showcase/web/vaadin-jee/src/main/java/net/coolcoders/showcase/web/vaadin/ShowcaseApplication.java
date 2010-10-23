package net.coolcoders.showcase.web.vaadin;

import com.vaadin.Application;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;
import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.service.CategoryService;
import net.coolcoders.showcase.service.MessageService;
import net.coolcoders.showcase.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import java.util.Arrays;
import java.util.Date;

/**
 * @author andreas
 */
@SessionScoped
public class ShowcaseApplication extends Application {

    private static final long serialVersionUID = 6995787607833385105L;

    private Window mainWindow;

    private VerticalLayout mainPanel;

    private GridLayout headerPanel;

    private GridLayout footerPanel;

    private VerticalLayout contentPanel;

    private User currentUser = new User();

    private Message message = new Message();

    @EJB
    private CategoryService categoryService;

    @EJB
    private UserService userService;

    @EJB
    private MessageService messageService;

    @EJB
    private UserFieldFactory userFieldFactory;

    @Override
    public void init() {
        initMainWindow();
        initMainPanel();
        initHeaderPanel();
        initContentPanel();
        initFooterPanel();

        goToLoginPanel();
    }

    private void initMainWindow() {
        mainWindow = new Window("COOL CODERS SHOWCASE with Vaadin");
        setMainWindow(mainWindow);
    }

    private void initMainPanel() {
        mainPanel = new VerticalLayout();
        mainWindow.addComponent(mainPanel);
        mainPanel.addStyleName(UiConstants.CSS_CONTENT_PANEL);
        mainPanel.setWidth(UiConstants.MAIN_WIDTH, Sizeable.UNITS_PIXELS);
        mainPanel.setSpacing(true);
    }

    private void initHeaderPanel() {
        headerPanel = new HeaderPanel(mainWindow);
        mainPanel.addComponent(headerPanel);
        mainPanel.setComponentAlignment(headerPanel, Alignment.MIDDLE_CENTER);
    }

    private void initContentPanel() {
        contentPanel = new VerticalLayout();
        contentPanel.addStyleName(UiConstants.CSS_CONTENT_PANEL);
        contentPanel.setWidth(UiConstants.CONTENT_WIDTH, Sizeable.UNITS_PIXELS);
        mainPanel.addComponent(contentPanel);
        mainPanel.setComponentAlignment(contentPanel, Alignment.MIDDLE_CENTER);
    }

    private void initFooterPanel() {
        footerPanel = new FooterPanel();
        mainPanel.addComponent(footerPanel);
        mainPanel.setComponentAlignment(footerPanel, Alignment.MIDDLE_CENTER);
    }

    private void initRegisterPanel() {
        final Form registerForm = new Form();
        registerForm.setStyleName("marginTop");
        registerForm.setCaption("Register");
        registerForm.setWidth(260, Sizeable.UNITS_PIXELS);

        BeanItem<User> userItem = new BeanItem<User>(currentUser);
        registerForm.setItemDataSource(userItem);
        registerForm.setFormFieldFactory(userFieldFactory);
        registerForm.setVisibleItemProperties(Arrays.asList("username", "fullname", "email", "gender", "password", "birthday", "categories"));

        contentPanel.addComponent(registerForm);
        contentPanel.setComponentAlignment(registerForm, Alignment.MIDDLE_CENTER);

        // The cancel / apply btnPanel
        HorizontalLayout btnPanel = new HorizontalLayout();
        btnPanel.setStyleName("marginBottom");
        btnPanel.setWidth(160, Sizeable.UNITS_PIXELS);
        btnPanel.setSpacing(true);
        Button okBtn = new Button("Ok", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                registerForm.commit();
                userService.persist(currentUser);
                contentPanel.removeAllComponents();
            }
        });
        btnPanel.addComponent(okBtn);
        Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                goToLoginPanel();
            }
        });
        btnPanel.addComponent(cancelBtn);
        contentPanel.addComponent(btnPanel);
        contentPanel.setComponentAlignment(btnPanel, Alignment.MIDDLE_CENTER);
    }

    private void initMessage() {
        message = new Message();
        message.setAuthor(currentUser);
        message.setContent("");
    }

    private void initMessagesPanel() {
        initMessage();

        VerticalLayout layout = new VerticalLayout();
        layout.setWidth(300, Sizeable.UNITS_PIXELS);
        final TextArea textArea = new TextArea("What cool code are you hacking right now?");
        textArea.setWidth(230, Sizeable.UNITS_PIXELS);
        textArea.setHeight(50, Sizeable.UNITS_PIXELS);
        textArea.setImmediate(true);
        layout.addComponent(textArea);
        layout.setComponentAlignment(textArea, Alignment.MIDDLE_CENTER);

        Button btnSend = new Button("Send");
        btnSend.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                message.setCreated(new Date());
                message.setContent((String) textArea.getValue());
                messageService.persist(message);
                initMessage();
            }
        });

        layout.addComponent(btnSend);
        layout.setComponentAlignment(btnSend, Alignment.MIDDLE_CENTER);

        contentPanel.addComponent(layout);
        contentPanel.setComponentAlignment(layout, Alignment.MIDDLE_CENTER);
    }

    public void clearContentPanel() {
        contentPanel.removeAllComponents();
    }

    public void goToLoginPanel() {
        clearContentPanel();
        VerticalLayout loginLayout = new LoginPanel(this);
        contentPanel.addComponent(loginLayout);
    }

    public void goToRegisterPanel() {
        clearContentPanel();
        initRegisterPanel();
    }

    public void goToMessagesPanel() {
        clearContentPanel();
        initMessagesPanel();
    }

    public UserService getUserService() {
        return userService;
    }

    public UserFieldFactory getUserFieldFactory() {
        return userFieldFactory;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
