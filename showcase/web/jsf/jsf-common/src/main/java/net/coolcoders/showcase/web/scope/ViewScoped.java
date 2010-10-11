package net.coolcoders.showcase.web.scope;

import javax.enterprise.context.NormalScope;
import java.lang.annotation.*;

/**
 * User: <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 * Date: 05.10.2010
 * Time: 23:20:43
 */
@Target(value={ElementType.METHOD, ElementType.TYPE, ElementType.FIELD})
@Retention(value= RetentionPolicy.RUNTIME)
@NormalScope
@Inherited
public @interface ViewScoped {
}
