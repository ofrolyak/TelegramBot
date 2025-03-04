package com.tatko.api.services;

import com.tatko.api.apis.models.FilterLongApiObject;
import com.tatko.api.apis.models.FilterOptionalLongApiObject;
import com.tatko.api.apis.models.FilterOptionalNegateFieldApiObject;
import com.tatko.api.apis.models.FilterOptionalPresentFieldApiObject;
import com.tatko.api.apis.models.FilterOptionalStringApiObject;
import com.tatko.api.apis.models.FilterOptionalValueFieldApiObject;
import com.tatko.api.apis.models.FilterStringApiObject;
import org.junit.jupiter.api.BeforeEach;

class PredicatesHolderStringClassService4buildPredicateCore4IntegratedTest
        extends PredicatesHolderClassService4buildPredicateCore4IntegratedTest {

    @Override
    @BeforeEach
    protected void setUp() {

        super.setUp();

        // Prepare
        FilterOptionalStringApiObject filterOptionalStringApiObject = new FilterOptionalStringApiObject();
        FilterOptionalPresentFieldApiObject filterOptionalPresentFieldApiObject = new FilterOptionalPresentFieldApiObject();
        filterOptionalPresentFieldApiObject.setPresent(Boolean.TRUE);
        filterOptionalStringApiObject.setPresent(filterOptionalPresentFieldApiObject);
        FilterOptionalValueFieldApiObject filterOptionalValueFieldApiObject = new FilterOptionalValueFieldApiObject();
        FilterOptionalNegateFieldApiObject filterOptionalNegateFieldApiObject = new FilterOptionalNegateFieldApiObject();
        //filterOptionalNegateFieldApiObject.setNegate(Boolean.FALSE);
        filterOptionalValueFieldApiObject.setNegate(filterOptionalNegateFieldApiObject);
        FilterStringApiObject filterStringApiObject = new FilterStringApiObject();
        //filterLongApiObject.setAction(FilterLongApiObject.ActionEnum.EQUAL);
        //filterLongApiObject.setOnValue(1L);
        filterOptionalValueFieldApiObject.setValue(filterStringApiObject);
        filterOptionalStringApiObject.setValue(filterOptionalValueFieldApiObject);
        filterAdApiRequest.setAd(filterOptionalStringApiObject);

    }

}