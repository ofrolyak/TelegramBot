package com.tatko.api.services;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.AdsApiObject;
import com.tatko.api.entities.AdJpaEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AdsUtilityStatic4buildAdsApiObject4MockedTest extends MockitoExtensionBaseMockTests {

    @Test
    void verifyEmptyResult4Test() {

        // Action
        AdsApiObject adsApiObject = AdsUtilityStatic.buildAdsApiObject(List.of());

        // Then
        assertThat(adsApiObject)
                .isNotNull();
        assertThat(adsApiObject.getAd())
                .hasSize(0);

    }

    @Test
    void verifyNotEmptyResult4Test() {

        // Prepare
        AdJpaEntity adJpaEntity = getGen().nextObject(AdJpaEntity.class);

        // Action
        AdsApiObject adsApiObject = AdsUtilityStatic.buildAdsApiObject(List.of(adJpaEntity));

        // Then
        assertThat(adsApiObject)
                .isNotNull();
        assertThat(adsApiObject.getAd())
                .hasSize(1);
        assertThat(adsApiObject.getAd().get(0))
                .usingRecursiveComparison()
                .isEqualTo(adJpaEntity);

    }

}