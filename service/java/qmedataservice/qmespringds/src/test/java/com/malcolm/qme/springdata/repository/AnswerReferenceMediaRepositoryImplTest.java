/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerReferenceMediaRepositoryImplTest.java
 * Date      : May 16, 2015
 * Developer : Malcolm
 * Purpose   : Tests for Answer Reference Repository Implementation
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

import com.malcolm.qme.core.domain.AnswerReferenceMedia;
import com.malcolm.qme.core.domain.Question;
import com.malcolm.qme.core.repository.AnswerReferenceMediaRepository;
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
public class AnswerReferenceMediaRepositoryImplTest {

	/**
	 * Answer Reference Media  Repository
	 */
	@Autowired
	@Qualifier("AnswerReferenceMediaRepository")
	private AnswerReferenceMediaRepository answerReferenceMediaRepository;


	/**
	 * Question Repository
	 */
	@Autowired
	@Qualifier("QuestionRepository")
	private QuestionRepository questionRepository;

	@Test
	public void testFindAll(){
		assertNotNull(answerReferenceMediaRepository);
		final List<AnswerReferenceMedia> answerReferenceMediaList = answerReferenceMediaRepository.findAll();
		assertNotNull(answerReferenceMediaList);
		assertThat(answerReferenceMediaList.size(), greaterThan(0));
	}

	@Test
	public void testFindById(){
		assertNotNull(answerReferenceMediaRepository);
		final AnswerReferenceMedia answerReferenceMedia = answerReferenceMediaRepository.findById(1L);
		assertNotNull(answerReferenceMedia);
		assertThat(answerReferenceMedia.getAnswerRefMediaID(), equalTo(1L));
	}

	@Test
	public void testCRUD() {

		assertNotNull(answerReferenceMediaRepository);

		assertNotNull(questionRepository);

		Question question = new Question(1L,"AnswerReferenceMediaRepositoryImplTest Question","AnswerReferenceMediaRepositoryImplTest Answer", 1L);

		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		final Long questionID = question.getQuestionID();


		AnswerReferenceMedia answerReferenceMedia = new AnswerReferenceMedia(questionID, 1,new String("Testing").getBytes());
		answerReferenceMedia = answerReferenceMediaRepository.save(answerReferenceMedia);
		assertNotNull(answerReferenceMedia);
		assertThat(answerReferenceMedia.getAnswerRefMediaID(), greaterThan(0L));
		final Long answerReferenceMediaID =  answerReferenceMedia.getAnswerRefMediaID();

		answerReferenceMedia = answerReferenceMediaRepository.findById(answerReferenceMediaID);
		assertNotNull(answerReferenceMedia);
		assertThat(answerReferenceMedia.getAnswerRefMediaID(), equalTo(answerReferenceMediaID));

		AnswerReferenceMedia answerReferenceMediaUpdate = new AnswerReferenceMedia(
				answerReferenceMedia.getAnswerRefMediaID(),
				questionID,
				1,new String("TestingNEW").getBytes());
		answerReferenceMediaUpdate = answerReferenceMediaRepository.save(answerReferenceMediaUpdate);
		assertNotNull(answerReferenceMediaUpdate);
		assertThat(answerReferenceMediaUpdate.getAnswerRefMediaID(), equalTo(answerReferenceMediaID));
		assertThat(new String(answerReferenceMediaUpdate.getMedia()), equalTo("TestingNEW"));


		answerReferenceMediaRepository.delete(answerReferenceMediaID);
		answerReferenceMedia = answerReferenceMediaRepository.findById(answerReferenceMediaID);
		assertNull(answerReferenceMedia);

		questionRepository.delete(questionID);
		question = questionRepository.findById(questionID);
		assertNull(question);
	}

	@Test
	public void testFindByQuestionId() {

		assertNotNull(answerReferenceMediaRepository);

		assertNotNull(questionRepository);

		Question question = new Question(1L,"AnswerReferenceMediaRepositoryImplTest Question","AnswerReferenceMediaRepositoryImplTest Answer", 1L);

		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		final Long questionID = question.getQuestionID();


		AnswerReferenceMedia answerReferenceMedia = new AnswerReferenceMedia(questionID, 1,new String("Testing").getBytes());
		answerReferenceMedia = answerReferenceMediaRepository.save(answerReferenceMedia);
		assertNotNull(answerReferenceMedia);
		assertThat(answerReferenceMedia.getAnswerRefMediaID(), greaterThan(0L));
		final Long answerReferenceMediaID =  answerReferenceMedia.getAnswerRefMediaID();

		answerReferenceMedia = answerReferenceMediaRepository.findById(answerReferenceMediaID);
		assertNotNull(answerReferenceMedia);
		assertThat(answerReferenceMedia.getAnswerRefMediaID(), equalTo(answerReferenceMediaID));

		List<AnswerReferenceMedia>  answerReferenceMediaList = answerReferenceMediaRepository.findByQuestionId(questionID);
		assertNotNull(answerReferenceMediaList);
		assertThat(answerReferenceMediaList.size(), equalTo(1));
		assertThat(answerReferenceMediaList.get(0).getAnswerRefMediaID(), equalTo(answerReferenceMediaID));
		assertThat(new String(answerReferenceMediaList.get(0).getMedia()), equalTo("Testing"));


		assertNotNull(answerReferenceMedia);
		assertThat(answerReferenceMedia.getAnswerRefMediaID(), equalTo(answerReferenceMediaID));

		AnswerReferenceMedia answerReferenceMediaUpdate = new AnswerReferenceMedia(
				answerReferenceMedia.getAnswerRefMediaID(),
				questionID,
				1,new String("TestingNEW").getBytes());
		answerReferenceMediaUpdate = answerReferenceMediaRepository.save(answerReferenceMediaUpdate);
		assertNotNull(answerReferenceMediaUpdate);
		assertThat(answerReferenceMediaUpdate.getAnswerRefMediaID(), equalTo(answerReferenceMediaID));
		assertThat(new String(answerReferenceMediaUpdate.getMedia()), equalTo("TestingNEW"));

		answerReferenceMedia = answerReferenceMediaRepository.findById(answerReferenceMediaID);
		assertNotNull(answerReferenceMedia);
		assertThat(answerReferenceMedia.getAnswerRefMediaID(), equalTo(answerReferenceMediaID));
		assertThat(new String(answerReferenceMediaUpdate.getMedia()), equalTo("TestingNEW"));

		answerReferenceMediaList = answerReferenceMediaRepository.findByQuestionId(questionID);
		assertNotNull(answerReferenceMediaList);
		assertThat(answerReferenceMediaList.size(), equalTo(1));
		assertThat(answerReferenceMediaList.get(0).getAnswerRefMediaID(), equalTo(answerReferenceMediaID));
		assertThat(new String(answerReferenceMediaList.get(0).getMedia()), equalTo("TestingNEW"));

		answerReferenceMediaRepository.delete(answerReferenceMediaID);
		answerReferenceMedia = answerReferenceMediaRepository.findById(answerReferenceMediaID);
		assertNull(answerReferenceMedia);

		questionRepository.delete(questionID);
		question = questionRepository.findById(questionID);
		assertNull(question);
	}
}