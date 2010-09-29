package net.coolcoders.showcase.components;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import net.coolcoders.showcase.views.ViewConstants;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class ShowcaseBaseButton extends Button {
    public ShowcaseBaseButton() {
        initMySelf();
    }

    public ShowcaseBaseButton(JavaScriptObject jsObj) {
        super(jsObj);
        initMySelf();
    }

    public ShowcaseBaseButton(String title) {
        super(title);
        initMySelf();
    }

    private void initMySelf() {
        setWidth(ViewConstants.STD_BUTTON_WIDTH);
        setAlign(Alignment.CENTER);
    }
}
