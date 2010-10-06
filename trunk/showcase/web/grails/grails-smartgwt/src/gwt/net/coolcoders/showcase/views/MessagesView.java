package net.coolcoders.showcase.views;

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tile.TileGrid;
import com.smartgwt.client.widgets.viewer.DetailViewerField;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.showcase.client.UserInfoResponse;
import net.coolcoders.showcase.components.AddMessagePanel;
import net.coolcoders.showcase.components.ShowcaseBaseView;
import net.coolcoders.showcase.data.MessagesDataSource;


/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class MessagesView extends ShowcaseBaseView {
    private final TileGrid tileGrid = new TileGrid();
    private final Criteria currentCriteria = new Criteria();
    private final MessagesDataSource dataSource;
    private final AddMessagePanel addMessagePanel;

    public MessagesView(GwtActionServiceAsync actionServiceAsync, UserInfoResponse userInfo, final MessagesDataSource ds) {
        super(actionServiceAsync);
        this.dataSource = ds;
        this.addMessagePanel = new AddMessagePanel(dataSource);
        initMySelf();
        layoutMySelf();
    }

    private void initMySelf() {
        currentCriteria.addCriteria("_startRow", 0);
        currentCriteria.addCriteria("_endRow", 10);
        tileGrid.setWidth(800);
        tileGrid.setHeight(800);

        tileGrid.setTileWidth(750);
        tileGrid.setTileHeight(200);
        tileGrid.setShowAllRecords(true);
        tileGrid.setDataSource(dataSource);
        tileGrid.filterData(currentCriteria);
        DetailViewerField field = new DetailViewerField("content");
        tileGrid.setFields(field);
    }

    private void layoutMySelf() {
        tileGrid.setAlign(Alignment.CENTER);
        VLayout layout = getVerticalLayout();
        layout.addMember(addMessagePanel);
        layout.addMember(tileGrid);
        addMember(layout);
    }

}
