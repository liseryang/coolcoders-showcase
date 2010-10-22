package net.coolcoders.showcase.web.vaadin;

import com.vaadin.Application;
import com.vaadin.terminal.gwt.server.AbstractApplicationServlet;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *         Date: 22.10.2010
 *         Time: 11:57:36
 */
@WebServlet(urlPatterns = "/*")
public class VaadinAppServlet extends AbstractApplicationServlet {

    @Inject
    LoginBean application;

    @Override
    protected Class<? extends Application> getApplicationClass() throws ClassNotFoundException {
        return LoginBean.class;
    }

    @Override
    protected Application getNewApplication(HttpServletRequest request) throws ServletException {
        return application;
    }
    
}
