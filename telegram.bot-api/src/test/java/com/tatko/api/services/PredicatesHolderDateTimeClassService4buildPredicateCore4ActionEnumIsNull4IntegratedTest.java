package com.tatko.api.services;

import com.tatko.api.apis.models.FilterDateTimeApiObject;
import com.tatko.api.apis.models.FilterStringApiObject;
import com.tatko.api.entities.AdJpaEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PredicatesHolderDateTimeClassService4buildPredicateCore4ActionEnumIsNull4IntegratedTest
        extends PredicatesHolderDateTimeClassService4buildPredicateCore4IntegratedTest {

    @BeforeEach
    @Override
    protected void setUp() {
        super.setUp();
        ((FilterDateTimeApiObject) filterAdApiRequest.getCreatingTime().getValue().getValue()).setAction(FilterDateTimeApiObject.ActionEnum.IS_NULL);
    }

    @Test
    void base4Test() {

        // Prepare
        filterAdApiRequest.getCreatingTime().getValue().getNegate().setNegate(false);

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(0);

    }

    @Test
    void base4negate4Test() {

        // Prepare
        filterAdApiRequest.getCreatingTime().getValue().getNegate().setNegate(true);

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(3);

    }

}