package com.tatko.api.services;

import com.tatko.api.apis.models.FilterOptionalNegateFieldApiObject;
import com.tatko.api.apis.models.FilterOptionalParentApiObject;
import com.tatko.api.apis.models.FilterValueApiInterface;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import java.util.Objects;
import java.util.Optional;

public abstract class PredicatesHolderAbstractClass
        <T extends FilterValueApiInterface> {

    abstract Predicate buildPredicateCore(
            CriteriaBuilder criteriaBuilder,
            From root, String fieldName,
            T filterValueApiObject);

    /**
     * Validates the provided FilterOptionalParentApiObject to ensure
     * it is not null,
     * its `present` field is not null, and that the `present` field
     * contains a value.
     * This method is used to check the validity of the filter object
     * before further
     * processing or predicate construction.
     *
     * @param filterOptionalParentApiObject The filter object to validate.
     *                                      It is expected
     *                                      to contain a potentially optional
     *                                      value to be
     * @return true/false
     */
    boolean primaryValidate(
            final FilterOptionalParentApiObject
                    filterOptionalParentApiObject) {

        if (Objects.isNull(filterOptionalParentApiObject)
                || Objects.isNull(filterOptionalParentApiObject.getPresent())
                || !filterOptionalParentApiObject.getPresent().getPresent()) {
            return false;
        }

        return true;
    }

    /**
     * Applies negation logic to the provided predicate based on the specified
     * FilterOptionalNegateFieldApiObject. If the filter object is null or does
     * not indicate negation, the original predicate is returned
     * without modification.
     *
     * @param criteriaBuilder the CriteriaBuilder instance used
     *                        for constructing predicates
     * @param predicate the predicate to potentially negate
     * @param filterOptionalNegateFieldApiObject
     * the filter object containing negation information;
     * if null or does not indicate negation, the predicate is not modified
     * @return the modified predicate if negation is applied, otherwise
     * the original predicate
     */
    Predicate negateValidate(
            final CriteriaBuilder criteriaBuilder,
            final Predicate predicate,
            final FilterOptionalNegateFieldApiObject
                    filterOptionalNegateFieldApiObject) {

        if (Objects.isNull(filterOptionalNegateFieldApiObject)) {
            return predicate;
        }

        // Process negate case
        if (filterOptionalNegateFieldApiObject.getNegate()) {
            return criteriaBuilder.not(predicate);
        }

        return predicate;
    }

    /**
     * Constructs an {@code Optional<Predicate>} based on the provided inputs.
     * It validates the input filter object, delegates the core predicate
     * building
     * to an abstract method, and applies negation logic if specified.
     *
     * @param criteriaBuilder The {@link CriteriaBuilder} to construct
     *                        the predicate.
     * @param root The {@link From} object representing the root entity
     *             in the query.
     * @param fieldName The name of the field on which the predicate
     *                  will be built.
     * @param filterOptionalParentApiObject The filter object containing
     *                                      the value
     *        and optional negation information for building the predicate.
     * @return An {@code Optional<Predicate>} containing the constructed
     * predicate
     *         if the input is valid; otherwise, {@code Optional.empty()}.
     */
    public Optional<Predicate> buildPredicate(
            final CriteriaBuilder criteriaBuilder,
            final From root, final String fieldName,
            final FilterOptionalParentApiObject
                    filterOptionalParentApiObject) {

        if (!primaryValidate(filterOptionalParentApiObject)) {
            return Optional.empty();
        }

        // todo Unchecked casting
        Predicate predicate = buildPredicateCore(
                criteriaBuilder, root, fieldName,
                (T) filterOptionalParentApiObject.getValue().getValue());

        // Process negate case
        predicate = negateValidate(criteriaBuilder, predicate,
                filterOptionalParentApiObject.getValue().getNegate());

        return Optional.of(predicate);

    }

}
