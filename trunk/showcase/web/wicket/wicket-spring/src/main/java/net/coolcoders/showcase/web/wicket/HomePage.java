package net.coolcoders.showcase.web.wicket;

import net.coolcoders.showcase.service.UserService;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *         Date: 24.10.2010
 *         Time: 17:57:17
 */
public class HomePage extends WebPage {
    
    private static final long serialVersionUID = 1L;

    // TODO Add any page properties or variables here

    @SpringBean
    private UserService userService;

    /**
     * Constructor that is invoked when page is invoked without a session.
     *
     * @param parameters
     *            Page parameters
     */
    public HomePage(final PageParameters parameters) {
        userService.listAll();
        // Add the simplest type of label
        add(new Label("message", "If you see this message wicket is properly configured and running"));

        // TODO Add your page's components here
    }
    
}
