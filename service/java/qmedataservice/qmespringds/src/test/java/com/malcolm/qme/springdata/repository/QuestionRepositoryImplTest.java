/**
 * Name      : com.malcolm.qme.springdata.repository.QuestionRepositoryImplTest.java
 * Date      : 5/14/15
 * Developer : Malcolm
 * Purpose   : Tests for Question Repository
 */
package com.malcolm.qme.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.domain.Question;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;

/**
 * @Author: malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class QuestionRepositoryImplTest {

	/**
     * Question Repository
     */
    @Autowired
    @Qualifier("QuestionRepository")
    private QuestionRepository questionRepository;
    
    /**
     * Category Repository
     */
    @Autowired
    @Qualifier("CategoryRepository")
    private CategoryRepository categoryRepo;
    
    @Test
    public void testFindAll(){
        assertNotNull(questionRepository);
        List<Question> questionList = questionRepository.findAll();
        assertNotNull(questionList);
        assertThat(questionList.size(), greaterThan(0));
    }
	
    @Test
    public void testFindById(){
        assertNotNull(questionRepository);
        Question question = questionRepository.findById(1L);
        assertNotNull(question);
        assertThat(question.getQuestionID(), equalTo(1L));
    }
    
    @Test
    public void testCRUD(){
    	 assertNotNull(questionRepository);
    	 
    	 Question question = new Question(1L,"QuestionRepositoryImplTest Question","QuestionRepositoryImplTest Answer",1L);
    	 
    	 question = questionRepository.save(question);
         assertNotNull(question);
         assertThat(question.getQuestionID(), greaterThan(0L));
         Long questionID = question.getQuestionID();
         
         question = questionRepository.findById(questionID);
         assertNotNull(question);
         assertThat(question.getQuestionID(), equalTo(questionID));
         
         Question questionUpdates = new Question(
        		 question.getQuestionID(), 
        		 question.getCategoryID(),
        		 "QuestionRepositoryImplTest Question Update",
        		 "QuestionRepositoryImplTest Answer Update",
        		 question.getLikes(),
        		 question.getQuestionCreateDate(),
        		 question.getCreateUserID(),
        		 question.getQuestionUpdateDate(),
        		 question.getUpdateUserID());
         
         question = questionRepository.save(questionUpdates);
         question = questionRepository.findById(questionID);
         assertNotNull(question);
         assertThat(question.getQuestionID(), equalTo(questionID));
         assertThat(question.getQuestionText(), equalTo("QuestionRepositoryImplTest Question Update"));
         assertThat(question.getAnswer(), equalTo("QuestionRepositoryImplTest Answer Update"));
         
         questionRepository.delete(questionID);
         question = questionRepository.findById(questionID);
         assertNull(question);
    }
    
	@Test
	public void testFindByCatId() {

		assertNotNull(questionRepository);
		
		assertNotNull(categoryRepo);
		
		Category category = new Category("QuestionRepositoryImplTest Name", 1L);
        category = categoryRepo.save(category);
        assertNotNull(category);
        assertThat(category.getCategoryID(), greaterThan(0L));

        Long catID = category.getCategoryID();
		
		Question question = new Question(catID, "QuestionRepositoryImplTest Question1", "QuestionRepositoryImplTest Answer", 1L);
		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		Long question1ID = question.getQuestionID();
		
		question = new Question(catID, "QuestionRepositoryImplTest Question2", "QuestionRepositoryImplTest Answer", 1L);
		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		Long question2ID = question.getQuestionID();
		
		question = new Question(catID, "QuestionRepositoryImplTest Question3", "QuestionRepositoryImplTest Answer", 1L);
		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		Long question3ID = question.getQuestionID();
		
		
		List<Question> questionList = questionRepository.findByCategoryId(catID);
        assertNotNull(questionList);
        assertThat(questionList.size(), equalTo(3));
        for (Question questionEntity : questionList) {
        	assertThat(questionEntity.getCategoryID(), equalTo(catID));
        }
		
		questionRepository.delete(question1ID);
		question = questionRepository.findById(question1ID);
		assertNull(question);
		
		questionRepository.delete(question2ID);
		question = questionRepository.findById(question2ID);
		assertNull(question);
		
		questionRepository.delete(question3ID);
		question = questionRepository.findById(question3ID);
		assertNull(question);
		
		 categoryRepo.delete(catID);
         category = categoryRepo.findById(catID);
         assertNull(category);
	}
	
	@Test
	public void findByMostLiked() {
		
		assertNotNull(questionRepository);
		
		assertNotNull(categoryRepo);
		
		Category category = new Category("QuestionRepositoryImplTest Name", 1L);
        category = categoryRepo.save(category);
        assertNotNull(category);
        assertThat(category.getCategoryID(), greaterThan(0L));

        Long catID = category.getCategoryID();
		
		Question question = new Question(catID, "QuestionRepositoryImplTest Question1", "QuestionRepositoryImplTest Answer", 1L);
		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		Long question1ID = question.getQuestionID();
		for(int i = 0 ; i < 100 ; i ++){
			Question questionUpdates =  null;
			
			if(question.getLikes() == null){
				questionUpdates = new Question(
		        		 question.getQuestionID(), 
		        		 question.getCategoryID(),
		        		 question.getQuestionText(),
		        		 question.getAnswer(),
		        		 1L,
		        		 question.getQuestionCreateDate(),
		        		 question.getCreateUserID(),
		        		 question.getQuestionUpdateDate(),
		        		 question.getUpdateUserID());
				
				
        	}else{
        		questionUpdates = new Question(
		        		 question.getQuestionID(), 
		        		 question.getCategoryID(),
		        		 question.getQuestionText(),
		        		 question.getAnswer(),
		        		 question.getLikes() + 1,
		        		 question.getQuestionCreateDate(),
		        		 question.getCreateUserID(),
		        		 question.getQuestionUpdateDate(),
		        		 question.getUpdateUserID());
				
        	}
			question = questionRepository.save(questionUpdates);
        }
		
		question = new Question(catID, "QuestionRepositoryImplTest Question2", "QuestionRepositoryImplTest Answer", 1L);
		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		Long question2ID = question.getQuestionID();
		for(int i = 0 ; i < 10  ; i ++){
			Question questionUpdates =  null;
			
			if(question.getLikes() == null){
				questionUpdates = new Question(
		        		 question.getQuestionID(), 
		        		 question.getCategoryID(),
		        		 question.getQuestionText(),
		        		 question.getAnswer(),
		        		 1L,
		        		 question.getQuestionCreateDate(),
		        		 question.getCreateUserID(),
		        		 question.getQuestionUpdateDate(),
		        		 question.getUpdateUserID());
				
				
        	}else{
        		questionUpdates = new Question(
		        		 question.getQuestionID(), 
		        		 question.getCategoryID(),
		        		 question.getQuestionText(),
		        		 question.getAnswer(),
		        		 question.getLikes() + 1,
		        		 question.getQuestionCreateDate(),
		        		 question.getCreateUserID(),
		        		 question.getQuestionUpdateDate(),
		        		 question.getUpdateUserID());
				
        	}
			question = questionRepository.save(questionUpdates);
        }
		
		question = new Question(catID, "QuestionRepositoryImplTest Question3", "QuestionRepositoryImplTest Answer", 1L);
		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		Long question3ID = question.getQuestionID();
		
		List<Question> questionList = questionRepository.findByCategoryId(catID);
        assertNotNull(questionList);
        assertThat(questionList.size(), equalTo(3));
        for (Question questionEntity : questionList) {
        	assertThat(questionEntity.getCategoryID(), equalTo(catID));
        }
        
        questionList = questionRepository.findByMostLiked();
        assertNotNull(questionList);
        assertThat(questionList.size(), greaterThan(0));
        assertThat((questionList.get(0)).getCategoryID(),equalTo(catID));
        assertThat((questionList.get(0)).getQuestionText(),equalTo("QuestionRepositoryImplTest Question1"));
		
		questionRepository.delete(question1ID);
		question = questionRepository.findById(question1ID);
		assertNull(question);
		
		questionRepository.delete(question2ID);
		question = questionRepository.findById(question2ID);
		assertNull(question);
		
		questionRepository.delete(question3ID);
		question = questionRepository.findById(question3ID);
		assertNull(question);
		
		 categoryRepo.delete(catID);
         category = categoryRepo.findById(catID);
         assertNull(category);
		
	}
}
