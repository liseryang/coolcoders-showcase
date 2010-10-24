package net.coolcoders.showcase.web.vaadin.panel;

import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Button;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.web.vaadin.ShowcaseApplication;
import net.coolcoders.showcase.web.vaadin.UiConstants;
import net.coolcoders.showcase.web.vaadin.template.HorizontalLayoutCentered;

import java.util.List;


/**
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *         Date: 24.10.2010
 *         Time: 11:18:05
 */
public class FriendsPanel extends VerticalLayout {

    private ShowcaseApplication application;

    private User selectedUser;
    private List<User> users;
    private Button btnUnfollow;
    private HorizontalLayoutCentered btnLayout;
    private VerticalLayout friendsLayout;

    public FriendsPanel(final ShowcaseApplication application) {
        this.application = application;
        this.setStyleName(UiConstants.CSS_CONTENT_PANEL + "marginTop");
        this.setSpacing(true);

        friendsLayout = new VerticalLayout();
        this.addComponent(friendsLayout);
        addFriendsTable();

        btnLayout = new HorizontalLayoutCentered(80);
        this.addComponent(btnLayout);
        addUnfollowButton();

        addNavPanel();
    }

    private void addNavPanel() {
        HorizontalLayoutCentered navLayout = new HorizontalLayoutCentered(160);
        navLayout.setStyleName("marginTop marginBottom");

        Button btnGotoMessages = new Button("Messages");
        btnGotoMessages.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                application.goToMessagesPanel();
            }
        });
        navLayout.getContentLayout().addComponent(btnGotoMessages);

        Button btnGotoSearch = new Button("Search");
        btnGotoSearch.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                application.goToSearchUsersPanel();
            }
        });
        navLayout.getContentLayout().addComponent(btnGotoSearch);
        
        this.addComponent(navLayout);
    }

    private void addFriendsTable() {
        users = application.getUserService().listUsersYouFollow(application.getCurrentUser().getId());
        BeanItemContainer<User> container = new BeanItemContainer<User>(User.class);
        for (User user : users) {
            container.addBean(user);
        }

        Table table = new Table();
        table.setWidth(100, Sizeable.UNITS_PERCENTAGE);
        table.setHeight(200, Sizeable.UNITS_PIXELS);
        table.setSelectable(true);
        table.setImmediate(true);

        table.addContainerProperty("messageCount", String.class, "", "Posts", null, Table.ALIGN_CENTER);
        table.setContainerDataSource(container);
        table.setVisibleColumns(new String[] {"username", "messageCount"});

        table.addListener(new Table.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                selectedUser = (User) valueChangeEvent.getProperty().getValue();
                refreshUnfollowButton();
            }
        });
        friendsLayout.addComponent(table);
    }

    private void refreshFriendsTable() {
        friendsLayout.removeAllComponents();
        addFriendsTable();
    }

    private void addUnfollowButton() {

        btnUnfollow = new Button("Unfollow");
        btnUnfollow.setEnabled(selectedUser != null);
        btnUnfollow.addListener(new Button.ClickListener() {

            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                unfollow();
                refreshFriendsTable();
                refreshUnfollowButton();
            }

        });
        btnLayout.getContentLayout().addComponent(btnUnfollow);
    }

    private void refreshUnfollowButton() {
        btnLayout.getContentLayout().removeComponent(btnUnfollow);
        addUnfollowButton();
    }


    public void unfollow() {
        if(selectedUser != null) {
            User user = application.getCurrentUser();
            user.getFollowing().remove(selectedUser);
            application.getUserService().merge(user);
            users = null;
        }

    }
}
