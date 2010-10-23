package net.coolcoders.showcase.components;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.layout.VLayout;
import net.coolcoders.showcase.client.ShowcaseConstants;
import net.coolcoders.showcase.data.MessagesDataSource;
import net.coolcoders.showcase.views.ViewConstants;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class AddMessagePanel extends VLayout implements ClickHandler {
    private final ShowcaseConstants constants = GWT.create(ShowcaseConstants.class);
    private final MessagesDataSource dataSource;
    private final DynamicForm form = new DynamicForm();
    private final TextAreaItem editor = new TextAreaItem("content", constants.message_create_label());
    private final Button sendButton = new ShowcaseBaseButton(constants.button_send());

    public AddMessagePanel(MessagesDataSource ds) {
        super(5);
        this.dataSource = ds;
        initMySelf();
    }


    private void initMySelf() {
        setWidth100();
        setDefaultLayoutAlign(Alignment.CENTER);
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

        VLayout container = new VLayout(ViewConstants.STD_MEMBERS_MARGIN);
        container.setWidth(800);
        container.setDefaultLayoutAlign(Alignment.CENTER);
        container.setPadding(20);
        container.setShowEdges(true);
        container.setBackgroundColor(ViewConstants.STD_DIALOG_BG_COLOR);
        container.addMember(form);
        container.addMember(sendButton);
        addMember(container);
    }

    public void onClick(ClickEvent clickEvent) {
        if (form.validate()) {
            form.saveData();
            form.deselectAllRecords();
        }
    }
}
