package com.tatko.api.services;

import com.tatko.api.apis.models.FilterLongApiObject;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;

@Service
public class PredicatesHolderLongClassService
        extends PredicatesHolderAbstractClass<FilterLongApiObject> {

    // todo Need create validator for input structure

    /**
     * Builds a {@link Predicate} based on the specified filter
     * criteria provided
     * through the {@code FilterLongApiObject}.
     * This method supports multiple filter
     * actions such as EQUAL, GREATER_EQUAL, LESS_EQUAL, BETWEEN, and IS_NULL.
     *
     * @param criteriaBuilder the {@code CriteriaBuilder} used
     *                        to create {@code Predicate} objects
     * @param root the root type in the criteria query,
     *             representing the entity being queried
     * @param fieldName the name of the field within the entity
     *                  on which the filtering is applied
     * @param filterLongApiObject the filter criteria object containing
     *                            the action and associated values
     *                            to construct the predicate
     * @return the {@code Predicate} constructed based on the specified
     * filter criteria
     */
    @Override
    Predicate buildPredicateCore(
            final CriteriaBuilder criteriaBuilder,
            final From root, final String fieldName,
            final FilterLongApiObject filterLongApiObject) {

        return switch (filterLongApiObject.getAction()) {
            case EQUAL -> criteriaBuilder.equal(root.get(fieldName)
                    .as(Long.class), filterLongApiObject.getOnValue());
            case GREATER_EQUAL -> criteriaBuilder.greaterThanOrEqualTo(
                    root.get(fieldName)
                    .as(Long.class), filterLongApiObject.getOnValue());
            case LESS_EQUAL -> criteriaBuilder.lessThanOrEqualTo(
                    root.get(fieldName)
                    .as(Long.class), filterLongApiObject.getOnValue());
            case BETWEEN -> criteriaBuilder.between(root.get(fieldName)
                            .as(Long.class), filterLongApiObject.getMinValue(),
                    filterLongApiObject.getMaxValue());
            case IS_NULL -> criteriaBuilder.isNull(root.get(fieldName)
                    .as(Long.class));
        };

    }

}
