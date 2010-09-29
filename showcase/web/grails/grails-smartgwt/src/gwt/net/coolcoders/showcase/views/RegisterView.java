package net.coolcoders.showcase.views;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.layout.HLayout;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.showcase.client.ShowcaseConstants;
import net.coolcoders.showcase.components.ShowcaseBaseButton;
import net.coolcoders.showcase.components.ShowcaseBaseView;

import java.util.LinkedHashMap;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class RegisterView extends ShowcaseBaseView implements ClickHandler {
    private ShowcaseConstants constants = GWT.create(ShowcaseConstants.class);
    private DynamicForm registerForm;
    private Button register, cancel;
    LinkedHashMap<Long, String> categories;

    public RegisterView(GwtActionServiceAsync actionServiceAsync, LinkedHashMap<Long, String> categories) {
        super(actionServiceAsync);
        this.categories = categories;
        initWidgets();
        layout();
    }

    private void initWidgets() {
        registerForm = new DynamicForm();
        registerForm.setAction("/smartgwtsc/register/register");
        //the form's fields
        FormItem[] items = new FormItem[7 + categories.size()];
        TextItem username = new TextItem("username", constants.username_label());
        username.setTooltip("Your desired username.");
        username.setRequired(true);
        items[0] = username;
        PasswordItem password = new PasswordItem("password", constants.password_label());
        password.setTooltip("Your desired Password.");
        password.setRequired(true);
        items[1] = password;
        PasswordItem passwordRep = new PasswordItem("passwordRep", constants.repassword_label());
        passwordRep.setTooltip("Repeat your desired Password.");
        passwordRep.setRequired(true);
        items[2] = passwordRep;
        TextItem fullname = new TextItem("fullname", constants.fullname_label());
        fullname.setTooltip("Your real name.");
        fullname.setRequired(true);
        items[3] = fullname;
        TextItem email = new TextItem("email", "Email");
        email.setTooltip("Your email address.");
        email.setRequired(true);
        items[4] = email;
        SelectItem genderItem = new SelectItem("gender", constants.gender_label());
        genderItem.setTooltip("Your gender.");
        genderItem.setValueMap(constants.gender_female(), constants.gender_male());
        genderItem.setAllowEmptyValue(false);
        items[5] = genderItem;
        DateItem birthdayItem = new DateItem("birthday", constants.birthday_label());
        birthdayItem.setTooltip("Yout birthday.");
        items[6] = birthdayItem;
        int i = 0;
        for (Long key : categories.keySet()) {
            CheckboxItem checkboxItem = new CheckboxItem("category_" + key, categories.get(key));
            checkboxItem.setTooltip("Are you into " + categories.get(key) + " ?");
            items[7 + i] = checkboxItem;
            i++;
        }
        registerForm.setFields(items);
        registerForm.setAlign(Alignment.CENTER);
        //the buttons
        register = new ShowcaseBaseButton(constants.register_label());
        register.addClickHandler(this);
        cancel = new ShowcaseBaseButton("Cancel");
        cancel.addClickHandler(this);
    }

    private void layout() {
        HLayout layout = getHorizontalLayout();
        layout.addMember(registerForm);
        HLayout buttonLayout = getHorizontalLayout();
        buttonLayout.addMember(cancel);
        buttonLayout.addMember(register);
        addMember(layout);
        addMember(buttonLayout);
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
        if (checkPassword()) {
            registerForm.submitForm();
        } else {
            SC.say("Your passwords do not match !");
            registerForm.setFieldErrors("password", "Password don't match !", true);
            registerForm.setFieldErrors("passwordRep", "Password don't match !", true);
        }
    }

    private boolean checkPassword() {
        String passwd = (String) registerForm.getValue("password");
        String passwdRep = (String) registerForm.getValue("passwordRep");
        if (passwd != null && passwdRep != null && passwd.equals(passwdRep)) {
            return true;
        }
        return false;
    }
}
