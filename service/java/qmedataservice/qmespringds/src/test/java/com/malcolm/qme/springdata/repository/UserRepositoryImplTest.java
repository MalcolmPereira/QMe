/**
 * Name      : com.malcolm.qme.springdata.repository.UserRepositoryImplTest.java
 * Date      : 5/4/2015
 * Developer : Malcolm
 * Purpose   : Tests for User Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
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
    public void testFindAll() {
        assertNotNull(userRepo);
        List<User> users = userRepo.findAll();
        assertNotNull(users);
        assertThat(users.size(), greaterThan(0));
    }

    @Test
    public void testFindById() {
        assertNotNull(userRepo);
        User user = userRepo.findById(1L);
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(1L));
    }


    @Test
    public void testCRUD() {
        assertNotNull(userRepo);

        User user = new User("UserRepositoryImplTest", "Test", "Test", "Test", "UserRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));

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
                user.getUserRegisteredDate(), LocalDateTime.now(),
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
    public void testFindByUserName() {

        assertNotNull(userRepo);

        User user = new User("UserRepositoryImplTestUserName", "Test", "Test", "Test", "UserRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));

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
                user.getUserRegisteredDate(), LocalDateTime.now(),
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
    public void testFindByUserEmail() {

        assertNotNull(userRepo);

        User user = new User("UserRepositoryImplTestUserName1", "Test", "Test", "Test", "UserRepositoryImplTestUserName1@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));

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
                user.getUserRegisteredDate(), LocalDateTime.now(),
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


}
