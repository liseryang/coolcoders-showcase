package net.coolcoders.showcase.dao.generic;

import javax.persistence.criteria.JoinType;
import javax.persistence.metamodel.Attribute;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 *
 */
public class QueryFetch {

    private Map<Attribute, JoinType> attributeNames = new HashMap<Attribute, JoinType>();

    public QueryFetch(Attribute attributeName) {
        attributeNames.put(attributeName, JoinType.INNER);
    }

    public QueryFetch(Attribute attributeName, JoinType joinType) {
        attributeNames.put(attributeName, joinType);
    }

    public static QueryFetch withInnerJoin(Attribute attributeName) {
        return new QueryFetch(attributeName, JoinType.INNER);
    }

    public static QueryFetch withLeftJoin(Attribute attributeName) {
        return new QueryFetch(attributeName, JoinType.LEFT);
    }

    public static QueryFetch withRighJoin(Attribute attributeName) {
        return new QueryFetch(attributeName, JoinType.RIGHT);
    }

    public QueryFetch andInnerJoin(Attribute attributeName) {
        attributeNames.put(attributeName, JoinType.INNER);
        return this;
    }

    public QueryFetch andLeftJoin(Attribute attributeName) {
        attributeNames.put(attributeName, JoinType.LEFT);
        return this;
    }

    public QueryFetch andRightJoin(Attribute attributeName) {
        attributeNames.put(attributeName, JoinType.RIGHT);
        return this;
    }

    public Map<Attribute, JoinType> getAttributeNames() {
        return attributeNames;
    }
}
