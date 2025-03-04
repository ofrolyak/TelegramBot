package com.tatko.api.services;

import com.tatko.api.apis.models.FilterStringApiObject;
import com.tatko.api.entities.AdJpaEntity;
import com.tatko.api.repositories.AdsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PredicatesHolderStringClassService4buildPredicateCore4ActionEnumEqual4IntegratedTest
        extends PredicatesHolderStringClassService4buildPredicateCore4IntegratedTest {

    @BeforeEach
    @Override
    protected void setUp() {
        super.setUp();
        ((FilterStringApiObject) filterAdApiRequest.getAd().getValue().getValue()).setAction(FilterStringApiObject.ActionEnum.EQUAL);
    }

    @Test
    void base4Test() {

        // Prepare
        filterAdApiRequest.getAd().getValue().getNegate().setNegate(false);
        ((FilterStringApiObject) filterAdApiRequest.getAd().getValue().getValue()).setValue(adJpaEntity1.getAd());

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(1);

    }

    @Autowired
    AdsRepository adsRepository;

    @Test
    void base4negate4Test() {

        // Prepare
        filterAdApiRequest.getAd().getValue().getNegate().setNegate(true);
        ((FilterStringApiObject) filterAdApiRequest.getAd().getValue().getValue()).setValue(adJpaEntity1.getAd());

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(2);

    }

    @Test
    void base4notExistedValue4Test() {

        // Prepare
        filterAdApiRequest.getAd().getValue().getNegate().setNegate(false);
        ((FilterStringApiObject) filterAdApiRequest.getAd().getValue().getValue()).setValue("fake");

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(0);

    }

    @Test
    void base4notExistedValueAndNegate4Test() {

        // Prepare
        filterAdApiRequest.getAd().getValue().getNegate().setNegate(true);
        ((FilterStringApiObject) filterAdApiRequest.getAd().getValue().getValue()).setValue("fake");

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, pageable);

        // Then
        assertThat(adJpaEntities)
                .hasSize(3);

    }

}