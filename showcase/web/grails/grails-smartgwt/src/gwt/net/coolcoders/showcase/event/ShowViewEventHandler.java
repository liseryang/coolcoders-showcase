package net.coolcoders.showcase.event;

import com.google.gwt.event.shared.EventHandler;
import com.smartgwt.client.types.AnimationEffect;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.AnimationCallback;
import net.coolcoders.showcase.components.ShowcaseBaseView;

import java.util.Map;
import java.util.Random;


/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class ShowViewEventHandler implements EventHandler, AnimationCallback {
    private final Map<ShowViewEvent.ViewToShow, ShowcaseBaseView> views;
    private ShowViewEvent.ViewToShow viewToShow;
    private AnimationEffect[] effects = new AnimationEffect[]{AnimationEffect.WIPE, AnimationEffect.FADE, AnimationEffect.FLY, AnimationEffect.SLIDE};

    public ShowViewEventHandler(Map<ShowViewEvent.ViewToShow, ShowcaseBaseView> theViews) {
        this.views = theViews;
        for (ShowcaseBaseView view : theViews.values()) {
            view.addShowEventHandler(this);
        }
    }

    public void showView(ShowViewEvent event) {
        this.viewToShow = event.getViewToShow();
        for (ShowcaseBaseView view : views.values()) {
            if (view.isVisible()) {
                view.animateHide(getRandomEffect(), this);
            }
        }
    }

    public void execute(boolean b) {
        SC.logWarn("ShowViewEventHandler::AnimationCallback.execute( " + b + " )");
        ShowcaseBaseView theView = views.get(viewToShow);
        theView.animateShow(getRandomEffect());
    }

    private AnimationEffect getRandomEffect() {
        SC.logWarn("Getting random AnimationEffect");
        int rand = new Random().nextInt(5);
        SC.logWarn("Got Effect index:" + rand);
        return effects[rand];
    }
}
