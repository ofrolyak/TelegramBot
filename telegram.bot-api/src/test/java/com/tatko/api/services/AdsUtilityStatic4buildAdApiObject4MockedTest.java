package com.tatko.api.services;

import com.tatko.api.MockitoExtensionBaseMockTests;
import com.tatko.api.apis.models.AdApiObject;
import com.tatko.api.entities.AdJpaEntity;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AdsUtilityStatic4buildAdApiObject4MockedTest extends MockitoExtensionBaseMockTests {

    @Test
    void verifyFields4Test() {

        // Prepare
        AdJpaEntity adJpaEntity = getGen().nextObject(AdJpaEntity.class);

        // Action
        AdApiObject adApiObject = AdsUtilityStatic.buildAdApiObject(adJpaEntity);

        // Then
        assertThat(adApiObject)
                .usingRecursiveComparison()
                .isEqualTo(adJpaEntity);

    }

}