package com.tatko.telegram.bot.dao;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.entity.AdJpaEntity;
import com.tatko.telegram.bot.repository.AdRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;

class AdDaoService4Save4Test extends MockitoExtensionBaseMockTests {

    @Mock
    AdRepository adRepository;
    @InjectMocks
    AdDaoService adDaoService;

    @Test
    void save4success4Test() {

        // Before
        AdJpaEntity adJpaEntity = getGen().nextObject(AdJpaEntity.class);

        // When
        Mockito.when(adRepository.save(Mockito.eq(adJpaEntity)))
                .thenReturn(adJpaEntity);

        // Action
        AdJpaEntity adJpaEntitySaved = adDaoService.save(adJpaEntity);

        // Then
        assertThat(adJpaEntitySaved)
                .isEqualTo(adJpaEntity);
        Mockito.verify(adRepository, Mockito.times(1))
                .save(Mockito.eq(adJpaEntity));
    }

}