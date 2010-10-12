package net.coolcoders.showcase.dao.generic;

import javax.persistence.criteria.JoinType;
import javax.persistence.metamodel.Attribute;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: andreas
 * Date: 29.09.2010
 * Time: 21:33:05
 * To change this template use File | Settings | File Templates.
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
