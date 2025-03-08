package com.tatko.api.dao;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;

@FunctionalInterface
public interface PredicateCreator extends OperationMarkerInterface {

    /**
     * Generates a JPA Predicate used for constructing dynamic query conditions
     * based on the provided CriteriaBuilder and root entity context.
     * This method is typically implemented to define filtering or selection
     * criteria in a type-safe and dynamic way when working
     * with JPA Criteria API.
     *
     * @param criteriaBuilder the factory for building Criteria API objects,
     *                        used to construct predicates
     *                        and other query components
     * @param root            the root type in the from clause of the query,
     *                        representing the entity being queried
     * @return a Predicate instance representing the filtering or
     *         selection condition for the query
     */
    Predicate execute(CriteriaBuilder criteriaBuilder, From root);

}
