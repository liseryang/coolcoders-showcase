package net.coolcoders.showcase.web.vaadin;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Label;

/**
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *         Date: 23.10.2010
 *         Time: 17:50:17
 */
public class FooterPanel extends GridLayout{

    public FooterPanel() {
        super(1, 1);
        this
                .setStyleName(UiConstants.CSS_HEADER_PANEL + "marginTop marginBottom");
        this.setWidth(UiConstants.CONTENT_WIDTH, Sizeable.UNITS_PIXELS);

        Label footerCaption = new Label("<h3><a href=\"http://vaadin.com/home\">Vaadin</a> Showcase by <a href=\"http://code.google.com/p/coolcoders-showcase/\">Cool Coders</a></h3>");
        footerCaption.setContentMode(Label.CONTENT_XHTML);
        footerCaption.setWidth(260, Sizeable.UNITS_PIXELS);
        this.addComponent(footerCaption, 0, 0);
        this.setComponentAlignment(footerCaption, Alignment.MIDDLE_CENTER);
    }
}
