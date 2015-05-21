/**
 * Name      : com.malcolm.qme.springdata.repository.UserRoleRepositoryImplTest.java
 * Date      : 5/11/15
 * Developer : Malcolm
 * Purpose   : QMe User Roles Repository Implementation Test Cases
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Role;
import com.malcolm.qme.core.domain.User;
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
import static org.junit.Assert.*;

/**
 * @author Malcolm
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

    @Test
    public void testCRUD(){

        assertNotNull(userRoleRepository);

        assertNotNull(userRepo);

        assertNotNull(roleRepo);

        User user = new User("RoleRepositoryImplTest", "Test", "Test", "Test","RoleRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));

        Long userID = user.getUserID();
        user = userRepo.findById(userID);
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(userID));

        Role role = new Role("RoleRepositoryImplTest","RoleRepositoryImplTest Desc");
        role = roleRepo.save(role);
        assertNotNull(role);
        assertThat(role.getRoleID(), greaterThan(0));

        Integer roleID = role.getRoleID();

        role = roleRepo.findById(roleID);
        assertNotNull(role);
        assertThat(role.getRoleID(), equalTo(roleID));

        UserRole userRole = new UserRole(roleID,userID);
        userRole = userRoleRepository.save(userRole);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), greaterThan(0L));

        Long userRoleID = userRole.getUserRoleID();

        userRole = userRoleRepository.findById(userRoleID);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), equalTo(userRoleID));

        userRoleRepository.delete(userRoleID);
        userRole = userRoleRepository.findById(userRoleID);
        assertNull(userRole);

        roleRepo.delete(roleID);
        role = roleRepo.findById(roleID);
        assertNull(role);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

    }

    @Test
    public void testFindByUserId(){

        assertNotNull(userRoleRepository);

        assertNotNull(userRepo);

        assertNotNull(roleRepo);

        User user = new User("RoleRepositoryImplTest ByUserID ", "Test", "Test", "Test","RoleRepositoryImplTestByUserID@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));

        Long userID = user.getUserID();
        user = userRepo.findById(userID);
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(userID));

        Role role = new Role("RoleRepositoryImplTest ByUserID","RoleRepositoryImplTest ByUserID Desc");
        role = roleRepo.save(role);
        assertNotNull(role);
        assertThat(role.getRoleID(), greaterThan(0));

        Integer roleID = role.getRoleID();

        role = roleRepo.findById(roleID);
        assertNotNull(role);
        assertThat(role.getRoleID(), equalTo(roleID));

        UserRole userRole = new UserRole(roleID,userID);
        userRole = userRoleRepository.save(userRole);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), greaterThan(0L));

        Long userRoleID = userRole.getUserRoleID();

        userRole = userRoleRepository.findById(userRoleID);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), equalTo(userRoleID));

        List<UserRole>  userRolesList = userRoleRepository.findByUserId(userID);
        assertNotNull(userRolesList);
        assertThat(userRolesList.size(), equalTo(1));

        userRoleRepository.delete(userRoleID);
        userRole = userRoleRepository.findById(userRoleID);
        assertNull(userRole);

        roleRepo.delete(roleID);
        role = roleRepo.findById(roleID);
        assertNull(role);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    }

    @Test
    public void testFindByRoleId(){

        assertNotNull(userRoleRepository);

        assertNotNull(userRepo);

        assertNotNull(roleRepo);

        User user = new User("RoleRepositoryImplTest ByRoleId ", "Test", "Test", "Test","RoleRepositoryImplTestByRoleId@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));

        Long userID = user.getUserID();
        user = userRepo.findById(userID);
        assertNotNull(user);
        assertThat(user.getUserID(), equalTo(userID));

        Role role = new Role("RoleRepositoryImplTest ByRoleId","RoleRepositoryImplTest ByRoleId Desc");
        role = roleRepo.save(role);
        assertNotNull(role);
        assertThat(role.getRoleID(), greaterThan(0));

        Integer roleID = role.getRoleID();

        role = roleRepo.findById(roleID);
        assertNotNull(role);
        assertThat(role.getRoleID(), equalTo(roleID));

        UserRole userRole = new UserRole(roleID,userID);
        userRole = userRoleRepository.save(userRole);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), greaterThan(0L));

        Long userRoleID = userRole.getUserRoleID();

        userRole = userRoleRepository.findById(userRoleID);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), equalTo(userRoleID));

        List<UserRole>  userRolesList = userRoleRepository.findByRoleId(roleID);
        assertNotNull(userRolesList);
        assertThat(userRolesList.size(), equalTo(1));

        userRoleRepository.delete(userRoleID);
        userRole = userRoleRepository.findById(userRoleID);
        assertNull(userRole);

        roleRepo.delete(roleID);
        role = roleRepo.findById(roleID);
        assertNull(role);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

    }
}
