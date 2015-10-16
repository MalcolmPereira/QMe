/**
 * Name      : com.malcolm.qme.springdata.repository.java
 * Date      : 10/16/15
 * Developer : Malcolm
 * Purpose   : Simple User Generator
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserRole;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.core.repository.UserRoleRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class UserGeneratorUtil {
    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepo;

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRoleRepository")
    private UserRoleRepository userRoleRepository;

    /**
     * Method to generate test users in the system.
     * Please comment @Ignore to run test and uncomment back
     * after user generation is completed
     * @throws QMeException
     */
    @Ignore("Please comment this line if new users need to be generated")
    @Test
    public void generateUsers() throws QMeException {
        int MAX_USERS_TO_GENERATE = 250;

        int counter = 1;
        while(counter < 250) {
            User user = new User("generatedUser" + counter, "$2a$10$81/yJKsljVeq27wObdbvpeQ6bZUc6KbFS6h7dAk1CnE.Jz3FbZxSK", "Generated", "User" + counter, "generated" + counter + "@user.com");
            user = userRepo.save(user);
            assertNotNull(user);
            assertThat(user.getUserID(), greaterThan(0L));
            long userId = user.getUserID();

            //1 - User Role
            UserRole userRole = new UserRole(1, userId);
            userRole = userRoleRepository.save(userRole);
            assertNotNull(userRole);
            assertThat(userRole.getUserRoleID(), greaterThan(0L));
            counter++;
        }
        counter = 1;
        while(counter < 10) {
            User user = new User("AGeneratedAdmin" + counter, "$2a$10$81/yJKsljVeq27wObdbvpeQ6bZUc6KbFS6h7dAk1CnE.Jz3FbZxSK", "AGenerated", "Admin" + counter, "agenerated" + counter + "@admin.com");
            user = userRepo.save(user);
            assertNotNull(user);
            assertThat(user.getUserID(), greaterThan(0L));
            long userId = user.getUserID();

            //1 - User Role
            UserRole userRole = new UserRole(1, userId);
            userRole = userRoleRepository.save(userRole);
            assertNotNull(userRole);
            assertThat(userRole.getUserRoleID(), greaterThan(0L));

            //2 - Reviewer Role
            userRole = new UserRole(2, userId);
            userRole = userRoleRepository.save(userRole);
            assertNotNull(userRole);
            assertThat(userRole.getUserRoleID(), greaterThan(0L));

            //3 - Moderator Role
            userRole = new UserRole(3, userId);
            userRole = userRoleRepository.save(userRole);
            assertNotNull(userRole);
            assertThat(userRole.getUserRoleID(), greaterThan(0L));

            //4 - Admin Role
            userRole = new UserRole(4, userId);
            userRole = userRoleRepository.save(userRole);
            assertNotNull(userRole);
            assertThat(userRole.getUserRoleID(), greaterThan(0L));

            //5 - Read Role
            userRole = new UserRole(4, userId);
            userRole = userRoleRepository.save(userRole);
            assertNotNull(userRole);
            assertThat(userRole.getUserRoleID(), greaterThan(0L));

            counter++;
        }
    }
}
