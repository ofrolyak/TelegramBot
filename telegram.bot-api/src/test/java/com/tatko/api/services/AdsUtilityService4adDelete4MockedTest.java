package com.tatko.api.services;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.dao.AdsDaoService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AdsUtilityService4adDelete4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    AdsDaoService adsDaoService;
    @Spy
    @InjectMocks
    AdsUtilityService adsUtilityService;

    @Test
    void base4Test() {

        // Prepare
        long id = getGen().nextLong();

        // When
        doNothing()
                .when(adsUtilityService)
                .verifyIfAdExists(eq(id));;
        doNothing()
                .when(adsDaoService)
                .deleteById(eq(id));

        // Action
        adsUtilityService.deleteById(id);

        // Then
        verify(adsUtilityService, times(1))
                .verifyIfAdExists(eq(id));
        verify(adsDaoService, times(1))
                .deleteById(eq(id));

    }

}