package com.tatko.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
/**
 * Application configuration.
 */

@Configuration
@PropertySource("classpath:application.yaml")
public class AppConfig {
}
