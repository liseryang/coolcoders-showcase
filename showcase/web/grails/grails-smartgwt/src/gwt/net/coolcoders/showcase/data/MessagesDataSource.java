package net.coolcoders.showcase.data;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSOperationType;
import net.coolcoders.showcase.client.ShowcaseConstants;

import java.util.Collections;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class MessagesDataSource extends RestDataSource {
    ShowcaseConstants constants = GWT.create(ShowcaseConstants.class);
    private static MessagesDataSource instance;
    private final String userId;

    public static MessagesDataSource getInstance(String userId) {
        if (instance == null) {
            instance = new MessagesDataSource(userId);
        }
        return instance;
    }

    private MessagesDataSource(String userId) {
        this.userId = userId;
        init();
    }

    private void init() {
        DataSourceTextField messageContentField = new DataSourceTextField("content", constants.message_label());
        DataSourceDateField createdField = new DataSourceDateField("created");
        setFields(messageContentField, createdField);
        setDefaultParams(Collections.singletonMap("userId", userId));
        OperationBinding fetch = new OperationBinding(DSOperationType.FETCH, "/smartgwtsc/messages/list");
    }

}
