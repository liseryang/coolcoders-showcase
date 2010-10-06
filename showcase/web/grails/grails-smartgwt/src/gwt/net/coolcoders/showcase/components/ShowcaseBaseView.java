package net.coolcoders.showcase.components;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.VLayout;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.showcase.client.ShowcaseConstants;
import net.coolcoders.showcase.client.ShowcaseMessages;
import net.coolcoders.showcase.views.ViewConstants;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public abstract class ShowcaseBaseView extends VLayout {
    protected ShowcaseConstants constants = GWT.create(ShowcaseConstants.class);
    protected ShowcaseMessages messages = GWT.create(ShowcaseMessages.class);
    protected final GwtActionServiceAsync actionService;

    public ShowcaseBaseView(GwtActionServiceAsync actionServiceAsync) {
        super(ViewConstants.STD_MEMBERS_MARGIN);
        this.actionService = actionServiceAsync;
        setWidth100();
        setAutoHeight();
        setDefaultLayoutAlign(Alignment.CENTER);
        addMember(getGreetingsLabel());
    }

    private Label getGreetingsLabel() {
        Label label = new Label();
        label.setContents(constants.showcase_heading());
        label.setStyleName("heading");
        label.setAlign(Alignment.LEFT);
        label.setWidth100();
        label.setAutoHeight();
        label.setTooltip(constants.showcase_heading_tooltip());
        return label;
    }

    protected VLayout getDialogContainer() {
        VLayout container = new VLayout(ViewConstants.STD_MEMBERS_MARGIN);
        container.setAutoWidth();
        container.setDefaultLayoutAlign(Alignment.CENTER);
        container.setPadding(20);
        container.setMargin(100);
        container.setShowEdges(true);
        container.setBackgroundColor(ViewConstants.STD_DIALOG_BG_COLOR);
        return container;
    }
}
