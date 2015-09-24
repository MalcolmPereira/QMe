/**
 * Name      : com.malcolm.qme.rest.service.impl.UserServiceImplTest.java
 * Date      : 5/27/2015
 * Developer : Malcolm
 * Purpose   : Test Cases for User Service Implementation
 **/
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.fixtures.UserFixtures;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.core.repository.UserRoleRepository;
import com.malcolm.qme.rest.api.AtomicTokenGenerator;
import com.malcolm.qme.rest.exception.*;
import com.malcolm.qme.rest.model.QMeResetPassword;
import com.malcolm.qme.rest.model.QMeUser;
import com.malcolm.qme.rest.model.QMeUserDetail;
import com.malcolm.qme.rest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.mail.Session;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
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

        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        Boolean staged = userService.stageUser(qmeUser, "some url");

        assertThat(staged, equalTo(Boolean.TRUE));

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

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testStageUserInvalidUserName() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException{
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName(null);
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        userService.stageUser(qmeUser, "some url");
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testStageUserInvalidUserPassword() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException{
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword(null);
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        userService.stageUser(qmeUser, "some url");
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testStageUserInvalidUserEmail() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException{
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail(null);
        userService.stageUser(qmeUser, "some url");
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testStageUserInvalidFirstName() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException{
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName(null);
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        userService.stageUser(qmeUser, "some url");
    }

    @Test(expected = QMeResourceConflictException.class)
    public void testStageUserConflictUserName() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserName("suser6")).thenReturn(UserFixtures.simpleUser());
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        userService.stageUser(qmeUser, "some url");
        verify(userRepo).findByUserName("suser6");
    }

    @Test(expected = QMeResourceConflictException.class)
    public void testStageUserConflictUserNameStage() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserName("suser6")).thenReturn(null);
        when(userRepo.findStagedUserByUserName("suser6")).thenReturn(UserFixtures.simpleUser());
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        userService.stageUser(qmeUser, "some url");
        verify(userRepo).findByUserName("suser6");
        verify(userRepo).findStagedUserByUserName("suser6");
    }

    @Test(expected = QMeResourceConflictException.class)
    public void testStageUserConflictUserEmail() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenReturn(UserFixtures.simpleUser());
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        userService.stageUser(qmeUser, "some url");
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
    }

    @Test(expected = QMeResourceConflictException.class)
    public void testStageUserConflictUserEmailStage() throws QMeInvalidResourceDataException, QMeResourceConflictException, QMeServerException, QMeException {
        when(userRepo.findByUserEmail("SimpleUser6@User.com")).thenReturn(null);
        when(userRepo.findStagedUserByUserEmail("SimpleUser6@User.com")).thenReturn(UserFixtures.simpleUser());
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");
        userService.stageUser(qmeUser, "some url");
        verify(userRepo).findByUserEmail("SimpleUser6@User.com");
        verify(userRepo).findStagedUserByUserEmail("SimpleUser6@User.com");
    }

    @Test
    public void testUpdate() throws QMeResourceException, QMeException {
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        when(userRepo.update(Matchers.<User>anyObject(), eq(1L))).thenReturn(UserFixtures.simpleUser());

        QMeUser qmeUser = new QMeUser();
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

    @Test(expected = QMeServerException.class)
    public void testUpdateQMeException() throws QMeResourceException, QMeException {
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        when(userRepo.update(Matchers.<User>anyObject(), eq(1L))).thenThrow(QMeException.class);

        QMeUser qmeUser = new QMeUser();
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
}