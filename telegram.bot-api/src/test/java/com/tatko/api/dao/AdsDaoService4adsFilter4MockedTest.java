package com.tatko.api.dao;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.FilterAdApiRequest;
import com.tatko.api.entities.AdJpaEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AdsDaoService4adsFilter4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    DaoService daoService;
    @Spy
    @InjectMocks
    AdsDaoService adsDaoService;

    @Test
    void base4Test() {

        // Prepare
        //FilterAdApiRequest filterAdApiRequest = getGen().nextObject(FilterAdApiRequest.class);
        FilterAdApiRequest filterAdApiRequest = new FilterAdApiRequest();

        // When
        doReturn(List.of())
                .when(daoService)
                .entityFilter(any(PredicateCreator.class), any(Pageable.class), eq(AdJpaEntity.class));

        // Action
        List<AdJpaEntity> adJpaEntities = adsDaoService.adsFilter(filterAdApiRequest, Pageable.unpaged());

        // Then
        assertThat(adJpaEntities)
                .isEmpty();
        verify(daoService, times(1))
                .entityFilter(any(PredicateCreator.class), any(Pageable.class), eq(AdJpaEntity.class));


    }

}