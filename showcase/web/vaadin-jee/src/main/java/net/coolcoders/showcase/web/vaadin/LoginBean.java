package net.coolcoders.showcase.web.vaadin;

import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;
import net.coolcoders.showcase.dao.generic.QueryParameter;
import net.coolcoders.showcase.model.*;
import net.coolcoders.showcase.service.CategoryService;
import net.coolcoders.showcase.service.MessageService;
import net.coolcoders.showcase.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author andreas
 */
@SessionScoped
public class LoginBean extends Application {

    private static final long serialVersionUID = 6995787607833385105L;

    private static final int MAIN_WIDTH = 820;

    private static final int CONTENT_WIDTH = 800;

    private static final String CSS_CONTENT_PANEL = "ui-widget ui-widget-content ui-corner-all ";

    private static final String CSS_HEADER_PANEL = "ui-widget ui-widget-header ui-corner-all ";

    private static final List<String> THEMES = Arrays.asList("sunny", "redmond");

    private static final String COMMON_FIELD_WIDTH = "12em";


    private String currentTheme = "sunny";

    private Window mainWindow;

    private VerticalLayout mainPanel;

    private GridLayout headerPanel;

    private VerticalLayout contentPanel;

    private GridLayout footerPanel;

    private User user = new User();

    private Message message = new Message();

    @EJB
    private CategoryService categoryService;

    @EJB
    private UserService userService;

    @EJB
    private MessageService messageService;

    @Override
    public void init() {
        initMainWindow();
        initMainPanel();
        initHeaderPanel();
        initContentPanel();
        initFooterPanel();

        initLoginForm();

    }

    private void initMainWindow() {
        mainWindow = new Window("COOL CODERS SHOWCASE with Vaadin");
        setTheme(currentTheme);
        setMainWindow(mainWindow);
    }

    private void initMainPanel() {
        mainPanel = new VerticalLayout();
        mainWindow.addComponent(mainPanel);
        mainPanel.addStyleName(CSS_CONTENT_PANEL);
        mainPanel.setWidth(MAIN_WIDTH, Sizeable.UNITS_PIXELS);
        mainPanel.setSpacing(true);
    }

    private void initHeaderPanel() {
        headerPanel = new GridLayout(2, 1);
        headerPanel
                .setStyleName(CSS_HEADER_PANEL + " marginTop");
        headerPanel.setWidth(CONTENT_WIDTH, Sizeable.UNITS_PIXELS);

        Label headerCaption = new Label("<h1>Cool Coders Showcase</h1>");
        headerCaption.setContentMode(Label.CONTENT_XHTML);
        headerCaption.setWidth(600, Sizeable.UNITS_PIXELS);
        headerPanel.addComponent(headerCaption, 0, 0);

        VerticalLayout themesBoxPanel = new VerticalLayout();
        themesBoxPanel.setWidth(200, Sizeable.UNITS_PIXELS);
        ComboBox themesBox = new ComboBox("Choose your theme!!!", THEMES);
        themesBox.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                currentTheme = (String) valueChangeEvent.getProperty().getValue();
                mainWindow.setTheme(currentTheme);
            }
        });
        themesBox.setImmediate(true);
        themesBoxPanel.addComponent(themesBox);

        headerPanel.addComponent(themesBoxPanel, 1, 0);
        headerPanel.setComponentAlignment(themesBoxPanel, Alignment.MIDDLE_RIGHT);

        mainPanel.addComponent(headerPanel);
        mainPanel.setComponentAlignment(headerPanel, Alignment.MIDDLE_CENTER);
    }

    private void initContentPanel() {
        contentPanel = new VerticalLayout();
        contentPanel.addStyleName(CSS_CONTENT_PANEL);
        contentPanel.setWidth(CONTENT_WIDTH, Sizeable.UNITS_PIXELS);
        mainPanel.addComponent(contentPanel);
        mainPanel.setComponentAlignment(contentPanel, Alignment.MIDDLE_CENTER);
    }

    private void initFooterPanel() {
        footerPanel = new GridLayout(1, 1);
        footerPanel
                .setStyleName(CSS_HEADER_PANEL + "marginTop marginBottom");
        footerPanel.setWidth(CONTENT_WIDTH, Sizeable.UNITS_PIXELS);

        Label footerCaption = new Label("<h3><a href=\"http://vaadin.com/home\">Vaadin</a> Showcase by <a href=\"http://code.google.com/p/coolcoders-showcase/\">Cool Coders</a></h3>");
        footerCaption.setContentMode(Label.CONTENT_XHTML);
        footerCaption.setWidth(260, Sizeable.UNITS_PIXELS);
        footerPanel.addComponent(footerCaption, 0, 0);
        footerPanel.setComponentAlignment(footerCaption, Alignment.MIDDLE_CENTER);

        mainPanel.addComponent(footerPanel);
        mainPanel.setComponentAlignment(footerPanel, Alignment.MIDDLE_CENTER);
    }


    private void initLoginForm() {
        final Form loginForm = new Form();
        loginForm.setStyleName("marginTop");
        loginForm.setCaption("Login");
        loginForm.setWidth(260, Sizeable.UNITS_PIXELS);

        BeanItem<User> userItem = new BeanItem<User>(user);
        loginForm.setItemDataSource(userItem);
        loginForm.setFormFieldFactory(new UserFieldFactory());
        loginForm.setVisibleItemProperties(Arrays.asList("username", "password"));

        contentPanel.addComponent(loginForm);
        contentPanel.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

        // The cancel / apply btnPanel
        HorizontalLayout btnPanel = new HorizontalLayout();
        btnPanel.setStyleName("marginBottom");
        btnPanel.setWidth(160, Sizeable.UNITS_PIXELS);
        btnPanel.setSpacing(true);
        Button loginBtn = new Button("Login", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                loginForm.commit();
                User loggedInUser = userService.find(QueryParameter.with(User_.username, user.getUsername()).and(User_.password, user.getPassword()));
                if(loggedInUser != null) {
                    user = loggedInUser;
                    contentPanel.removeAllComponents();
                    initMessagesForm();
                }
            }
        });
        btnPanel.addComponent(loginBtn);
        Button registerBtn = new Button("Register", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                contentPanel.removeAllComponents();
                initRegisterForm();
            }
        });
        btnPanel.addComponent(registerBtn);
        contentPanel.addComponent(btnPanel);
        contentPanel.setComponentAlignment(btnPanel, Alignment.MIDDLE_CENTER);
    }

    private void initRegisterForm() {
        final Form registerForm = new Form();
        registerForm.setStyleName("marginTop");
        registerForm.setCaption("Register");
        registerForm.setWidth(260, Sizeable.UNITS_PIXELS);

        BeanItem<User> userItem = new BeanItem<User>(user);
        registerForm.setItemDataSource(userItem);
        registerForm.setFormFieldFactory(new UserFieldFactory());
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
                userService.persist(user);
                contentPanel.removeAllComponents();
            }
        });
        btnPanel.addComponent(okBtn);
        Button cancelBtn = new Button("Cancel", new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                contentPanel.removeAllComponents();
                initLoginForm();
            }
        });
        btnPanel.addComponent(cancelBtn);
        contentPanel.addComponent(btnPanel);
        contentPanel.setComponentAlignment(btnPanel, Alignment.MIDDLE_CENTER);
    }

    private void initMessage() {
        message = new Message();
        message.setAuthor(user);
        message.setContent("");
    }

    private void initMessagesForm() {
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

    private class UserFieldFactory extends DefaultFieldFactory {

        public UserFieldFactory() {
        }

        @Override
        public Field createField(Item item, Object propertyId,
                                 Component uiContext) {
            Field f = super.createField(item, propertyId, uiContext);
            if ("username".equals(propertyId)) {
                TextField tf = (TextField) f;
                tf.setRequired(true);
                tf.setRequiredError("Please enter your Username");
                tf.setWidth(COMMON_FIELD_WIDTH);
                tf.setNullRepresentation("");
            } else if ("password".equals(propertyId)) {
                TextField tf = (TextField) f;
                tf.setRequired(true);
                tf.setRequiredError("Please enter your Password");
                tf.setWidth(COMMON_FIELD_WIDTH);
                tf.setNullRepresentation("");
            } else if ("email".equals(propertyId)) {
                TextField tf = (TextField) f;
                tf.setRequired(true);
                tf.setRequiredError("Please enter your Email");
                tf.setWidth(COMMON_FIELD_WIDTH);
                tf.setNullRepresentation("");
            } else if ("fullname".equals(propertyId)) {
                TextField tf = (TextField) f;
                tf.setWidth(COMMON_FIELD_WIDTH);
                tf.setNullRepresentation("");
            } else if ("gender".equals(propertyId)) {
                OptionGroup group = new OptionGroup("Gender", Arrays.asList(Gender.MALE, Gender.FEMALE));
                group.setNullSelectionAllowed(false);
                return group;
            } else if ("categories".equals(propertyId)) {
                List<Category> categories = categoryService.listAll();
                BeanItemContainer<Category> container = new BeanItemContainer<Category>(Category.class);
                for (Category category : categories) {
                    container.addBean(category);
                }
                OptionGroup group = new OptionGroup("Categories", container);
                group.setItemCaptionMode(AbstractSelect.ITEM_CAPTION_MODE_PROPERTY);
                group.setItemCaptionPropertyId("name");

                return group;
            }
            return f;
        }
    }
}
