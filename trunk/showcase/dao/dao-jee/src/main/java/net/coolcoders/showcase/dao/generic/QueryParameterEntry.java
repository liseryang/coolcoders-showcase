package net.coolcoders.showcase.dao.generic;

import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author <a href="mailto:andreas@bambo.it">Andreas Baumgartner, andreas@bambo.it</a>
 * 
 */
public class QueryParameterEntry<X, T> {

    public enum Operator {
        EQ, GT, GE, LT, LE, STARTS, CONTAINS, ENDS
    }

    private SingularAttribute<X, T> attribute;

    private T value;

    private Operator operator = Operator.EQ;

    public QueryParameterEntry(SingularAttribute<X, T> attribute, T value) {
        this.attribute = attribute;
        this.value = value;
    }

    public QueryParameterEntry(SingularAttribute<X, T> attribute, T value, Operator operator) {
        this.attribute = attribute;
        this.value = value;
        this.operator = operator;
    }

    public SingularAttribute<X, T> getAttribute() {
        return attribute;
    }

    public T getValue() {
        return value;
    }

    public Operator getOperator() {
        return operator;
    }
}

