package net.coolcoders.showcase.web.vaadin;

import com.vaadin.data.Property;
import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.*;

import java.util.Arrays;
import java.util.List;

/**
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *         Date: 23.10.2010
 *         Time: 17:46:19
 */
public class HeaderPanel extends GridLayout {

    private static final List<String> THEMES = Arrays.asList("sunny", "redmond");

    private String currentTheme = "sunny";

    public HeaderPanel(final Window mainWindow) {
        super(2, 1);
        mainWindow.setTheme(currentTheme);

        this.setStyleName(UiConstants.CSS_HEADER_PANEL + " marginTop");
        this.setWidth(UiConstants.CONTENT_WIDTH, Sizeable.UNITS_PIXELS);

        Label headerCaption = new Label("<h1>Cool Coders Showcase</h1>");
        headerCaption.setContentMode(Label.CONTENT_XHTML);
        headerCaption.setWidth(600, Sizeable.UNITS_PIXELS);
        this.addComponent(headerCaption, 0, 0);

        VerticalLayout themesBoxPanel = new VerticalLayout();
        themesBoxPanel.setWidth(200, Sizeable.UNITS_PIXELS);
        ComboBox themesBox = new ComboBox("Choose your theme!!!", THEMES);
        themesBox.addListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                currentTheme = (String) valueChangeEvent.getProperty().getValue();
                mainWindow.setTheme(currentTheme);
            }
        });
        themesBox.setImmediate(true);
        themesBoxPanel.addComponent(themesBox);

        this.addComponent(themesBoxPanel, 1, 0);
        this.setComponentAlignment(themesBoxPanel, Alignment.MIDDLE_RIGHT);
    }
}
