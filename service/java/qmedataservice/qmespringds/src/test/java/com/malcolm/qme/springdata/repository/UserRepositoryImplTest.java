/**
 * Name      : com.malcolm.qme.springdata.repository.UserRepositoryImplTest.java
 * Date      : 5/4/2015
 * Developer : Malcolm
 * Purpose   : Tests for User Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

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


    @Test
    public void testFindAll() throws QMeException {
        assertNotNull(userRepo);
        List<User> users = userRepo.findAll();
        assertNotNull(users);
        assertThat(users.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(userRepo);
        User user = userRepo.findById(1L);
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(1L));
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

        user = userRepo.findStagedUserByUserName(userName);
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

        user = userRepo.findStagedUserByUserEmail(userName+"@test.com");
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));

        user = userRepo.confirmUserRegistration(stagingToken);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));

        user = userRepo.findStagedUserByUserEmail(userName+"@test.com");
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

        User user = new User("URepoImplTestUserNamePassReset1", "Test", "Test", "Test", "URepoImplTestUserNamePassReset1@test.com");
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
        assertThat(user.getUserLastLoginDate().format(DateTimeFormatter.ISO_DATE_TIME).substring(0,16), equalTo(loginDate.format(DateTimeFormatter.ISO_DATE_TIME).substring(0,16)));

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    }

    @Test
    public void testResetUserPassword() throws QMeException, InterruptedException {
        assertNotNull(userRepo);

        User user = new User("URepoImplTestUserNamePassReset2", "Test", "Test", "Test", "URepoImplTestUserNamePassReset2@test.com");
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
}