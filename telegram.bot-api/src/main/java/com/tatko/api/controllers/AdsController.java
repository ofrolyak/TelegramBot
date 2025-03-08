package com.tatko.api.controllers;

import com.tatko.api.apis.AdsApi;
import com.tatko.api.apis.models.AdApiObject;
import com.tatko.api.apis.models.AdCreateApiRequest;
import com.tatko.api.apis.models.AdsApiObject;
import com.tatko.api.apis.models.FilterAdApiRequest;
import com.tatko.api.services.AdsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class AdsController implements AdsApi {

    /**
     * Autowired by String AdsService class instance.
     */
    @Autowired
    private AdsService adsService;

    /**
     * Filter Ads.
     * @param page Page number.
     * @param size Page size.
     * @param body FilterAdApiRequest class instance.
     * @return
     */
    @Override
    public ResponseEntity<AdsApiObject> adsRequest(
            final Integer page, final Integer size,
            final FilterAdApiRequest body) {
        AdsApiObject ads = adsService.adsRequest(
                body, Pageable.ofSize(size).withPage(page));
        return new ResponseEntity<>(ads, HttpStatus.OK);
    }

    /**
     * Create Ad.
     * @param body AdCreateApiRequest class instance.
     * @return ResponseEntity wrapper for AdApiObject class instance.
     */
    @Override
    public ResponseEntity<AdApiObject> adCreate(final AdCreateApiRequest body) {
        AdApiObject ad = adsService.adCreate(body);
        return new ResponseEntity<>(ad, HttpStatus.CREATED);
    }

    /**
     * Delete Ad by its ID.
     * @param adId Ad ID.
     * @return ResponseEntity wrapper for Void class instance.
     */
    @Override
    public ResponseEntity<Void> adDelete(final Long adId) {
        adsService.adDelete(adId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Get Ad info by its ID.
     * @param adId Ad ID.
     * @return ResponseEntity wrapper for AdApiObject class instance.
     */
    @Override
    public ResponseEntity<AdApiObject> adGetById(final Long adId) {
        AdApiObject ad = adsService.adGetById(adId);
        return new ResponseEntity<>(ad, HttpStatus.OK);
    }

}
