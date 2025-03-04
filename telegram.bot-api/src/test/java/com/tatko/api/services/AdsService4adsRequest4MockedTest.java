package com.tatko.api.services;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.AdsApiObject;
import com.tatko.api.apis.models.FilterAdApiRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AdsService4adsRequest4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    AdsUtilityService adsUtilityService;
    @InjectMocks
    AdsService adsService;

    @Test
    void base4Test() {

        // Prepare
        AdsApiObject adsApiObject = getGen().nextObject(AdsApiObject.class);
        //FilterAdApiRequest filterAdApiRequest = getGen().nextObject(FilterAdApiRequest.class);
        FilterAdApiRequest filterAdApiRequest = new FilterAdApiRequest();
        Pageable pageable = Pageable.unpaged();
        long id = getGen().nextLong();

        // When
        doReturn(adsApiObject)
                .when(adsUtilityService)
                .adsRequest(eq(filterAdApiRequest), eq(pageable));

        // Action
        AdsApiObject adsApiObjectRetrieved = adsService.adsRequest(filterAdApiRequest, pageable);

        // Then
        assertThat(adsApiObjectRetrieved)
                .isEqualTo(adsApiObject);
        verify(adsUtilityService, times(1))
                .adsRequest(eq(filterAdApiRequest), eq(pageable));

    }

}