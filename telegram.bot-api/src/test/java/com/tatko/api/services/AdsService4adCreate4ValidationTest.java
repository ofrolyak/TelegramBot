package com.tatko.api.services;

import com.tatko.api.SpringBootTestBaseMockTests;
import com.tatko.api.apis.models.AdCreateApiRequest;
import com.tatko.api.validators.AdsServiceValidate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AdsService4adCreate4ValidationTest extends SpringBootTestBaseMockTests {

    @MockBean
    private AdsServiceValidate adsServiceValidate;
    @MockBean
    private AdsUtilityService adsUtilityService;
    @Autowired
    private AdsService adsService;

    @Test
    void adCreate() {

        // Prepare
        AdCreateApiRequest adCreateApiRequest
                = getGen().nextObject(AdCreateApiRequest.class);

        // When
        doNothing()
                .when(adsServiceValidate)
                .adCreateValidate(eq(adCreateApiRequest));
        doReturn(null)
                .when(adsUtilityService)
                .adCreate(eq(adCreateApiRequest));

        // Action
        adsService.adCreate(adCreateApiRequest);

        // Then
        verify(adsServiceValidate, times(1))
                .adCreateValidate(eq(adCreateApiRequest));

    }

}