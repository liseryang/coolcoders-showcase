package net.coolcoders.showcase.data;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSOperationType;
import net.coolcoders.showcase.client.ShowcaseConstants;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class MessagesDataSource extends RestDataSource {
    ShowcaseConstants constants = GWT.create(ShowcaseConstants.class);
    private static MessagesDataSource instance;

    public static MessagesDataSource getInstance() {
        if (instance == null) {
            instance = new MessagesDataSource();
        }
        return instance;
    }

    private MessagesDataSource() {
        initFields();
        initOperations();
    }

    private void initFields() {
        //fields
        DataSourceTextField idField = new DataSourceTextField("id", "ID", 3, false);
        idField.setPrimaryKey(true);
        idField.setDetail(true);
        DataSourceTextField messageContentField = new DataSourceTextField("content", constants.message_label());
        DataSourceDateField createdField = new DataSourceDateField("created");
        createdField.setDetail(true);
        DataSourceTextField creatorField = new DataSourceTextField("creator");
        creatorField.setDetail(true);
        setFields(idField, messageContentField, createdField, creatorField);
    }

    private void initOperations() {
        OperationBinding fetch = new OperationBinding(DSOperationType.FETCH, "/smartgwtsc/message/list");
        setOperationBindings(fetch);
    }

}
