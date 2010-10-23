package net.coolcoders.showcase.web.vaadin;

import com.vaadin.Application;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.service.CategoryService;
import net.coolcoders.showcase.service.MessageService;
import net.coolcoders.showcase.service.UserService;
import net.coolcoders.showcase.web.vaadin.fieldFactory.UserFieldFactory;
import net.coolcoders.showcase.web.vaadin.panel.*;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

/**
 * @author andreas
 */
@SessionScoped
public class ShowcaseApplication extends Application {

    private static final long serialVersionUID = 6995787607833385105L;

    private static final String WINDOW_CAPTION = "COOL CODERS SHOWCASE with Vaadin";

    private Window mainWindow;

    private VerticalLayout mainPanel;

    private GridLayout headerPanel;

    private GridLayout footerPanel;

    private VerticalLayout contentPanel;

    private User currentUser = new User();

    @EJB
    private CategoryService categoryService;

    @EJB
    private UserService userService;

    @EJB
    private MessageService messageService;

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
        mainWindow = new Window(WINDOW_CAPTION);
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

    public void clearContentPanel() {
        contentPanel.removeAllComponents();
    }

    public void goToLoginPanel() {
        clearContentPanel();
        contentPanel.addComponent(new LoginPanel(this));
    }

    public void goToRegisterPanel() {
        clearContentPanel();
        contentPanel.addComponent(new RegisterPanel(this));
    }

    public void goToMessagesPanel() {
        clearContentPanel();
        contentPanel.addComponent(new MessagesPanel(this));
    }

    public UserFieldFactory getUserFieldFactory() {
        if(userFieldFactory == null) {
            userFieldFactory = new UserFieldFactory(categoryService);
        }
        return userFieldFactory;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public UserService getUserService() {
        return userService;
    }

    public MessageService getMessageService() {
        return messageService;
    }

}
