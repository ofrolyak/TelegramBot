package com.tatko.telegram.bot.service.business;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.dao.AdDaoService;
import com.tatko.telegram.bot.entity.AdJpaEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AdService4refreshDeliveredDateForAd4Test extends MockitoExtensionBaseMockTests {

    @Mock
    AdDaoService adDaoService;
    @InjectMocks
    AdService adService;

    @Test
    void process4Test() {

        // Before
        AdJpaEntity adJpaEntity = mock(AdJpaEntity.class);

        // When
        doReturn(adJpaEntity)
                .when(adDaoService)
                .save(eq(adJpaEntity));

        // Action
        adService.refreshDeliveredDateForAd(adJpaEntity);

        // Then
        verify(adJpaEntity, times(1))
                .setDeliveredTime(any(LocalDateTime.class));
        verify(adDaoService, times(1))
                .save(eq(adJpaEntity));

    }

}
