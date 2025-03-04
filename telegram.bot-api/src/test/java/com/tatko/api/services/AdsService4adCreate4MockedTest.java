package com.tatko.api.services;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.AdApiObject;
import com.tatko.api.apis.models.AdCreateApiRequest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AdsService4adCreate4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    AdsUtilityService adsUtilityService;
    @InjectMocks
    AdsService adsService;

    @Test
    void base4Test() {

        // Prepare
        AdCreateApiRequest adCreateApiRequest = getGen().nextObject(AdCreateApiRequest.class);
        AdApiObject adApiObject = getGen().nextObject(AdApiObject.class);

        // When
        when(adsUtilityService.adCreate(eq(adCreateApiRequest)))
                .thenReturn(adApiObject);

        // Action
        AdApiObject adApiObjectCreated = adsService.adCreate(adCreateApiRequest);

        // Then
        assertThat(adApiObject)
                .isEqualTo(adApiObjectCreated);
        verify(adsUtilityService, times(1))
                .adCreate(eq(adCreateApiRequest));

    }

}