/**
 * Name      : com.malcolm.qme.rest.service.UserServiceImplTest.java
 * Date      : 5/27/2015
 * Developer : Malcolm
 * Purpose   : Test Cases for User Service Implementation
 **/
package com.malcolm.qme.rest.service;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.fixtures.UserFixtures;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.model.QMeUser;
import com.malcolm.qme.rest.model.QMeUserDetail;
import com.malcolm.qme.rest.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * @author Malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepo;

    @Mock
    public PasswordEncoder passwordEncoder;

    @InjectMocks
    private final UserService userService = new UserServiceImpl();

    @Test
    public void testList() throws QMeResourceException {
        when(userRepo.findAll()).thenReturn((List) UserFixtures.simpleUserList());

        List<QMeUserDetail> userList = userService.list();

        assertNotNull(userList);
        assertThat(userList.size(),equalTo(5));

        for (QMeUserDetail qmeUserDetail : userList) {
            assertThat(qmeUserDetail.getUserID(), anyOf(
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
    public void testSearchById() throws QMeResourceException {

        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());

        QMeUserDetail userDetail = userService.searchById(1L);

        assertNotNull(userDetail);

        assertThat(userDetail.getUserID(), equalTo(1L));
        assertThat(userDetail.getUserName(), equalTo("suser1"));
    }

    @Test
    public void testSearchByUser() throws QMeResourceException {

        when(userRepo.findByUserName("suser1")).thenReturn(UserFixtures.simpleUser());

        QMeUserDetail userDetail = userService.searchByUser("suser1");

        assertNotNull(userDetail);

        assertThat(userDetail.getUserID(), equalTo(1L));
        assertThat(userDetail.getUserName(), equalTo("suser1"));
    }


    @Test
    public void testSearchByEmail() throws QMeResourceException {

        when(userRepo.findByUserEmail("SimpleUser1@User.com")).thenReturn(UserFixtures.simpleUser());

        QMeUserDetail userDetail = userService.searchByEmail("SimpleUser1@User.com");

        assertNotNull(userDetail);

        assertThat(userDetail.getUserID(), equalTo(1L));
        assertThat(userDetail.getUserName(), equalTo("suser1"));
    }

    @Test
    public void testCreate() throws QMeResourceException {
        when(userRepo.save(Matchers.<User>anyObject())).thenReturn(UserFixtures.simpleUser());
        when(passwordEncoder.encode(Matchers.<String>anyObject())).thenReturn("someencodedvalue");

        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser6");
        qmeUser.setUserPassword("spassword6");
        qmeUser.setUserFirstName("Simple 6");
        qmeUser.setUserLastName("Simple User 6");
        qmeUser.setUserEmail("SimpleUser6@User.com");

        QMeUserDetail userDetail = userService.save(qmeUser,1L);

        assertNotNull(userDetail);

        assertThat(userDetail.getUserID(), equalTo(1L));
        assertThat(userDetail.getUserName(), equalTo("suser1"));
    }

    @Test
    public void testUpdate() throws QMeResourceException {
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        when(userRepo.update(Matchers.<User>anyObject(), eq(1L))).thenReturn(UserFixtures.simpleUser());

        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser1");
        qmeUser.setUserPassword("spassword1");
        qmeUser.setUserFirstName("Simple 1");
        qmeUser.setUserLastName("Simple User 1");
        qmeUser.setUserEmail("SimpleUser1@User.com");

        QMeUserDetail userDetail = userService.update(qmeUser, 1L, 1L);

        assertNotNull(userDetail);

        assertThat(userDetail.getUserID(), equalTo(1L));
        assertThat(userDetail.getUserName(), equalTo("suser1"));
    }

    @Test
    public void testDelete() throws QMeResourceException {
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        doNothing().when(userRepo).delete(1L);
        userService.delete(1L);
    }
}
