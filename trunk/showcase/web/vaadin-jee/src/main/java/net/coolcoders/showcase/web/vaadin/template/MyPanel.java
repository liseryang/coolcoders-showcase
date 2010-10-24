package net.coolcoders.showcase.web.vaadin.template;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import net.coolcoders.showcase.web.vaadin.UiConstants;

/**
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *         Date: 24.10.2010
 *         Time: 14:01:30
 */
public class MyPanel extends VerticalLayout {

    private VerticalLayout headerPanel;

    public MyPanel(String caption) {
        this.setStyleName(UiConstants.CSS_CONTENT_PANEL);
        this.setSpacing(true);

        headerPanel = new VerticalLayout();
        headerPanel.setStyleName(UiConstants.CSS_HEADER_PANEL);
        this.addComponent(headerPanel);

        Label lblHeaderCaption = new Label(caption);
        lblHeaderCaption.setSizeUndefined();
        headerPanel.addComponent(lblHeaderCaption);
        headerPanel.setComponentAlignment(lblHeaderCaption, Alignment.MIDDLE_CENTER);
    }

    public VerticalLayout getHeaderPanel() {
        return headerPanel;
    }
}
