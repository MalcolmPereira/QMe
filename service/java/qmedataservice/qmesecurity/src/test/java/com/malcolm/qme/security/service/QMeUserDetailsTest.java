/**
 * Name      : com.malcolm.qme.security.config.QMeUserDetailsTest.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : QMe User Details Test
 */
package com.malcolm.qme.security.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QMeUserDetailsTest  {

    @Mock
    private GrantedAuthority authority;

    @Test
    public void testCreate() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        assertNotNull(qMeUserDetails);
    }

    @Test
    public void testCreateNoRoles() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password");
        assertNotNull(qMeUserDetails);
        assertNotNull(qMeUserDetails.getAuthorities());
    }

    @Test
    public void testCreateWithAuthority() throws Exception {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password",authorities);
        assertNotNull(qMeUserDetails);
        assertNotNull(qMeUserDetails.getAuthorities());
    }

    @Test
    public void testGetAuthorities() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        assertNotNull(qMeUserDetails);
        assertNotNull(qMeUserDetails.getAuthorities());
        assertThat(qMeUserDetails.getAuthorities().size(), equalTo(3));
        assertThat(qMeUserDetails.getAuthorities().size(), equalTo(3));
        for(GrantedAuthority role : qMeUserDetails.getAuthorities()){
            assertThat(role.getAuthority(), anyOf(
                    is("Role 1"),
                    is("Role 2"),
                    is("Role 3")
            ));
        }
    }

    @Test
    public void testGetPassword() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getPassword(), equalTo("Some Password"));
    }

    @Test
    public void testGetUsername() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUsername(), equalTo("Some User Name"));
    }

    @Test
    public void testIsAccountNonExpired() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.isAccountNonExpired(), equalTo(true));
    }

    @Test
    public void testIsAccountNonLocked() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.isAccountNonLocked(), equalTo(true));
    }

    @Test
    public void testIsCredentialsNonExpired() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.isCredentialsNonExpired(), equalTo(true));
    }

    @Test
    public void testIsEnabled() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.isEnabled(), equalTo(true));
    }

    @Test
    public void testGetUserID() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUserID(), equalTo(1L));
    }

    @Test
    public void testGetUserFirstName() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        qMeUserDetails.setUserFirstName("Some First Name");
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUserFirstName(), equalTo("Some First Name"));
    }

    @Test
    public void testSetUserFirstName() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        qMeUserDetails.setUserFirstName("Some First Name");
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUserFirstName(), equalTo("Some First Name"));
    }

    @Test
    public void testGetUserLastName() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        qMeUserDetails.setUserLastName("Some Last Name");
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUserLastName(), equalTo("Some Last Name"));
    }

    @Test
    public void testSetUserLastName() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        qMeUserDetails.setUserLastName("Some Last Name");
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUserLastName(), equalTo("Some Last Name"));
    }

    @Test
    public void testGetUserEmail() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        qMeUserDetails.setUserEmail("Some Email");
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUserEmail(), equalTo("Some Email"));
    }

    @Test
    public void testSetUserEmail() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        qMeUserDetails.setUserEmail("Some Email");
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUserEmail(), equalTo("Some Email"));
    }

    @Test
    public void testGetUserRegisteredDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        qMeUserDetails.setUserRegisteredDate(now);
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUserRegisteredDate(), equalTo(now));
    }

    @Test
    public void testSetUserRegisteredDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        qMeUserDetails.setUserRegisteredDate(now);
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUserRegisteredDate(), equalTo(now));
    }

    @Test
    public void testGetUserUpdateDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        qMeUserDetails.setUserUpdateDate(now);
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUserUpdateDate(), equalTo(now));
    }

    @Test
    public void testSetUserUpdateDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        qMeUserDetails.setUserUpdateDate(now);
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUserUpdateDate(), equalTo(now));
    }

    @Test
    public void testGetUserLastLoginDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        qMeUserDetails.setUserLastLoginDate(now);
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUserLastLoginDate(), equalTo(now));
    }

    @Test
    public void testSetUserLastLoginDate() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        qMeUserDetails.setUserLastLoginDate(now);
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUserLastLoginDate(), equalTo(now));
    }

    @Test
    public void testGetUpdateUserID() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        qMeUserDetails.setUpdateUserID(1L);
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUpdateUserID(), equalTo(1L));
    }

    @Test
    public void testSetUpdateUserID() throws Exception {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        qMeUserDetails.setUpdateUserID(1L);
        assertNotNull(qMeUserDetails);
        assertThat(qMeUserDetails.getUpdateUserID(), equalTo(1L));
    }

    @Test
    public void testGetQMeAuthenticatedUser() {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        assertNotNull(qMeUserDetails);
        assertNotNull(qMeUserDetails.getQMeAuthenticatedUser());
        assertNotNull(((Authentication)qMeUserDetails.getQMeAuthenticatedUser()).getCredentials());
        assertThat(((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).getCredentials(), equalTo("Some Password"));
        assertNotNull(((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).getDetails());
        assertNotNull(((User) ((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).getDetails()).getUsername());
        assertThat(((User) ((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).getDetails()).getUsername(), equalTo("Some User Name"));
        assertNotNull(((User) ((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).getDetails()).getPassword());
        assertThat(((User) ((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).getDetails()).getPassword(), equalTo("Some Password"));
        assertNotNull(((User) ((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).getDetails()).getAuthorities());
        assertThat(((User) ((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).getDetails()).getAuthorities().size(), equalTo(3));
        assertThat(((User) ((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).getDetails()).isAccountNonExpired(), equalTo(true));
        assertThat( ((User)((Authentication)qMeUserDetails.getQMeAuthenticatedUser()).getDetails()).isAccountNonLocked(), equalTo(true));
        assertThat( ((User)((Authentication)qMeUserDetails.getQMeAuthenticatedUser()).getDetails()).isCredentialsNonExpired(), equalTo(true));
        assertThat( ((User)((Authentication)qMeUserDetails.getQMeAuthenticatedUser()).getDetails()).isEnabled(), equalTo(true));
        assertNotNull(((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).getPrincipal());
        assertThat(((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).getPrincipal(), equalTo("Some User Name"));
        assertThat(((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).isAuthenticated(), equalTo(true));
        assertNotNull(((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).getName());
        assertThat(((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).getName(), equalTo("Some User Name"));
        ((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).setAuthenticated(false);
        assertThat(((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).isAuthenticated(), equalTo(false));
        assertNotNull(((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).getAuthorities());
        assertThat(((Authentication) qMeUserDetails.getQMeAuthenticatedUser()).getAuthorities().size(), equalTo(3));
    }
}