package com.tatko.api.services;

import com.tatko.api.apis.models.FilterLongApiObject;
import com.tatko.api.entities.AdJpaEntity;
import com.tatko.api.repositories.AdsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PredicatesHolderLongClassService4buildPredicateCore4ActionEnumGreaterEqual4IntegratedTest
        extends PredicatesHolderLongClassService4buildPredicateCore4IntegratedTest {

    @BeforeEach
    @Override
    protected void setUp() {
        super.setUp();
        ((FilterLongApiObject) filterAdApiRequest.getId().getValue().getValue()).setAction(FilterLongApiObject.ActionEnum.GREATER_EQUAL);
    }

    @Test
    void base4Test() {

        // Prepare
        filterAdApiRequest.getId().getValue().getNegate().setNegate(false);
        ((FilterLongApiObject) filterAdApiRequest.getId().getValue().getValue()).setOnValue(adJpaEntity1.getId());

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(3);

    }

    @Test
    void base4negate4Test() {

        // Prepare
        filterAdApiRequest.getId().getValue().getNegate().setNegate(true);
        ((FilterLongApiObject) filterAdApiRequest.getId().getValue().getValue()).setOnValue(adJpaEntity1.getId());

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(0);

    }

    @Test
    void base4notExistedValue4Test() {

        // Prepare
        filterAdApiRequest.getId().getValue().getNegate().setNegate(false);
        ((FilterLongApiObject) filterAdApiRequest.getId().getValue().getValue()).setOnValue(0L);

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(3);

    }

    @Test
    void base4notExistedValueAndNegate4Test() {

        // Prepare
        filterAdApiRequest.getId().getValue().getNegate().setNegate(true);
        ((FilterLongApiObject) filterAdApiRequest.getId().getValue().getValue()).setOnValue(0L);

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(0);

    }

}