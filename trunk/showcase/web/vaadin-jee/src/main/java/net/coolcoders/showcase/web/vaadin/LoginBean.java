package net.coolcoders.showcase.web.vaadin;

import com.vaadin.Application;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;
import net.coolcoders.showcase.model.User;

import javax.enterprise.context.SessionScoped;

/**
 *
 * @author andreas
 */
@SessionScoped
public class LoginBean extends Application {

	private static final long serialVersionUID = 6995787607833385105L;

    public static final String THEME = "mytheme";

	private static final String COMMON_FIELD_WIDTH = "12em";

	private Window mainWindow;

	private VerticalLayout mainPanel;

	private User user = new User();

    @Override
	public void init() {
		initMainWindow();
		initMainPanel();
		initHeader();

		final VerticalLayout contentPanel = new VerticalLayout();
		contentPanel.addStyleName("ui-widget ui-widget-content ui-corner-all marginBottom");
		contentPanel.setWidth(800, Sizeable.UNITS_PIXELS);
		mainPanel.addComponent(contentPanel);
		mainPanel.setComponentAlignment(contentPanel, Alignment.MIDDLE_CENTER);

		BeanItem<User> userItem = new BeanItem<User>(user);

		Form loginForm = new Form(new VerticalLayout(), new UserFieldFactory());
		loginForm.setCaption("Login Form");

		loginForm.setItemDataSource(userItem);
		loginForm.setWidth(800, Sizeable.UNITS_PIXELS);
		loginForm.setVisibleItemProperties(java.util.Arrays.asList(new String[] {"username", "password"}));

		contentPanel.addComponent(loginForm);
		contentPanel.setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
	}

	private void initMainWindow() {
		mainWindow = new Window("Vaadin Example");
		setTheme(THEME);
		setMainWindow(mainWindow);
	}

	private void initMainPanel() {
		mainPanel = new VerticalLayout();
		mainWindow.addComponent(mainPanel);
		mainPanel.addStyleName("ui-widget ui-widget-content ui-corner-all");
		mainPanel.setWidth(820, Sizeable.UNITS_PIXELS);
		mainPanel.setSpacing(true);
	}

	private void initHeader() {
		final VerticalLayout headerPanel = new VerticalLayout();
		headerPanel
				.setStyleName("ui-widget ui-widget-header ui-corner-all marginTop");
		headerPanel.setWidth(800, Sizeable.UNITS_PIXELS);

		Label headerCaption = new Label("<h1>Cool Coders Showcase</h1>");
		headerCaption.setContentMode(Label.CONTENT_XHTML);
		headerPanel.addComponent(headerCaption);

		mainPanel.addComponent(headerPanel);
		mainPanel.setComponentAlignment(headerPanel, Alignment.MIDDLE_CENTER);
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
			} else if ("password".equals(propertyId)) {
				TextField tf = (TextField) f;
				tf.setRequired(true);
				tf.setRequiredError("Please enter your Password");
				tf.setWidth(COMMON_FIELD_WIDTH);
			}
			return f;
		}
	}
}
