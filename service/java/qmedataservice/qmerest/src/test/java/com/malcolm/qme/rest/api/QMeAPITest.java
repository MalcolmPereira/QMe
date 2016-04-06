/**
 * Name      : com.malcolm.qme.rest.config.QMeMailSenderTest.java
 * Date      : 4/5/16
 * Developer : Malcolm
 * Purpose   : Test Cases for QMe API
 */
package com.malcolm.qme.rest.api;

import com.malcolm.qme.security.service.QMeUserDetails;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.context.SecurityContextHolder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;


/**
 * @author malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QMeAPITest implements QMeAPI{

    @Before
    public void setContext(){
        SecurityContextHolder.getContext().setAuthentication((QMeUserDetails)QMeUserDetails.create(1L, "admin", "password", "USER","ADMIN"));
    }

    @Test
    public void testGetCurrentUser() throws Exception {
        assertThat(getCurrentUser(), notNullValue());
        SecurityContextHolder.getContext().setAuthentication(null);
        assertThat(getCurrentUser(), nullValue());

    }

    @Test
    public void testLog() throws Exception {
        log(null,"some message");
        log(getCurrentUser(),"some message");
    }

    @Test
    public void testGetPageNumber() throws Exception {
        assertThat(getPageNumber("1"),equalTo(1));
        assertThat(getPageNumber("A"), nullValue());
    }

    @Test
    public void testGetPageSizeNumber() throws Exception {
        assertThat(getPageSizeNumber("1"),equalTo(1));
        assertThat(getPageSizeNumber("A"), nullValue());
    }

    @Test
    public void testGetSortAsc() throws Exception {
        assertThat(getSortAsc("true"),equalTo(true));
        assertThat(getSortAsc("A"), equalTo(false));
        assertThat(getSortAsc(null), equalTo(true));
    }

    @Test
    public void testGetSortOrderFields() throws Exception {
        assertThat(getSortOrderFields("1,2,3,4").length,equalTo(4));
        assertThat(getSortOrderFields("A").length, equalTo(1));
        assertThat(getSortOrderFields("").length, equalTo(0));
    }
}