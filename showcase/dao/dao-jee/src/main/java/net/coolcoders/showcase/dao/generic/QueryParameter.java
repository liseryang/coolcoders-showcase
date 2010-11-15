package net.coolcoders.showcase.dao.generic;

import javax.persistence.metamodel.SingularAttribute;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *
 */
public class QueryParameter {

    private List<QueryParameterEntry> parameters = null;

    private QueryParameter(SingularAttribute name, Object value) {
        this.parameters = new ArrayList<QueryParameterEntry>();
        this.parameters.add(new QueryParameterEntry(name, value));
    }

    private QueryParameter(SingularAttribute name, Object value, QueryParameterEntry.Operator operator) {
        this.parameters = new ArrayList<QueryParameterEntry>();
        this.parameters.add(new QueryParameterEntry(name, value, operator));
    }

    public static QueryParameter with(SingularAttribute name, Object value) {
        return new QueryParameter(name, value);
    }

    public static QueryParameter with(SingularAttribute name, QueryParameterEntry.Operator operator, Object value) {
        return new QueryParameter(name, value, operator);
    }

    public QueryParameter and(SingularAttribute name, Object value) {
        this.parameters.add(new QueryParameterEntry(name, value));
        return this;
    }

    public QueryParameter and(SingularAttribute name, QueryParameterEntry.Operator operator, Object value) {
        this.parameters.add(new QueryParameterEntry(name, value, operator));
        return this;
    }

    public List<QueryParameterEntry> parameters() {
        return this.parameters;
    }
}
