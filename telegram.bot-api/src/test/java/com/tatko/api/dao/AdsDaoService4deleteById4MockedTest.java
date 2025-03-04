package com.tatko.api.dao;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.repositories.AdsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AdsDaoService4deleteById4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    AdsRepository adsRepository;
    @InjectMocks
    AdsDaoService adsDaoService;

    @Test
    void base4Test() {

        // Prepare
        long id = getGen().nextLong();

        // When
        doNothing()
                .when(adsRepository)
                .deleteById(eq(id));

        // Action
        adsDaoService.deleteById(id);

        // Then
        verify(adsRepository, times(1))
                .deleteById(eq(id));

    }

}