package com.tatko.api.controllers;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.services.AdsService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class AdsController4adDelete4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    AdsService adsService;
    @InjectMocks
    AdsController adsController;

    @Test
    void base4Test() {

        // Prepare
        long id = getGen().nextLong();

        // When
        doNothing()
                .when(adsService)
                .adDelete(eq(id));

        // Action
        ResponseEntity<Void> voidResponseEntity = adsController.adDelete(id);

        // Then
        assertThat(voidResponseEntity.getStatusCode())
                .isEqualTo(HttpStatus.NO_CONTENT);
        verify(adsService, times(1))
                .adDelete(eq(id));

    }

}