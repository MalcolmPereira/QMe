/**
 * Name      : com.malcolm.qme.springdata.repository.DateTimeUtil.java
 * Date      : 5/21/15
 * Developer : Malcolm
 * Purpose   : Simple Date Time Util Class
 */

package com.malcolm.qme.springdata.repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author malcolm
 */
public class DateTimeUtil {

    /**
     * Convert from LocalDateTime to Date
     *
     * @param date LocalDateTime to convert
     * @return Date
     */
    public static Date convertToDate(LocalDateTime date) {
        Instant instant = date.atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    /**
     *
     * @param date Date to convert
     * @return LocalDateTime
     */
    public static LocalDateTime convertToLocalDateTime(Date date) {
        Instant instant = date.toInstant();
        return LocalDateTime.from(instant.atZone(ZoneId.systemDefault()));
    }
}
