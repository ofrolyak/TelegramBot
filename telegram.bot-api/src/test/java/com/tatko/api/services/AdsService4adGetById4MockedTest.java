package com.tatko.api.services;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.AdApiObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AdsService4adGetById4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    AdsUtilityService adsUtilityService;
    @InjectMocks
    AdsService adsService;

    @Test
    void base4Test() {

        // Prepare
        AdApiObject adApiObject = getGen().nextObject(AdApiObject.class);
        long id = getGen().nextLong();

        // When
        doReturn(adApiObject)
                .when(adsUtilityService)
                .adGetById(eq(id));

        // Action
        AdApiObject adApiObjectRetrieved = adsService.adGetById(id);

        // Then
        assertThat(adApiObjectRetrieved)
                .isEqualTo(adApiObject);
        verify(adsUtilityService, times(1))
                .adGetById(eq(id));

    }

}