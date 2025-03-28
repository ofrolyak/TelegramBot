package com.tatko.api.services;

import com.tatko.api.apis.models.AdApiObject;
import com.tatko.api.apis.models.AdCreateApiRequest;
import com.tatko.api.apis.models.AdsApiObject;
import com.tatko.api.apis.models.FilterAdApiRequest;
import com.tatko.api.dao.AdsDaoService;
import com.tatko.api.entities.AdJpaEntity;
import com.tatko.api.exceptions.AdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.tatko.api.services.AdsUtilityStatic.buildAdApiObject;
import static com.tatko.api.services.AdsUtilityStatic.buildAdJpaEntity;
import static com.tatko.api.services.AdsUtilityStatic.buildAdsApiObject;

@Service
public class AdsUtilityService {

    /**
     * Service for accessing and managing advertisement data in the database.
     * Provides methods for database operations like retrieval, creation,
     * deletion, and filtering of advertisements.
     *
     * This variable is an instance of AdsDaoService, which interfaces with
     * the data layer.
     *
     * Automatically injected by Spring's dependency injection framework.
     */
    @Autowired
    private AdsDaoService adsDaoService;

    /**
     * Verifies if an advertisement exists in the database by its identifier.
     * If the advertisement does not exist, an AdNotFoundException is thrown.
     *
     * @param adId the unique identifier of the advertisement to verify.
     *             Must correspond to an existing advertisement.
     */
    public void verifyIfAdExists(final Long adId) {

        //adsDaoService.findById(adId).orElseThrow(AdNotFoundException::new);
        adsDaoService.findById(adId).orElseThrow(()
                -> new AdNotFoundException(new String[]{adId.toString()}));

    }

    /**
     * Retrieves an advertisement by its unique ID.
     *
     * @param adId the unique identifier of the advertisement to fetch.
     *             Must refer to an existing advertisement; otherwise,
     *             an exception is thrown.
     * @return an AdApiObject representing the advertisement data.
     */
    public AdApiObject adGetById(final Long adId) {

        verifyIfAdExists(adId);

        AdJpaEntity adJpaEntity = adsDaoService.findById(adId).get();

        AdApiObject adApiObject = buildAdApiObject(adJpaEntity);

        return adApiObject;
    }

    /**
     * Deletes an advertisement by its ID.
     *
     * @param adId the ID of the advertisement to be deleted.
     *             Must refer to an existing advertisement;
     *             otherwise, an exception is thrown.
     */
    public void deleteById(final Long adId) {

        // Verify if exists
        verifyIfAdExists(adId);

        adsDaoService.deleteById(adId);

    }

    /**
     * Creates a new advertisement and returns its representation
     * as an AdApiObject.
     *
     * @param adCreateApiRequest the request object containing
     *                           the necessary data
     *                           for creating an advertisement.
     * @return the created advertisement represented as an AdApiObject.
     */
    public AdApiObject adCreate(final AdCreateApiRequest adCreateApiRequest) {

        AdJpaEntity adJpaEntity = buildAdJpaEntity(adCreateApiRequest);

        AdJpaEntity adJpaEntitySaved = adsDaoService.save(adJpaEntity);

        AdApiObject adApiObject = buildAdApiObject(adJpaEntitySaved);

        return adApiObject;

    }

    /**
     * Processes a request to filter advertisements based
     * on the provided filters and pagination criteria.
     *
     * @param filterAdApiRequest the filter criteria encapsulated
     *                           in a FilterAdApiRequest object.
     * @param pageable the pagination details encapsulated
     *                 in a Pageable object.
     * @return an AdsApiObject containing the filtered list of advertisements.
     */
    public AdsApiObject adsRequest(
            final FilterAdApiRequest filterAdApiRequest,
            final Pageable pageable) {

        List<AdJpaEntity> adJpaEntityList = adsDaoService.adsFilter(
                filterAdApiRequest, pageable);

        AdsApiObject adsApiObject = buildAdsApiObject(adJpaEntityList);

        return adsApiObject;

    }

}
