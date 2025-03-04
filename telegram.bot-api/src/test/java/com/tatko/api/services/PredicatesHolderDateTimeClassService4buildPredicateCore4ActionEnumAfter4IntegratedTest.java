package com.tatko.api.services;

import com.tatko.api.apis.models.FilterDateTimeApiObject;
import com.tatko.api.entities.AdJpaEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PredicatesHolderDateTimeClassService4buildPredicateCore4ActionEnumAfter4IntegratedTest
        extends PredicatesHolderDateTimeClassService4buildPredicateCore4IntegratedTest {

    @BeforeEach
    @Override
    protected void setUp() {
        super.setUp();
        ((FilterDateTimeApiObject) filterAdApiRequest.getCreatingTime().getValue().getValue()).setAction(FilterDateTimeApiObject.ActionEnum.AFTER);
    }

    @Test
    void base4Test() {

        // Prepare
        filterAdApiRequest.getCreatingTime().getValue().getNegate().setNegate(false);
        ((FilterDateTimeApiObject) filterAdApiRequest.getCreatingTime().getValue().getValue()).setOnDate(adJpaEntity1.getCreatingTime().minusMinutes(1));

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(3);

    }

    @Test
    void base4negate4Test() {

        // Prepare
        filterAdApiRequest.getCreatingTime().getValue().getNegate().setNegate(true);
        ((FilterDateTimeApiObject) filterAdApiRequest.getCreatingTime().getValue().getValue()).setOnDate(adJpaEntity1.getCreatingTime().minusMinutes(1));

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(0);

    }

    @Test
    void base4notExistedValue4Test() {

        // Prepare
        filterAdApiRequest.getCreatingTime().getValue().getNegate().setNegate(false);
        ((FilterDateTimeApiObject) filterAdApiRequest.getCreatingTime().getValue().getValue()).setOnDate(LocalDateTime.now().minusYears(1));

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(3);

    }

    @Test
    void base4notExistedValueAndNegate4Test() {

        // Prepare
        filterAdApiRequest.getCreatingTime().getValue().getNegate().setNegate(true);
        ((FilterDateTimeApiObject) filterAdApiRequest.getCreatingTime().getValue().getValue()).setOnDate(LocalDateTime.now().minusYears(1));

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(0);

    }

}