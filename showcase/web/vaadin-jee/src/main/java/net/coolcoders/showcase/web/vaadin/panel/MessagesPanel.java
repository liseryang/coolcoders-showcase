package net.coolcoders.showcase.web.vaadin.panel;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.web.vaadin.ShowcaseApplication;

import java.util.Date;

/**
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *         Date: 23.10.2010
 *         Time: 18:20:41
 */
public class MessagesPanel extends VerticalLayout {

    private ShowcaseApplication application;

    private Message message = new Message();

    public MessagesPanel(final ShowcaseApplication application) {
        this.application = application;
        this.setWidth(300, Sizeable.UNITS_PIXELS);
        final TextArea textArea = new TextArea("What cool code are you hacking right now?");
        textArea.setWidth(230, Sizeable.UNITS_PIXELS);
        textArea.setHeight(50, Sizeable.UNITS_PIXELS);
        textArea.setImmediate(true);
        this.addComponent(textArea);
        this.setComponentAlignment(textArea, Alignment.MIDDLE_CENTER);

        Button btnSend = new Button("Send");
        btnSend.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                message.setCreated(new Date());
                message.setContent((String) textArea.getValue());
                application.getMessageService().persist(message);
                initMessage();
            }
        });

        this.addComponent(btnSend);
        this.setComponentAlignment(btnSend, Alignment.MIDDLE_CENTER);

    }

    private void initMessage() {
        message = new Message();
        message.setAuthor(application.getCurrentUser());
        message.setContent("");
    }

}
