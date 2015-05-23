/**
 * Name      : com.malcolm.qme.rest.config.RestConfig.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : Configuration for REST MVC
 */

package com.malcolm.qme.rest.config;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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
        builder.serializers(new LocalDateTimeSerializer());
        return builder;
    }

    /**
     * JSON LocalDateTime Serializer
     */
    public final class LocalDateTimeSerializer extends StdScalarSerializer<LocalDateTime> {
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
