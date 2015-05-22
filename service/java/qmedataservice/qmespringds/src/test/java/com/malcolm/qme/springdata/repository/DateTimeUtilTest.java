/**
 * Name      : com.malcolm.qme.springdata.repository.DateTimeUtilTest.java
 * Date      : 5/21/15
 * Developer : Malcolm
 * Purpose   : Test Cases for Date Time Util
 */

package com.malcolm.qme.springdata.repository;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

/**
 * @Author: malcolm
 */
public class DateTimeUtilTest {
    @Test
    public void testConvertToLocalDateTime(){
        assertNotNull(DateTimeUtil.convertToLocalDateTime(new Date()));
    }

    @Test
    public void testConvertToDate(){
        assertNotNull(DateTimeUtil.convertToDate(LocalDateTime.now()));
    }
}
