package com.tatko.telegram.bot.dao;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.entity.AdJpaEntity;
import com.tatko.telegram.bot.repository.AdRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;

class AdDao4FindAdToDeliver4TestService extends MockitoExtensionBaseMockTests {

    @Mock
    AdRepository adRepository;
    @InjectMocks
    AdDaoService adDaoService;

    @Test
    void findAdToDeliver4successAndExist4Test() {

        // Before
        AdJpaEntity adJpaEntity = getGen().nextObject(AdJpaEntity.class);
        LocalDateTime localDateTime = getGen().nextObject(LocalDateTime.class);

        // When
        Mockito.when(
                adRepository
                        .findFirstByDeliveredTimeIsNullOrDeliveredTimeIsBefore(
                eq(localDateTime)))
                .thenReturn(Optional.of(adJpaEntity));

        // Action
        Optional<AdJpaEntity> adOptional = adDaoService.findAdToDeliver(localDateTime);

        // Then
        assertThat(adOptional.isPresent())
                .isTrue();
        assertThat(adOptional.get())
                .isEqualTo(adJpaEntity);
        Mockito.verify(adRepository, Mockito.times(1))
                .findFirstByDeliveredTimeIsNullOrDeliveredTimeIsBefore(
                        eq(localDateTime));
    }

    @Test
    void findAdToDeliver4successAndNotExist4Test() {

        // Before
        AdJpaEntity adJpaEntity = getGen().nextObject(AdJpaEntity.class);
        LocalDateTime localDateTime = getGen().nextObject(LocalDateTime.class);

        // When
        Mockito.when(
                adRepository
                        .findFirstByDeliveredTimeIsNullOrDeliveredTimeIsBefore(
                eq(localDateTime)))
                .thenReturn(Optional.empty());

        // Action
        Optional<AdJpaEntity> adOptional = adDaoService.findAdToDeliver(localDateTime);

        // Then
        assertThat(adOptional.isEmpty())
                .isTrue();
        Mockito.verify(adRepository, Mockito.times(1))
                .findFirstByDeliveredTimeIsNullOrDeliveredTimeIsBefore(
                        eq(localDateTime));
    }

}