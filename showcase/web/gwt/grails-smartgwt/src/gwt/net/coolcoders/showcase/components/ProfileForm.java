package net.coolcoders.showcase.components;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.DateDisplayFormat;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.*;
import net.coolcoders.showcase.client.ShowcaseConstants;

import java.util.LinkedHashMap;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class ProfileForm extends DynamicForm {
    private final ShowcaseConstants constants = GWT.create(ShowcaseConstants.class);
    private final LinkedHashMap<String, String> categories;

    public ProfileForm(LinkedHashMap<String, String> categories) {
        this.categories = categories;
        initItems();
    }

    private void initItems() {
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
        genderItem.setValueMap("FEMALE", "MALE");
        genderItem.setAllowEmptyValue(false);
        items[5] = genderItem;
        DateItem birthdayItem = new DateItem("birthday", constants.birthday_label());
        birthdayItem.setTooltip("Your birthday.");
        birthdayItem.setDisplayFormat(DateDisplayFormat.TOSERIALIZEABLEDATE);
        birthdayItem.setUseTextField(true);
        items[6] = birthdayItem;
        int i = 0;
        for (String key : categories.keySet()) {
            CheckboxItem checkboxItem = new CheckboxItem("category_" + key, categories.get(key));
            checkboxItem.setTooltip("Are you into " + categories.get(key) + " ?");
            items[7 + i] = checkboxItem;
            i++;
        }
        setFields(items);
    }
}
