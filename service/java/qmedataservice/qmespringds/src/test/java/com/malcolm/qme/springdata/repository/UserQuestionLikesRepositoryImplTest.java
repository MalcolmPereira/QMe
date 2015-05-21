/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuestionLikesRepositoryImplTest.java
 * Date      : 5/14/2015
 * Developer : Malcolm
 * Purpose   : Tests for User Question Likes Repository Implementation
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

import com.malcolm.qme.core.domain.Question;
import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserQuestionLikes;
import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.core.repository.UserQuestionLikesRepository;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;

/**
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class UserQuestionLikesRepositoryImplTest {
	
	 /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepo;
    
    /**
     * Question Repository
     */
    @Autowired
    @Qualifier("QuestionRepository")
    private QuestionRepository questionRepo;
    
    
    /**
     * Question Repository
     */
    @Autowired
    @Qualifier("UserQuestionLikesRepository")
    private UserQuestionLikesRepository userQuestionLikesRepo;
    
    @Test
    public void testFindAll(){
        assertNotNull(userQuestionLikesRepo);
        List<UserQuestionLikes> userQuestionLikesList = userQuestionLikesRepo.findAll();
        assertNotNull(userQuestionLikesList);
        assertThat(userQuestionLikesList.size(), greaterThan(0));
    }
    
    @Test
    public void testFindById(){
        assertNotNull(userQuestionLikesRepo);
        UserQuestionLikes userQuestionLikes = new UserQuestionLikes(1L,1L);
        userQuestionLikes = userQuestionLikesRepo.findById(userQuestionLikes);
        assertNotNull(userQuestionLikes);
        assertThat(userQuestionLikes.getUserID(), equalTo(1L));
        assertThat(userQuestionLikes.getQuestionID(), equalTo(1L));
    }

    @Test
    public void testCRUD(){
    	
    	assertNotNull(userQuestionLikesRepo);
    	
    	assertNotNull(userRepo);
    	
    	assertNotNull(questionRepo);
    	
    	User user = new User("UserQuestionLikesRepositoryImplTest", "Test", "Test", "Test","UserQuestionLikesRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));
        Long userID = user.getUserID();
         
        Question question = new Question(1L,"UserQuestionLikesRepositoryImplTest Question","UserQuestionLikesRepositoryImplTest Answer",1L);
    	question = questionRepo.save(question);
        assertNotNull(question);
        assertThat(question.getQuestionID(), greaterThan(0L));
        Long questionID = question.getQuestionID();
        
        UserQuestionLikes userQuestionLikes = new UserQuestionLikes(userID,questionID);
        userQuestionLikes = userQuestionLikesRepo.save(userQuestionLikes);
        assertNotNull(userQuestionLikes);
        assertThat(userQuestionLikes.getUserID(), equalTo(userID));
        assertThat(userQuestionLikes.getQuestionID(), equalTo(questionID)); 
        
        userQuestionLikes = userQuestionLikesRepo.findById(userQuestionLikes);
        assertNotNull(userQuestionLikes);
        assertThat(userQuestionLikes.getUserID(), equalTo(userID));
        assertThat(userQuestionLikes.getQuestionID(), equalTo(questionID));
        
        userQuestionLikesRepo.delete(userQuestionLikes);
        userQuestionLikes = userQuestionLikesRepo.findById(userQuestionLikes);
        assertNull(userQuestionLikes);
      
        questionRepo.delete(questionID);
        question = questionRepo.findById(questionID);
        assertNull(question);
        
        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    }
    
    @Test
    public void testFindByUserId(){
    	
    	assertNotNull(userQuestionLikesRepo);
    	
    	assertNotNull(userRepo);
    	
    	assertNotNull(questionRepo);
    	
    	User user = new User("UserQuestionLikesRepositoryImplTestByUser", "Test", "Test", "Test","UserQuestionLikesRepositoryImplTestByUser@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));
        Long userID = user.getUserID();
         
        Question question = new Question(1L,"UserQuestionLikesRepositoryImplTestByUser Question","UserQuestionLikesRepositoryImplTestByUser Answer",1L);
    	question = questionRepo.save(question);
        assertNotNull(question);
        assertThat(question.getQuestionID(), greaterThan(0L));
        Long questionID = question.getQuestionID();
        
        UserQuestionLikes userQuestionLikes = new UserQuestionLikes(userID,questionID);
        userQuestionLikes = userQuestionLikesRepo.save(userQuestionLikes);
        assertNotNull(userQuestionLikes);
        assertThat(userQuestionLikes.getUserID(), equalTo(userID));
        assertThat(userQuestionLikes.getQuestionID(), equalTo(questionID)); 
        
        userQuestionLikes = userQuestionLikesRepo.findById(userQuestionLikes);
        assertNotNull(userQuestionLikes);
        assertThat(userQuestionLikes.getUserID(), equalTo(userID));
        assertThat(userQuestionLikes.getQuestionID(), equalTo(questionID));
        
        List<UserQuestionLikes> userQuestionLikesList = userQuestionLikesRepo.findByUserId(userID);
        assertNotNull(userQuestionLikesList);
        assertThat(userQuestionLikesList.size(), equalTo(1));
        
        userQuestionLikesList = userQuestionLikesRepo.findByQuestionId(questionID);
        assertNotNull(userQuestionLikesList);
        assertThat(userQuestionLikesList.size(), equalTo(1));
        
        userQuestionLikesRepo.delete(userQuestionLikes);
        userQuestionLikes = userQuestionLikesRepo.findById(userQuestionLikes);
        assertNull(userQuestionLikes);
      
        questionRepo.delete(questionID);
        question = questionRepo.findById(questionID);
        assertNull(question);
        
        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    	
    }
    
    @Test
    public void testFindByQuestionId(){
    	
    	assertNotNull(userQuestionLikesRepo);
    	
    	assertNotNull(userRepo);
    	
    	assertNotNull(questionRepo);
    	
    	User user = new User("UserQuestionLikesRepositoryImplTestByQID", "Test", "Test", "Test","UserQuestionLikesRepositoryImplTestByQID@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));
        Long userID = user.getUserID();
         
        Question question = new Question(1L,"UserQuestionLikesRepositoryImplTestByQID Question","UserQuestionLikesRepositoryImplTestByQID Answer",1L);
    	question = questionRepo.save(question);
        assertNotNull(question);
        assertThat(question.getQuestionID(), greaterThan(0L));
        Long questionID = question.getQuestionID();
        
        UserQuestionLikes userQuestionLikes = new UserQuestionLikes(userID,questionID);
        userQuestionLikes = userQuestionLikesRepo.save(userQuestionLikes);
        assertNotNull(userQuestionLikes);
        assertThat(userQuestionLikes.getUserID(), equalTo(userID));
        assertThat(userQuestionLikes.getQuestionID(), equalTo(questionID)); 
        
        userQuestionLikes = userQuestionLikesRepo.findById(userQuestionLikes);
        assertNotNull(userQuestionLikes);
        assertThat(userQuestionLikes.getUserID(), equalTo(userID));
        assertThat(userQuestionLikes.getQuestionID(), equalTo(questionID));
        
        List<UserQuestionLikes> userQuestionLikesList = userQuestionLikesRepo.findByUserId(userID);
        assertNotNull(userQuestionLikesList);
        assertThat(userQuestionLikesList.size(), equalTo(1));
        
        userQuestionLikesList = userQuestionLikesRepo.findByQuestionId(questionID);
        assertNotNull(userQuestionLikesList);
        assertThat(userQuestionLikesList.size(), equalTo(1));
        
        userQuestionLikesRepo.delete(userQuestionLikes);
        userQuestionLikes = userQuestionLikesRepo.findById(userQuestionLikes);
        assertNull(userQuestionLikes);
      
        questionRepo.delete(questionID);
        question = questionRepo.findById(questionID);
        assertNull(question);
        
        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    	
    }
    
}
