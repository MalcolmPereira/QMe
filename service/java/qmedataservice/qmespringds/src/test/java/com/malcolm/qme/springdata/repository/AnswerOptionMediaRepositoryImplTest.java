/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerOptionMediaRepositoryImplTest.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : Tests for AnswerOptionMedia Repository
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

import com.malcolm.qme.core.domain.AnswerOption;
import com.malcolm.qme.core.domain.AnswerOptionMedia;
import com.malcolm.qme.core.domain.Question;
import com.malcolm.qme.core.repository.AnswerOptionMediaRepository;
import com.malcolm.qme.core.repository.AnswerOptionRepository;
import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;

/**
 * @author Malcolm
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class AnswerOptionMediaRepositoryImplTest {	
	
	/**
	 * Answer OptionMedia  Repository
	 */
	@Autowired
	@Qualifier("AnswerOptionMediaRepository")
	private AnswerOptionMediaRepository answerOptionMediaRepository;
	
	/**
	 * Answer Option Repository
	 */
	@Autowired
	@Qualifier("AnswerOptionRepository")
	private AnswerOptionRepository answerOptionRepository;

	/**
	 * Question Repository
	 */
	@Autowired
	@Qualifier("QuestionRepository")
	private QuestionRepository questionRepository;
	
	@Test
    public void testFetchAll(){
        assertNotNull(answerOptionMediaRepository);
        List<AnswerOptionMedia> answerOptionMediaList = answerOptionMediaRepository.findAll();
        assertNotNull(answerOptionMediaList);
        assertThat(answerOptionMediaList.size(), greaterThan(0));
    }

    @Test
    public void testFindById(){
        assertNotNull(answerOptionMediaRepository);
        final AnswerOptionMedia answerOptionMedia = answerOptionMediaRepository.findById(1L);
        assertNotNull(answerOptionMedia);
        assertThat(answerOptionMedia.getAnswerOptionMediaID(), equalTo(1L));
    }
    
    @Test
    public void testCRUD(){
    	
    	assertNotNull(answerOptionMediaRepository);
    	
    	assertNotNull(answerOptionRepository);

		assertNotNull(questionRepository);

		Question question = new Question(1L,"AnswerOptionMediaRepositoryImplTest Question","AnswerOptionMediaRepositoryImplTest Answer",1L);

		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		final Long questionID = question.getQuestionID();

		AnswerOption answerOption = new AnswerOption(questionID, "Option 1", false);
		answerOption = answerOptionRepository.save(answerOption);
		assertNotNull(answerOption);
		assertThat(answerOption.getAnswerOptionID(), greaterThan(0L));
		final Long answerOptionID  = answerOption.getAnswerOptionID();
		
		AnswerOptionMedia answerOptionMedia = new AnswerOptionMedia(answerOptionID,1,new String("testing").getBytes());
		answerOptionMedia = answerOptionMediaRepository.save(answerOptionMedia);
	    assertNotNull(answerOptionMedia);
	    assertThat(answerOptionMedia.getAnswerOptionMediaID(), greaterThan(0L));
	    final Long answerOptionMediaID  = answerOptionMedia.getAnswerOptionMediaID();
	    
	    answerOptionMedia = answerOptionMediaRepository.findById(answerOptionMediaID);
        assertNotNull(answerOptionMedia);
        assertThat(answerOptionMedia.getAnswerOptionMediaID(), equalTo(answerOptionMediaID));
        assertThat(new String(answerOptionMedia.getMedia()), equalTo("testing"));
		
        AnswerOptionMedia answerOptionMediaUpdates = new AnswerOptionMedia(
        		answerOptionMedia.getAnswerOptionMediaID(), 
        		answerOptionMedia.getAnswerOptionID(), 
        		answerOptionMedia.getMediaTypeID(),
        		new String("testing updates").getBytes());
        answerOptionMediaUpdates = answerOptionMediaRepository.save(answerOptionMediaUpdates);
	    assertNotNull(answerOptionMediaUpdates);
	    assertThat(answerOptionMediaUpdates.getAnswerOptionMediaID(), equalTo(answerOptionMediaID));
	    assertThat(new String(answerOptionMediaUpdates.getMedia()), equalTo("testing updates"));
	    
	    answerOptionMediaRepository.delete(answerOptionMediaID);
	    answerOptionMedia = answerOptionMediaRepository.findById(answerOptionMediaID);
	    assertNull(answerOptionMedia);
        
		answerOptionRepository.delete(answerOptionID);
		answerOption = answerOptionRepository.findById(answerOptionID);
		assertNull(answerOption);

		questionRepository.delete(questionID);
		question = questionRepository.findById(questionID);
		assertNull(question);
    	
    }
    
    @Test
    public void testFindByAnswerOptionId(){
    	
    	assertNotNull(answerOptionMediaRepository);
    	
    	assertNotNull(answerOptionRepository);

		assertNotNull(questionRepository);

		Question question = new Question(1L,"AnswerOptionMediaRepositoryImplTest Question","AnswerOptionMediaRepositoryImplTest Answer",1L);

		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		final Long questionID = question.getQuestionID();

		AnswerOption answerOption = new AnswerOption(questionID, "Option 1", false);
		answerOption = answerOptionRepository.save(answerOption);
		assertNotNull(answerOption);
		assertThat(answerOption.getAnswerOptionID(), greaterThan(0L));
		final Long answerOptionID  = answerOption.getAnswerOptionID();
		
		AnswerOptionMedia answerOptionMedia = new AnswerOptionMedia(answerOptionID,1,new String("testing").getBytes());
		answerOptionMedia = answerOptionMediaRepository.save(answerOptionMedia);
	    assertNotNull(answerOptionMedia);
	    assertThat(answerOptionMedia.getAnswerOptionMediaID(), greaterThan(0L));
	    final Long answerOptionMediaID  = answerOptionMedia.getAnswerOptionMediaID();
	    
	    answerOptionMedia = answerOptionMediaRepository.findById(answerOptionMediaID);
        assertNotNull(answerOptionMedia);
        assertThat(answerOptionMedia.getAnswerOptionMediaID(), equalTo(answerOptionMediaID));
        assertThat(new String(answerOptionMedia.getMedia()), equalTo("testing"));
		
        List<AnswerOptionMedia> answerOptionMediaList = answerOptionMediaRepository.findByAnswerOptionId(answerOptionID);
        assertNotNull(answerOptionMediaList);
        assertThat(answerOptionMediaList.size(), equalTo(1));
        assertThat(answerOptionMediaList.get(0).getAnswerOptionMediaID(), equalTo(answerOptionMediaID));
        assertThat(new String(answerOptionMediaList.get(0).getMedia()), equalTo("testing"));
        
        AnswerOptionMedia answerOptionMediaUpdates = new AnswerOptionMedia(
        		answerOptionMedia.getAnswerOptionMediaID(), 
        		answerOptionMedia.getAnswerOptionID(), 
        		answerOptionMedia.getMediaTypeID(),
        		new String("testing updates").getBytes());
        answerOptionMediaUpdates = answerOptionMediaRepository.save(answerOptionMediaUpdates);
	    assertNotNull(answerOptionMediaUpdates);
	    assertThat(answerOptionMediaUpdates.getAnswerOptionMediaID(), equalTo(answerOptionMediaID));
	    assertThat(new String(answerOptionMediaUpdates.getMedia()), equalTo("testing updates"));
	    
	    answerOptionMediaList = answerOptionMediaRepository.findByAnswerOptionId(answerOptionID);
        assertNotNull(answerOptionMediaList);
        assertThat(answerOptionMediaList.size(), equalTo(1));
        assertThat(answerOptionMediaList.get(0).getAnswerOptionMediaID(), equalTo(answerOptionMediaID));
        assertThat(new String(answerOptionMediaList.get(0).getMedia()), equalTo("testing updates"));
	    
	    answerOptionMediaRepository.delete(answerOptionMediaID);
	    answerOptionMedia = answerOptionMediaRepository.findById(answerOptionMediaID);
	    assertNull(answerOptionMedia);
        
		answerOptionRepository.delete(answerOptionID);
		answerOption = answerOptionRepository.findById(answerOptionID);
		assertNull(answerOption);

		questionRepository.delete(questionID);
		question = questionRepository.findById(questionID);
		assertNull(question);
    	
    }
    
}