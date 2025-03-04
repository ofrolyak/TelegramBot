package com.tatko.api.services;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.AdApiObject;
import com.tatko.api.apis.models.AdCreateApiRequest;
import com.tatko.api.dao.AdsDaoService;
import com.tatko.api.entities.AdJpaEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AdsUtilityService4adCreate4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    AdsDaoService adsDaoService;
    @InjectMocks
    AdsUtilityService adsUtilityService;

    @Test
    void base4Test() {

        // Prepare
        AdCreateApiRequest adCreateApiRequest = getGen().nextObject(AdCreateApiRequest.class);
        AdJpaEntity adJpaEntity = getGen().nextObject(AdJpaEntity.class);

        // When
        when(adsDaoService.save(any(AdJpaEntity.class)))
                .thenReturn(adJpaEntity);

        // Action
        AdApiObject adApiObject = adsUtilityService.adCreate(adCreateApiRequest);

        // Then
        assertThat(adApiObject)
                .usingRecursiveComparison()
                .isEqualTo(adJpaEntity);
        verify(adsDaoService, times(1))
                .save(any(AdJpaEntity.class));

    }

}