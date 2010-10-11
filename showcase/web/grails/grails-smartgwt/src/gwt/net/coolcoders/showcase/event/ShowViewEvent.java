package net.coolcoders.showcase.event;

import com.google.gwt.event.shared.GwtEvent;


/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class ShowViewEvent extends GwtEvent<ShowViewEventHandler> {
    public static Type<ShowViewEventHandler> SWITCHVIEW = new Type<ShowViewEventHandler>();

    public enum ViewToShow {
        PROFILE, LOGOUT, USERLIST
    }

    private final ViewToShow viewToShow;

    public ShowViewEvent(Type<ShowViewEventHandler> type, ViewToShow viewToShow) {
        this.viewToShow = viewToShow;
    }

    public ViewToShow getViewToShow() {
        return viewToShow;
    }

    @Override
    public Type<ShowViewEventHandler> getAssociatedType() {
        return SWITCHVIEW;
    }

    @Override
    protected void dispatch(ShowViewEventHandler handler) {
        handler.showView(this);
    }
}
