package net.coolcoders.showcase.views;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VStack;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.showcase.client.UserInfoResponse;
import net.coolcoders.showcase.components.ProfileForm;
import net.coolcoders.showcase.components.ShowcaseBaseButton;
import net.coolcoders.showcase.components.ShowcaseLoggedInView;
import net.coolcoders.showcase.event.ShowViewEvent;

import java.util.LinkedHashMap;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class ProfileView extends ShowcaseLoggedInView implements ClickHandler {
    private final LinkedHashMap<String, String> categoriesMap;
    private ProfileForm profileForm;
    private Button saveButton, backButton;

    public ProfileView(GwtActionServiceAsync actionServiceAsync, UserInfoResponse userInfo, LinkedHashMap<String, String> categoriesMap) {
        super(actionServiceAsync, userInfo);
        this.categoriesMap = categoriesMap;
        initMySelf();
        layoutMySelf();
    }

    private void initMySelf() {
        profileForm = new ProfileForm(categoriesMap);
        saveButton = new ShowcaseBaseButton(constants.button_save());
        backButton = new ShowcaseBaseButton(constants.button_back());
    }

    private void layoutMySelf() {
        HLayout buttonLayout = new HLayout(ViewConstants.STD_MEMBERS_MARGIN);
        buttonLayout.setAlign(Alignment.CENTER);
        buttonLayout.addMember(backButton);
        buttonLayout.addMember(saveButton);
        VStack stack = new VStack(ViewConstants.STD_MEMBERS_MARGIN);
        stack.setAlign(Alignment.CENTER);
        stack.addMember(profileForm);
        stack.addMember(buttonLayout);
        addMember(stack);
    }

    public void onClick(ClickEvent clickEvent) {
        if (backButton.equals(clickEvent.getSource())) {
            fireEvent(new ShowViewEvent(ShowViewEvent.ViewToShow.MESSAGES));
        }
    }
}
