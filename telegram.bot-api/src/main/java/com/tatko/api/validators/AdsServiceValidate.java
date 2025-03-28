package com.tatko.api.validators;

import com.tatko.api.apis.models.AdCreateApiRequest;
import com.tatko.api.validators.exceptions.InvalidInputDataException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AdsServiceValidate {

    /**
     * 100.
     */
    private static final int ONE_HUNDRED = 100;

    /**
     * Encapsulated validate functionality for specific method.
     * @param body AdCreateApiRequest instance
     */
    public void adCreateValidate(final AdCreateApiRequest body) {

        if (Objects.isNull(body)) {
            throw new InvalidInputDataException("body is null");
        }

        if (Objects.isNull(body.getAd())
                || StringUtils.isEmpty(body.getAd().trim())) {
            throw new InvalidInputDataException("Ad text is null/empty");
        }

        if (body.getAd().length() > ONE_HUNDRED) {
            throw new InvalidInputDataException(
                    "Ad text is greater then 100 characters");
        }

    }

}
