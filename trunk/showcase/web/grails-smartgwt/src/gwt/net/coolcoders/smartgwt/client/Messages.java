package net.coolcoders.smartgwt.client;

import com.google.gwt.core.client.EntryPoint;
import net.coolcoders.smartgwt.views.MessagesView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Messages extends BaseEntryPoint implements EntryPoint {
    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        super.onModuleLoad();
        MessagesView messagesView = new MessagesView(actionService);
        messagesView.draw();
    }
}
