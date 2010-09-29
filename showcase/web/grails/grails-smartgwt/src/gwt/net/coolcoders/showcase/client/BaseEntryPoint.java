package net.coolcoders.showcase.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import grails.plugins.gwt.client.GwtActionService;
import grails.plugins.gwt.client.GwtActionServiceAsync;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class BaseEntryPoint implements EntryPoint {
    protected GwtActionServiceAsync actionService;

    public void onModuleLoad() {
        actionService = GWT.create(GwtActionService.class);
        ((ServiceDefTarget) actionService).setServiceEntryPoint(GWT.getModuleBaseURL() + "rpc");
    }
}
