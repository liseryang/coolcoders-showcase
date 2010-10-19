package net.coolcoders.showcase.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.showcase.client.RegisterAction;
import net.coolcoders.showcase.client.RegisterResponse;
import net.coolcoders.showcase.client.ShowcaseConstants;
import net.coolcoders.showcase.components.ProfileForm;
import net.coolcoders.showcase.components.ShowcaseBaseButton;
import net.coolcoders.showcase.components.ShowcaseBaseView;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class RegisterView extends ShowcaseBaseView implements ClickHandler, AsyncCallback<RegisterResponse> {
    private ShowcaseConstants constants = GWT.create(ShowcaseConstants.class);
    private DynamicForm registerForm;
    private Button register, cancel;
    LinkedHashMap<String, String> categories;

    public RegisterView(GwtActionServiceAsync actionServiceAsync, LinkedHashMap<String, String> categories) {
        super(actionServiceAsync);
        this.categories = categories;
        initWidgets();
        layout();
    }

    private void initWidgets() {
        registerForm = new ProfileForm(categories);
        registerForm.setAction("/smartgwtsc/register/register");
        //the buttons
        register = new ShowcaseBaseButton(constants.register_label());
        register.addClickHandler(this);
        cancel = new ShowcaseBaseButton("Cancel");
        cancel.addClickHandler(this);
    }

    private void layout() {
        HLayout buttonLayout = new HLayout(ViewConstants.STD_MEMBERS_MARGIN);
        buttonLayout.addMember(cancel);
        buttonLayout.addMember(register);
        VLayout container = getDialogContainer();
        container.addMember(registerForm);
        container.addMember(buttonLayout);
        addMember(container);
    }

    public void onClick(ClickEvent clickEvent) {
        Button source = (Button) clickEvent.getSource();
        if (register.equals(source)) {
            doRegister();

        } else if (cancel.equals(source)) {
            Window.Location.assign("/smartgwtsc/login/index");
        }
    }

    private void doRegister() {
        if (registerForm.validate()) {
            RegisterAction action = new RegisterAction();
            action.setUsername((String) registerForm.getValue("username"));
            action.setPassword((String) registerForm.getValue("password"));
            action.setPasswordRepetition((String) registerForm.getValue("passwordRep"));
            action.setEmail((String) registerForm.getValue("email"));
            action.setBirthday((Date) registerForm.getValue("birthday"));
            action.setFullname((String) registerForm.getValue("fullname"));
            action.setGender((String) registerForm.getValue("gender"));
            Set<String> categoryIds = new HashSet<String>();
            for (String catId : categories.keySet()) {
                CheckboxItem item = (CheckboxItem) registerForm.getItem("category_" + catId);
                if (item.getValueAsBoolean()) {
                    categoryIds.add(catId);
                }
            }
            action.getCategoryIds().addAll(categoryIds);
            actionService.execute(action, this);
        }
    }

    public void onFailure(Throwable caught) {
        SC.say("Oh fuck !" + caught.getMessage());
    }

    public void onSuccess(RegisterResponse result) {
        if (result.getSuccessful()) {
            Window.Location.assign("/smartgwtsc/messages/index/?userId=" + result.getUserId());
        } else {
            registerForm.setErrors(result.getErrors(), true);
        }
    }

}