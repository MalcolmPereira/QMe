/**
 * Name      : com.malcolm.qme.springdata.repository.QuizQuestionRepositoryImplTest.java
 * Date      : May 17, 2015
 * Developer : Malcolm
 * Purpose   : Tests for QuizQuestion Repository
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
import com.malcolm.qme.core.domain.Quiz;
import com.malcolm.qme.core.domain.QuizQuestion;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.core.repository.QuizQuestionRepository;
import com.malcolm.qme.core.repository.QuizRepository;
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
public class QuizQuestionRepositoryImplTest {
	/**
	 * QuizQuestion Repository
	 */
	@Autowired
	@Qualifier("QuizQuestionRepository")
	private QuizQuestionRepository quizQuestionRepository;

	/**
	 * Category Repository
	 */
	@Autowired
	@Qualifier("CategoryRepository")
	private CategoryRepository categoryRepo;

	/**
	 * Question Repository
	 */
	@Autowired
	@Qualifier("QuestionRepository")
	private QuestionRepository questionRepository;

	/**
	 * Quiz Repository
	 */
	@Autowired
	@Qualifier("QuizRepository")
	private QuizRepository quizRepository;

	@Test
	public void testFindAll(){
		assertNotNull(quizQuestionRepository);
		final List<QuizQuestion> quizQuestionList = quizQuestionRepository.findAll();
		assertNotNull(quizQuestionList);
		assertThat(quizQuestionList.size(), greaterThan(0));
	}

	@Test
	public void testFindById(){
		assertNotNull(quizQuestionRepository);
		final QuizQuestion quizQuestion = quizQuestionRepository.findById(1L);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), equalTo(1L));
	}

	@Test
	public void testCRUD() {

		assertNotNull(categoryRepo);

		assertNotNull(questionRepository);

		assertNotNull(quizRepository);

		assertNotNull(quizQuestionRepository);

		Category category = new Category("QuizQuestionRepositoryImplTest", 1L);
		category = categoryRepo.save(category);
		assertNotNull(category);
		assertThat(category.getCategoryID(), greaterThan(0L));
		final Long catID = category.getCategoryID();

		Question question = new Question(catID, "QuizQuestionRepositoryImplTest Question1", "QuizQuestionRepositoryImplTest Answer", 1L);
		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		final Long question1ID = question.getQuestionID();

		question = new Question(catID, "QuizQuestionRepositoryImplTest Question2", "QuizQuestionRepositoryImplTest Answer", 1L);
		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		final Long question2ID = question.getQuestionID();

		question = new Question(catID, "QuizQuestionRepositoryImplTest Question3", "QuizQuestionRepositoryImplTest Answer", 1L);
		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		final Long question3ID = question.getQuestionID();

		Quiz quiz = new Quiz(
				"QuizQuestionRepositoryImplTest Quiz","QuizQuestionRepositoryImplTest Quiz Desc", catID, 0,1L);
		quiz = quizRepository.save(quiz);
		assertNotNull(quiz);
		assertThat(quiz.getQuizID(), greaterThan(0L));
		final Long quizID = quiz.getQuizID();


		QuizQuestion quizQuestion = new QuizQuestion(quizID,question1ID);
		quizQuestion = quizQuestionRepository.save(quizQuestion);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), greaterThan(0L));
		final Long quizQuestionID1 = quizQuestion.getQuizQuestionID();
		quizQuestion = quizQuestionRepository.findById(quizQuestionID1);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), equalTo(quizQuestionID1));

		quizQuestion = new QuizQuestion(quizID,question2ID);
		quizQuestion = quizQuestionRepository.save(quizQuestion);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), greaterThan(0L));
		final Long quizQuestionID2 = quizQuestion.getQuizQuestionID();
		quizQuestion = quizQuestionRepository.findById(quizQuestionID2);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), equalTo(quizQuestionID2));

		quizQuestion = new QuizQuestion(quizID,question3ID);
		quizQuestion = quizQuestionRepository.save(quizQuestion);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), greaterThan(0L));
		final Long quizQuestionID3 = quizQuestion.getQuizQuestionID();
		quizQuestion = quizQuestionRepository.findById(quizQuestionID3);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), equalTo(quizQuestionID3));


		quizQuestionRepository.delete(quizQuestionID1);
		quizQuestion = quizQuestionRepository.findById(quizQuestionID1);
		assertNull(quizQuestion);

		quizQuestionRepository.delete(quizQuestionID2);
		quizQuestion = quizQuestionRepository.findById(quizQuestionID2);
		assertNull(quizQuestion);

		quizQuestionRepository.delete(quizQuestionID3);
		quizQuestion = quizQuestionRepository.findById(quizQuestionID3);
		assertNull(quizQuestion);

		quizRepository.delete(quizID);
		quiz = quizRepository.findById(quizID);
		assertNull(quiz);

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
	public void testFindByQuizId() {

		assertNotNull(categoryRepo);

		assertNotNull(questionRepository);

		assertNotNull(quizRepository);

		assertNotNull(quizQuestionRepository);

		Category category = new Category("QuizQuestionRepositoryImplTestByQuiz", 1L);
		category = categoryRepo.save(category);
		assertNotNull(category);
		assertThat(category.getCategoryID(), greaterThan(0L));
		final Long catID = category.getCategoryID();

		Question question = new Question(catID, "QuizQuestionRepositoryImplTestByQuiz Question1", "QuizQuestionRepositoryImplTestByQuiz Answer", 1L);
		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		final Long question1ID = question.getQuestionID();

		question = new Question(catID, "QuizQuestionRepositoryImplTestByQuiz Question2", "QuizQuestionRepositoryImplTestByQuiz Answer", 1L);
		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		final Long question2ID = question.getQuestionID();

		question = new Question(catID, "QuizQuestionRepositoryImplTestByQuiz Question3", "QuizQuestionRepositoryImplTestByQuiz Answer", 1L);
		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		final Long question3ID = question.getQuestionID();

		Quiz quiz = new Quiz(
				"QuizQuestionRepositoryImplTestByQuiz Quiz","QuizQuestionRepositoryImplTestByQuiz Quiz Desc", catID, 0,1L);
		quiz = quizRepository.save(quiz);
		assertNotNull(quiz);
		assertThat(quiz.getQuizID(), greaterThan(0L));
		final Long quizID = quiz.getQuizID();


		QuizQuestion quizQuestion = new QuizQuestion(quizID,question1ID);
		quizQuestion = quizQuestionRepository.save(quizQuestion);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), greaterThan(0L));
		final Long quizQuestionID1 = quizQuestion.getQuizQuestionID();
		quizQuestion = quizQuestionRepository.findById(quizQuestionID1);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), equalTo(quizQuestionID1));

		quizQuestion = new QuizQuestion(quizID,question2ID);
		quizQuestion = quizQuestionRepository.save(quizQuestion);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), greaterThan(0L));
		final Long quizQuestionID2 = quizQuestion.getQuizQuestionID();
		quizQuestion = quizQuestionRepository.findById(quizQuestionID2);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), equalTo(quizQuestionID2));

		quizQuestion = new QuizQuestion(quizID,question3ID);
		quizQuestion = quizQuestionRepository.save(quizQuestion);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), greaterThan(0L));
		final Long quizQuestionID3 = quizQuestion.getQuizQuestionID();
		quizQuestion = quizQuestionRepository.findById(quizQuestionID3);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), equalTo(quizQuestionID3));

		final List<QuizQuestion> quizQuestionList = quizQuestionRepository.findByQuizId(quizID);
		assertNotNull(quizQuestionList);
		assertThat(quizQuestionList.size(), equalTo(3));

		quizQuestionRepository.delete(quizQuestionID1);
		quizQuestion = quizQuestionRepository.findById(quizQuestionID1);
		assertNull(quizQuestion);

		quizQuestionRepository.delete(quizQuestionID2);
		quizQuestion = quizQuestionRepository.findById(quizQuestionID2);
		assertNull(quizQuestion);

		quizQuestionRepository.delete(quizQuestionID3);
		quizQuestion = quizQuestionRepository.findById(quizQuestionID3);
		assertNull(quizQuestion);

		quizRepository.delete(quizID);
		quiz = quizRepository.findById(quizID);
		assertNull(quiz);

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
	public void testFindByQuestionId() {

		assertNotNull(categoryRepo);

		assertNotNull(questionRepository);

		assertNotNull(quizRepository);

		assertNotNull(quizQuestionRepository);

		Category category = new Category("QuizQuestionRepositoryImplTestByQuestion", 1L);
		category = categoryRepo.save(category);
		assertNotNull(category);
		assertThat(category.getCategoryID(), greaterThan(0L));
		final Long catID = category.getCategoryID();

		Question question = new Question(catID, "QuizQuestionRepositoryImplTestByQuestion Question1", "QuizQuestionRepositoryImplTestByQuestion Answer", 1L);
		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		final Long question1ID = question.getQuestionID();

		question = new Question(catID, "QuizQuestionRepositoryImplTestByQuestion Question2", "QuizQuestionRepositoryImplTestByQuestion Answer", 1L);
		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		final Long question2ID = question.getQuestionID();

		question = new Question(catID, "QuizQuestionRepositoryImplTestByQuestion Question3", "QuizQuestionRepositoryImplTestByQuestion Answer", 1L);
		question = questionRepository.save(question);
		assertNotNull(question);
		assertThat(question.getQuestionID(), greaterThan(0L));
		final Long question3ID = question.getQuestionID();

		Quiz quiz = new Quiz(
				"QuizQuestionRepositoryImplTestByQuestion Quiz","QuizQuestionRepositoryImplTestByQuestion Quiz Desc", catID, 0,1L);
		quiz = quizRepository.save(quiz);
		assertNotNull(quiz);
		assertThat(quiz.getQuizID(), greaterThan(0L));
		final Long quizID = quiz.getQuizID();


		QuizQuestion quizQuestion = new QuizQuestion(quizID,question1ID);
		quizQuestion = quizQuestionRepository.save(quizQuestion);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), greaterThan(0L));
		final Long quizQuestionID1 = quizQuestion.getQuizQuestionID();
		quizQuestion = quizQuestionRepository.findById(quizQuestionID1);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), equalTo(quizQuestionID1));

		quizQuestion = new QuizQuestion(quizID,question2ID);
		quizQuestion = quizQuestionRepository.save(quizQuestion);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), greaterThan(0L));
		final Long quizQuestionID2 = quizQuestion.getQuizQuestionID();
		quizQuestion = quizQuestionRepository.findById(quizQuestionID2);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), equalTo(quizQuestionID2));

		quizQuestion = new QuizQuestion(quizID,question3ID);
		quizQuestion = quizQuestionRepository.save(quizQuestion);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), greaterThan(0L));
		final Long quizQuestionID3 = quizQuestion.getQuizQuestionID();
		quizQuestion = quizQuestionRepository.findById(quizQuestionID3);
		assertNotNull(quizQuestion);
		assertThat(quizQuestion.getQuizQuestionID(), equalTo(quizQuestionID3));

		List<QuizQuestion> quizQuestionList = quizQuestionRepository.findByQuestionId(question1ID);
		assertNotNull(quizQuestionList);
		assertThat(quizQuestionList.size(), equalTo(1));

		quizQuestionList = quizQuestionRepository.findByQuestionId(question2ID);
		assertNotNull(quizQuestionList);
		assertThat(quizQuestionList.size(), equalTo(1));

		quizQuestionList = quizQuestionRepository.findByQuestionId(question3ID);
		assertNotNull(quizQuestionList);
		assertThat(quizQuestionList.size(), equalTo(1));

		quizQuestionRepository.delete(quizQuestionID1);
		quizQuestion = quizQuestionRepository.findById(quizQuestionID1);
		assertNull(quizQuestion);

		quizQuestionRepository.delete(quizQuestionID2);
		quizQuestion = quizQuestionRepository.findById(quizQuestionID2);
		assertNull(quizQuestion);

		quizQuestionRepository.delete(quizQuestionID3);
		quizQuestion = quizQuestionRepository.findById(quizQuestionID3);
		assertNull(quizQuestion);

		quizRepository.delete(quizID);
		quiz = quizRepository.findById(quizID);
		assertNull(quiz);

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
