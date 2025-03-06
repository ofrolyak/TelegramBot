package com.tatko.api.dao;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.FilterAdApiRequest;
import com.tatko.api.apis.models.FilterOptionalDateTimeApiObject;
import com.tatko.api.apis.models.FilterOptionalLongApiObject;
import com.tatko.api.apis.models.FilterOptionalParentApiObject;
import com.tatko.api.apis.models.FilterOptionalStringApiObject;
import com.tatko.api.services.PredicatesHolderDateTimeClassService;
import com.tatko.api.services.PredicatesHolderLongClassService;
import com.tatko.api.services.PredicatesHolderStringClassService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.From;
import jakarta.persistence.criteria.Predicate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.doReturn;

class AdsDaoService4buildPredicate4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    private PredicatesHolderLongClassService predicatesHolderLongClassService;
    @Mock
    private PredicatesHolderStringClassService predicatesHolderStringClassService;
    @Mock
    private PredicatesHolderDateTimeClassService predicatesHolderDateTimeClassService;
    @Spy
    @InjectMocks
    private AdsDaoService adsDaoService;

    @Test
    void base4Test() {

        // Prepare
        CriteriaBuilder criteriaBuilder = null;
        From from = null;
        FilterAdApiRequest filterAdApiRequest = new FilterAdApiRequest();
        filterAdApiRequest.setId(new FilterOptionalLongApiObject());
        filterAdApiRequest.setAd(new FilterOptionalStringApiObject());
        filterAdApiRequest.setCreatingTime(new FilterOptionalDateTimeApiObject());
        filterAdApiRequest.setDeliveredTime(new FilterOptionalDateTimeApiObject());
        Predicate predicate = null;


        // When
        doReturn(Optional.empty())
                .when(predicatesHolderLongClassService)
                .buildPredicate(nullable(CriteriaBuilder.class), nullable(From.class),
                        anyString(), nullable(FilterOptionalParentApiObject.class));
        doReturn(Optional.empty())
                .when(predicatesHolderStringClassService)
                .buildPredicate(nullable(CriteriaBuilder.class), nullable(From.class),
                        anyString(), nullable(FilterOptionalParentApiObject.class));
        doReturn(Optional.empty())
                .when(predicatesHolderDateTimeClassService)
                .buildPredicate(nullable(CriteriaBuilder.class), nullable(From.class),
                        anyString(), nullable(FilterOptionalParentApiObject.class));
        doReturn(predicate)
                .when(adsDaoService)
                .mergePredicates(nullable(CriteriaBuilder.class), anyList());

        // Action
        Predicate predicateAfter = adsDaoService.buildPredicate(criteriaBuilder, from, filterAdApiRequest);

        // Then
        assertThat(predicateAfter)
                .isEqualTo(predicate);

    }

}