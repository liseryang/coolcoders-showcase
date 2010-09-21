package net.coolcoders.showcase.dao.generic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 19.09.2010
 * Time: 20:34:02
 * To change this template use File | Settings | File Templates.
 */
public class QueryOrder {

    enum OrderDirection {ASC, DESC};

    private Map<String, OrderDirection> statements = null;

    private QueryOrder(String name, OrderDirection orderDirection) {
        this.statements = new HashMap<String, OrderDirection>();
        this.statements.put(name, orderDirection);
    }

    public static QueryOrder with(String name, OrderDirection orderDirection) {
        return new QueryOrder(name, orderDirection);
    }

    public QueryOrder and(String name, OrderDirection orderDirection) {
        this.statements.put(name, orderDirection);
        return this;
    }

    public Map<String, OrderDirection> statements() {
        return this.statements;
    }
    
}
