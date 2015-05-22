/**
 * Name      : com.malcolm.qme.rest.RestConfig.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : Configuration for REST MVC
 */

package com.malcolm.qme.rest;

import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.text.SimpleDateFormat;

/**
 * @author malcolm
 */
@Configuration
@ComponentScan({"com.malcolm.qme.rest"})
public class RestConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true);
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return builder;
    }
}
