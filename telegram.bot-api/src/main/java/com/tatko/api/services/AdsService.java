package com.tatko.api.services;

import com.tatko.api.apis.models.AdApiObject;
import com.tatko.api.apis.models.AdCreateApiRequest;
import com.tatko.api.apis.models.AdsApiObject;
import com.tatko.api.apis.models.FilterAdApiRequest;
import com.tatko.api.audit.annotations.InputValidateAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdsService {

    /**
     * The AdsUtilityService instance used to manage and handle operations
     * related to advertisements in the application. It provides functionalities
     * such as creating, retrieving, filtering, and deleting advertisements.
     * This service is utilized as a dependency in the AdsService class to
     * perform advertisement-related business logic.
     */
    @Autowired
    private AdsUtilityService adsUtilityService;

    /**
     * Filters and retrieves advertisements based on the specified
     * filter criteria
     * and pagination details.
     *
     * @param filterAdApiRequest the filter criteria encapsulated
     *                           in a FilterAdApiRequest object
     * @param pageable the pagination details encapsulated in a Pageable object
     * @return an AdsApiObject containing the filtered list of advertisements
     */
    public AdsApiObject adsRequest(
            final FilterAdApiRequest filterAdApiRequest,
            final Pageable pageable) {

        AdsApiObject ads = adsUtilityService.adsRequest(
                filterAdApiRequest, pageable);

        return ads;

    }

    /**
     * Creates a new advertisement based on the provided request object and
     * returns its representation as an AdApiObject.
     *
     * @param body the request object containing necessary data for creating
     *             an advertisement.
     * @return the created advertisement represented as an AdApi*/
    @InputValidateAnnotation
    public AdApiObject adCreate(final AdCreateApiRequest body) {

        AdApiObject adApiObject = adsUtilityService.adCreate(body);

        return adApiObject;

    }

    /**
     * Deletes an advertisement by its unique identifier.
     *
     * @param adId the unique identifier of the advertisement to be deleted.
     *             Must correspond to an existing advertisement in the system.
     */
    public void adDelete(final Long adId) {

        adsUtilityService.deleteById(adId);

    }

    /**
     * Retrieves an advertisement by its unique ID.
     *
     * @param adId the unique identifier of the advertisement to be retrieved.
     *             It must correspond to an existing advertisement
     *             in the system.
     * @return an AdApiObject representing the advertisement data.
     */
    public AdApiObject adGetById(final Long adId) {

        AdApiObject adApiObject = adsUtilityService.adGetById(adId);

        return adApiObject;
    }

}
