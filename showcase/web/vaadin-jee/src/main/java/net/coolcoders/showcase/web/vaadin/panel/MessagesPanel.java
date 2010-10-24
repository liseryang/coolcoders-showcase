package net.coolcoders.showcase.web.vaadin.panel;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;
import net.coolcoders.showcase.model.Message;
import net.coolcoders.showcase.web.vaadin.ShowcaseApplication;
import net.coolcoders.showcase.web.vaadin.UiConstants;
import net.coolcoders.showcase.web.vaadin.template.HorizontalLayoutCentered;
import net.coolcoders.showcase.web.vaadin.template.MyPanel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *         Date: 23.10.2010
 *         Time: 18:20:41
 */
public class MessagesPanel extends VerticalLayout {

    private ShowcaseApplication application;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");

    private Message message = new Message();

    private List<Message> messages;

    private int firstPage = 0;

    private int stepSize = 5;

    private Long messageCount = 0L;

    private VerticalLayout showMessagesPanel;

    public MessagesPanel(final ShowcaseApplication application) {
        this.application = application;

        addSendMessagePanel();
        addShowMessagesPanel();
        addNavPanel();
    }

    private void addSendMessagePanel() {
        MyPanel sendMessagePanel = new MyPanel("What cool code are you hacking right now?");
        sendMessagePanel.setStyleName("marginTop");
        
        final TextArea textArea = new TextArea();
        textArea.setWidth(230, Sizeable.UNITS_PIXELS);
        textArea.setHeight(50, Sizeable.UNITS_PIXELS);
        textArea.setImmediate(true);
        sendMessagePanel.addComponent(textArea);
        sendMessagePanel.setComponentAlignment(textArea, Alignment.MIDDLE_CENTER);

        Button btnSend = new Button("Send");
        btnSend.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                sendMessage((String) textArea.getValue());
                reloadShowMessagesPanel();
            }
        });

        sendMessagePanel.addComponent(btnSend);
        sendMessagePanel.setComponentAlignment(btnSend, Alignment.MIDDLE_CENTER);

        this.addComponent(sendMessagePanel);
        this.setComponentAlignment(sendMessagePanel, Alignment.MIDDLE_CENTER);
    }

    private void sendMessage(String content) {
        message.setAuthor(application.getCurrentUser());
        message.setCreated(new Date());
        message.setContent(content);
        application.getMessageService().persist(message);
        initMessage();
    }

    private void addShowMessagesPanel() {
        showMessagesPanel = new VerticalLayout();
        showMessagesPanel.setStyleName(UiConstants.CSS_CONTENT_PANEL + "marginTop marginBottom");
        showMessagesPanel.setWidth(600, Sizeable.UNITS_PIXELS);
        showMessagesPanel.setSpacing(true);

        List<Message> messageList = getMessages();
        for (Message msg : messageList) {
            HorizontalLayout msgLayout = new HorizontalLayout();
            msgLayout.setWidth(600, Sizeable.UNITS_PIXELS);
            msgLayout.setSpacing(true);
            msgLayout.setStyleName(UiConstants.CSS_CONTENT_PANEL);

            Label lblTimestamp = new Label(dateFormat.format(msg.getCreated()));
            msgLayout.addComponent(lblTimestamp);

            Label lblAuthor = new Label(msg.getAuthor().getUsername());
            msgLayout.addComponent(lblAuthor);

            Label lblContent = new Label(msg.getContent());
            msgLayout.addComponent(lblContent);

            showMessagesPanel.addComponent(msgLayout);
            showMessagesPanel.setComponentAlignment(msgLayout, Alignment.MIDDLE_CENTER);
        }

        HorizontalLayout btnLayout = new HorizontalLayout();
        btnLayout.setSpacing(true);
        if(getRenderPrev()) {
            Button btnPrev = new Button("Previous");
            btnPrev.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    prevMessages();
                }
            });
            btnLayout.addComponent(btnPrev);
        }

        if(getRenderNext()) {
            Button btnNext = new Button("Next");
            btnNext.addListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent clickEvent) {
                    nextMessages();
                }
            });
            btnLayout.addComponent(btnNext);
        }

        showMessagesPanel.addComponent(btnLayout);
        showMessagesPanel.setComponentAlignment(btnLayout, Alignment.MIDDLE_CENTER);

        this.addComponent(showMessagesPanel);
        this.setComponentAlignment(showMessagesPanel, Alignment.MIDDLE_CENTER);
    }

    private void reloadShowMessagesPanel() {
        this.removeComponent(showMessagesPanel);
        messages = null;
        addShowMessagesPanel();
    }

    private void addNavPanel() {
        HorizontalLayoutCentered navLayout = new HorizontalLayoutCentered(110);
        navLayout.setStyleName("marginTop marginBottom");
        Button btnFriends = new Button("Your Friends");
        btnFriends.setSizeUndefined();
        btnFriends.addListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                application.goToFriendsPanel();
            }
        });
        navLayout.getContentLayout().addComponent(btnFriends);
        this.addComponent(navLayout);
    }

    private void initMessage() {
        message = new Message();
        message.setContent("");
    }

    public List<Message> getMessages() {
        if (application.getCurrentUser() != null && messages == null) {
            messages = application.getMessageService().list(application.getCurrentUser().getId(),
                    getFirstPage(),
                    getStepSize());
        }
        return messages;
    }

    public Long getMessageCount() {
        if (application.getCurrentUser() != null) {
            messageCount = application.getMessageService().count(application.getCurrentUser().getId());
        }
        return messageCount;
    }

    public void nextMessages() {
        nextPage();
        reloadShowMessagesPanel();
    }

    public void prevMessages() {
        prevPage();
        reloadShowMessagesPanel();
    }

    public int getStepSize() {
        return stepSize;
    }

    public void setStepSize(int stepSize) {
        this.stepSize = stepSize;
    }

    public int getFirstPage() {
        return firstPage;
    }

    public void nextPage() {
        firstPage += stepSize;
    }

    public void prevPage() {
        if (firstPage - stepSize <= 0) {
            firstPage = 0;
        } else {
            firstPage -= stepSize;
        }
    }

    public boolean getRenderNext() {
        return getMessageCount() > firstPage + stepSize;
    }

    public boolean getRenderPrev() {
        return firstPage > 0;
    }

}
