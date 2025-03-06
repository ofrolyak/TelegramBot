package com.tatko.api.services;

import com.tatko.api.SpringBootTestBaseMockTests;
import com.tatko.api.apis.models.FilterAdApiRequest;
import com.tatko.api.apis.models.FilterLongApiObject;
import com.tatko.api.apis.models.FilterOptionalLongApiObject;
import com.tatko.api.apis.models.FilterOptionalNegateFieldApiObject;
import com.tatko.api.apis.models.FilterOptionalPresentFieldApiObject;
import com.tatko.api.apis.models.FilterOptionalValueFieldApiObject;
import com.tatko.api.dao.AdsDaoService;
import com.tatko.api.entities.AdJpaEntity;
import com.tatko.api.repositories.AdsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class PredicatesHolderClassService4buildPredicateCore4IntegratedTest extends SpringBootTestBaseMockTests {

    @Autowired
    AdsDaoService adsDaoService;

    AdJpaEntity adJpaEntity1;
    AdJpaEntity adJpaEntity2;
    AdJpaEntity adJpaEntity3;

    Pageable pageable;

    FilterAdApiRequest filterAdApiRequest;

    @BeforeEach
    protected void setUp() {

        super.setUp();

        // Prepare
        adJpaEntity1 = getGen().nextAdJpaEntity();
        //adJpaEntity1.setId(1L);
        adJpaEntity1.setAd("ad1");
        adJpaEntity1.setCreatingTime(LocalDateTime.now().minusDays(1));
        adJpaEntity1.setDeliveredTime(LocalDateTime.now().minusDays(1));
        adJpaEntity1 = adsDaoService.save(adJpaEntity1);

        adJpaEntity2 = getGen().nextAdJpaEntity();
        //adJpaEntity2.setId(2L);
        adJpaEntity2.setAd("ad2");
        adJpaEntity2.setCreatingTime(LocalDateTime.now());
        adJpaEntity2.setDeliveredTime(LocalDateTime.now());
        adJpaEntity2 = adsDaoService.save(adJpaEntity2);

        adJpaEntity3 = getGen().nextAdJpaEntity();
        //adJpaEntity3.setId(3L);
        adJpaEntity3.setAd("ad3");
        adJpaEntity3.setCreatingTime(LocalDateTime.now().plusDays(1));
        adJpaEntity3.setDeliveredTime(LocalDateTime.now().plusDays(1));
        adJpaEntity3 = adsDaoService.save(adJpaEntity3);

        // Prepare
        pageable = PageRequest.of(0, 20);

        // Prepare
        filterAdApiRequest = new FilterAdApiRequest();

    }

}