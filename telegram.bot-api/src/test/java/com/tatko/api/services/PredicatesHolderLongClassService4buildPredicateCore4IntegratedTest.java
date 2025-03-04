package com.tatko.api.services;

import com.tatko.api.apis.models.FilterLongApiObject;
import com.tatko.api.apis.models.FilterOptionalLongApiObject;
import com.tatko.api.apis.models.FilterOptionalNegateFieldApiObject;
import com.tatko.api.apis.models.FilterOptionalPresentFieldApiObject;
import com.tatko.api.apis.models.FilterOptionalValueFieldApiObject;
import org.junit.jupiter.api.BeforeEach;

class PredicatesHolderLongClassService4buildPredicateCore4IntegratedTest
        extends PredicatesHolderClassService4buildPredicateCore4IntegratedTest {

    @Override
    @BeforeEach
    protected void setUp() {

        super.setUp();

        // Prepare
        FilterOptionalLongApiObject filterOptionalLongApiObject = new FilterOptionalLongApiObject();
        FilterOptionalPresentFieldApiObject filterOptionalPresentFieldApiObject = new FilterOptionalPresentFieldApiObject();
        filterOptionalPresentFieldApiObject.setPresent(Boolean.TRUE);
        filterOptionalLongApiObject.setPresent(filterOptionalPresentFieldApiObject);
        FilterOptionalValueFieldApiObject filterOptionalValueFieldApiObject = new FilterOptionalValueFieldApiObject();
        FilterOptionalNegateFieldApiObject filterOptionalNegateFieldApiObject = new FilterOptionalNegateFieldApiObject();
        //filterOptionalNegateFieldApiObject.setNegate(Boolean.FALSE);
        filterOptionalValueFieldApiObject.setNegate(filterOptionalNegateFieldApiObject);
        FilterLongApiObject filterLongApiObject = new FilterLongApiObject();
        //filterLongApiObject.setAction(FilterLongApiObject.ActionEnum.EQUAL);
        //filterLongApiObject.setOnValue(1L);
        filterOptionalValueFieldApiObject.setValue(filterLongApiObject);
        filterOptionalLongApiObject.setValue(filterOptionalValueFieldApiObject);
        filterAdApiRequest.setId(filterOptionalLongApiObject);

    }

}