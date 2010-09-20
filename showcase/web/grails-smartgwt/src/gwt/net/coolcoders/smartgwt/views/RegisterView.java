package net.coolcoders.smartgwt.views;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.layout.HLayout;
import grails.plugins.gwt.client.GwtActionServiceAsync;
import net.coolcoders.smartgwt.client.ViewConstants;

import java.util.LinkedHashMap;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class RegisterView extends ShowcaseBaseView {
    private DynamicForm registerForm;

    public RegisterView(GwtActionServiceAsync actionServiceAsync, LinkedHashMap<Long, String> categories) {
        super(actionServiceAsync);
        initWidgets(categories);
        layout();
    }

    private void initWidgets(LinkedHashMap<Long, String> categories) {
        registerForm = new DynamicForm();
        //the form's fields
        FormItem[] items = new FormItem[7 + categories.size()];
        TextItem username = new TextItem("Username", "Username");
        username.setTooltip("Your desired username.");
        items[0] = username;
        PasswordItem password = new PasswordItem("Password", "Password");
        password.setTooltip("Your desired Password.");
        items[1] = password;
        PasswordItem passwordRep = new PasswordItem("Password", "Password");
        passwordRep.setTooltip("Repeat your desired Password.");
        items[2] = passwordRep;
        TextItem fullname = new TextItem("Your full name.");
        fullname.setTooltip("Your real name.");
        items[3] = fullname;
        TextItem email = new TextItem("Email", "Email");
        email.setTooltip("Your email address.");
        items[4] = email;
        SelectItem genderItem = new SelectItem("Gender", "Gender");
        genderItem.setTooltip("Your gender.");
        genderItem.setValueMap("FEMALE", "MALE");
        genderItem.setAllowEmptyValue(false);
        items[5] = genderItem;
        DateItem birthdayItem = new DateItem("Birthday", "Birthday");
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
    }

    private void layout() {
        HLayout layout = new HLayout(ViewConstants.STD_MEMBERS_MARGIN);
        layout.setWidth100();
        layout.setAlign(Alignment.CENTER);
        layout.addMember(registerForm);
        addMember(layout);
    }

}
