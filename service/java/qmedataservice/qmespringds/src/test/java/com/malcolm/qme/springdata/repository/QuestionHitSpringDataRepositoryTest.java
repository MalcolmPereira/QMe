/**
 * Name      : com.malcolm.qme.springdata.repository.QuestionHitSpringDataRepositoryTest.java
 * Date      : 5/14/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA QuestionHitEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Date;
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
import com.malcolm.qme.springdata.entity.CategoryEntity;
import com.malcolm.qme.springdata.entity.QuestionEntity;
import com.malcolm.qme.springdata.entity.QuestionHitEntity;

/**
 * @Author: malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class QuestionHitSpringDataRepositoryTest {
	
	/**
     * QuestionHitEntity Repository
     */
    @Autowired
    private QuestionHitSpringDataRepository questionHitSpringDataRepo;
    
    /**
     * QuestionEntity Repository
     */
    @Autowired
    private QuestionSpringDataRepository questionSpringDataRepository;
    
    /**
     * CategoryEntity Repository
     */
    @Autowired
    private CategorySpringDataRepository categorySpringDataRepository;
    
    @Test
    public void testFindAll(){
        assertNotNull(questionHitSpringDataRepo);
        List<QuestionHitEntity> questionHitEntities = questionHitSpringDataRepo.findAll();
        assertNotNull(questionHitEntities);
        assertThat(questionHitEntities.size(), greaterThan(0));
    }
    
    @Test
    public void testFindById(){
        assertNotNull(questionHitSpringDataRepo);
        QuestionHitEntity questionHitEntity = questionHitSpringDataRepo.findOne(1L);
        assertNotNull(questionHitEntity);
        assertThat(questionHitEntity.getQuestionId(), equalTo(1L));
        assertThat(questionHitEntity.getCatId(), equalTo(1L));
    }
    
    @Test
    public void testCRUD(){
    	
    	assertNotNull(questionHitSpringDataRepo);
    	
    	assertNotNull(categorySpringDataRepository);
    	
    	assertNotNull(questionSpringDataRepository);
    	
    	CategoryEntity categoryEntity = new CategoryEntity("QuestionHitSpringDataRepositoryTest", 0L, new Date(), 1L);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), greaterThan(0L));
        Long catID = categoryEntity.getCatId();
        
        QuestionEntity questionEntity = new QuestionEntity(catID,"QuestionHitSpringDataRepositoryTest Question","QuestionHitSpringDataRepositoryTest Answer",0L,new Date(),1L,new Date(),1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        Long questionID = questionEntity.getQuestionId();
        
        QuestionHitEntity questionHitEntity = new QuestionHitEntity(questionID,catID,0L,0L,0L);
        questionHitEntity = questionHitSpringDataRepo.save(questionHitEntity);
        assertNotNull(questionHitEntity);
        assertThat(questionHitEntity.getQuestionId(), equalTo(questionID));
        assertThat(questionHitEntity.getCatId(), equalTo(catID));
        
        questionHitEntity = questionHitSpringDataRepo.findOne(questionID);
        assertNotNull(questionHitEntity);
        assertThat(questionHitEntity.getQuestionId(), equalTo(questionID));
        assertThat(questionHitEntity.getCatId(), equalTo(catID));
        
        questionHitEntity.setQuestionHit(1L);
        questionHitEntity.setRightCount(1L);
        questionHitEntity = questionHitSpringDataRepo.save(questionHitEntity);
        assertNotNull(questionHitEntity);
        assertThat(questionHitEntity.getQuestionId(), equalTo(questionID));
        assertThat(questionHitEntity.getCatId(), equalTo(catID));
        assertThat(questionHitEntity.getQuestionHit(), equalTo(1L));
        assertThat(questionHitEntity.getRightCount(), equalTo(1L));
        
        questionHitSpringDataRepo.delete(questionID);
        questionHitEntity = questionHitSpringDataRepo.findOne(questionID);
        assertNull(questionHitEntity);
        
        questionSpringDataRepository.delete(questionID);
        questionEntity = questionSpringDataRepository.findOne(questionID);
        assertNull(questionEntity);
        
        categorySpringDataRepository.delete(catID);
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNull(categoryEntity);
        
    }
    
}
