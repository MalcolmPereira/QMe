/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerOptionRepositoryImplTest.java
 * Date      : May 17, 2015
 * Developer : Malcolm
 * Purpose   : Tests for AnswerOption Repository
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
import com.malcolm.qme.core.domain.Question;
import com.malcolm.qme.core.repository.AnswerOptionRepository;
import com.malcolm.qme.core.repository.QuestionRepository;
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
public class AnswerOptionRepositoryImplTest {

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
	public void testFindAll(){
		assertNotNull(answerOptionRepository);
		final List<AnswerOption> answerOptions = answerOptionRepository.findAll();
		assertNotNull(answerOptions);
		assertThat(answerOptions.size(), greaterThan(0));
	}

	@Test
	public void testFindById(){
		assertNotNull(answerOptionRepository);
		final AnswerOption answerOption = answerOptionRepository.findById(1L);
		assertNotNull(answerOption);
		assertThat(answerOption.getAnswerOptionID(), equalTo(1L));
	}

	@Test
	public void testCRUD(){
		assertNotNull(answerOptionRepository);

		assertNotNull(questionRepository);

		Question question = new Question(1L,"QuestionRepositoryImplTest Question","QuestionRepositoryImplTest Answer",1L);

		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		final Long questionID = question.getQuestionID();

		AnswerOption answerOption = new AnswerOption(questionID, "Option 1", false);
		answerOption = answerOptionRepository.save(answerOption);
		assertNotNull(answerOption);
		assertThat(answerOption.getAnswerOptionID(), greaterThan(0L));
		final Long answerOptionID  = answerOption.getAnswerOptionID();

		answerOption = answerOptionRepository.findById(answerOptionID);
		assertNotNull(answerOption);
		assertThat(answerOption.getAnswerOptionID(), equalTo(answerOptionID));

		AnswerOption answerOptionUpdate = new AnswerOption(answerOption.getAnswerOptionID(),answerOption.getQuestionID(), "Option 1 Updated", answerOption.isCorrect());
		answerOptionUpdate = answerOptionRepository.save(answerOptionUpdate);
		assertNotNull(answerOptionUpdate);
		assertThat(answerOptionUpdate.getAnswerOptionID(), equalTo(answerOptionID));
		assertThat(answerOptionUpdate.getOptionText(), equalTo("Option 1 Updated"));

		answerOptionRepository.delete(answerOptionID);
		answerOption = answerOptionRepository.findById(answerOptionID);
		assertNull(answerOption);

		questionRepository.delete(questionID);
		question = questionRepository.findById(questionID);
		assertNull(question);
	}

	@Test
	public void testFindByQuestionId(){
		assertNotNull(answerOptionRepository);

		assertNotNull(questionRepository);

		Question question = new Question(1L,"QuestionRepositoryImplTest Question","QuestionRepositoryImplTest Answer",1L);

		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		final Long questionID = question.getQuestionID();

		AnswerOption answerOption = new AnswerOption(questionID, "Option 1", false);
		answerOption = answerOptionRepository.save(answerOption);
		assertNotNull(answerOption);
		assertThat(answerOption.getAnswerOptionID(), greaterThan(0L));
		final Long answerOptionID  = answerOption.getAnswerOptionID();

		answerOption = answerOptionRepository.findById(answerOptionID);
		assertNotNull(answerOption);
		assertThat(answerOption.getAnswerOptionID(), equalTo(answerOptionID));

		final List<AnswerOption>  answerOptionList = answerOptionRepository.findByQuestionId(questionID);
		assertNotNull(answerOptionList);
		assertThat(answerOptionList.get(0).getAnswerOptionID(), equalTo(answerOptionID));
		assertThat(answerOptionList.get(0).getOptionText(), equalTo("Option 1"));

		AnswerOption answerOptionUpdate = new AnswerOption(answerOption.getAnswerOptionID(),answerOption.getQuestionID(), "Option 1 Updated", answerOption.isCorrect());
		answerOptionUpdate = answerOptionRepository.save(answerOptionUpdate);
		assertNotNull(answerOptionUpdate);
		assertThat(answerOptionUpdate.getAnswerOptionID(), equalTo(answerOptionID));
		assertThat(answerOptionUpdate.getOptionText(), equalTo("Option 1 Updated"));

		answerOptionRepository.delete(answerOptionID);
		answerOption = answerOptionRepository.findById(answerOptionID);
		assertNull(answerOption);

		questionRepository.delete(questionID);
		question = questionRepository.findById(questionID);
		assertNull(question);
	}

}
