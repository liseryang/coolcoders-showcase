package net.coolcoders.showcase.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import net.coolcoders.showcase.views.RegisterView;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Register extends BaseEntryPoint {
    /**
     * This is the entry point method.
     */
    @Override
    public void onModuleLoad() {
        super.onModuleLoad();
        initLookupData();
    }

    private void initLookupData() {
        actionService.execute(new LoadLookupDataAction(), new AsyncCallback<LoadLookupDataResponse>() {
            public void onFailure(Throwable caught) {
                //this error would suck quite a bit!
                SC.say("Error loading required lookup data! " + caught.getMessage());
            }

            public void onSuccess(LoadLookupDataResponse result) {
                //required lookup data loaded -> build the ui
                initViews(result);
            }
        });
    }

    private void initViews(LoadLookupDataResponse result) {
        new RegisterView(actionService, result.getCategoriesMap()).draw();
    }

}
