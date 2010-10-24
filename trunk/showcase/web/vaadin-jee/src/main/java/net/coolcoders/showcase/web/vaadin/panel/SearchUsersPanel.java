package net.coolcoders.showcase.web.vaadin.panel;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;
import net.coolcoders.showcase.dao.generic.QueryParameter;
import net.coolcoders.showcase.dao.generic.QueryParameterEntry;
import net.coolcoders.showcase.model.User;
import net.coolcoders.showcase.model.User_;
import net.coolcoders.showcase.web.vaadin.ShowcaseApplication;
import net.coolcoders.showcase.web.vaadin.UiConstants;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *         Date: 24.10.2010
 *         Time: 12:47:30
 */
public class SearchUsersPanel extends VerticalLayout {

    private ShowcaseApplication application;

    private List<User> users = new ArrayList<User>();

    private TextField tfSearch;

    private VerticalLayout usersLayout;
    
    private Table tblUsers;

    public SearchUsersPanel(ShowcaseApplication application) {
        this.application = application;
        addSearchPanel();

        usersLayout = new VerticalLayout();
        usersLayout.setStyleName(UiConstants.CSS_CONTENT_PANEL + "marginTop");
        this.addComponent(usersLayout);

        addUsersTable();
    }

    private void addSearchPanel() {
        VerticalLayout searchLayout = new VerticalLayout();
        this.addComponent(searchLayout);
        searchLayout.setStyleName(UiConstants.CSS_CONTENT_PANEL);

        VerticalLayout searchCaptionLayout = new VerticalLayout();
        searchCaptionLayout.setStyleName(UiConstants.CSS_HEADER_PANEL);
        Label lblSearchCaption = new Label("Search for user");
        lblSearchCaption.setSizeUndefined();
        searchCaptionLayout.addComponent(lblSearchCaption);
        searchCaptionLayout.setComponentAlignment(lblSearchCaption, Alignment.MIDDLE_CENTER);

        searchLayout.addComponent(searchCaptionLayout);

        HorizontalLayout searchInputLayout = new HorizontalLayout();
        searchInputLayout.setStyleName("marginTop marginBottom");
        searchInputLayout.setWidth(300, Sizeable.UNITS_PIXELS);

        tfSearch = new TextField();
        tfSearch.setImmediate(true);
        searchInputLayout.addComponent(tfSearch);

        Button btnSearch = new Button("Search");
        btnSearch.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                search((String) tfSearch.getValue());
                refreshUsersTable();
            }
        });
        searchInputLayout.addComponent(btnSearch);

        searchLayout.addComponent(searchInputLayout);
        searchLayout.setComponentAlignment(searchInputLayout, Alignment.MIDDLE_CENTER);
    }

    private void addUsersTable() {
        BeanItemContainer<User> container = new BeanItemContainer<User>(User.class);
        for (User user : users) {
            container.addBean(user);
        }
        tblUsers = new Table();
        tblUsers.setWidth(100, Sizeable.UNITS_PERCENTAGE);
        tblUsers.setHeight(200, Sizeable.UNITS_PIXELS);
        tblUsers.setSelectable(true);
        tblUsers.setImmediate(true);

        tblUsers.addContainerProperty("messageCount", String.class, "", "Posts", null, Table.ALIGN_CENTER);
        tblUsers.addContainerProperty("action", Component.class, null, "Action", null, Table.ALIGN_CENTER);
        tblUsers.addGeneratedColumn("action", new Table.ColumnGenerator() {
            @Override
            public Component generateCell(final Table source, final Object itemId, final Object columnId) {
                final User user = (User) itemId;
                Button btn;
                if(application.getCurrentUser().getFollowing().contains(user)) {
                    btn = new Button("Unfollow");
                    btn.addListener(new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent clickEvent) {
                            unfollow(user);
                            refreshUsersTable();
                        }
                    });
                } else {
                    btn = new Button("Follow");
                    btn.addListener(new Button.ClickListener() {
                        @Override
                        public void buttonClick(Button.ClickEvent clickEvent) {
                            follow(user);
                            refreshUsersTable();
                        }
                    });
                }
                return btn;
            }
        });
        tblUsers.setContainerDataSource(container);
        tblUsers.setVisibleColumns(new String[] {"username", "messageCount", "action"});
        usersLayout.addComponent(tblUsers);
    }

    public void refreshUsersTable() {
        usersLayout.removeAllComponents();
        addUsersTable();
    }

    public void search(String searchString) {
        users = application.getUserService().list(QueryParameter.with(User_.username, QueryParameterEntry.Operator.STARTS, searchString),
                null);
        // remove logged in user from result
        users.remove(application.getCurrentUser());
    }

    public void follow(User user) {
        User currentUser = application.getCurrentUser();
        currentUser.getFollowing().add(user);
        application.getUserService().merge(currentUser);
    }

    public void unfollow(User user) {
        User currentUser = application.getCurrentUser();
        currentUser.getFollowing().remove(user);
        application.getUserService().merge(currentUser);
    }

}
