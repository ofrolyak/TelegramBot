package com.tatko.api.services;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.AdCreateApiRequest;
import com.tatko.api.entities.AdJpaEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AdsUtilityStatic4buildAdJpaEntity4MockedTest extends MockitoExtensionBaseMockTests {

    @Test
    void verifyFields4Test() {

        // Prepare
        AdCreateApiRequest adCreateApiRequest = getGen().nextObject(AdCreateApiRequest.class);

        // Action
        AdJpaEntity adJpaEntity = AdsUtilityStatic.buildAdJpaEntity(adCreateApiRequest);

        // Then
        assertThat(adJpaEntity.getAd())
                .isEqualTo(adCreateApiRequest.getAd());
        assertThat(adJpaEntity.getCreatingTime())
                .isNotNull();

    }

}