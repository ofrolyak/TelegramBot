package com.tatko.api.services;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.dao.AdsDaoService;
import com.tatko.api.entities.AdJpaEntity;
import com.tatko.api.exceptions.AdNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AdsUtilityService4verifyIfAdExists4MockedTest extends MockitoExtensionBaseMockTests {

    @Mock
    AdsDaoService adsDaoService;
    @Spy
    @InjectMocks
    AdsUtilityService adsUtilityService;

    @Test
    void base4JpaEntityExists4Test() {

        // Prepare
        long id = getGen().nextLong();
        AdJpaEntity adJpaEntity = getGen().nextObject(AdJpaEntity.class);

        // When
        doReturn(Optional.of(adJpaEntity))
                .when(adsDaoService)
                .findById(eq(id));;

        // Action
        assertThatCode(() -> adsUtilityService.verifyIfAdExists(id))
            .doesNotThrowAnyException();

        // Then
        verify(adsDaoService, times(1))
                .findById(eq(id));

    }

    @Test
    void base4JpaEntityNotExists4Test() {

        // Prepare
        long id = getGen().nextLong();

        // When
        doReturn(Optional.empty())
                .when(adsDaoService)
                .findById(eq(id));;

        // Action
        assertThatCode(() -> adsUtilityService.verifyIfAdExists(id))
                .isInstanceOf(AdNotFoundException.class);

        // Then
        verify(adsDaoService, times(1))
                .findById(eq(id));

    }
}