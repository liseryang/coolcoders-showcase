package net.coolcoders.smartgwt.views;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;
import grails.plugins.gwt.client.GwtActionServiceAsync;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public abstract class ShowcaseBaseView extends VLayout {
    protected final GwtActionServiceAsync actionService;

    public ShowcaseBaseView(GwtActionServiceAsync actionServiceAsync) {
        super();
        actionService = actionServiceAsync;
        setWidth100();
        setAlign(Alignment.CENTER);
        setAutoHeight();
        addMember(getGreetingsLabel());
        addMember(getMessageLabel());
    }

    private Label getGreetingsLabel() {
        Label label = new Label();
        label.setContents("Welcome to the CoolCoders Grails / SmartGwt Showcase");
        label.setStyleName("heading");
        label.setAlign(Alignment.CENTER);
        label.setWidth100();
        label.setAutoHeight();
        return label;
    }

    private Label getMessageLabel() {
        Label label = new Label();
        label.setContents("Succeeding through coolness");
        label.setStyleName("subHeading");
        label.setAlign(Alignment.CENTER);
        label.setWidth100();
        label.setAutoHeight();
        return label;
    }
}
