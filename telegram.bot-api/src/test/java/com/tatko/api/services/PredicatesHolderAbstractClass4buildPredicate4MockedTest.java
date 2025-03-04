package com.tatko.api.services;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.FilterLongApiObject;
import com.tatko.api.apis.models.FilterOptionalNegateFieldApiObject;
import com.tatko.api.apis.models.FilterOptionalParentApiObject;
import com.tatko.api.apis.models.FilterOptionalPresentFieldApiObject;
import com.tatko.api.apis.models.FilterOptionalValueFieldApiObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Predicate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

class PredicatesHolderAbstractClass4buildPredicate4MockedTest extends MockitoExtensionBaseMockTests {

    @Spy
    @InjectMocks
    PredicatesHolderLongClassService predicatesHolderLongClassService;

    @Test
    void base4Test() {

        // Prepare
        Predicate predicateInput = mock(Predicate.class);
        FilterOptionalParentApiObject filterOptionalParentApiObject = new FilterOptionalParentApiObject();
        FilterOptionalPresentFieldApiObject filterOptionalPresentFieldApiObject = new FilterOptionalPresentFieldApiObject();
        filterOptionalPresentFieldApiObject.setPresent(true);
        filterOptionalParentApiObject.setPresent(filterOptionalPresentFieldApiObject);
        FilterOptionalValueFieldApiObject filterOptionalValueFieldApiObject = new FilterOptionalValueFieldApiObject();
        filterOptionalValueFieldApiObject.setValue(new FilterLongApiObject());
        filterOptionalParentApiObject.setValue(filterOptionalValueFieldApiObject);

        // When
        doReturn(true)
                .when(predicatesHolderLongClassService)
                .primaryValidate(nullable(FilterOptionalParentApiObject.class));
        lenient().doReturn(predicateInput)
                .when(predicatesHolderLongClassService)
                .buildPredicateCore(nullable(CriteriaBuilder.class), nullable(From.class), nullable(String.class), nullable(FilterLongApiObject.class));
        lenient().doReturn(predicateInput)
                .when(predicatesHolderLongClassService)
                .negateValidate(nullable(CriteriaBuilder.class), nullable(Predicate.class), nullable(FilterOptionalNegateFieldApiObject.class));

        // Action
        Optional<Predicate> predicate = predicatesHolderLongClassService.buildPredicate(null, null, null, filterOptionalParentApiObject);

        // Then
        assertThat(predicate)
                .isNotEmpty();
    }

}