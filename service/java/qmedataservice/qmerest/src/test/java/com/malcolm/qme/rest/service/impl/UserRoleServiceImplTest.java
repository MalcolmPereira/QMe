/**
 * Name      : com.malcolm.qme.rest.service.impl.UserRoleServiceImplTest.java
 * Date      : 9/24/15
 * Developer : malcolm
 * Purpose   : User Role Service Implementation Test
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.UserRole;
import com.malcolm.qme.core.domain.fixtures.RoleFixtures;
import com.malcolm.qme.core.domain.fixtures.UserFixtures;
import com.malcolm.qme.core.domain.fixtures.UserRoleFixtures;
import com.malcolm.qme.core.repository.RoleRepository;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.core.repository.UserRoleRepository;
import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.model.QMeUserRole;
import com.malcolm.qme.rest.model.fixtures.QMeUserRoleFixtures;
import com.malcolm.qme.rest.service.UserRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class UserRoleServiceImplTest {

    @Mock
    private UserRoleRepository userRoleRepo;

    @Mock
    private RoleRepository roleRepo;

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private final UserRoleService userRoleService = new UserRoleServiceImpl();

    @Test
    public void testList() throws Exception {
        when(userRoleRepo.findAll()).thenReturn(UserRoleFixtures.simpleUserRoleList());

        List<QMeUserRole> userRoleList = userRoleService.list();

        verify(userRoleRepo).findAll();
        assertNotNull(userRoleList);
        assertThat(userRoleList.size(), equalTo(5));

        for (QMeUserRole qMeUserRole : userRoleList) {
            assertThat(qMeUserRole.getRoleID(), anyOf(
                            is(1),
                            is(2),
                            is(3),
                            is(4),
                            is(5)
            ));
            assertThat(qMeUserRole.getRoleName(), anyOf(
                    is("role name 1"),
                    is("role name 2"),
                    is("role name 3"),
                    is("role name 4"),
                    is("role name 5")
            ));
        }
    }

    @Test
    public void testFindByUserId() throws Exception {
        when(userRoleRepo.findByUserId(1L)).thenReturn(UserRoleFixtures.simpleUserRoleListForUser());

        List<QMeUserRole> userRoleList = userRoleService.findByUserId(1L);

        verify(userRoleRepo).findByUserId(1L);
        assertNotNull(userRoleList);
        assertThat(userRoleList.size(), equalTo(3));

        for (QMeUserRole qMeUserRole : userRoleList) {
            assertThat(qMeUserRole.getRoleID(), anyOf(
                            is(1),
                            is(2),
                            is(3)
            ));
            assertThat(qMeUserRole.getRoleName(), anyOf(
                    is("role name 1"),
                    is("role name 2"),
                    is("role name 3")
            ));
        }
    }

    @Test
    public void testFindByRoleId() throws Exception {
        when(userRoleRepo.findByRoleId(1)).thenReturn(UserRoleFixtures.simpleUserRoleListForRole());

        List<QMeUserRole> userRoleList = userRoleService.findByRoleId(1);

        verify(userRoleRepo).findByRoleId(1);
        assertNotNull(userRoleList);
        assertThat(userRoleList.size(), equalTo(3));

        for (QMeUserRole qMeUserRole : userRoleList) {
            assertThat(qMeUserRole.getRoleID(), anyOf(
                    is(1),
                    is(2),
                    is(3)
            ));
            assertThat(qMeUserRole.getRoleName(), anyOf(
                    is("role name 1"),
                    is("role name 2"),
                    is("role name 3")
            ));
        }
    }



    @Test
    public void testSearchById() throws Exception {
        when(userRoleRepo.findById(1L)).thenReturn(UserRoleFixtures.simpleUserRole());

        QMeUserRole userRole = userRoleService.searchById(1L);

        verify(userRoleRepo).findById(1L);
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), equalTo(1L));
        assertThat(userRole.getRoleID(), equalTo(1));
        assertThat(userRole.getRoleName(), equalTo("role name"));
    }

    @Test
    public void testSave() throws Exception {
        when(roleRepo.findById(1)).thenReturn(RoleFixtures.simpleRole());
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        when(userRoleRepo.findByUserId(1L)).thenReturn(UserRoleFixtures.simpleUserRoleListForUserDiff());
        when(userRoleRepo.save(Matchers.<UserRole>anyObject())).thenReturn(UserRoleFixtures.simpleUserRole());

        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();
        userRole = userRoleService.save(userRole,1L);

        verify(roleRepo).findById(1);
        verify(userRepo).findById(1L);
        verify(userRoleRepo).findByUserId(1L);
        verify(userRoleRepo).save(Matchers.<UserRole>anyObject());
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), equalTo(1L));
        assertThat(userRole.getRoleID(), equalTo(1));
        assertThat(userRole.getRoleName(), equalTo("role name"));
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidRoleId() throws Exception {
        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();
        userRole.setRoleID(null);
        userRoleService.save(userRole,1L);
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidUserId() throws Exception {
        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();
        userRole.setUserID(null);
        userRoleService.save(userRole,1L);
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testSaveNoRoleById() throws Exception {
        when(roleRepo.findById(1)).thenReturn(null);
        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();
        userRoleService.save(userRole, 1L);
        verify(roleRepo).findById(1);
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testSaveNoUserById() throws Exception {
        when(roleRepo.findById(1)).thenReturn(RoleFixtures.simpleRole());
        when(userRepo.findById(1L)).thenReturn(null);
        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();
        userRoleService.save(userRole, 1L);
        verify(roleRepo).findById(1);
        verify(userRepo).findById(1L);
    }

    @Test(expected = QMeResourceConflictException.class)
    public void testSaveSameRolesForUser() throws Exception {
        when(roleRepo.findById(1)).thenReturn(RoleFixtures.simpleRole());
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        when(userRoleRepo.findByUserId(1L)).thenReturn(UserRoleFixtures.simpleUserRoleListForUser());

        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();
        userRoleService.save(userRole,1L);
        verify(roleRepo).findById(1);
        verify(userRepo).findById(1L);
        verify(userRoleRepo).findByUserId(1L);
    }

    @Test
    public void testUpdate() throws Exception {
        when(roleRepo.findById(1)).thenReturn(RoleFixtures.simpleRole());
        when(userRepo.findById(1L)).thenReturn(UserFixtures.simpleUser());
        when(userRoleRepo.findByUserId(1L)).thenReturn(UserRoleFixtures.simpleUserRoleListForUserDiff());
        when(userRoleRepo.update(Matchers.<UserRole>anyObject(), eq(1L))).thenReturn(UserRoleFixtures.simpleUserRole());
        QMeUserRole userRole = QMeUserRoleFixtures.simpleQMeUserRole();

        userRole = userRoleService.update(userRole,1L,1L);

        verify(roleRepo).findById(1);
        verify(userRepo).findById(1L);
        verify(userRoleRepo).findByUserId(1L);
        verify(userRoleRepo).update(Matchers.<UserRole>anyObject(), eq(1L));
        assertNotNull(userRole);
        assertThat(userRole.getUserRoleID(), equalTo(1L));
        assertThat(userRole.getRoleID(), equalTo(1));
        assertThat(userRole.getRoleName(), equalTo("role name"));
    }

    @Test
    public void testDelete() throws Exception {
        when(userRoleRepo.findById(1L)).thenReturn(UserRoleFixtures.simpleUserRole());
        doNothing().when(userRoleRepo).delete(1L);
        userRoleService.delete(1L);
        verify(userRoleRepo).findById(1L);
        verify(userRoleRepo).delete(1L);
    }
}