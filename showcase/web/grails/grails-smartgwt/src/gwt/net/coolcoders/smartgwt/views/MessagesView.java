package net.coolcoders.smartgwt.views;

import com.google.gwt.user.client.Window;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.grid.ListGrid;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.smartgwt.components.ShowcaseBaseView;
import net.coolcoders.smartgwt.data.MessagesDataSource;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class MessagesView extends ShowcaseBaseView {

    public MessagesView(GwtActionServiceAsync actionServiceAsync) {
        super(actionServiceAsync);
        addMember(new Label("MessagesView!"));
        String userId = Window.Location.getParameter("userId");
        SC.say("Welcome USer with id=" + userId);
        MessagesDataSource ds = MessagesDataSource.getInstance(userId);
        ListGrid grid = new ListGrid();
        grid.setDataSource(ds);
        addMember(grid);
    }
}
