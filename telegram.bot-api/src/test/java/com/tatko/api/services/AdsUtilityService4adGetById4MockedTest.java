package com.tatko.api.services;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.AdApiObject;
import com.tatko.api.dao.AdsDaoService;
import com.tatko.api.entities.AdJpaEntity;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class AdsUtilityService4adGetById4MockedTest extends MockitoExtensionBaseMockTests {

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
        doNothing()
                .when(adsUtilityService)
                .verifyIfAdExists(eq(id));
        doReturn(Optional.of(adJpaEntity))
                .when(adsDaoService)
                .findById(eq(id));

        // Action
        AdApiObject adApiObject = adsUtilityService.adGetById(id);

        // Then
        assertThat(adApiObject)
                .usingRecursiveComparison()
                .isEqualTo(adJpaEntity);
        verify(adsUtilityService, times(1))
                .verifyIfAdExists(eq(id));
        verify(adsDaoService, times(1))
                .findById(eq(id));

    }

}