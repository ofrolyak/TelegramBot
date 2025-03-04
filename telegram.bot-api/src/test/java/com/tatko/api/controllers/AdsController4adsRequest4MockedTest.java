package com.tatko.api.controllers;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.AdsApiObject;
import com.tatko.api.apis.models.FilterAdApiRequest;
import com.tatko.api.services.AdsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AdsController4adsRequest4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    AdsService adsService;
    @InjectMocks
    AdsController adsController;

    @Test
    void base4Test() {

        // Prepare
        int page = getGen().nextInt(1000);
        int size = getGen().nextInt(1000);
        //FilterAdApiRequest filterAdApiRequest = getGen().nextObject(FilterAdApiRequest.class);
        FilterAdApiRequest filterAdApiRequest = new FilterAdApiRequest();
        AdsApiObject adsApiObject = getGen().nextObject(AdsApiObject.class);

        // When
        when(adsService.adsRequest(eq(filterAdApiRequest), any(Pageable.class)))
                .thenReturn(adsApiObject);

        // Action
        ResponseEntity<AdsApiObject> adsApiObjectResponseEntity
                = adsController.adsRequest(page, size, filterAdApiRequest);

        // Then
        assertThat(adsApiObjectResponseEntity.getStatusCode())
                .isEqualTo(HttpStatus.OK);
        assertThat(adsApiObjectResponseEntity.getBody())
                .isEqualTo(adsApiObject);
        verify(adsService, times(1))
                .adsRequest(eq(filterAdApiRequest), any(Pageable.class));

    }

}