package net.coolcoders.showcase.event;

import com.google.gwt.event.shared.EventHandler;
import com.smartgwt.client.util.SC;


/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class ShowViewEventHandler implements EventHandler {
    public void showView(ShowViewEvent event) {
        SC.say("Showing view: " + event.getViewToShow());
    }
}
