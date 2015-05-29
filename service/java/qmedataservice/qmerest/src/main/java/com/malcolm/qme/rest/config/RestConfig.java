/**
 * Name      : com.malcolm.qme.rest.config.RestConfig.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : Configuration for REST MVC
 */

package com.malcolm.qme.rest.config;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.malcolm.qme.rest.api.AtomicTokenGenerator;
import com.malcolm.qme.security.config.QMeSecurityConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author malcolm
 */
@Configuration
@Import(QMeSecurityConfig.class)
@ComponentScan({"com.malcolm.qme.rest"})
public class RestConfig {

    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true);
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        builder.serializers(new LocalDateTimeSerializer());
        return builder;
    }

    @Bean
    public AtomicTokenGenerator atomicTokenGenerator() {

        return new AtomicTokenGenerator(){

            private final AtomicLong LAST_TIME_MS = new AtomicLong();

            @Override
            public Long generateUniqueResetToken() {

                Long now = System.currentTimeMillis();

                while(true) {

                    Long lastTime = LAST_TIME_MS.get();

                    if (lastTime >= now)
                        now = lastTime+1;

                    if (LAST_TIME_MS.compareAndSet(lastTime, now))
                        return now;
                }
            }
        };
    }

    /**
     * JSON LocalDateTime Serializer
     */
    private final class LocalDateTimeSerializer extends StdScalarSerializer<LocalDateTime> {
        public LocalDateTimeSerializer() {
            super(LocalDateTime.class);
        }
        @Override
        public void serialize(LocalDateTime dateTime,
                              JsonGenerator jsonGenerator,
                              SerializerProvider provider) throws IOException, JsonGenerationException {
            jsonGenerator.writeString( dateTime.format(DateTimeFormatter.ofPattern("YYYY-dd-MM HH:mm:ss")));
        }
    }

}
