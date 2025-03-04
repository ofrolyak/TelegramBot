package com.tatko.api.services;

import com.tatko.api.apis.models.FilterDateTimeApiObject;
import com.tatko.api.apis.models.FilterOptionalDateTimeApiObject;
import com.tatko.api.apis.models.FilterOptionalNegateFieldApiObject;
import com.tatko.api.apis.models.FilterOptionalPresentFieldApiObject;
import com.tatko.api.apis.models.FilterOptionalStringApiObject;
import com.tatko.api.apis.models.FilterOptionalValueFieldApiObject;
import com.tatko.api.apis.models.FilterStringApiObject;
import org.junit.jupiter.api.BeforeEach;

class PredicatesHolderDateTimeClassService4buildPredicateCore4IntegratedTest
        extends PredicatesHolderClassService4buildPredicateCore4IntegratedTest {

    @Override
    @BeforeEach
    protected void setUp() {

        super.setUp();

        // Prepare
        FilterOptionalDateTimeApiObject filterOptionalDateTimeApiObject = new FilterOptionalDateTimeApiObject();
        FilterOptionalPresentFieldApiObject filterOptionalPresentFieldApiObject = new FilterOptionalPresentFieldApiObject();
        filterOptionalPresentFieldApiObject.setPresent(Boolean.TRUE);
        filterOptionalDateTimeApiObject.setPresent(filterOptionalPresentFieldApiObject);
        FilterOptionalValueFieldApiObject filterOptionalValueFieldApiObject = new FilterOptionalValueFieldApiObject();
        FilterOptionalNegateFieldApiObject filterOptionalNegateFieldApiObject = new FilterOptionalNegateFieldApiObject();
        //filterOptionalNegateFieldApiObject.setNegate(Boolean.FALSE);
        filterOptionalValueFieldApiObject.setNegate(filterOptionalNegateFieldApiObject);
        FilterDateTimeApiObject filterDateTimeApiObject = new FilterDateTimeApiObject();
        //filterLongApiObject.setAction(FilterLongApiObject.ActionEnum.EQUAL);
        //filterLongApiObject.setOnValue(1L);
        filterOptionalValueFieldApiObject.setValue(filterDateTimeApiObject);
        filterOptionalDateTimeApiObject.setValue(filterOptionalValueFieldApiObject);
        filterAdApiRequest.setCreatingTime(filterOptionalDateTimeApiObject);

    }

}