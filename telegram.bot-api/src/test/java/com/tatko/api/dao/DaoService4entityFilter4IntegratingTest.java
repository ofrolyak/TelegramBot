package com.tatko.api.dao;

import com.tatko.api.SpringBootTestBaseMockTests;
import com.tatko.api.entities.AdJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class DaoService4entityFilter4IntegratingTest extends SpringBootTestBaseMockTests {

    @Autowired
    DaoService daoService;
    @Autowired
    AdsDaoService adsDaoService;

    @Override
    @BeforeEach
    protected void setUp() {
        super.setUp();
    }

    @Test
    void base4emptyCase4Test() {

        // Before
        Pageable pageable = PageRequest.of(0, 20);
        PredicateCreator predicateCreator = (criteriaBuilder, root)
                -> criteriaBuilder.isNull(root.get("id"));
        AdJpaEntity adJpaEntity = getGen().nextObject(AdJpaEntity.class);

        // Before
        adsDaoService.save(adJpaEntity);

        // Action
        List<AdJpaEntity> result = daoService.entityFilter(predicateCreator, pageable, AdJpaEntity.class);

        // Than
        assertThat(result)
                .isEmpty();

    }

    @Test
    void base4notEmptyCase4Test() {

        // Prepare
        Pageable pageable = PageRequest.of(0, 20);
        PredicateCreator predicateCreator = (criteriaBuilder, root)
                -> criteriaBuilder.isNotNull(root.get("id"));
        AdJpaEntity adJpaEntity = getGen().nextObject(AdJpaEntity.class);

        // Before
        adsDaoService.save(adJpaEntity);

        // Action
        List<AdJpaEntity> result = daoService.entityFilter(predicateCreator, pageable, AdJpaEntity.class);

        // Than
        assertThat(result)
                .hasSize(1);
        assertThat(result.get(0).getAd())
                .isEqualTo(adJpaEntity.getAd());

    }

}