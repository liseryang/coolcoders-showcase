package net.coolcoders.showcase.components;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.layout.VLayout;
import net.coolcoders.showcase.client.ShowcaseConstants;
import net.coolcoders.showcase.data.MessagesDataSource;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class AddMessagePanel extends VLayout implements ClickHandler {
    private final ShowcaseConstants constants = GWT.create(ShowcaseConstants.class);
    final MessagesDataSource dataSource;
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
        editor.setWidth(300);
        editor.setHeight(150);
        form.setTitleOrientation(TitleOrientation.TOP);
        form.setFields(editor);
        sendButton.addClickHandler(this);
        addMember(form);
        addMember(sendButton);
    }

    public void onClick(ClickEvent clickEvent) {
        SC.say("About to send !");
    }
}
