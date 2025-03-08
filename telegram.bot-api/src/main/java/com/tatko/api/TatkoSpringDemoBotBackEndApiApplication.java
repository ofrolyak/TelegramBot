package com.tatko.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TatkoSpringDemoBotBackEndApiApplication {

    /**
     * The main method serves as the entry point
     * for the Spring Boot application.
     * It starts the application by running
     * the TatkoSpringDemoBotBackEndApiApplication class.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(final String[] args) {
        SpringApplication.run(
                TatkoSpringDemoBotBackEndApiApplication.class,
                args);
    }

    /**
     * DUMMY for HideUtilityClassConstructor by CheckStyle.
     */
    @SuppressWarnings("unused")
    public final void foo() {
        throw new UnsupportedOperationException();
    }
}
