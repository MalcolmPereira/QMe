/**
 * Name      : com.malcolm.qme.springdata.repository.UserRepositoryImplTest.java
 * Date      : 5/4/2015
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA UserEntity Repository Implemetation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.springdata.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @Author: malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class UserRepositoryImplTest {

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepo;



    @Test
    public void testFetchAll(){
        assertNotNull(userRepo);
        List<User> users = userRepo.findAll();
        assertNotNull(users);
        assertThat(users.size(), greaterThan(0));
    }

    @Test
    public void testFetchOne(){
        assertNotNull(userRepo);
        User user = userRepo.findById(1L);
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(1L));
    }



    @Test
    public void testCRUD(){
        assertNotNull(userRepo);

        User user = new User("UserRepositoryImplTest", "Test", "Test", "Test","UserRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));

        long userID = user.getUserID();
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
                            user.getUserRegisteredDate(), new Date(),
                            2L );

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
