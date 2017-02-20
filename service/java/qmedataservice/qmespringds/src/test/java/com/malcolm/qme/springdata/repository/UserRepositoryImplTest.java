/**
 * Name      : com.malcolm.qme.springdata.repository.UserRepositoryImplTest.java
 * Date      : 5/4/2015
 * Developer : Malcolm
 * Purpose   : Tests for User Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.repository.PageSort;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.UserEntity;
import com.malcolm.qme.springdata.entity.UserPasswordResetEntity;
import com.malcolm.qme.springdata.entity.UserPasswordResetEntityId;
import com.malcolm.qme.springdata.entity.UserStagingEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class UserRepositoryImplTest {

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepo;


    @Mock
    private UserSpringDataRepository userSpringDataRepoMOCK;

    @Mock
    private UserStagingSpringDataRepository userStagingSpringDataRepositoryMOCK;

    @Mock
    private UserPasswordResetSpringDataRepository userPasswordResetDataRepoMOCK;

    @InjectMocks
    private UserRepository userRepositoryWithMock;

    @Before
    public void initMocks(){
        userRepositoryWithMock = new UserRepositoryImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() throws QMeException {
        assertNotNull(userRepo);
        List<User> users = userRepo.findAll();
        assertNotNull(users);
        assertThat(users.size(), greaterThan(0));
        for (User user : users){
            assertNotNull(user.getUserRoles());
        }
    }

    @Test
    public void testFindAllPaged() throws QMeException {
        assertNotNull(userRepo);
        PageSort pageSort = new PageSort(0,10,Boolean.TRUE);
        List<User> users = userRepo.findAll(pageSort);
        assertNotNull(users);
        assertThat(users.size(), greaterThan(0));
        for (User user : users){
            assertNotNull(user.getUserRoles());
        }

        pageSort = new PageSort(0,10,Boolean.TRUE,"USERNAME");
        users = userRepo.findAll(pageSort);
        assertNotNull(users);
        assertThat(users.size(), greaterThan(0));
        for (User user : users){
            assertNotNull(user.getUserRoles());
        }

        pageSort = new PageSort(0,10,Boolean.TRUE,"EMAIL");
        users = userRepo.findAll(pageSort);
        assertNotNull(users);
        assertThat(users.size(), greaterThan(0));
        for (User user : users){
            assertNotNull(user.getUserRoles());
        }

        pageSort = new PageSort(0,10,Boolean.TRUE,"FIRSTNAME");
        users = userRepo.findAll(pageSort);
        assertNotNull(users);
        assertThat(users.size(), greaterThan(0));
        for (User user : users){
            assertNotNull(user.getUserRoles());
        }

        pageSort = new PageSort(0,10,Boolean.TRUE,"LASTNAME");
        users = userRepo.findAll(pageSort);
        assertNotNull(users);
        assertThat(users.size(), greaterThan(0));
        for (User user : users){
            assertNotNull(user.getUserRoles());
        }

        pageSort = new PageSort(0,10,Boolean.FALSE,"USERNAME");
        users = userRepo.findAll(pageSort);
        assertNotNull(users);
        assertThat(users.size(), greaterThan(0));
        for (User user : users){
            assertNotNull(user.getUserRoles());
        }

        pageSort = new PageSort(0,10,Boolean.FALSE,"EMAIL");
        users = userRepo.findAll(pageSort);
        assertNotNull(users);
        assertThat(users.size(), greaterThan(0));
        for (User user : users){
            assertNotNull(user.getUserRoles());
        }

        pageSort = new PageSort(0,10,Boolean.FALSE,"FIRSTNAME");
        users = userRepo.findAll(pageSort);
        assertNotNull(users);
        assertThat(users.size(), greaterThan(0));
        for (User user : users){
            assertNotNull(user.getUserRoles());
        }

        pageSort = new PageSort(0,10,Boolean.FALSE,"LASTNAME");
        users = userRepo.findAll(pageSort);
        assertNotNull(users);
        assertThat(users.size(), greaterThan(0));
        for (User user : users){
            assertNotNull(user.getUserRoles());
        }


        pageSort = new PageSort(0,10,Boolean.TRUE,"USERNAME","EMAIL","FIRSTNAME","LASTNAME");
        users = userRepo.findAll(pageSort);
        assertNotNull(users);
        assertThat(users.size(), greaterThan(0));
        for (User user : users){
            assertNotNull(user.getUserRoles());
        }

        pageSort = new PageSort(0,10,Boolean.FALSE,"USERNAME","EMAIL","FIRSTNAME","LASTNAME");
        users = userRepo.findAll(pageSort);
        assertNotNull(users);
        assertThat(users.size(), greaterThan(0));
        for (User user : users){
            assertNotNull(user.getUserRoles());
        }

    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(userRepo);
        User user = userRepo.findById(1L);
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(1L));
        assertNotNull(user.getUserRoles());
    }


    @Test
    public void testCRUD() throws QMeException {
        assertNotNull(userRepo);

        User user = new User("UserRepositoryImplTest", "Test", "Test", "Test", "UserRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));

        Long userID = user.getUserID();
        user = userRepo.findById(userID);
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(userID));


        User userUpdate = new User(
                user.getUserID(),
                user.getUserName(),
                user.getUserPassword(),
                "First Name Updated",
                "Last Name Updated",
                user.getUserEmail(),
                user.getUserRegisteredDate(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                2L);

        userUpdate = userRepo.update(userUpdate, 2L);

        assertNotNull(userUpdate);
        assertThat(userUpdate.getUserID(), equalTo(userID));
        assertThat(userUpdate.getUserFirstName(), equalTo("First Name Updated"));
        assertThat(userUpdate.getUserLastName(), equalTo("Last Name Updated"));

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

    }

    @Test
    public void testUserStagingAndRegistration() throws QMeException {
        assertNotNull(userRepo);

        String userName = "UserRepositoryImplStagingTest" + System.currentTimeMillis();

        User user = new User(userName, "Test", "Test", "Test", userName+"@test.com");
        String stagingToken = userRepo.stageUserRegistration(user);
        assertNotNull(stagingToken);
        assertThat(stagingToken.length(), greaterThan(0));

        user = userRepo.confirmUserRegistration(stagingToken);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));

        Long userID = user.getUserID();
        user = userRepo.findById(userID);
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(userID));

        User userUpdate = new User(
                user.getUserID(),
                user.getUserName(),
                user.getUserPassword(),
                "First Name Updated",
                "Last Name Updated",
                user.getUserEmail(),
                user.getUserRegisteredDate(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                2L);

        userUpdate = userRepo.update(userUpdate, 2L);

        assertNotNull(userUpdate);
        assertThat(userUpdate.getUserID(), equalTo(userID));
        assertThat(userUpdate.getUserFirstName(), equalTo("First Name Updated"));
        assertThat(userUpdate.getUserLastName(), equalTo("Last Name Updated"));

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    }

    @Test
    public void testUserStagingDelete() throws QMeException {
        UserStagingEntity userEntity = new UserStagingEntity();
        userEntity.setUserId(1L);
        userEntity.setStagingToken("sometoken");
        when(userStagingSpringDataRepositoryMOCK.save(Matchers.<UserStagingEntity>anyObject())).thenReturn(userEntity);
        String userName = "UserRepositoryImplStagingTest" + System.currentTimeMillis();
        User user = new User(userName, "Test", "Test", "Test", userName+"@test.com");
        String stagingToken = userRepositoryWithMock.stageUserRegistration(user);
        assertNotNull(stagingToken);
        assertThat(stagingToken.length(), greaterThan(0));
        assertThat(stagingToken, equalTo("sometoken"));
        verify(userStagingSpringDataRepositoryMOCK).save(Matchers.<UserStagingEntity>anyObject());

        when(userStagingSpringDataRepositoryMOCK.findByStagingTokenIgnoreCase("sometoken")).thenReturn(userEntity);
        doNothing().when(userStagingSpringDataRepositoryMOCK).delete(1L);
        userRepositoryWithMock.deleteStagingToken("sometoken");
        verify(userStagingSpringDataRepositoryMOCK).findByStagingTokenIgnoreCase("sometoken");
        verify(userStagingSpringDataRepositoryMOCK).delete(1L);
    }

    @Test(expected = QMeException.class)
    public void testUserStagingDeleteFindQMeException() throws QMeException {
        when(userStagingSpringDataRepositoryMOCK.findByStagingTokenIgnoreCase("sometoken")).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.deleteStagingToken("sometoken");
        verify(userStagingSpringDataRepositoryMOCK).findByStagingTokenIgnoreCase("sometoken");
    }

    @Test(expected = QMeException.class)
    public void testUserStagingDeleteConfirmStagingQMeException() throws QMeException {
        assertNotNull(userRepo);

        String userName = "UserRepositoryImplDeleteStagingTest" + System.currentTimeMillis();

        User user = new User(userName, "Test", "Test", "Test", userName+"@test.com");
        String stagingToken = userRepo.stageUserRegistration(user);
        assertNotNull(stagingToken);
        assertThat(stagingToken.length(), greaterThan(0));

        userRepo.deleteStagingToken(stagingToken);

        userRepo.confirmUserRegistration(stagingToken);
    }

    @Test
    public void testUserStagingFindByUserName() throws QMeException {
        assertNotNull(userRepo);

        String userName = "URepoImplStagingFindUserName" + System.currentTimeMillis();

        User user = new User(userName, "Test", "Test", "Test", userName+"@test.com");
        String stagingToken = userRepo.stageUserRegistration(user);
        assertNotNull(stagingToken);
        assertThat(stagingToken.length(), greaterThan(0));

        user = userRepo.findStagedUserByUserName(userName);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));

        user = userRepo.confirmUserRegistration(stagingToken);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        user = userRepo.findStagedUserByUserName(userName);
        assertNull(user);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    }

    @Test
    public void testFindByUserName() throws QMeException {

        assertNotNull(userRepo);

        User user = new User("UserRepositoryImplTestUserName", "Test", "Test", "Test", "UserRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));

        Long userID = user.getUserID();
        user = userRepo.findById(userID);
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(userID));

        user = userRepo.findByUserName("UserRepositoryImplTestUserName");
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(userID));

        user = userRepo.findByUserName("userrepositoryimpltestusername");
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(userID));

        User userUpdate = new User(
                user.getUserID(),
                user.getUserName(),
                user.getUserPassword(),
                "First Name Updated",
                "Last Name Updated",
                user.getUserEmail(),
                user.getUserRegisteredDate(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                2L);

        userUpdate = userRepo.update(userUpdate, 2L);

        assertNotNull(userUpdate);
        assertThat(userUpdate.getUserID(), equalTo(userID));
        assertThat(userUpdate.getUserFirstName(), equalTo("First Name Updated"));
        assertThat(userUpdate.getUserLastName(), equalTo("Last Name Updated"));

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

    }

    @Test
    public void testFindByUserEmail() throws QMeException {

        assertNotNull(userRepo);

        User user = new User("UserRepositoryImplTestUserName1", "Test", "Test", "Test", "UserRepositoryImplTestUserName1@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));

        Long userID = user.getUserID();
        user = userRepo.findById(userID);
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(userID));

        user = userRepo.findByUserEmail("UserRepositoryImplTestUserName1@test.com");
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(userID));

        user = userRepo.findByUserEmail("userrepositoryimpltestusername1@TEST.COM");
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(userID));

        User userUpdate = new User(
                user.getUserID(),
                user.getUserName(),
                user.getUserPassword(),
                "First Name Updated",
                "Last Name Updated",
                user.getUserEmail(),
                user.getUserRegisteredDate(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                2L);

        userUpdate = userRepo.update(userUpdate, 2L);

        assertNotNull(userUpdate);
        assertThat(userUpdate.getUserID(), equalTo(userID));
        assertThat(userUpdate.getUserFirstName(), equalTo("First Name Updated"));
        assertThat(userUpdate.getUserLastName(), equalTo("Last Name Updated"));

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

    }


    @Test
    public void testUserStagingFindByUserEmail() throws QMeException {
        assertNotNull(userRepo);

        String userName = "URepoImplStagingFindUserEmail" + System.currentTimeMillis();

        User user = new User(userName, "Test", "Test", "Test", userName+"@test.com");
        String stagingToken = userRepo.stageUserRegistration(user);
        assertNotNull(stagingToken);
        assertThat(stagingToken.length(), greaterThan(0));

        user = userRepo.findStagedUserByUserEmail(userName + "@test.com");
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));

        user = userRepo.confirmUserRegistration(stagingToken);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

                user = userRepo.findStagedUserByUserEmail(userName + "@test.com");
        assertNull(user);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    }


    @Test
    public void testAddResetToken() throws QMeException, InterruptedException {

        assertNotNull(userRepo);

        User user = new User("URepoImplTestUserNamePassReset1", "Test", "Test", "Test", "URepoImplTestUserNamePassReset1@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        userRepo.addResetToken("somerandomtoken", userID);

        LocalDateTime tokenTime = userRepo.getResetTokenCreateTime("somerandomtoken", userID);
        assertNotNull(tokenTime);

        userRepo.deleteResetToken("somerandomtoken", userID);

        tokenTime = userRepo.getResetTokenCreateTime("somerandomtoken", userID);
        assertNull(tokenTime);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    }

    @Test
    public void testUpdateLoginDate() throws QMeException, InterruptedException {
        assertNotNull(userRepo);

        User existingUser = userRepo.findByUserName("URepoImplTestUserNamePassReset2");
        if(existingUser !=null ){
            userRepo.delete(existingUser.getUserID());
        }

        User user = new User("URepoImplTestUserNamePassReset2", "Test", "Test", "Test", "URepoImplTestUserNamePassReset2@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertNotNull(user.getUserLastLoginDate());
        assertNotNull(user.getUserLoginDate());

        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();
        LocalDateTime loginDate     = user.getUserLoginDate();

        user = userRepo.updateLoginDate(userID);
        assertNotNull(user);
        assertNotNull(user.getUserLastLoginDate());
        assertNotNull(user.getUserLoginDate());
        assertThat(user.getUserLastLoginDate().format(DateTimeFormatter.ISO_DATE_TIME).substring(0, 16), equalTo(loginDate.format(DateTimeFormatter.ISO_DATE_TIME).substring(0, 16)));

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    }

    @Test
    public void testResetUserPassword() throws QMeException, InterruptedException {
        assertNotNull(userRepo);

        User user = new User("URepoImplTestUserNamePassReset3", "Test", "Test", "Test", "URepoImplTestUserNamePassReset3@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        userRepo.addResetToken("somerandomtoken", userID);

        LocalDateTime tokenTime = userRepo.getResetTokenCreateTime("somerandomtoken", userID);
        assertNotNull(tokenTime);

        user =  userRepo.resetUserPassword("somerandomtoken", userID,"SomeNew");
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(userID));
        assertThat(user.getUserPassword(), equalTo("SomeNew"));

        tokenTime = userRepo.getResetTokenCreateTime("somerandomtoken", userID);
        assertNull(tokenTime);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

    }

    @Test
    public void testFindAllNullReturn() throws QMeException {
        when(userSpringDataRepoMOCK.findAll()).thenReturn(null);
        List<User> users = userRepositoryWithMock.findAll();
        verify(userSpringDataRepoMOCK).findAll();
        assertNotNull(users);
        assertThat(users.size(), equalTo(0));
    }

    @Test(expected = QMeException.class)
    public void testFindAllQMeException() throws QMeException {
        when(userSpringDataRepoMOCK.findAll()).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.findAll();
        verify(userSpringDataRepoMOCK).findAll();
    }

    @Test(expected = QMeException.class)
    public void testFindByUserNameQMeException() throws QMeException {
        when(userSpringDataRepoMOCK.findByUserNameIgnoreCase("test")).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.findByUserName("test");
        verify(userSpringDataRepoMOCK).findByUserNameIgnoreCase("test");
    }

    @Test
    public void testFindByUserNameReturnNull() throws QMeException {
        when(userSpringDataRepoMOCK.findByUserNameIgnoreCase("test")).thenReturn(null);
        User user = userRepositoryWithMock.findByUserName("test");
        verify(userSpringDataRepoMOCK).findByUserNameIgnoreCase("test");
        assertNull(user);
    }

    @Test(expected = QMeException.class)
    public void testFindStagedByUserNameQMeException() throws QMeException {
        when(userStagingSpringDataRepositoryMOCK.findByUserNameIgnoreCase("test")).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.findStagedUserByUserName("test");
        verify(userStagingSpringDataRepositoryMOCK).findByUserNameIgnoreCase("test");
    }

    @Test(expected = QMeException.class)
    public void testFindByUserEmailQMeException() throws QMeException {
        when(userSpringDataRepoMOCK.findByUserEmailIgnoreCase("test")).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.findByUserEmail("test");
        verify(userSpringDataRepoMOCK).findByUserEmailIgnoreCase("test");
    }

    @Test
    public void testFindByUserEmailReturnNull() throws QMeException {
        when(userSpringDataRepoMOCK.findByUserEmailIgnoreCase("test")).thenReturn(null);
        User user = userRepositoryWithMock.findByUserEmail("test");
        verify(userSpringDataRepoMOCK).findByUserEmailIgnoreCase("test");
        assertNull(user);
    }

    @Test(expected = QMeException.class)
    public void testFindStagedByUserEmailQMeException() throws QMeException {
        when(userStagingSpringDataRepositoryMOCK.findByUserEmailIgnoreCase("test")).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.findStagedUserByUserEmail("test");
        verify(userStagingSpringDataRepositoryMOCK).findByUserEmailIgnoreCase("test");
    }

    @Test(expected = QMeException.class)
    public void testFindByIdQMeException() throws QMeException {
        when(userSpringDataRepoMOCK.findOne(1L)).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.findById(1L);
        verify(userSpringDataRepoMOCK).findOne(1L);
    }

    @Test(expected = QMeException.class)
    public void testSaveQMeException() throws QMeException {
        when(userSpringDataRepoMOCK.save(Matchers.<UserEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.save(new User("UserRepositoryImplTestUserName", "Test", "Test", "Test", "UserRepositoryImplTest@test.com"));
        verify(userSpringDataRepoMOCK).save(Matchers.<UserEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testStageQMeException() throws QMeException {
        when(userStagingSpringDataRepositoryMOCK.save(Matchers.<UserStagingEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.stageUserRegistration(new User("UserRepositoryImplTestUserName", "Test", "Test", "Test", "UserRepositoryImplTest@test.com"));
        verify(userStagingSpringDataRepositoryMOCK).save(Matchers.<UserStagingEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testConfirmUserRegistrationNoTokenQMeException() throws QMeException {
        when(userStagingSpringDataRepositoryMOCK.findByStagingTokenIgnoreCase("sometoken")).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.confirmUserRegistration("sometoken");
        verify(userStagingSpringDataRepositoryMOCK).findByStagingTokenIgnoreCase("sometoken");
    }

    @Test(expected = QMeException.class)
    public void testConfirmUserRegistrationNullTokenQMeException() throws QMeException {
        when(userStagingSpringDataRepositoryMOCK.findByStagingTokenIgnoreCase("sometoken")).thenReturn(null);
        userRepositoryWithMock.confirmUserRegistration("sometoken");
        verify(userStagingSpringDataRepositoryMOCK).findByStagingTokenIgnoreCase("sometoken");
    }

    @Test(expected = QMeException.class)
    public void testConfirmUserRegistrationQMeException() throws QMeException {
        UserStagingEntity userStagingEntity = new UserStagingEntity();
        userStagingEntity.setUserId(1L);
        userStagingEntity.setUserName("test");
        userStagingEntity.setUserPasscode("test");
        userStagingEntity.setUserFirstName("test");
        userStagingEntity.setUserLastName("test");
        userStagingEntity.setUserEmail("test");
        userStagingEntity.setUserStagingDate(LocalDateTime.now());
        when(userStagingSpringDataRepositoryMOCK.findByStagingTokenIgnoreCase("sometoken")).thenReturn(userStagingEntity);
        when(userSpringDataRepoMOCK.save(Matchers.<UserEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.confirmUserRegistration("sometoken");
        verify(userStagingSpringDataRepositoryMOCK).findByStagingTokenIgnoreCase("sometoken");
        verify(userSpringDataRepoMOCK).save(Matchers.<UserEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testUpdateQMeException() throws QMeException {
        when(userSpringDataRepoMOCK.save(Matchers.<UserEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.update(new User("UserRepositoryImplTestUserName", "Test", "Test", "Test", "UserRepositoryImplTest@test.com"), 1L);
        verify(userSpringDataRepoMOCK).save(Matchers.<UserEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testDeleteQMeException() throws QMeException {
        doThrow(new RuntimeException("some error")).when(userSpringDataRepoMOCK).delete(1L);
        userRepositoryWithMock.delete(1L);
        verify(userSpringDataRepoMOCK).delete(1L);
    }

    @Test(expected = QMeException.class)
    public void testAddResetTokenFindQMeException() throws QMeException {
        when(userPasswordResetDataRepoMOCK.findByUserId(1L)).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.addResetToken("sometoken", 1L);
        verify(userPasswordResetDataRepoMOCK).findByUserId(1L);
    }

    @Test(expected = QMeException.class)
    public void testAddResetTokenDeleteTokenQMeException() throws QMeException {
        List<UserPasswordResetEntity> resetTokenList = new ArrayList<>();
        UserPasswordResetEntityId resetToken = new UserPasswordResetEntityId(1L, "sometoken");
        resetTokenList.add(new UserPasswordResetEntity(resetToken, LocalDateTime.now()));
        when(userPasswordResetDataRepoMOCK.findByUserId(1L)).thenReturn(resetTokenList);
        doThrow(new RuntimeException("some error")).when(userPasswordResetDataRepoMOCK).delete(resetToken);
        userRepositoryWithMock.addResetToken("sometoken", 1L);
        verify(userPasswordResetDataRepoMOCK).findByUserId(1L);
        verify(userPasswordResetDataRepoMOCK).delete(resetToken);
    }

    @Test(expected = QMeException.class)
    public void testAddResetTokenSaveokenQMeException() throws QMeException {
        List<UserPasswordResetEntity> resetTokenList = new ArrayList<>();
        UserPasswordResetEntityId resetToken = new UserPasswordResetEntityId(1L, "sometoken");
        resetTokenList.add(new UserPasswordResetEntity(resetToken,LocalDateTime.now()));
        when(userPasswordResetDataRepoMOCK.findByUserId(1L)).thenReturn(resetTokenList);
        doNothing().when(userPasswordResetDataRepoMOCK).delete(resetToken);
        when(userPasswordResetDataRepoMOCK.save(Matchers.<UserPasswordResetEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.addResetToken("sometoken", 1L);
        verify(userPasswordResetDataRepoMOCK).findByUserId(1L);
        verify(userPasswordResetDataRepoMOCK).delete(resetToken);
        verify(userPasswordResetDataRepoMOCK).save(Matchers.<UserPasswordResetEntity>anyObject());

    }

    @Test(expected = QMeException.class)
    public void testGetResetTokenCreateTimeQMeException() throws QMeException {
        UserPasswordResetEntityId resetToken = new UserPasswordResetEntityId(1L, "sometoken");
        when(userPasswordResetDataRepoMOCK.findOne(resetToken)).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.getResetTokenCreateTime("sometoken", 1L);
        verify(userPasswordResetDataRepoMOCK).findOne(resetToken);
    }

    @Test(expected = QMeException.class)
    public void testDeleteTokenQMeException() throws QMeException {
        UserPasswordResetEntityId resetToken = new UserPasswordResetEntityId(1L, "sometoken");
        doThrow(new RuntimeException("some error")).when(userPasswordResetDataRepoMOCK).delete(resetToken);
        userRepositoryWithMock.deleteResetToken("sometoken", 1L);
        verify(userPasswordResetDataRepoMOCK).delete(resetToken);
    }

    @Test(expected = QMeException.class)
    public void testUpdateLoginDateFindQMeException() throws QMeException, InterruptedException {
        when(userSpringDataRepoMOCK.findOne(1L)).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.updateLoginDate(1L);
        verify(userSpringDataRepoMOCK).findOne(1L);
    }

    @Test
    public void testUpdateLoginDateReturnNull() throws QMeException, InterruptedException {
        when(userSpringDataRepoMOCK.findOne(1L)).thenReturn(null);
        User user = userRepositoryWithMock.updateLoginDate(1L);
        verify(userSpringDataRepoMOCK).findOne(1L);
        assertNull(user);
    }

    @Test(expected = QMeException.class)
    public void testUpdateLoginDateQMeException() throws QMeException, InterruptedException {
        UserEntity userEntity = new UserEntity();
        when(userSpringDataRepoMOCK.findOne(1L)).thenReturn(userEntity);
        when(userSpringDataRepoMOCK.save(Matchers.<UserEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.updateLoginDate(1L);
        verify(userSpringDataRepoMOCK).findOne(1L);
        verify(userSpringDataRepoMOCK).save(Matchers.<UserEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testResetPasswordFindQMeException() throws QMeException, InterruptedException {
        UserPasswordResetEntityId resetToken = new UserPasswordResetEntityId(1L, "sometoken");
        when(userPasswordResetDataRepoMOCK.findOne(resetToken)).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.resetUserPassword("sometoken", 1L, "somepassword");
        verify(userPasswordResetDataRepoMOCK).findOne(resetToken);
    }

    @Test(expected = QMeException.class)
    public void testResetPasswordFindNullUser() throws QMeException, InterruptedException {
        UserPasswordResetEntityId resetToken = new UserPasswordResetEntityId(1L, "sometoken");
        when(userPasswordResetDataRepoMOCK.findOne(resetToken)).thenReturn(null);
        userRepositoryWithMock.resetUserPassword("sometoken", 1L, "somepassword");
        verify(userPasswordResetDataRepoMOCK).findOne(resetToken);
    }


    @Test(expected = QMeException.class)
    public void testResetPasswordFindNullReturnQMeException() throws QMeException, InterruptedException {
        UserPasswordResetEntityId resetToken = new UserPasswordResetEntityId(1L, "sometoken");
        when(userPasswordResetDataRepoMOCK.findOne(resetToken)).thenReturn(null);
        userRepositoryWithMock.resetUserPassword("sometoken", 1L, "somepassword");
        verify(userPasswordResetDataRepoMOCK).findOne(resetToken);
    }

    @Test(expected = QMeException.class)
    public void testResetPasswordFindUserQMeException() throws QMeException, InterruptedException {
        UserPasswordResetEntityId resetToken = new UserPasswordResetEntityId(1L, "sometoken");
        UserPasswordResetEntity userPasswordResetEntity = new UserPasswordResetEntity(resetToken,LocalDateTime.now());
        when(userPasswordResetDataRepoMOCK.findOne(resetToken)).thenReturn(userPasswordResetEntity);
        when(userSpringDataRepoMOCK.findOne(1L)).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.resetUserPassword("sometoken", 1L, "somepassword");
        verify(userPasswordResetDataRepoMOCK).findOne(resetToken);
        verify(userSpringDataRepoMOCK).findOne(1L);
    }

    @Test(expected = QMeException.class)
    public void testResetPasswordFindUserNullUser() throws QMeException, InterruptedException {
        UserPasswordResetEntityId resetToken = new UserPasswordResetEntityId(1L, "sometoken");
        UserPasswordResetEntity userPasswordResetEntity = new UserPasswordResetEntity(resetToken,LocalDateTime.now());
        when(userPasswordResetDataRepoMOCK.findOne(resetToken)).thenReturn(userPasswordResetEntity);
        when(userSpringDataRepoMOCK.findOne(1L)).thenReturn(null);
        userRepositoryWithMock.resetUserPassword("sometoken", 1L, "somepassword");
        verify(userPasswordResetDataRepoMOCK).findOne(resetToken);
        verify(userSpringDataRepoMOCK).findOne(1L);
    }

    @Test(expected = QMeException.class)
    public void testResetPasswordSaveUserQMeException() throws QMeException, InterruptedException {
        UserPasswordResetEntityId resetToken = new UserPasswordResetEntityId(1L, "sometoken");
        UserPasswordResetEntity userPasswordResetEntity = new UserPasswordResetEntity(resetToken,LocalDateTime.now());
        when(userPasswordResetDataRepoMOCK.findOne(resetToken)).thenReturn(userPasswordResetEntity);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1L);
        userEntity.setUserName("test");
        userEntity.setUserPasscode("test");
        userEntity.setUserFirstName("test");
        userEntity.setUserLastName("test");
        userEntity.setUserEmail("test");
        userEntity.setUserRegisteredDate(LocalDateTime.now());
        userEntity.setUserUpdatedDate(LocalDateTime.now());
        userEntity.setUserLastLoginDate(LocalDateTime.now());
        userEntity.setUserLoginDate(LocalDateTime.now());
        userEntity.setUpdateUser(1L);
        when(userSpringDataRepoMOCK.findOne(1L)).thenReturn(userEntity);
        when(userSpringDataRepoMOCK.save(Matchers.<UserEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userRepositoryWithMock.resetUserPassword("sometoken", 1L, "somepassword");
        verify(userPasswordResetDataRepoMOCK).findOne(resetToken);
        verify(userSpringDataRepoMOCK).findOne(1L);
        verify(userSpringDataRepoMOCK).save(Matchers.<UserEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testResetPasswordDeleteTokenUserQMeException() throws QMeException, InterruptedException {
        UserPasswordResetEntityId resetToken = new UserPasswordResetEntityId(1L, "sometoken");
        UserPasswordResetEntity userPasswordResetEntity = new UserPasswordResetEntity(resetToken,LocalDateTime.now());
        when(userPasswordResetDataRepoMOCK.findOne(resetToken)).thenReturn(userPasswordResetEntity);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId(1L);
        userEntity.setUserName("test");
        userEntity.setUserPasscode("test");
        userEntity.setUserFirstName("test");
        userEntity.setUserLastName("test");
        userEntity.setUserEmail("test");
        userEntity.setUserRegisteredDate(LocalDateTime.now());
        userEntity.setUserUpdatedDate(LocalDateTime.now());
        userEntity.setUserLastLoginDate(LocalDateTime.now());
        userEntity.setUserLoginDate(LocalDateTime.now());
        userEntity.setUpdateUser(1L);
        when(userSpringDataRepoMOCK.findOne(1L)).thenReturn(userEntity);
        when(userSpringDataRepoMOCK.save(Matchers.<UserEntity>anyObject())).thenReturn(userEntity);
        doThrow(new RuntimeException("some error")).when(userPasswordResetDataRepoMOCK).delete(resetToken);
        userRepositoryWithMock.resetUserPassword("sometoken", 1L, "somepassword");
        verify(userPasswordResetDataRepoMOCK).findOne(resetToken);
        verify(userSpringDataRepoMOCK).findOne(1L);
        verify(userSpringDataRepoMOCK).save(Matchers.<UserEntity>anyObject());
        verify(userPasswordResetDataRepoMOCK).delete(resetToken);
    }

}