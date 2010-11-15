package net.coolcoders.showcase.dao.generic;

import javax.persistence.metamodel.SingularAttribute;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *
 */
public class QueryOrder {

    public enum OrderDirection {ASC, DESC};

    private Map<SingularAttribute, OrderDirection> statements = null;

    private QueryOrder(SingularAttribute name, OrderDirection orderDirection) {
        this.statements = new HashMap<SingularAttribute, OrderDirection>();
        this.statements.put(name, orderDirection);
    }

    public static QueryOrder with(SingularAttribute name, OrderDirection orderDirection) {
        return new QueryOrder(name, orderDirection);
    }

    public QueryOrder and(SingularAttribute name, OrderDirection orderDirection) {
        this.statements.put(name, orderDirection);
        return this;
    }

    public Map<SingularAttribute, OrderDirection> statements() {
        return this.statements;
    }

}
