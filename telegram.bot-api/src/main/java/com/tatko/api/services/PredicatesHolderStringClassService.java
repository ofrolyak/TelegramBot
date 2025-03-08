package com.tatko.api.services;

import com.tatko.api.apis.models.FilterStringApiObject;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;

@Service
public class PredicatesHolderStringClassService
        extends PredicatesHolderAbstractClass<FilterStringApiObject> {

    /**
     * Constructs a JPA Criteria API predicate based
     * on the specified filter action.
     *
     * The method handles three types of filter actions:
     * - LIKE: Generates a predicate for a `LIKE` comparison.
     * - EQUAL: Generates a predicate for an equality check.
     * - IS_NULL: Generates a predicate to check if the field is null.
     *
     * @param criteriaBuilder The CriteriaBuilder instance used
     *                        to create predicates.
     * @param root The root entity reference from which the field is derived.
     * @param fieldName The name of the field in the entity
     *                  for which the predicate is built.
     * @param filterStringApiObject The filter object containing the action
     *                              and value
     *                              for constructing the predicate.
     * @return A Predicate complying with the specified filter action and value.
     */
    @Override
    Predicate buildPredicateCore(
            final CriteriaBuilder criteriaBuilder,
            final From root, final String fieldName,
            final FilterStringApiObject filterStringApiObject) {

        return switch (filterStringApiObject.getAction()) {
            case LIKE -> criteriaBuilder.like(root.get(fieldName)
                    .as(String.class), filterStringApiObject.getValue());
            case EQUAL -> criteriaBuilder.equal(root.get(fieldName)
                    .as(String.class), filterStringApiObject.getValue());
            case IS_NULL -> criteriaBuilder.isNull(root.get(fieldName)
                    .as(String.class));
        };

    }

}
