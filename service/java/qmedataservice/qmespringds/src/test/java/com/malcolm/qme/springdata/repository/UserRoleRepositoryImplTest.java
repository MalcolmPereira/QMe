/**
 * Name      : com.malcolm.qme.springdata.repository.UserRoleRepositoryImplTest.java
 * Date      : 5/11/15
 * Developer : Malcolm
 * Purpose   : QMe User Roles Repository Implementation Test Cases
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.UserRole;
import com.malcolm.qme.core.repository.RoleRepository;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.core.repository.UserRoleRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
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

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @Author: Malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class UserRoleRepositoryImplTest {

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRoleRepository")
    private UserRoleRepository userRoleRepository;

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepo;

    /**
     * Spring Data RoleEntity Repository
     */
    @Autowired
    @Qualifier("RoleRepository")
    private RoleRepository roleRepo;

    @Test
    public void testFindAll(){
        assertNotNull(userRoleRepository);
        List<UserRole> userRoles = userRoleRepository.findAll();
        assertNotNull(userRoles);
        assertThat(userRoles.size(), greaterThan(0));
    }

    @Test
    public void testFindById(){
        assertNotNull(userRoleRepository);
        UserRole userRole= userRoleRepository.findById(1L);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), equalTo(1L));
    }
}
