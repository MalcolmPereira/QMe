/**
 * Name      : com.malcolm.qme.security.config.QMeUserDetailsServiceTest.java
 * Date      : 9/23/15
 * Developer : Malcolm
 * Purpose   : QMe User Details Service Test
 */
package com.malcolm.qme.security.service;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserRole;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.core.repository.UserRoleRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QMeUserDetailsServiceTest {

    @InjectMocks
    private  QMeUserDetailsService qMeUserDetailsService;

    @Mock
    private UserRepository userRepo;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Before
    public void initMocks(){
        qMeUserDetailsService = new QMeUserDetailsService();
        MockitoAnnotations.initMocks(this);
    }


    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameNull() {
        qMeUserDetailsService.loadUserByUsername(null);
    }

    @Test
    public void testLoadUserByUsername() throws QMeException {
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        List<UserRole> userRoles   = new ArrayList<>();
        UserRole userRole = new UserRole(1L, 1, "Some Role", 1L);
        userRoles.add(userRole);
        when(userRepo.findByUserName("Some User Name")).thenReturn(user);
        when(userRoleRepository.findByUserId(1L)).thenReturn(userRoles);
        when(userRepo.updateLoginDate(1L)).thenReturn(user);
        UserDetails userDetails = qMeUserDetailsService.loadUserByUsername("Some User Name");
        verify(userRepo).findByUserName("Some User Name");
        verify(userRoleRepository).findByUserId(1L);
        verify(userRepo).updateLoginDate(1L);
        assertNotNull(userDetails);
        assertThat(userDetails.getUsername(), equalTo("Some User Name"));
        assertNotNull(userDetails.getAuthorities());
        assertThat(userDetails.getAuthorities().size(), equalTo(1));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameNotFound() throws QMeException {
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        when(userRepo.findByUserName("Some User Name")).thenReturn(null);
        UserDetails userDetails = qMeUserDetailsService.loadUserByUsername("Some User Name");
        verify(userRepo).findByUserName("Some User Name");
    }

    @Test
    public void testLoadUserByUserEmail() throws QMeException {
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        List<UserRole> userRoles   = new ArrayList<>();
        UserRole userRole = new UserRole(1L, 1, "Some Role", 1L);
        userRoles.add(userRole);

        when(userRepo.findByUserEmail("Email@Email")).thenReturn(user);
        when(userRoleRepository.findByUserId(1L)).thenReturn(userRoles);
        when(userRepo.updateLoginDate(1L)).thenReturn(user);
        UserDetails userDetails = qMeUserDetailsService.loadUserByUsername("Email@Email");
        verify(userRepo).findByUserEmail("Email@Email");
        verify(userRoleRepository).findByUserId(1L);
        verify(userRepo).updateLoginDate(1L);
        assertNotNull(userDetails);
        assertThat(userDetails.getUsername(), equalTo("Some User Name"));
        assertNotNull(userDetails.getAuthorities());
        assertThat(userDetails.getAuthorities().size(), equalTo(1));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUserEmailNotFound() throws QMeException {
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        when(userRepo.findByUserEmail("Email@Email")).thenReturn(null);
        UserDetails userDetails = qMeUserDetailsService.loadUserByUsername("Email@Email");
        verify(userRepo).findByUserEmail("Email@Email");
    }

}