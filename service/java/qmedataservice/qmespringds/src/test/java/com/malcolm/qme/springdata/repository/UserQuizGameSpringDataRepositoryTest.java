/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizGameSpringDataRepositoryTest.java
 * Date      : 5/18/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA UserQuizGameEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.UserQuizGameEntity;
import com.malcolm.qme.springdata.entity.UserQuizGameEntityId;

/**
 * @Author: malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class UserQuizGameSpringDataRepositoryTest {
	/**
     * UserEntity Repository
     */
    @Autowired
    private UserSpringDataRepository userSpringDataRepo;
    
    /**
     * UserQuizGameEntity Repository
     */
    @Autowired
    private UserQuizGameSpringDataRepository userQuizGameSpringDataRepository;
    
    @Test
    public void testFindAll(){
        assertNotNull(userQuizGameSpringDataRepository);
        List<UserQuizGameEntity> userQuizGameEntities = userQuizGameSpringDataRepository.findAll();
        assertNotNull(userQuizGameEntities);
        assertThat(userQuizGameEntities.size(), greaterThan(0));
    }
    
    @Test
    public void testFindById(){
        assertNotNull(userQuizGameSpringDataRepository);
        UserQuizGameEntityId id = new UserQuizGameEntityId();
        id.setUserId(1L);
        id.setCatId(1L);
        id.setQuizGameToken(1L);
        UserQuizGameEntity userQuizGameEntity = userQuizGameSpringDataRepository.findOne(id);
        assertNotNull(userQuizGameEntity);
        assertThat(userQuizGameEntity.getId().getUserId(), equalTo(1L));
        assertThat(userQuizGameEntity.getId().getCatId(), equalTo(1L));
        assertThat(userQuizGameEntity.getId().getQuizGameToken(), equalTo(1L));
    }
}
