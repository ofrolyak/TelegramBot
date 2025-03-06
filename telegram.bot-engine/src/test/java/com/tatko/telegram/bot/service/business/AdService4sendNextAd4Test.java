package com.tatko.telegram.bot.service.business;

import com.tatko.telegram.bot.MockitoExtensionBaseMockTests;
import com.tatko.telegram.bot.dao.AdDaoService;
import com.tatko.telegram.bot.entity.AdJpaEntity;
import com.tatko.telegram.bot.service.custom.operation.SendMessageOperation2Params;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AdService4sendNextAd4Test extends MockitoExtensionBaseMockTests {

    @Mock
    AdDaoService adDaoService;
    @Spy
    @InjectMocks
    AdService adService;

    @Test
    void process4notPresentCase4Test() {

        // Before
        AdJpaEntity adJpaEntity = getGen().nextObject(AdJpaEntity.class);
        SendMessageOperation2Params sendMessageOperation2Params
                = mock(SendMessageOperation2Params.class);

        // When
        doReturn(Optional.empty())
                .when(adDaoService)
                .findAdToDeliver(any(LocalDateTime.class));

        // Action
        adService.sendNextAd(sendMessageOperation2Params);

        // Then
        verify(adDaoService, times(1))
                .findAdToDeliver(any(LocalDateTime.class));
        verify(adService, never())
                .deliverAdToUsers(any(SendMessageOperation2Params.class),
                        any(AdJpaEntity.class));

    }

    @Test
    void process4presentCase4Test() {

        // Before
        AdJpaEntity adJpaEntity = getGen().nextObject(AdJpaEntity.class);
        SendMessageOperation2Params sendMessageOperation2Params
                = mock(SendMessageOperation2Params.class);

        // When
        doReturn(Optional.of(adJpaEntity))
                .when(adDaoService)
                .findAdToDeliver(any(LocalDateTime.class));
        doNothing()
                .when(adService)
                .deliverAdToUsers(eq(sendMessageOperation2Params), eq(adJpaEntity));

        // Action
        adService.sendNextAd(sendMessageOperation2Params);

        // Then
        verify(adDaoService, times(1))
                .findAdToDeliver(any(LocalDateTime.class));
        verify(adService, times(1))
                .deliverAdToUsers(any(SendMessageOperation2Params.class),
                        any(AdJpaEntity.class));

    }

}
