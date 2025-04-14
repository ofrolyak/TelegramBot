package com.tatko.api.controllers;

import com.tatko.api.apis.AdsApi;
import com.tatko.api.apis.models.AdApiObject;
import com.tatko.api.apis.models.AdCreateApiRequest;
import com.tatko.api.apis.models.AdsApiObject;
import com.tatko.api.apis.models.FilterAdApiRequest;
import com.tatko.api.services.AdsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class AdsController implements AdsApi {

    /**
     * The AdsService instance, autowired into the AdsController class,
     * serves as the primary service for handling advertisement-related
     * operations such as creating, retrieving, filtering, and deleting
     * advertisements. It encapsulates interactions with the underlying
     * business logic and utilities to provide these functionalities.
     */
    @Autowired
    private AdsService adsService;

    /**
     * Handles requests for retrieving advertisements based on filtering
     * criteria with pagination.
     *
     * @param page The page number of results to retrieve.
     * @param size The number of results per page.
     * @param body The filtering criteria for retrieving advertisements.
     * @return A ResponseEntity containing an AdsApiObject with the requested
     * advertisements and a HTTP status of OK.
     */
    @Override
    @PreAuthorize("hasRole('user_role')")
    public ResponseEntity<AdsApiObject> adsRequest(
            final Integer page, final Integer size,
            final FilterAdApiRequest body) {
        log.info("Started AdsController: adsRequest for page: {}, "
                + "size: {}, body {}", page, size, body);
        AdsApiObject ads = adsService.adsRequest(
                body, Pageable.ofSize(size).withPage(page));
        log.info("Finished AdsController: AdsApiObject : {}", ads);
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    /**
     * Creates a new advertisement using the provided request body
     * and returns the created advertisement object.
     *
     * @param body The request object containing details of the advertisement
     *             to be created.
     * @return A ResponseEntity containing the created AdApiObject
     * and a HTTP status of CREATED.
     */
    @Override
    @PreAuthorize("hasRole('admin_role')")
    public ResponseEntity<AdApiObject> adCreate(final AdCreateApiRequest body) {
        log.info("Started AdsController: adCreate for body {}", body);
        AdApiObject ad = adsService.adCreate(body);
        log.info("Finished AdsController: AdApiObject : {}", ad);
        return new ResponseEntity<>(ad, HttpStatus.CREATED);
    }

    /**
     * Deletes an advertisement by its unique identifier.
     *
     * @param adId The unique identifier of the advertisement to be deleted.
     *             Must correspond to an existing advertisement in the system.
     * @return A ResponseEntity with an HTTP status of NO_CONTENT indicating
     * the advertisement
     *         was successfully deleted.
     */
    @Override
    @PreAuthorize("hasRole('admin_role')")
    public ResponseEntity<Void> adDelete(final Long adId) {
        log.info("Started AdsController: adDelete for adId {}", adId);
        adsService.adDelete(adId);
        log.info("Finished AdsController: adDelete for adId {}", adId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieves an advertisement by its unique identifier.
     *
     * @param adId The unique identifier of the advertisement to retrieve.
     *             Must correspond to an existing advertisement in the system.
     * @return A ResponseEntity containing the retrieved AdApiObject
     * and an HTTP status of OK.
     */
    @Override
    @PreAuthorize("hasRole('user_role')")
    public ResponseEntity<AdApiObject> adGetById(final Long adId) {
        log.info("Started AdsController: adGetById for adId {}", adId);
        AdApiObject ad = adsService.adGetById(adId);
        log.info("Finished AdsController: adGetById for adId {}", adId);
        return new ResponseEntity<>(ad, HttpStatus.OK);
    }

}
