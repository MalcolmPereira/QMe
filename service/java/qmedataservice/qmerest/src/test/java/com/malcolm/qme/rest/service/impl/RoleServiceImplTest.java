/**
 * Name      : com.malcolm.qme.rest.service.impl.RoleServiceImplTest.java
 * Date      : 9/23/15
 * Developer : malcolm
 * Purpose   : Role Service Implementation Test
 */
package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.Role;
import com.malcolm.qme.core.domain.fixtures.RoleFixtures;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.RoleRepository;
import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeRole;
import com.malcolm.qme.rest.model.fixtures.QMeRoleFixtures;
import com.malcolm.qme.rest.service.RoleService;
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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

/**
 * @author malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class RoleServiceImplTest {

    @Mock
    private RoleRepository roleRepo;

    @InjectMocks
    private final RoleService roleService = new RoleServiceImpl();

    @Test
    public void testList() throws Exception {
        when(roleRepo.findAll()).thenReturn(RoleFixtures.simpleRoleList());

        List<QMeRole> roleList = roleService.list();
        verify(roleRepo).findAll();
        assertNotNull(roleList);
        assertThat(roleList.size(), equalTo(5));
        for (QMeRole qMeRole : roleList) {
            assertThat(qMeRole.getRoleID(), anyOf(
                            is(1),
                            is(2),
                            is(3),
                            is(4),
                            is(5))
            );
            assertThat(qMeRole.getRoleName(), anyOf(
                    is("role name 1"),
                    is("role name 2"),
                    is("role name 3"),
                    is("role name 4"),
                    is("role name 5")
            ));

        }
    }

    @Test(expected = QMeServerException.class)
    public void testListQMeException() throws Exception {
        when(roleRepo.findAll()).thenThrow(QMeException.class);
        roleService.list();
    }

    @Test
    public void testSearchById() throws Exception {
        when(roleRepo.findById(1)).thenReturn(RoleFixtures.simpleRole());
        QMeRole qMeRole = roleService.searchById(1);
        verify(roleRepo).findById(1);
        assertNotNull(qMeRole);
        assertThat(qMeRole.getRoleID(), equalTo(1));
        assertThat(qMeRole.getRoleName(), equalTo("role name"));
        assertThat(qMeRole.getRoleDesc(), equalTo("role desc"));
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testSearchByIdNotFound() throws Exception {
        when(roleRepo.findById(1)).thenReturn(null);
        roleService.searchById(1);
        verify(roleRepo).findById(1);
    }

    @Test(expected = QMeServerException.class)
    public void testSearchByIdQMeException() throws Exception {
        when(roleRepo.findById(1)).thenThrow(QMeException.class);
        roleService.searchById(1);
        verify(roleRepo).findById(1);
    }

    @Test
    public void testFindByRoleName() throws Exception {
        when(roleRepo.findByRoleName("role name")).thenReturn(RoleFixtures.simpleRole());
        QMeRole qMeRole = roleService.findByRoleName("role name");
        verify(roleRepo).findByRoleName("role name");
        assertNotNull(qMeRole);
        assertThat(qMeRole.getRoleID(), equalTo(1));
        assertThat(qMeRole.getRoleName(), equalTo("role name"));
        assertThat(qMeRole.getRoleDesc(), equalTo("role desc"));
    }

    @Test(expected = QMeResourceNotFoundException.class)
    public void testFindByRoleNameNotFound() throws Exception {
        when(roleRepo.findByRoleName("role name")).thenReturn(null);
        roleService.findByRoleName("role name");
        verify(roleRepo).findByRoleName("role name");
    }

    @Test(expected = QMeServerException.class)
    public void testFindByRoleQMeException() throws Exception {
        when(roleRepo.findByRoleName("role name")).thenThrow(QMeException.class);
        roleService.findByRoleName("role name");
        verify(roleRepo).findByRoleName("role name");
    }

    @Test
    public void testSave() throws Exception {
        when(roleRepo.findByRoleName("role name")).thenReturn(null);
        when(roleRepo.save(Matchers.<Role>anyObject())).thenReturn(RoleFixtures.simpleRole());
        QMeRole qMeRole = QMeRoleFixtures.simpleQMeRole();
        qMeRole = roleService.save(qMeRole,1L);
        verify(roleRepo).findByRoleName("role name");
        verify(roleRepo).save(Matchers.<Role>anyObject());
        assertNotNull(qMeRole);
        assertThat(qMeRole.getRoleID(), equalTo(1));
        assertThat(qMeRole.getRoleName(), equalTo("role name"));
        assertThat(qMeRole.getRoleDesc(), equalTo("role desc"));
    }

    @Test(expected = QMeInvalidResourceDataException.class)
    public void testSaveInvalidRoleName() throws Exception {
        QMeRole qMeRole = QMeRoleFixtures.simpleQMeRole();
        qMeRole.setRoleName(null);
        roleService.save(qMeRole, 1L);
    }

    @Test(expected = QMeResourceConflictException.class)
    public void testSaveRoleExists() throws Exception {
        when(roleRepo.findByRoleName("role name")).thenReturn(RoleFixtures.simpleRole());
        QMeRole qMeRole = QMeRoleFixtures.simpleQMeRole();
        roleService.save(qMeRole, 1L);
        verify(roleRepo).findByRoleName("role name");
    }

    @Test(expected = QMeServerException.class)
    public void testSaveRoleQMeException() throws Exception {
        when(roleRepo.findByRoleName("role name")).thenThrow(QMeException.class);
        QMeRole qMeRole = QMeRoleFixtures.simpleQMeRole();
        roleService.save(qMeRole, 1L);
        verify(roleRepo).findByRoleName("role name");
    }

    @Test
    public void testUpdate() throws Exception {
        when(roleRepo.findByRoleName("role name")).thenReturn(null);
        when(roleRepo.update(Matchers.<Role>anyObject(), eq(1L))).thenReturn(RoleFixtures.simpleRole());
        QMeRole qMeRole = QMeRoleFixtures.simpleQMeRole();
        qMeRole = roleService.update(qMeRole,1,1L);
        verify(roleRepo).findByRoleName("role name");
        verify(roleRepo).update(Matchers.<Role>anyObject(), eq(1L));
        assertNotNull(qMeRole);
        assertThat(qMeRole.getRoleID(), equalTo(1));
        assertThat(qMeRole.getRoleName(), equalTo("role name"));
        assertThat(qMeRole.getRoleDesc(), equalTo("role desc"));
    }

    @Test (expected = QMeInvalidResourceDataException.class)
    public void testUpdateInvalidData() throws Exception {
        QMeRole qMeRole = QMeRoleFixtures.simpleQMeRole();
        qMeRole.setRoleName("");
        roleService.update(qMeRole, 1,1L);
    }

    @Test (expected = QMeResourceConflictException.class)
      public void testUpdateConflict() throws Exception {
        when(roleRepo.findByRoleName("role name")).thenReturn(RoleFixtures.simpleRole());
        QMeRole qMeRole = QMeRoleFixtures.simpleQMeRole();
        roleService.update(qMeRole,1,1L);
        verify(roleRepo).findByRoleName("role name");
    }

    @Test
    public void testDelete() throws Exception {
        when(roleRepo.findById(1)).thenReturn(RoleFixtures.simpleRole());
        doNothing().when(roleRepo).delete(1);
        roleService.delete(1);
        verify(roleRepo).findById(1);
        verify(roleRepo).delete(1);
    }
}