package com.tatko.api.services;

import com.tatko.api.SpringBootTestBaseMockTests;
import com.tatko.api.apis.models.FilterOptionalParentApiObject;
import com.tatko.api.apis.models.FilterOptionalPresentFieldApiObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

class PredicatesHolderAbstractClass4primaryValidate4IntegratedTest extends SpringBootTestBaseMockTests {

    @Autowired
    PredicatesHolderLongClassService predicatesHolderLongClassService;

    @Test
    void base4filterOptionalParentApiObjectIsNull4Test() {

        // Prepare
        FilterOptionalParentApiObject filterOptionalParentApiObject = new FilterOptionalParentApiObject();

        // Before
        filterOptionalParentApiObject = null;

        // Action
        boolean isValidated = predicatesHolderLongClassService.primaryValidate(filterOptionalParentApiObject);

        // Then
        assertThat(isValidated)
                .isFalse();

    }

    @Test
    void base4filterOptionalParentApiObjectGetPresentIsNull4Test() {

        // Prepare
        FilterOptionalParentApiObject filterOptionalParentApiObject = new FilterOptionalParentApiObject();

        // Before
        filterOptionalParentApiObject.setPresent(null);

        // Action
        boolean isValidated = predicatesHolderLongClassService.primaryValidate(filterOptionalParentApiObject);

        // Then
        assertThat(isValidated)
                .isFalse();

    }

    @Test
    void base4filterOptionalParentApiObjectGetPresentIsFalse4Test() {

        // Prepare
        FilterOptionalParentApiObject filterOptionalParentApiObject = new FilterOptionalParentApiObject();

        // Before
        FilterOptionalPresentFieldApiObject filterOptionalPresentFieldApiObject = new FilterOptionalPresentFieldApiObject();
        filterOptionalPresentFieldApiObject.setPresent(false);
        filterOptionalParentApiObject.setPresent(filterOptionalPresentFieldApiObject);

        // Action
        boolean isValidated = predicatesHolderLongClassService.primaryValidate(filterOptionalParentApiObject);

        // Then
        assertThat(isValidated)
                .isFalse();

    }

    @Test
    void base4filterOptionalParentApiObjectGetPresentIsTrue4Test() {

        // Prepare
        FilterOptionalParentApiObject filterOptionalParentApiObject = new FilterOptionalParentApiObject();

        // Before
        FilterOptionalPresentFieldApiObject filterOptionalPresentFieldApiObject = new FilterOptionalPresentFieldApiObject();
        filterOptionalPresentFieldApiObject.setPresent(true);
        filterOptionalParentApiObject.setPresent(filterOptionalPresentFieldApiObject);

        // Action
        boolean isValidated = predicatesHolderLongClassService.primaryValidate(filterOptionalParentApiObject);

        // Then
        assertThat(isValidated)
                .isTrue();

    }
}