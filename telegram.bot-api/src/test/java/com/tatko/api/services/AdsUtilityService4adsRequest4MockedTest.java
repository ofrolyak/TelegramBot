package com.tatko.api.services;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.AdsApiObject;
import com.tatko.api.apis.models.FilterAdApiRequest;
import com.tatko.api.dao.AdsDaoService;
import com.tatko.api.entities.AdJpaEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AdsUtilityService4adsRequest4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    AdsDaoService adsDaoService;
    @Spy
    @InjectMocks
    AdsUtilityService adsUtilityService;

    @Test
    void base4emptyCase4Test() {

        // Prepare
        //FilterAdApiRequest filterAdApiRequest = getGen().nextObject(FilterAdApiRequest.class);
        FilterAdApiRequest filterAdApiRequest = new FilterAdApiRequest();
        Pageable pageable = Pageable.unpaged();

        // When
        doReturn(List.of())
                .when(adsDaoService)
                .adsFilter(eq(filterAdApiRequest), eq(pageable));

        // Action
        AdsApiObject adsApiObject = adsUtilityService.adsRequest(filterAdApiRequest, pageable);

        // Then
        assertThat(adsApiObject.getAd())
                .isEmpty();
        verify(adsDaoService, times(1))
                .adsFilter(eq(filterAdApiRequest), eq(pageable));

    }

    @Test
    void base4notEmptyCase4Test() {

        // Prepare
        //FilterAdApiRequest filterAdApiRequest = getGen().nextObject(FilterAdApiRequest.class);
        FilterAdApiRequest filterAdApiRequest = new FilterAdApiRequest();
        AdJpaEntity adJpaEntity = getGen().nextObject(AdJpaEntity.class);
        Pageable pageable = Pageable.unpaged();

        // When
        doReturn(List.of(adJpaEntity))
                .when(adsDaoService)
                .adsFilter(eq(filterAdApiRequest), eq(pageable));

        // Action
        AdsApiObject adsApiObject = adsUtilityService.adsRequest(filterAdApiRequest, pageable);

        // Then
        assertThat(adsApiObject.getAd())
                .hasSize(1);
        assertThat(adsApiObject.getAd().get(0))
                .usingRecursiveComparison()
                .isEqualTo(adJpaEntity);
        verify(adsDaoService, times(1))
                .adsFilter(eq(filterAdApiRequest), eq(pageable));

    }

}