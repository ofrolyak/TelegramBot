package com.tatko.api.dao;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.entities.AdJpaEntity;
import com.tatko.api.repositories.AdsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AdsDaoService4findById4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    AdsRepository adsRepository;
    @InjectMocks
    AdsDaoService adsDaoService;

    @Test
    void baseEmptyResult4Test() {

        // Prepare
        long id = getGen().nextLong();

        // When
        when(adsRepository.findById(eq(id)))
                .thenReturn(Optional.empty());

        // Action
        Optional<AdJpaEntity> byId = adsDaoService.findById(id);

        // Then
        assertThat(byId)
                .isEmpty();
        verify(adsRepository, times(1))
                .findById(eq(id));

    }

    @Test
    void baseNotEmptyResult4Test() {

        // Prepare
        long id = getGen().nextLong();
        AdJpaEntity adJpaEntity = getGen().nextObject(AdJpaEntity.class);

        // When
        when(adsRepository.findById(eq(id)))
                .thenReturn(Optional.of(adJpaEntity));

        // Action
        Optional<AdJpaEntity> byId = adsDaoService.findById(id);

        // Then
        assertThat(byId)
                .isNotEmpty();
        assertThat(byId.get())
                .isEqualTo(adJpaEntity);
        verify(adsRepository, times(1))
                .findById(eq(id));

    }

}