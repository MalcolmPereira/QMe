/**
 * Name      : com.malcolm.qme.springdata.entity.LocalDateTimeConverter.java
 * Date      : 5/23/15
 * Developer : Malcolm
 * Purpose   : LocalDateTime Convertor
 */

package com.malcolm.qme.springdata.entity;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author Malcolm
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime,Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDateTime dateTime) {
        if(dateTime != null){
            return Timestamp.valueOf(dateTime);
        }
        return null;
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Timestamp timestamp) {
        if(timestamp != null){
            return timestamp.toLocalDateTime();
        }
        return null;
    }
}
