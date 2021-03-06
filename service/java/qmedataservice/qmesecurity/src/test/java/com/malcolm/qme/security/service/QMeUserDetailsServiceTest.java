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

    @Mock
    private UserRepository userRepo;

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private  QMeUserDetailsService qMeUserDetailsService;

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
        UserDetails userDetails = qMeUserDetailsService.loadUserByUsername("Some User Name");
        verify(userRepo).findByUserName("Some User Name");
        verify(userRoleRepository).findByUserId(1L);
        assertNotNull(userDetails);
        assertThat(userDetails.getUsername(), equalTo("Some User Name"));
        assertNotNull(userDetails.getAuthorities());
        assertThat(userDetails.getAuthorities().size(), equalTo(1));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameNotFound() throws QMeException {
        when(userRepo.findByUserName("Some User Name")).thenReturn(null);
        qMeUserDetailsService.loadUserByUsername("Some User Name");
        verify(userRepo).findByUserName("Some User Name");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameRoleNotFound() throws QMeException {
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        when(userRepo.findByUserName("Some User Name")).thenReturn(user);
        when(userRoleRepository.findByUserId(1L)).thenReturn(null);
        qMeUserDetailsService.loadUserByUsername("Some User Name");
        verify(userRepo).findByUserName("Some User Name");
        verify(userRoleRepository).findByUserId(1L);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUsernameEmptyRole() throws QMeException {
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        when(userRepo.findByUserName("Some User Name")).thenReturn(user);
        when(userRoleRepository.findByUserId(1L)).thenReturn(new ArrayList<>());
        qMeUserDetailsService.loadUserByUsername("Some User Name");
        verify(userRepo).findByUserName("Some User Name");
        verify(userRoleRepository).findByUserId(1L);
    }

    @Test
    public void testLoadUserByUserEmail() throws QMeException {
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        List<UserRole> userRoles   = new ArrayList<>();
        UserRole userRole = new UserRole(1L, 1, "Some Role", 1L);
        userRoles.add(userRole);
        when(userRepo.findByUserEmail("Email@Email")).thenReturn(user);
        when(userRoleRepository.findByUserId(1L)).thenReturn(userRoles);
        UserDetails userDetails = qMeUserDetailsService.loadUserByUsername("Email@Email");
        verify(userRepo).findByUserEmail("Email@Email");
        verify(userRoleRepository).findByUserId(1L);
        assertNotNull(userDetails);
        assertThat(userDetails.getUsername(), equalTo("Some User Name"));
        assertNotNull(userDetails.getAuthorities());
        assertThat(userDetails.getAuthorities().size(), equalTo(1));
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUserEmailNotFound() throws QMeException {
        when(userRepo.findByUserEmail("Email@Email")).thenReturn(null);
        qMeUserDetailsService.loadUserByUsername("Email@Email");
        verify(userRepo).findByUserEmail("Email@Email");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByUserEmailQMeException() throws QMeException {
        when(userRepo.findByUserEmail("Email@Email")).thenThrow(QMeException.class);
        qMeUserDetailsService.loadUserByUsername("Email@Email");
        verify(userRepo).findByUserEmail("Email@Email");
    }

    @Test
    public void testUpdateUserLastLoginDate() throws QMeException {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        when(userRepo.updateLoginDate(1L)).thenReturn(user);
        qMeUserDetailsService.updateUserLastLoginDate(qMeUserDetails);
        verify(userRepo).updateLoginDate(1L);
    }

    @Test
    public void testUpdateUserLastLoginDateException() throws QMeException {
        QMeUserDetails qMeUserDetails = (QMeUserDetails) QMeUserDetails.create(1L, "Some User Name", "Some Password", "Role 1", "Role 2", "Role 3");
        User user = new User(1L, "Some User Name", "Some Password", "First Name", "Last Name", "Email", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now(), LocalDateTime.now(), 1L);
        when(userRepo.updateLoginDate(1L)).thenThrow(QMeException.class);
        qMeUserDetailsService.updateUserLastLoginDate(qMeUserDetails);
        verify(userRepo).updateLoginDate(1L);
    }
}