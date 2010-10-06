package net.coolcoders.showcase.components;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.layout.VLayout;
import net.coolcoders.showcase.client.ShowcaseConstants;
import net.coolcoders.showcase.data.MessagesDataSource;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class AddMessagePanel extends Canvas {
    private final ShowcaseConstants constants = GWT.create(ShowcaseConstants.class);
    final MessagesDataSource dataSource;
    private final TextAreaItem editor = new TextAreaItem();
    private final Button sendButton = new ShowcaseBaseButton(constants.button_send());

    public AddMessagePanel(MessagesDataSource ds) {
        this.dataSource = ds;
        initMySelf();
    }

    private void initMySelf() {
        setWidth(400);
        setAlign(Alignment.CENTER);
        setBackgroundColor("#f0ffff");
        editor.setWidth(300);
        editor.setHeight(150);
        DynamicForm form = new DynamicForm();
        form.setFields(editor);
        Label label = new Label(constants.message_create_label());
        label.setAlign(Alignment.CENTER);
        label.setWidth(400);
        VLayout layout = new VLayout();
        layout.addMember(label);
        layout.addMember(form);
        layout.addMember(sendButton);
        addChild(layout);
    }
}
