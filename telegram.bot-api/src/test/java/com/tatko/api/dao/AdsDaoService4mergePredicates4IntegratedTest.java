package com.tatko.api.dao;

import com.tatko.api.SpringBootTestBaseMockTests;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class AdsDaoService4mergePredicates4IntegratedTest extends SpringBootTestBaseMockTests {

    @PersistenceContext
    EntityManager entityManager;
    @Autowired
    AdsDaoService adsDaoService;

    @Test
    void base4emptyListOfPredicates4Test() {

        // Prepare
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Action
        Predicate predicate = adsDaoService.mergePredicates(criteriaBuilder, List.of());

        // Then
        assertThat(predicate)
                .isNotNull();
        assertThat(predicate.getExpressions())
                .isEmpty();

    }

    @Test
    void base4listOfEmptyOptionalOfPredicates4Test() {

        // Prepare
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

        // Action
        Predicate predicate = adsDaoService.mergePredicates(criteriaBuilder, List.of(Optional.empty()));

        // Then
        assertThat(predicate)
                .isNotNull();
        assertThat(predicate.getExpressions())
                .isEmpty();

    }

    @Test
    void base4notEmptyListOfPredicates4Test() {

        // Prepare
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        Predicate predicateInput = criteriaBuilder.isNull(criteriaBuilder.literal(2));

        // Action
        Predicate predicate = adsDaoService.mergePredicates(criteriaBuilder, List.of(Optional.of(predicateInput)));

        // Then
        assertThat(predicate)
                .isNotNull();
        assertThat(predicate.getExpressions())
                .hasSize(1);

    }

}