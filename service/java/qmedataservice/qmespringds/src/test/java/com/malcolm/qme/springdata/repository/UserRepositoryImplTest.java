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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.List;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
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

}
