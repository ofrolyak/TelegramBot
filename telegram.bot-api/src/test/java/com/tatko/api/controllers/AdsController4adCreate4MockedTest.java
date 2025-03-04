package com.tatko.api.controllers;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.AdApiObject;
import com.tatko.api.apis.models.AdCreateApiRequest;
import com.tatko.api.services.AdsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AdsController4adCreate4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    AdsService adsService;
    @InjectMocks
    AdsController adsController;

    @Test
    void base4Test() {

        // Prepare
        int page = getGen().nextInt(1000);
        int size = getGen().nextInt(1000);
        AdCreateApiRequest adCreateApiRequest = getGen().nextObject(AdCreateApiRequest.class);
        AdApiObject adApiObject = getGen().nextObject(AdApiObject.class);

        // When
        when(adsService.adCreate(eq(adCreateApiRequest)))
                .thenReturn(adApiObject);

        // Action
        ResponseEntity<AdApiObject> adApiObjectResponseEntity
                = adsController.adCreate(adCreateApiRequest);

        // Then
        assertThat(adApiObjectResponseEntity.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
        assertThat(adApiObjectResponseEntity.getBody())
                .isEqualTo(adApiObject);
        verify(adsService, times(1))
                .adCreate(eq(adCreateApiRequest));

    }

}