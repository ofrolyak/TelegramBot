package com.tatko.api.dao;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.entities.AdJpaEntity;
import com.tatko.api.repositories.AdsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AdsDaoService4save4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    AdsRepository adsRepository;
    @InjectMocks
    AdsDaoService adsDaoService;

    @Test
    void base4Test() {

        // Prepare
        AdJpaEntity adJpaEntity = getGen().nextObject(AdJpaEntity.class);
        AdJpaEntity adJpaEntitySaved = getGen().nextObject(AdJpaEntity.class);

        // When
        when(adsRepository.save(eq(adJpaEntity)))
                .thenReturn(adJpaEntitySaved);

        // Action
        AdJpaEntity adJpaEntityFromDao = adsDaoService.save(adJpaEntity);

        // Then
        assertThat(adJpaEntityFromDao)
                .isEqualTo(adJpaEntitySaved);
        verify(adsRepository, times(1))
                .save(eq(adJpaEntity));


    }

}