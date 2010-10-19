package net.coolcoders.showcase.components;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.showcase.client.ShowcaseConstants;
import net.coolcoders.showcase.client.ShowcaseMessages;
import net.coolcoders.showcase.event.ShowViewEvent;
import net.coolcoders.showcase.event.ShowViewEventHandler;
import net.coolcoders.showcase.views.ViewConstants;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public abstract class ShowcaseBaseView extends VLayout implements HasHandlers {
    protected ShowcaseConstants constants = GWT.create(ShowcaseConstants.class);
    protected ShowcaseMessages messages = GWT.create(ShowcaseMessages.class);
    protected final GwtActionServiceAsync actionService;
    protected final HLayout headerPanel = new HLayout();
    protected final HandlerManager handlerManager;

    public ShowcaseBaseView(GwtActionServiceAsync actionServiceAsync) {
        super(ViewConstants.STD_MEMBERS_MARGIN);
        this.actionService = actionServiceAsync;
        this.handlerManager = new HandlerManager(this);
        setWidth100();
        setAutoHeight();
        setDefaultLayoutAlign(Alignment.CENTER);
        setAnimateFadeTime(1000);
        setAnimateShowTime(1000);
        headerPanel.addMember(getGreetingsLabel());
        headerPanel.setWidth100();
        headerPanel.setStyleName("bottomBorder");
        addMember(headerPanel);
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

    @Override
    public void fireEvent(GwtEvent<?> event) {
        handlerManager.fireEvent(event);
    }

    public HandlerRegistration addShowEventHandler(ShowViewEventHandler handler) {
        return handlerManager.addHandler(ShowViewEvent.SWITCHVIEW, handler);
    }
}
