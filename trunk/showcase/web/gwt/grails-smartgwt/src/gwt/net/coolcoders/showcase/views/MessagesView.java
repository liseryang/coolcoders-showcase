package net.coolcoders.showcase.views;

import com.smartgwt.client.data.*;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tile.TileGrid;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.showcase.client.UserInfoResponse;
import net.coolcoders.showcase.components.ShowcaseBaseButton;
import net.coolcoders.showcase.components.ShowcaseLoggedInView;
import net.coolcoders.showcase.data.MessagesDataSource;


/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class MessagesView extends ShowcaseLoggedInView implements ClickHandler {
    private final TileGrid tileGrid;
    private final Criteria currentCriteria = new Criteria();
    private final MessagesDataSource dataSource;
    private final DynamicForm form = new DynamicForm();
    private final TextAreaItem editor = new TextAreaItem("content", constants.message_create_label());
    private final Button sendButton = new ShowcaseBaseButton(constants.button_send());


    public MessagesView(GwtActionServiceAsync actionServiceAsync, UserInfoResponse userInfo, final MessagesDataSource ds) {
        super(actionServiceAsync, userInfo);
        this.dataSource = ds;
        tileGrid = new TileGrid() {
            @Override
            protected String getTileHTML(Record record) {
                return "<div class=\"messageEntry\">" + record.getAttribute("creator") + ": " + record.getAttribute("content") + "</div>";
            }
        };
        initMySelf();
        layoutMySelf();
    }

    private void initMySelf() {
        currentCriteria.addCriteria("_startRow", 0);
        currentCriteria.addCriteria("_endRow", 10);

        tileGrid.setInitialCriteria(currentCriteria);
        tileGrid.setWidth(800);
        tileGrid.setHeight(800);
        tileGrid.setTileWidth(750);
        tileGrid.setTileHeight(70);
        tileGrid.setAlign(Alignment.CENTER);
        tileGrid.setDataSource(dataSource);
        tileGrid.setAutoFetchData(false);
        tileGrid.filterData(currentCriteria);

        editor.setTitleStyle("messageHeading");
        editor.setWidth(400);
        editor.setHeight(50);
        editor.setRequired(true);
        form.setTitleOrientation(TitleOrientation.TOP);
        form.setFields(editor);
        form.setDataSource(dataSource);
        form.setAlign(Alignment.CENTER);
        form.setAutoWidth();
        sendButton.addClickHandler(this);

    }

    private void layoutMySelf() {
        VLayout formContainer = new VLayout(ViewConstants.STD_MEMBERS_MARGIN);
        formContainer.setWidth(800);
        formContainer.setDefaultLayoutAlign(Alignment.CENTER);
        formContainer.setPadding(20);
        formContainer.setShowEdges(true);
        formContainer.setBackgroundColor(ViewConstants.STD_DIALOG_BG_COLOR);
        formContainer.addMember(form);
        formContainer.addMember(sendButton);

        addMember(formContainer);
        addMember(tileGrid);
    }

    public void onClick(ClickEvent clickEvent) {
        if (form.validate()) {
            form.saveData(new DSCallback() {
                public void execute(DSResponse dsResponse, Object o, DSRequest dsRequest) {
                    form.editNewRecord();
                    tileGrid.sortByProperty("created", false);
                }
            });
        }
    }
}
