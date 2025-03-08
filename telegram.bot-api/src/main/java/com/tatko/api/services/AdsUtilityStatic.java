package com.tatko.api.services;

import com.tatko.api.apis.models.AdApiObject;
import com.tatko.api.apis.models.AdCreateApiRequest;
import com.tatko.api.apis.models.AdsApiObject;
import com.tatko.api.entities.AdJpaEntity;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AdsUtilityStatic {

    protected AdsUtilityStatic() {
        // prevents calls from subclass
        throw new UnsupportedOperationException();
    }

    static AdApiObject buildAdApiObject(final AdJpaEntity adJpaEntity) {

        AdApiObject adApiInstance = new AdApiObject();
        BeanUtils.copyProperties(adJpaEntity, adApiInstance);

        return adApiInstance;

    }

    static AdJpaEntity buildAdJpaEntity(
            final AdCreateApiRequest adCreateApiRequest) {

        AdJpaEntity adJpaEntity = new AdJpaEntity();
        BeanUtils.copyProperties(adCreateApiRequest, adJpaEntity);
        adJpaEntity.setCreatingTime(LocalDateTime.now());

        return adJpaEntity;

    }

    static AdsApiObject buildAdsApiObject(
            final List<AdJpaEntity> adJpaEntityList) {

        AdsApiObject ads = new AdsApiObject();

        ads.setAd(adJpaEntityList.stream()
                .map(AdsUtilityStatic::buildAdApiObject)
                .collect(Collectors.toList()));

        return ads;

    }

}
