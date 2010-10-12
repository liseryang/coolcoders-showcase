package net.coolcoders.showcase.event;

import com.google.gwt.event.shared.EventHandler;
import com.smartgwt.client.util.SC;
import net.coolcoders.showcase.components.ShowcaseBaseView;

import java.util.Map;


/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class ShowViewEventHandler implements EventHandler {
    private final Map<ShowViewEvent.ViewToShow, ShowcaseBaseView> views;

    public ShowViewEventHandler(Map<ShowViewEvent.ViewToShow, ShowcaseBaseView> theViews) {
        this.views = theViews;
        for (ShowcaseBaseView view : theViews.values()) {
            view.addShowEventHandler(this);
        }
    }

    public void showView(ShowViewEvent event) {
        for (ShowcaseBaseView view : views.values()) {
            if (view.isVisible()) {
                view.hide();
                SC.say("Hiding: " + view);
            }
        }
        ShowcaseBaseView theView = views.get(event.getViewToShow());
        SC.say("Showing: " + theView);
        theView.show();
    }
}
