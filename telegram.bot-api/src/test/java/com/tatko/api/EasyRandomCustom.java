package com.tatko.api;

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

}
