package net.coolcoders.smartgwt.views;

import com.smartgwt.client.widgets.Label;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.smartgwt.client.ShowCaseUi;
import net.coolcoders.smartgwt.components.ShowcaseBaseView;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class MessagesView extends ShowcaseBaseView {

    public MessagesView(GwtActionServiceAsync actionServiceAsync, ShowCaseUi showCaseUi) {
        super(actionServiceAsync, showCaseUi);
        addMember(new Label("MessagesView!"));
    }
}
