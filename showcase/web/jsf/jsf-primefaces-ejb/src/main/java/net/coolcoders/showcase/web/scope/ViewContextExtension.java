package net.coolcoders.showcase.web.scope;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterBeanDiscovery;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;

/**
 * User: <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 * Date: 05.10.2010
 * Time: 23:26:40
 */
public class ViewContextExtension implements Extension {

    public void afterBeanDiscovery(@Observes AfterBeanDiscovery event, BeanManager manager) {
        event.addContext(new ViewContext());
    }

}
