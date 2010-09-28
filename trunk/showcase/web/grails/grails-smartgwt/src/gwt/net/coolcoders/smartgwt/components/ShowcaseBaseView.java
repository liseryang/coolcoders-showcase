package net.coolcoders.smartgwt.components;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.smartgwt.client.ViewConstants;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public abstract class ShowcaseBaseView extends VLayout {
    protected final GwtActionServiceAsync actionService;

    public ShowcaseBaseView(GwtActionServiceAsync actionServiceAsync) {
        super();
        this.actionService = actionServiceAsync;
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

    protected HLayout getHorizontalLayout() {
        HLayout layout = new HLayout(ViewConstants.STD_MEMBERS_MARGIN);
        layout.setAlign(Alignment.CENTER);
        layout.setWidth100();
        return layout;
    }

    protected VLayout getVerticalLayout() {
        VLayout layout = new VLayout(ViewConstants.STD_MEMBERS_MARGIN);
        layout.setAlign(Alignment.CENTER);
        layout.setHeight100();
        return layout;
    }
}
