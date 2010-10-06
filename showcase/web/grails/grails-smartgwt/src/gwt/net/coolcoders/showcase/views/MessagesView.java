package net.coolcoders.showcase.views;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VStack;
import com.smartgwt.client.widgets.tile.TileGrid;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.showcase.client.UserInfoResponse;
import net.coolcoders.showcase.components.AddMessagePanel;
import net.coolcoders.showcase.components.ShowcaseBaseView;
import net.coolcoders.showcase.data.MessagesDataSource;


/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class MessagesView extends ShowcaseBaseView {
    private final TileGrid tileGrid;
    private final Criteria currentCriteria = new Criteria();
    private final MessagesDataSource dataSource;
    private final AddMessagePanel addMessagePanel;

    public MessagesView(GwtActionServiceAsync actionServiceAsync, UserInfoResponse userInfo, final MessagesDataSource ds) {
        super(actionServiceAsync);
        this.dataSource = ds;
        this.addMessagePanel = new AddMessagePanel(dataSource);
        addMessagePanel.setAlign(Alignment.CENTER);
        tileGrid = new TileGrid() {
            @Override
            protected String getTileHTML(Record record) {
                return "<div class=\"messageEntry\">" + record.getAttribute("content") + "</div>";
            }
        };
        initMySelf();
        layoutMySelf();
    }

    private void initMySelf() {
        currentCriteria.addCriteria("_startRow", 0);
        currentCriteria.addCriteria("_endRow", 10);
        tileGrid.setWidth(800);
        tileGrid.setHeight(800);
        tileGrid.setTileWidth(750);
        tileGrid.setTileHeight(75);
        tileGrid.setAlign(Alignment.CENTER);
        tileGrid.setDataSource(dataSource);
        tileGrid.filterData(currentCriteria);
    }

    private void layoutMySelf() {
        VStack stack = new VStack(20);
        stack.setAlign(Alignment.CENTER);
        stack.addMember(addMessagePanel);
        stack.addMember(tileGrid);
        stack.setAutoWidth();
        HLayout container = new HLayout();
        container.setWidth100();
        container.addMember(stack);
        container.setAlign(Alignment.CENTER);
        addMember(container);
    }

}
