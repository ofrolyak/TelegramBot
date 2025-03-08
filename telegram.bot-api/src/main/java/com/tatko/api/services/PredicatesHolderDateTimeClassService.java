package com.tatko.api.services;

import com.tatko.api.apis.models.FilterDateTimeApiObject;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDateTime;

@Service
public class PredicatesHolderDateTimeClassService
        extends PredicatesHolderAbstractClass<FilterDateTimeApiObject> {

    /**
     * Builds a Predicate based on the criteria specified
     * by the given parameters.
     * The Predicate is created according to the action specified in the
     * FilterDateTimeApiObject.
     *
     * @param criteriaBuilder the CriteriaBuilder used to construct
     *                        the Predicate
     * @param root the root entity or From clause in a criteria query
     * @param fieldName the name of the field on which
     *                  the Predicate will be applied
     * @param filterDateTimeApiObject the filter object containing the action
     *                                and
     *        date details for constructing the Predicate
     * @return the constructed Predicate based on the specified filtering
     * criteria
     */
    @Override
    Predicate buildPredicateCore(
            final CriteriaBuilder criteriaBuilder,
            final From root, final String fieldName,
            final FilterDateTimeApiObject filterDateTimeApiObject) {

        return switch (filterDateTimeApiObject.getAction()) {
            case AFTER -> criteriaBuilder.greaterThan(root.get(fieldName)
                    .as(LocalDateTime.class),
                    filterDateTimeApiObject.getOnDate());
            case BEFORE -> criteriaBuilder.lessThan(root.get(fieldName)
                    .as(LocalDateTime.class),
                    filterDateTimeApiObject.getOnDate());
            case BETWEEN -> {
                Predicate predicateBefore = criteriaBuilder.lessThan(
                        root.get(fieldName)
                                .as(LocalDateTime.class),
                        filterDateTimeApiObject.getEndDate());
                Predicate predicateAfter = criteriaBuilder.greaterThan(
                        root.get(fieldName)
                                .as(LocalDateTime.class),
                        filterDateTimeApiObject.getStartDate());
                yield criteriaBuilder.and(predicateBefore, predicateAfter);
            }
            case IS_NULL -> criteriaBuilder.isNull(root.get(fieldName)
                    .as(LocalDateTime.class));
        };

    }

}
