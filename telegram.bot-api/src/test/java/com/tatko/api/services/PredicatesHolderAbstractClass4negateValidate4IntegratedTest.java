package com.tatko.api.services;

import com.tatko.api.SpringBootTestBaseMockTests;
import com.tatko.api.apis.models.FilterOptionalNegateFieldApiObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

class PredicatesHolderAbstractClass4negateValidate4IntegratedTest extends SpringBootTestBaseMockTests {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    PredicatesHolderLongClassService predicatesHolderLongClassService;


    @Test
    void base4filterOptionalNegateFieldApiObjectIsNull4Test() {

        // Prepare
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Predicate predicateInput = criteriaBuilder.isNull(criteriaBuilder.literal(2));
        FilterOptionalNegateFieldApiObject filterOptionalNegateFieldApiObject = new FilterOptionalNegateFieldApiObject();
        filterOptionalNegateFieldApiObject = null;

        // Action
        Predicate predicate = predicatesHolderLongClassService.negateValidate(criteriaBuilder, predicateInput, filterOptionalNegateFieldApiObject);

        // Then
        assertThat(predicate)
                .isEqualTo(predicateInput);

    }

    @Test
    void base4filterOptionalNegateFieldApiObjectIsFalse4Test() {

        // Prepare
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Predicate predicateInput = criteriaBuilder.isNull(criteriaBuilder.literal(2));
        FilterOptionalNegateFieldApiObject filterOptionalNegateFieldApiObject = new FilterOptionalNegateFieldApiObject();
        filterOptionalNegateFieldApiObject.setNegate(false);

        // Action
        Predicate predicate = predicatesHolderLongClassService.negateValidate(criteriaBuilder, predicateInput, filterOptionalNegateFieldApiObject);

        // Then
        assertThat(predicate)
                .isEqualTo(predicateInput);

    }

    @Test
    void base4filterOptionalNegateFieldApiObjectIsTrue4Test() {

        // Prepare
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Predicate predicateInput = criteriaBuilder.isNull(criteriaBuilder.literal(2));
        FilterOptionalNegateFieldApiObject filterOptionalNegateFieldApiObject = new FilterOptionalNegateFieldApiObject();
        filterOptionalNegateFieldApiObject.setNegate(true);

        // Action
        Predicate predicate = predicatesHolderLongClassService.negateValidate(criteriaBuilder, predicateInput, filterOptionalNegateFieldApiObject);

        // Then
        assertThat(predicate)
                .isNotEqualTo(predicateInput);

    }

}