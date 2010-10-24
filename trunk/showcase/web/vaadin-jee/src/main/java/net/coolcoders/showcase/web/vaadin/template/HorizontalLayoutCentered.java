package net.coolcoders.showcase.web.vaadin.template;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;

/**
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *         Date: 24.10.2010
 *         Time: 14:33:51
 */
public class HorizontalLayoutCentered extends VerticalLayout {

    private HorizontalLayout contentLayout;

    public HorizontalLayoutCentered(int width) {
        contentLayout = new HorizontalLayout();
        contentLayout.setSpacing(true);
        contentLayout.setWidth(width, Sizeable.UNITS_PIXELS);

        this.addComponent(contentLayout);
        this.setComponentAlignment(contentLayout, Alignment.MIDDLE_CENTER);
    }

    public HorizontalLayout getContentLayout() {
        return contentLayout;
    }
}
