package com.tatko.api.services;

import com.tatko.api.apis.models.FilterLongApiObject;
import com.tatko.api.apis.models.FilterStringApiObject;
import com.tatko.api.entities.AdJpaEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PredicatesHolderStringClassService4buildPredicateCore4ActionEnumIsNull4IntegratedTest
        extends PredicatesHolderStringClassService4buildPredicateCore4IntegratedTest {

    @BeforeEach
    @Override
    protected void setUp() {
        super.setUp();
        ((FilterStringApiObject) filterAdApiRequest.getAd().getValue().getValue()).setAction(FilterStringApiObject.ActionEnum.IS_NULL);
    }

    @Test
    void base4Test() {

        // Prepare
        filterAdApiRequest.getAd().getValue().getNegate().setNegate(false);

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(0);

    }

    @Test
    void base4negate4Test() {

        // Prepare
        filterAdApiRequest.getAd().getValue().getNegate().setNegate(true);

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(3);

    }

}