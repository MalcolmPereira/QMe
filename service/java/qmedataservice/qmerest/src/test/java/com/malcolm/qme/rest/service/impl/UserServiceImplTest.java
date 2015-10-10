/**
 * Name      : com.malcolm.qme.rest.service.impl.UserServiceImplTest.java
 * Date      : 5/27/2015
 * Developer : Malcolm
 * Purpose   : Test Cases for User Service Implementation
 **/
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserRole;
import com.malcolm.qme.core.domain.fixtures.UserFixtures;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.core.repository.UserRoleRepository;
import com.malcolm.qme.rest.api.AtomicTokenGenerator;
import com.malcolm.qme.rest.exception.*;
import com.malcolm.qme.rest.model.*;
import com.malcolm.qme.rest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    private UserRoleRepository userRoleRepo;

    @Mock
    public PasswordEncoder passwordEncoder;

    @Mock
    public AtomicTokenGenerator atomicTokenGenerator;

    @Mock
    public JavaMailSenderImpl javaMailSender;

    @InjectMocks
    private final UserService userService = new UserServiceImpl();

    @Test
    public void testList() throws QMeResourceException, QMeException{
        when(userRepo.findAll()).thenReturn(UserFixtures.simpleUserList());

        List<QMeUserDetail> userList = userService.list();

        verify(userRepo).findAll();

        assertNotNull(userList);
        assertThat(userList.size(), equalTo(5));

        for (QMeUserDetail qmeUserDetail : userList) {
            assertThat(qmeUserDetail.getUserId(), anyOf(
                            is(1L),
                            is(2L),
                            is(3L),
                            is(4L),
                            is(5L))
            );
            assertThat(qmeUserDetail.getUserName(), anyOf(
                    is("suser1"),
                    is("suser2"),
                    is("suser3"),
                    is("suser4"),
                    is("suser5")
            ));
        }
    }

    @Test
    public void testListNullReturn() throws QMeResourceException, QMeException{
        when(userRepo.findAll()).thenReturn(null);
        List<QMeUserDetail> userList = userService.list();
        verify(userRepo).findAll();
        assertNotNull(userList);
        assertThat(userList.size(), equalTo(0));
    }

    @Test(expected = QMeServerException.class)
    public void testListQMeException() throws QMeResourceException, QMeException{
        when(userRepo.findAll()).thenThrow(QMeException.class);
        userService.list();
        verify(userRepo).findAll();
    }

    @Test
    public void testSearchById() throws QMeResourceException, QMeException {

        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        when(userRoleRepo.findByUserId(1L)).thenReturn(UserFixtures.simpleUserRoleList());

        QMeUserDetail userDetail = userService.searchById(1L);

        verify(userRepo).findById(1L);
        verify(userRoleRepo).findByUserId(1L);

        assertNotNull(userDetail);

        assertThat(userDetail.getUserId(), equalTo(1L));
        assertThat(userDetail.getUserName(), equalTo("suser1"));
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testSearchByIdNotFound() throws QMeResourceException, QMeException {
        when(userRepo.findById(1L)).thenReturn(null);
        userService.searchById(1L);
        verify(userRepo).findById(1L);
    }

    @Test(expected = QMeServerException.class)
    public void testSearchByIdQMeException() throws QMeResourceException, QMeException {
        when(userRepo.findById(1L)).thenThrow(QMeException.class);
        userService.searchById(1L);
        verify(userRepo).findById(1L);
    }

    @Test
    public void testSearchByUser() throws QMeResourceException, QMeException {

        when(userRepo.findByUserName("suser1")).thenReturn(UserFixtures.simpleUser());
        when(userRoleRepo.findByUserId(1L)).thenReturn(UserFixtures.simpleUserRoleList());

        QMeUserDetail userDetail = userService.searchByUser("suser1");

        verify(userRepo).findByUserName("suser1");
        verify(userRoleRepo).findByUserId(1L);

        assertNotNull(userDetail);

        assertThat(userDetail.getUserId(), equalTo(1L));
        assertThat(userDetail.getUserName(), equalTo("suser1"));
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testSearchByUserNotFound() throws QMeResourceException, QMeException {
        when(userRepo.findByUserName("suser1")).thenReturn(null);
        userService.searchByUser("suser1");
        verify(userRepo).findByUserName("suser1");
    }

    @Test(expected = QMeServerException.class)
    public void testSearchByUserQMeException() throws QMeResourceException, QMeException {
        when(userRepo.findByUserName("suser1")).thenThrow(QMeException.class);
        userService.searchByUser("suser1");
        verify(userRepo).findByUserName("suser1");
    }

    @Test
    public void testSearchByEmail() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenReturn(UserFixtures.simpleUser());
        when(userRoleRepo.findByUserId(1L)).thenReturn(UserFixtures.simpleUserRoleList());

        QMeUserDetail userDetail = userService.searchByEmail("SimpleUser1@User.com");

        verify(userRepo).findByUserEmail("SimpleUser1@User.com");
        verify(userRoleRepo).findByUserId(1L);


        assertNotNull(userDetail);

        assertThat(userDetail.getUserId(), equalTo(1L));
        assertThat(userDetail.getUserName(), equalTo("suser1"));
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testSearchByEmailNotFound() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenReturn(null);
        userService.searchByEmail("SimpleUser1@User.com");
        verify(userRepo).findByUserEmail("SimpleUser1@User.com");
    }

    @Test(expected = QMeServerException.class)
    public void testSearchByEmailQMEException() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenThrow(QMeException.class);
        userService.searchByEmail("SimpleUser1@User.com");
        verify(userRepo).findByUserEmail("SimpleUser1@User.com");
    }

    @Test
    public void testSave() throws QMeResourceException, QMeException {
        when(userRepo.findByUserName("suser6")).thenReturn(null);
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.save(Matchers.<User>anyObject())).thenReturn(UserFixtures.simpleUser());
        when(passwordEncoder.encode(Matchers.<String>anyObject())).thenReturn("someencodedvalue");
        when(userRoleRepo.save(Matchers.<UserRole>anyObject())).thenReturn(null);

        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");

        QMeUserDetail userDetail = userService.save(qmeUser, 1L);

        verify(userRepo).findByUserName("suser6");
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
        verify(userRepo).save(Matchers.<User>anyObject());
        verify(passwordEncoder).encode(Matchers.<String>anyObject());
        verify(userRoleRepo).save(Matchers.<UserRole>anyObject());


        assertNotNull(userDetail);

        assertThat(userDetail.getUserId(), equalTo(1L));
        assertThat(userDetail.getUserName(), equalTo("suser1"));
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidUserName() throws QMeResourceException, QMeException {
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName(null);
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        userService.save(qmeUser, 1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidUserPassword() throws QMeResourceException, QMeException {
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword(null);
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        userService.save(qmeUser, 1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidUserEmail() throws QMeResourceException, QMeException {
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserEmail(null);
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        userService.save(qmeUser, 1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidFirstName() throws QMeResourceException, QMeException {
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setUserFirstName(null);
        qmeUser.setUserLastName("Simple User 6");
        userService.save(qmeUser, 1L);
    }

    @Test(expected = QMeResourceConflictException.class)
    public void testSaveConflictUserName() throws QMeResourceException, QMeException {
        when(userRepo.findByUserName("suser6")).thenReturn(UserFixtures.simpleUser());
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        userService.save(qmeUser, 1L);
        verify(userRepo).findByUserName("suser6");
    }

    @Test(expected = QMeResourceConflictException.class)
    public void testSaveConflictEmail() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenReturn(UserFixtures.simpleUser());
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        userService.save(qmeUser, 1L);
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
    }

    @Test(expected = QMeServerException.class)
    public void testSaveGetUserQMeExceptionEmail() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenThrow(QMeException.class);
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        userService.save(qmeUser, 1L);
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
    }


    @Test(expected = QMeServerException.class)
    public void testSaveQMeException() throws QMeResourceException, QMeException {
        when(userRepo.findByUserName("suser6")).thenReturn(null);
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.save(Matchers.<User>anyObject())).thenThrow(QMeException.class);
        when(passwordEncoder.encode(Matchers.<String>anyObject())).thenReturn("someencodedvalue");

        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");

        userService.save(qmeUser, 1L);

        verify(userRepo).findByUserName("suser6");
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
        verify(userRepo).save(Matchers.<User>anyObject());
        verify(passwordEncoder).encode(Matchers.<String>anyObject());
    }

    @Test
    public void testStageUser() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserName("suser6")).thenReturn(null);
        when(userRepo.findStagedUserByUserName("suser6")).thenReturn(null);
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.findStagedUserByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.stageUserRegistration(Matchers.<User>anyObject())).thenReturn("sometoken");
        when(passwordEncoder.encode(Matchers.<String>anyObject())).thenReturn("someencodedvalue");
        when(javaMailSender.getUsername()).thenReturn("someusername");
        when(javaMailSender.getPassword()).thenReturn("somepassword");
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        doNothing().when(javaMailSender).send(Matchers.<MimeMessage>anyObject());

        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL("some url");
        userService.stageUser(qmeUser);


        verify(userRepo).findByUserName("suser6");
        verify(userRepo).findStagedUserByUserName("suser6");
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
        verify(userRepo).findStagedUserByUserEmail("SimpleUser6@User.com");
        verify(userRepo).stageUserRegistration(Matchers.<User>anyObject());
        verify(passwordEncoder).encode(Matchers.<String>anyObject());
        verify(javaMailSender,atLeastOnce()).getUsername();
        verify(javaMailSender,atLeastOnce()).getPassword();
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(Matchers.<MimeMessage>anyObject());
    }

    @Test
    public void testStageUserUrlWithSlash() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserName("suser6")).thenReturn(null);
        when(userRepo.findStagedUserByUserName("suser6")).thenReturn(null);
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.findStagedUserByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.stageUserRegistration(Matchers.<User>anyObject())).thenReturn("sometoken");
        when(passwordEncoder.encode(Matchers.<String>anyObject())).thenReturn("someencodedvalue");
        when(javaMailSender.getUsername()).thenReturn("someusername");
        when(javaMailSender.getPassword()).thenReturn("somepassword");
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        doNothing().when(javaMailSender).send(Matchers.<MimeMessage>anyObject());

        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL("some.url/");
        userService.stageUser(qmeUser);

        verify(userRepo).findByUserName("suser6");
        verify(userRepo).findStagedUserByUserName("suser6");
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
        verify(userRepo).findStagedUserByUserEmail("SimpleUser6@User.com");
        verify(userRepo).stageUserRegistration(Matchers.<User>anyObject());
        verify(passwordEncoder).encode(Matchers.<String>anyObject());
        verify(javaMailSender,atLeastOnce()).getUsername();
        verify(javaMailSender,atLeastOnce()).getPassword();
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(Matchers.<MimeMessage>anyObject());
    }

    @Test(expected = QMeServerException.class)
    public void testStageUserEmailInvalidCredentialsError() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserName("suser6")).thenReturn(null);
        when(userRepo.findStagedUserByUserName("suser6")).thenReturn(null);
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.findStagedUserByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.stageUserRegistration(Matchers.<User>anyObject())).thenReturn("sometoken");
        when(passwordEncoder.encode(Matchers.<String>anyObject())).thenReturn("someencodedvalue");
        when(javaMailSender.getUsername()).thenReturn(null);
        doNothing().when(userRepo).deleteStagingToken("sometoken");

        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL("some url");
        userService.stageUser(qmeUser);

        verify(userRepo).findByUserName("suser6");
        verify(userRepo).findStagedUserByUserName("suser6");
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
        verify(userRepo).findStagedUserByUserEmail("SimpleUser6@User.com");
        verify(userRepo).stageUserRegistration(Matchers.<User>anyObject());
        verify(passwordEncoder).encode(Matchers.<String>anyObject());
        verify(javaMailSender,atLeastOnce()).getUsername();
        verify(userRepo).deleteStagingToken("sometoken");
    }

    @Test(expected = QMeServerException.class)
    public void testStageUserEmailMessagingError() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserName("suser6")).thenReturn(null);
        when(userRepo.findStagedUserByUserName("suser6")).thenReturn(null);
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.findStagedUserByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.stageUserRegistration(Matchers.<User>anyObject())).thenReturn("sometoken");
        when(passwordEncoder.encode(Matchers.<String>anyObject())).thenReturn("someencodedvalue");
        when(javaMailSender.getUsername()).thenReturn("someusername");
        when(javaMailSender.getPassword()).thenReturn("somepassword");
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        doThrow(MessagingException.class).when(javaMailSender).send(Matchers.<MimeMessage>anyObject());
        doNothing().when(userRepo).deleteStagingToken("sometoken");

        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL("some url");

        userService.stageUser(qmeUser);

        verify(userRepo).findByUserName("suser6");
        verify(userRepo).findStagedUserByUserName("suser6");
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
        verify(userRepo).findStagedUserByUserEmail("SimpleUser6@User.com");
        verify(userRepo).stageUserRegistration(Matchers.<User>anyObject());
        verify(passwordEncoder).encode(Matchers.<String>anyObject());
        verify(javaMailSender,atLeastOnce()).getUsername();
        verify(javaMailSender,atLeastOnce()).getPassword();
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(Matchers.<MimeMessage>anyObject());
        verify(userRepo).deleteStagingToken("sometoken");
    }

    @Test(expected = QMeServerException.class)
    public void testStageUserEmailError() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserName("suser6")).thenReturn(null);
        when(userRepo.findStagedUserByUserName("suser6")).thenReturn(null);
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.findStagedUserByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.stageUserRegistration(Matchers.<User>anyObject())).thenReturn("sometoken");
        when(passwordEncoder.encode(Matchers.<String>anyObject())).thenReturn("someencodedvalue");
        when(javaMailSender.getUsername()).thenReturn("someusername");
        when(javaMailSender.getPassword()).thenReturn("somepassword");
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));
        doThrow(Exception.class).when(javaMailSender).send(Matchers.<MimeMessage>anyObject());
        doNothing().when(userRepo).deleteStagingToken("sometoken");

        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL("some url");

        userService.stageUser(qmeUser);

        verify(userRepo).findByUserName("suser6");
        verify(userRepo).findStagedUserByUserName("suser6");
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
        verify(userRepo).findStagedUserByUserEmail("SimpleUser6@User.com");
        verify(userRepo).stageUserRegistration(Matchers.<User>anyObject());
        verify(passwordEncoder).encode(Matchers.<String>anyObject());
        verify(javaMailSender,atLeastOnce()).getUsername();
        verify(javaMailSender,atLeastOnce()).getPassword();
        verify(javaMailSender).createMimeMessage();
        verify(javaMailSender).send(Matchers.<MimeMessage>anyObject());
        verify(userRepo).deleteStagingToken("sometoken");
    }

    @Test
    public void testStageUserNoSuccess() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserName("suser6")).thenReturn(null);
        when(userRepo.findStagedUserByUserName("suser6")).thenReturn(null);
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.findStagedUserByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.stageUserRegistration(Matchers.<User>anyObject())).thenReturn(null);
        when(passwordEncoder.encode(Matchers.<String>anyObject())).thenReturn("someencodedvalue");

        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL("some url");
        userService.stageUser(qmeUser);

        verify(userRepo).findByUserName("suser6");
        verify(userRepo).findStagedUserByUserName("suser6");
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
        verify(userRepo).findStagedUserByUserEmail("SimpleUser6@User.com");
        verify(userRepo).stageUserRegistration(Matchers.<User>anyObject());
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testStageUserInvalidUserName() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException{
        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName(null);
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL("some url");
        userService.stageUser(qmeUser);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testStageUserInvalidUserPassword() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException{
        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword(null);
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL("some url");
        userService.stageUser(qmeUser);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testStageUserInvalidUserEmail() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException{
        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail(null);
        qmeUser.setConfirmURL("some url");
        userService.stageUser(qmeUser);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testStageUserInvalidFirstName() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException{
        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName(null);
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL("some url");
        userService.stageUser(qmeUser);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testStageUserInvalidConfirmURL() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException{
        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL(null);
        userService.stageUser(qmeUser);
    }


    @Test(expected = QMeResourceConflictException.class)
    public void testStageUserConflictUserName() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserName("suser6")).thenReturn(UserFixtures.simpleUser());
        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL("some url");
        userService.stageUser(qmeUser);
        verify(userRepo).findByUserName("suser6");
    }

    @Test(expected = QMeResourceConflictException.class)
    public void testStageUserConflictUserNameStage() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserName("suser6")).thenReturn(null);
        when(userRepo.findStagedUserByUserName("suser6")).thenReturn(UserFixtures.simpleUser());
        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL("some url");
        userService.stageUser(qmeUser);
        verify(userRepo).findByUserName("suser6");
        verify(userRepo).findStagedUserByUserName("suser6");
    }

    @Test(expected = QMeServerException.class)
    public void testStageGetUserQMeException() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenThrow(QMeException.class);
        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL("some url");
        userService.stageUser(qmeUser);
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
    }

    @Test(expected = QMeResourceConflictException.class)
    public void testStageUserConflictUserEmail() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenReturn(UserFixtures.simpleUser());
        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL("some url");
        userService.stageUser(qmeUser);
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
    }

    @Test(expected = QMeResourceConflictException.class)
    public void testStageUserConflictUserEmailStage() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.findStagedUserByUserEmail("SimpleUser6@User.com")).thenReturn(UserFixtures.simpleUser());
        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL("some url");
        userService.stageUser(qmeUser);
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
        verify(userRepo).findStagedUserByUserEmail("SimpleUser6@User.com");
    }

    @Test(expected = QMeServerException.class)
    public void testStageUserQMeException() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserName("suser6")).thenReturn(null);
        when(userRepo.findStagedUserByUserName("suser6")).thenReturn(null);
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.findStagedUserByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.stageUserRegistration(Matchers.<User>anyObject())).thenThrow(QMeException.class);
        when(passwordEncoder.encode(Matchers.<String>anyObject())).thenReturn("someencodedvalue");

        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        qmeUser.setConfirmURL("some url");
        userService.stageUser(qmeUser);

        verify(userRepo).findByUserName("suser6");
        verify(userRepo).findStagedUserByUserName("suser6");
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
        verify(userRepo).findStagedUserByUserEmail("SimpleUser6@User.com");
        verify(userRepo).stageUserRegistration(Matchers.<User>anyObject());
        verify(passwordEncoder).encode(Matchers.<String>anyObject());
    }

    @Test
    public void testConfirmRegistration() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException, QMeResourceNotFoundException {
        when(userRepo.confirmUserRegistration("some token")).thenReturn(UserFixtures.simpleUser());
        when(userRoleRepo.save(Matchers.<UserRole>anyObject())).thenReturn(null);
        userService.confirmUserRegistration("some token");
        verify(userRepo).confirmUserRegistration("some token");
        verify(userRoleRepo).save(Matchers.<UserRole>anyObject());
    }

    @Test(expected = QMeServerException.class)
    public void testConfirmRegistrationQMeException() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException, QMeResourceNotFoundException {
        when(userRepo.confirmUserRegistration("some token")).thenThrow(QMeException.class);
        userService.confirmUserRegistration("some token");
        verify(userRepo).confirmUserRegistration("some token");
    }

    @Test
    public void testUpdate() throws QMeResourceException, QMeException {
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        when(userRepo.update(Matchers.<User>anyObject(), eq(1L))).thenReturn(UserFixtures.simpleUser());

        QMeUpdateUser qmeUser = new QMeUpdateUser();
        qmeUser.setUserName("suser1");
        qmeUser.setUserPassword("spassword1");
        qmeUser.setUserFirstName("Simple 1");
        qmeUser.setUserLastName("Simple User 1");
        qmeUser.setUserEmail("SimpleUser1@User.com");

        QMeUserDetail userDetail = userService.update(qmeUser, 1L, 1L);

        verify(userRepo).findById(1L);
        verify(userRepo).update(Matchers.<User>anyObject(), eq(1L));

        assertNotNull(userDetail);

        assertThat(userDetail.getUserId(), equalTo(1L));
        assertThat(userDetail.getUserName(), equalTo("suser1"));
    }

    @Test
    public void testUpdateUpdatePassword() throws QMeResourceException, QMeException {
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUserWithTestPassword());
        when(userRepo.update(Matchers.<User>anyObject(), eq(1L))).thenReturn(UserFixtures.simpleUser());
        when(passwordEncoder.matches(eq("testtestpassword"), Matchers.<String>anyObject())).thenReturn(true);

        QMeUpdateUser qmeUser = new QMeUpdateUser();
        qmeUser.setUserName("suser1");
        qmeUser.setUserPassword("testtestpassword");
        qmeUser.setUpdatedUserPassword("newpassword");
        qmeUser.setUserFirstName("Simple 1");
        qmeUser.setUserLastName("Simple User 1");
        qmeUser.setUserEmail("SimpleUser1@User.com");

        QMeUserDetail userDetail = userService.update(qmeUser, 1L, 1L);

        verify(userRepo).findById(1L);
        verify(userRepo).update(Matchers.<User>anyObject(), eq(1L));
        verify(passwordEncoder).matches(eq("testtestpassword"), Matchers.<String>anyObject());

        assertNotNull(userDetail);

        assertThat(userDetail.getUserId(), equalTo(1L));
        assertThat(userDetail.getUserName(), equalTo("suser1"));
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testUpdateUpdatePasswordInvalidCurrentPassword() throws QMeResourceException, QMeException {
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUserWithTestPassword());
        when(userRepo.update(Matchers.<User>anyObject(), eq(1L))).thenReturn(UserFixtures.simpleUser());
        when(passwordEncoder.matches(eq("testtestpassword"),Matchers.<String>anyObject())).thenReturn(true);

        QMeUpdateUser qmeUser = new QMeUpdateUser();
        qmeUser.setUserName("suser1");
        qmeUser.setUserPassword(null);
        qmeUser.setUpdatedUserPassword("newpassword");
        qmeUser.setUserFirstName("Simple 1");
        qmeUser.setUserLastName("Simple User 1");
        qmeUser.setUserEmail("SimpleUser1@User.com");

        userService.update(qmeUser, 1L, 1L);

        verify(userRepo).findById(1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testUpdateUpdatePasswordWrongCurrentPassword() throws QMeResourceException, QMeException {
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUserWithTestPassword());
        when(userRepo.update(Matchers.<User>anyObject(), eq(1L))).thenReturn(UserFixtures.simpleUser());
        when(passwordEncoder.matches(eq("testtestpassword"),Matchers.<String>anyObject())).thenReturn(false);

        QMeUpdateUser qmeUser = new QMeUpdateUser();
        qmeUser.setUserName("suser1");
        qmeUser.setUserPassword(null);
        qmeUser.setUpdatedUserPassword("newpassword");
        qmeUser.setUserFirstName("Simple 1");
        qmeUser.setUserLastName("Simple User 1");
        qmeUser.setUserEmail("SimpleUser1@User.com");

        userService.update(qmeUser, 1L, 1L);

        verify(userRepo).findById(1L);
        verify(passwordEncoder).matches(eq("testtestpassword"), Matchers.<String>anyObject());

    }


    @Test(expected = QMeResourceNotFoundException.class)
    public void testUpdateNullUserQMeException() throws QMeResourceException, QMeException {
        when(userRepo.findById(1L)).thenReturn(null);
        QMeUpdateUser qmeUser = new QMeUpdateUser();
        qmeUser.setUserName("suser1");
        qmeUser.setUserPassword("spassword1");
        qmeUser.setUserFirstName("Simple 1");
        qmeUser.setUserLastName("Simple User 1");
        qmeUser.setUserEmail("SimpleUser1@User.com");
        userService.update(qmeUser, 1L, 1L);
        verify(userRepo).findById(1L);
    }

    @Test(expected = QMeServerException.class)
    public void testUpdateFindUserQMeException() throws QMeResourceException, QMeException {
        when(userRepo.findById(1L)).thenThrow(new QMeException("some error"));
        QMeUpdateUser qmeUser = new QMeUpdateUser();
        qmeUser.setUserName("suser1");
        qmeUser.setUserPassword("spassword1");
        qmeUser.setUserFirstName("Simple 1");
        qmeUser.setUserLastName("Simple User 1");
        qmeUser.setUserEmail("SimpleUser1@User.com");
        userService.update(qmeUser, 1L, 1L);
        verify(userRepo).findById(1L);
    }

    @Test(expected = QMeServerException.class)
    public void testUpdateQMeException() throws QMeResourceException, QMeException {
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        when(userRepo.update(Matchers.<User>anyObject(), eq(1L))).thenThrow(QMeException.class);

        QMeUpdateUser qmeUser = new QMeUpdateUser();
        qmeUser.setUserName("suser1");
        qmeUser.setUserPassword("spassword1");
        qmeUser.setUserFirstName("Simple 1");
        qmeUser.setUserLastName("Simple User 1");
        qmeUser.setUserEmail("SimpleUser1@User.com");

        userService.update(qmeUser, 1L, 1L);

        verify(userRepo).findById(1L);
        verify(userRepo).update(Matchers.<User>anyObject(), eq(1L));

    }


    @Test
    public void testDelete() throws QMeResourceException, QMeException {
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        doNothing().when(userRepo).delete(1L);
        userService.delete(1L);
        verify(userRepo).findById(1L);
        verify(userRepo).delete(1L);
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testDeleteUserNotFound() throws QMeResourceException, QMeException {
        when(userRepo.findById(1L)).thenReturn(null);
        userService.delete(1L);
        verify(userRepo).findById(1L);
    }

    @Test(expected = QMeServerException.class)
    public void testDeleteQMeException() throws QMeResourceException, QMeException {
        when(userRepo.findById(1L)).thenThrow(QMeException.class);
        userService.delete(1L);
        verify(userRepo).findById(1L);
    }


     @Test
     public void testForgotPassword() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenReturn(UserFixtures.simpleUser());
        when(atomicTokenGenerator.generateUniqueResetToken()).thenReturn("somerandomtoken");
        doNothing().when(userRepo).addResetToken("somerandomtoken", 1L);

        when(javaMailSender.getUsername()).thenReturn("someusername");
        when(javaMailSender.getPassword()).thenReturn("somepassword");
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        doNothing().when(javaMailSender).send(Matchers.<MimeMessage>anyObject());

        userService.forgotPassword("SimpleUser1@User.com", "some url");

        verify(userRepo).findByUserEmail("SimpleUser1@User.com");
        verify(atomicTokenGenerator).generateUniqueResetToken();
        verify(userRepo).addResetToken("somerandomtoken", 1L);
        verify(javaMailSender).send(Matchers.<MimeMessage>anyObject());
    }

    @Test
    public void testForgotPasswordUrlWithSlash() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenReturn(UserFixtures.simpleUser());
        when(atomicTokenGenerator.generateUniqueResetToken()).thenReturn("somerandomtoken");
        doNothing().when(userRepo).addResetToken("somerandomtoken", 1L);

        when(javaMailSender.getUsername()).thenReturn("someusername");
        when(javaMailSender.getPassword()).thenReturn("somepassword");
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        doNothing().when(javaMailSender).send(Matchers.<MimeMessage>anyObject());

        userService.forgotPassword("SimpleUser1@User.com", "some.url/");

        verify(userRepo).findByUserEmail("SimpleUser1@User.com");
        verify(atomicTokenGenerator).generateUniqueResetToken();
        verify(userRepo).addResetToken("somerandomtoken", 1L);
        verify(javaMailSender).send(Matchers.<MimeMessage>anyObject());
    }

    @Test(expected = QMeServerException.class)
    public void testForgotPasswordInvalidMailCredentialsError() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenReturn(UserFixtures.simpleUser());
        when(atomicTokenGenerator.generateUniqueResetToken()).thenReturn("somerandomtoken");
        doNothing().when(userRepo).addResetToken("somerandomtoken", 1L);
        doNothing().when(userRepo).deleteResetToken("somerandomtoken", 1L);

        when(javaMailSender.getUsername()).thenReturn(null);

        userService.forgotPassword("SimpleUser1@User.com", "some url");

        verify(userRepo).findByUserEmail("SimpleUser1@User.com");
        verify(atomicTokenGenerator).generateUniqueResetToken();
        verify(userRepo).addResetToken("somerandomtoken", 1L);
        verify(javaMailSender).getUsername();
        verify(userRepo).deleteResetToken("somerandomtoken", 1L);
    }

    @Test(expected = QMeServerException.class)
    public void testForgotPasswordMailMessagingError() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenReturn(UserFixtures.simpleUser());
        when(atomicTokenGenerator.generateUniqueResetToken()).thenReturn("somerandomtoken");
        doNothing().when(userRepo).addResetToken("somerandomtoken", 1L);
        doNothing().when(userRepo).deleteResetToken("somerandomtoken", 1L);

        when(javaMailSender.getUsername()).thenReturn("someusername");
        when(javaMailSender.getPassword()).thenReturn("somepassword");
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        doThrow(MessagingException.class).when(javaMailSender).send(Matchers.<MimeMessage>anyObject());

        userService.forgotPassword("SimpleUser1@User.com", "some url");

        verify(userRepo).findByUserEmail("SimpleUser1@User.com");
        verify(atomicTokenGenerator).generateUniqueResetToken();
        verify(userRepo).addResetToken("somerandomtoken", 1L);
        verify(javaMailSender).send(Matchers.<MimeMessage>anyObject());
        verify(userRepo).deleteResetToken("somerandomtoken", 1L);
    }

    @Test(expected = QMeServerException.class)
    public void testForgotPasswordMailError() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenReturn(UserFixtures.simpleUser());
        when(atomicTokenGenerator.generateUniqueResetToken()).thenReturn("somerandomtoken");
        doNothing().when(userRepo).addResetToken("somerandomtoken", 1L);
        doNothing().when(userRepo).deleteResetToken("somerandomtoken", 1L);

        when(javaMailSender.getUsername()).thenReturn("someusername");
        when(javaMailSender.getPassword()).thenReturn("somepassword");
        when(javaMailSender.createMimeMessage()).thenReturn(new MimeMessage((Session) null));

        doThrow(Exception.class).when(javaMailSender).send(Matchers.<MimeMessage>anyObject());

        userService.forgotPassword("SimpleUser1@User.com", "some url");

        verify(userRepo).findByUserEmail("SimpleUser1@User.com");
        verify(atomicTokenGenerator).generateUniqueResetToken();
        verify(userRepo).addResetToken("somerandomtoken", 1L);
        verify(javaMailSender).send(Matchers.<MimeMessage>anyObject());
        verify(userRepo).deleteResetToken("somerandomtoken", 1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testForgotPasswordInvalidURL() throws QMeResourceException, QMeException {
        userService.forgotPassword("SimpleUser1@User.com", null);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testForgotPasswordInvalidEmil() throws QMeResourceException, QMeException {
        userService.forgotPassword(null, "som url");
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testForgotPasswordUserNotFound() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenReturn(null);
        userService.forgotPassword("SimpleUser1@User.com", "some url");
        verify(userRepo).findByUserEmail("SimpleUser1@User.com");
    }

    @Test(expected = QMeServerException.class)
    public void testForgotPasswordQMeException() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenThrow(QMeException.class);
        userService.forgotPassword("SimpleUser1@User.com", "some url");
        verify(userRepo).findByUserEmail("SimpleUser1@User.com");
    }

    @Test
    public void testResetPassword() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenReturn(UserFixtures.simpleUser());

        when(userRepo.getResetTokenCreateTime("somerandomtoken", 1L)).thenReturn(LocalDateTime.now());

        when(passwordEncoder.encode(Matchers.<String>anyObject())).thenReturn("someencodedvalue");

        when(userRepo.resetUserPassword("somerandomtoken", 1L, "someencodedvalue")).thenReturn(UserFixtures.simpleUser());

        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setToken("somerandomtoken");
        qMeResetPassword.setUserName("suser1");
        qMeResetPassword.setUserPassword("somepssword");

        QMeUserDetail userDetail = userService.resetPassword("SimpleUser1@User.com", qMeResetPassword);

        verify(userRepo).findByUserEmail("SimpleUser1@User.com");

        verify(userRepo).getResetTokenCreateTime("somerandomtoken", 1L);

        verify(passwordEncoder).encode(Matchers.<String>anyObject());

        verify(userRepo).resetUserPassword("somerandomtoken", 1L, "someencodedvalue");


        assertNotNull(userDetail);

        assertThat(userDetail.getUserId(), equalTo(1L));
        assertThat(userDetail.getUserName(), equalTo("suser1"));
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testResetPasswordInvalidEmail() throws QMeResourceException, QMeException {
        userService.resetPassword(null, null);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testResetPasswordInvalidResetToken() throws QMeResourceException, QMeException {
        userService.resetPassword("SimpleUser1@User.com", null);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testResetPasswordInvalidToken() throws QMeResourceException, QMeException {
        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setToken(null);
        qMeResetPassword.setUserName("suser1");
        qMeResetPassword.setUserPassword("somepssword");
        userService.resetPassword("SimpleUser1@User.com", qMeResetPassword);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testResetPasswordInvalidUserName() throws QMeResourceException, QMeException {
        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setToken("somerandomtoken");
        qMeResetPassword.setUserName(null);
        qMeResetPassword.setUserPassword("somepssword");
        userService.resetPassword("SimpleUser1@User.com", qMeResetPassword);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testResetPasswordInvalidUserPassword() throws QMeResourceException, QMeException {
        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setToken("somerandomtoken");
        qMeResetPassword.setUserName("suser1");
        qMeResetPassword.setUserPassword(null);
        userService.resetPassword("SimpleUser1@User.com", qMeResetPassword);
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testResetPasswordUserNotFound() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenReturn(null);
        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setToken("somerandomtoken");
        qMeResetPassword.setUserName("suser1");
        qMeResetPassword.setUserPassword("somepssword");
        userService.resetPassword("SimpleUser1@User.com", qMeResetPassword);
        verify(userRepo).findByUserEmail("SimpleUser1@User.com");
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testResetPasswordUserNotMatching() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenReturn(UserFixtures.simpleUser());
        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setToken("somerandomtoken");
        qMeResetPassword.setUserName("suserdiffone");
        qMeResetPassword.setUserPassword("somepssword");
        userService.resetPassword("SimpleUser1@User.com", qMeResetPassword);
        verify(userRepo).findByUserEmail("SimpleUser1@User.com");
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testResetPasswordInvalidTokenCreateTime() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenReturn(UserFixtures.simpleUser());

        when(userRepo.getResetTokenCreateTime("somerandomtoken", 1L)).thenReturn(null);

        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setToken("somerandomtoken");
        qMeResetPassword.setUserName("suser1");
        qMeResetPassword.setUserPassword("somepssword");

        userService.resetPassword("SimpleUser1@User.com", qMeResetPassword);

        verify(userRepo).findByUserEmail("SimpleUser1@User.com");

        verify(userRepo).getResetTokenCreateTime("somerandomtoken", 1L);

    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testResetPasswordInvalidTokenExpired() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenReturn(UserFixtures.simpleUser());

        when(userRepo.getResetTokenCreateTime("somerandomtoken", 1L)).thenReturn(LocalDateTime.now().minusDays(5));

        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setToken("somerandomtoken");
        qMeResetPassword.setUserName("suser1");
        qMeResetPassword.setUserPassword("somepssword");

        userService.resetPassword("SimpleUser1@User.com", qMeResetPassword);

        verify(userRepo).findByUserEmail("SimpleUser1@User.com");

        verify(userRepo).getResetTokenCreateTime("somerandomtoken", 1L);

    }

    @Test(expected = QMeServerException.class)
    public void testResetPasswordQMeException() throws QMeResourceException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenThrow(QMeException.class);
        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setToken("somerandomtoken");
        qMeResetPassword.setUserName("suser1");
        qMeResetPassword.setUserPassword("somepssword");
        userService.resetPassword("SimpleUser1@User.com", qMeResetPassword);
    }
}