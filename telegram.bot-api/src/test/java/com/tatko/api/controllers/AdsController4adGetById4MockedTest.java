package com.tatko.api.controllers;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.AdApiObject;
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

public class AdsController4adGetById4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    AdsService adsService;
    @InjectMocks
    AdsController adsController;

    @Test
    void base4Test() {

        // Prepare
        long id = getGen().nextLong();
        AdApiObject adApiObject = getGen().nextObject(AdApiObject.class);

        // When
        when(adsService.adGetById(eq(id)))
                .thenReturn(adApiObject);

        // Action
        ResponseEntity<AdApiObject> adApiObjectResponseEntity = adsController.adGetById(id);

        // Then
        assertThat(adApiObjectResponseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(adApiObjectResponseEntity.getBody())
                .isEqualTo(adApiObject);
        verify(adsService, times(1))
                .adGetById(eq(id));

    }

}