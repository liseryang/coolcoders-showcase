package net.coolcoders.showcase.components;

import com.google.gwt.core.client.JavaScriptObject;
import com.smartgwt.client.widgets.Button;
import net.coolcoders.showcase.views.ViewConstants;

/**
 * @author <a href="mailto:josip.mihelko@googlemail.com">Josip Mihelko</a>
 */
public class ShowcaseBaseButton extends Button {
    public ShowcaseBaseButton() {
        setWidth(ViewConstants.STD_BUTTON_WIDTH);
    }

    public ShowcaseBaseButton(JavaScriptObject jsObj) {
        super(jsObj);
        setWidth(ViewConstants.STD_BUTTON_WIDTH);
    }

    public ShowcaseBaseButton(String title) {
        super(title);
        setWidth(ViewConstants.STD_BUTTON_WIDTH);
    }
}