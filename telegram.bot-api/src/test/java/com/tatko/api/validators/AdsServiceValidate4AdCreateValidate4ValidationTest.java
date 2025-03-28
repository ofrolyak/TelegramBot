package com.tatko.api.validators;

import com.tatko.api.SpringBootTestBaseMockTests;
import com.tatko.api.apis.models.AdCreateApiRequest;
import com.tatko.api.validators.exceptions.InvalidInputDataException;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThatCode;

class AdsServiceValidate4AdCreateValidate4ValidationTest
        extends SpringBootTestBaseMockTests {

    @Autowired
    private AdsServiceValidate adsServiceValidate;

    @Test
    void adCreateValidate4bodyIsNull4Test() {

        // Action
        assertThatCode(() -> adsServiceValidate.adCreateValidate(null))
                .isInstanceOf(InvalidInputDataException.class);

    }

    @Test
    void adCreateValidate4adIsNull4Test() {

        // Before
        AdCreateApiRequest adCreateApiRequest
                = getGen().nextObject(AdCreateApiRequest.class);
        adCreateApiRequest.setAd(null);

        // Action
        assertThatCode(()
                -> adsServiceValidate.adCreateValidate(adCreateApiRequest))
                .isInstanceOf(InvalidInputDataException.class);

    }

    @Test
    void adCreateValidate4adIsEmpty4Test() {

        // Before
        AdCreateApiRequest adCreateApiRequest
                = getGen().nextObject(AdCreateApiRequest.class);
        adCreateApiRequest.setAd(StringUtils.EMPTY);

        // Action
        assertThatCode(()
                -> adsServiceValidate.adCreateValidate(adCreateApiRequest))
                .isInstanceOf(InvalidInputDataException.class);

    }

    @Test
    void adCreateValidate4adIsSpace4Test() {

        // Before
        AdCreateApiRequest adCreateApiRequest
                = getGen().nextObject(AdCreateApiRequest.class);
        adCreateApiRequest.setAd(StringUtils.SPACE);

        // Action
        assertThatCode(()
                -> adsServiceValidate.adCreateValidate(adCreateApiRequest))
                .isInstanceOf(InvalidInputDataException.class);

    }

    @Test
    void adCreateValidate4byLength4Test() {

        // Before
        AdCreateApiRequest adCreateApiRequest
                = getGen().nextObject(AdCreateApiRequest.class);
        adCreateApiRequest.setAd("a".repeat(101));

        // Action
        assertThatCode(()
                -> adsServiceValidate.adCreateValidate(adCreateApiRequest))
                .isInstanceOf(InvalidInputDataException.class);

    }

    @Test
    void adCreateValidate4success4Test() {

        // Before
        AdCreateApiRequest adCreateApiRequest
                = getGen().nextObject(AdCreateApiRequest.class);
        adCreateApiRequest.setAd("a".repeat(100));

        // Action
        assertThatCode(()
                -> adsServiceValidate.adCreateValidate(adCreateApiRequest))
                .doesNotThrowAnyException();

    }

}