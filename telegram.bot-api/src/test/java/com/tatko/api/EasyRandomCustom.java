package com.tatko.api;

import com.tatko.api.entities.AdJpaEntity;
import org.jeasy.random.EasyRandom;

public class EasyRandomCustom extends EasyRandom {

    /**
     * Generate String object.
     *
     * @return Get generated random String object.
     */
    public String nextString() {
        return this.nextObject(String.class);
    }

    /**
     * Generate AdJpaEntity object.
     *
     * @return Get generated random AdJpaEntity object.
     */
    public AdJpaEntity nextAdJpaEntity() {
        AdJpaEntity adJpaEntity = this.nextObject(AdJpaEntity.class);
        adJpaEntity.setId(null);
        return adJpaEntity;
    }

}
