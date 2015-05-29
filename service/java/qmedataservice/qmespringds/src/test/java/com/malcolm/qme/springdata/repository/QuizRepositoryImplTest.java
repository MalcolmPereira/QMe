/**
 * Name      : com.malcolm.qme.springdata.repository.QuizRepositoryImplTest.java
 * Date      : May 17, 2015
 * Developer : Malcolm
 * Purpose   : Tests for Quiz Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.domain.Quiz;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.QuizRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;

/**
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class QuizRepositoryImplTest {

    /**
     * Quiz Repository
     */
    @Autowired
    @Qualifier("QuizRepository")
    private QuizRepository quizRepository;

    /**
     * Category Repository
     */
    @Autowired
    @Qualifier("CategoryRepository")
    private CategoryRepository categoryRepo;

    @Test
    public void testFindAll() throws QMeException {
        assertNotNull(quizRepository);
        final List<Quiz> quizList = quizRepository.findAll();
        assertNotNull(quizList);
        assertThat(quizList.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(quizRepository);
        final Quiz quiz = quizRepository.findById(1L);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), equalTo(1L));
    }

    @Test
    public void testCRUD() throws QMeException {
        assertNotNull(quizRepository);

        Quiz quiz = new Quiz(
                "QuizRepositoryImplTest Quiz", "QuizRepositoryImplTest Quiz Desc", 1L, 0, 1L);
        quiz = quizRepository.save(quiz);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), greaterThan(0L));

        final Long quizID = quiz.getQuizID();

        quiz = quizRepository.findById(quizID);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), equalTo(quizID));
        assertThat(quiz.getQuizName(), equalTo("QuizRepositoryImplTest Quiz"));

        Quiz quizUpdate = new Quiz(quiz.getQuizID(),
                "QuizRepositoryImplTest Quiz Update",
                "QuizRepositoryImplTest Quiz Desc Update",
                quiz.getCategoryID(),
                quiz.getLikes(),
                quiz.getQuizHit(),
                quiz.getQuizMaxAttempts(),
                quiz.getQuizCreateDate(),
                quiz.getCreateUserID(),
                LocalDateTime.now(),
                1L);
        quizUpdate = quizRepository.update(quizUpdate, 1L);
        assertNotNull(quizUpdate);
        assertThat(quizUpdate.getQuizID(), equalTo(quizID));

        quiz = quizRepository.findById(quizID);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), equalTo(quizID));
        assertThat(quiz.getQuizName(), equalTo("QuizRepositoryImplTest Quiz Update"));

        quizRepository.delete(quizID);
        quiz = quizRepository.findById(quizID);
        assertNull(quiz);
    }

    @Test
    public void testFindByCatId() throws QMeException {

        assertNotNull(categoryRepo);

        Category category = new Category("QuizRepositoryImplTest", 1L);
        category = categoryRepo.save(category);
        assertNotNull(category);
        assertThat(category.getCategoryID(), greaterThan(0L));

        final Long catID = category.getCategoryID();

        Quiz quiz = new Quiz(
                "QuizRepositoryImplTestByCatID Quiz", "QuizRepositoryImplTestByCatID Quiz Desc", catID, 0, 1L);
        quiz = quizRepository.save(quiz);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), greaterThan(0L));

        final Long quizID = quiz.getQuizID();

        quiz = quizRepository.findById(quizID);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), equalTo(quizID));
        assertThat(quiz.getQuizName(), equalTo("QuizRepositoryImplTestByCatID Quiz"));

        List<Quiz> quizList = quizRepository.findByCategoryId(catID);
        assertNotNull(quizList);
        assertThat(quizList.size(), equalTo(1));
        assertThat(quizList.get(0).getQuizID(), equalTo(quizID));
        assertThat(quizList.get(0).getQuizName(), equalTo("QuizRepositoryImplTestByCatID Quiz"));

        Quiz quizUpdate = new Quiz(quiz.getQuizID(),
                "QuizRepositoryImplTestByCatID Quiz Update",
                "QuizRepositoryImplTestByCatID Quiz Desc Update",
                quiz.getCategoryID(),
                quiz.getLikes(),
                quiz.getQuizHit(),
                quiz.getQuizMaxAttempts(),
                quiz.getQuizCreateDate(),
                quiz.getCreateUserID(),
                LocalDateTime.now(),
                1L);
        quizUpdate = quizRepository.update(quizUpdate, 1L);
        assertNotNull(quizUpdate);
        assertThat(quizUpdate.getQuizID(), equalTo(quizID));

        quiz = quizRepository.findById(quizID);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), equalTo(quizID));
        assertThat(quiz.getQuizName(), equalTo("QuizRepositoryImplTestByCatID Quiz Update"));

        quizList = quizRepository.findByCategoryId(catID);
        assertNotNull(quizList);
        assertThat(quizList.size(), equalTo(1));
        assertThat(quizList.get(0).getQuizID(), equalTo(quizID));
        assertThat(quizList.get(0).getQuizName(), equalTo("QuizRepositoryImplTestByCatID Quiz Update"));

        quizRepository.delete(quizID);
        quiz = quizRepository.findById(quizID);
        assertNull(quiz);

        categoryRepo.delete(catID);
        category = categoryRepo.findById(catID);
        assertNull(category);
    }

    @Test
    public void testFindByQuizNameIgnoreCaseLike() throws QMeException {

        assertNotNull(quizRepository);

        Quiz quiz = new Quiz(
                "QuizRepositoryImplTestByName Quiz", "QuizRepositoryImplTestByName Quiz Desc", 1L, 0, 1L);
        quiz = quizRepository.save(quiz);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), greaterThan(0L));

        final Long quizID = quiz.getQuizID();

        quiz = quizRepository.findById(quizID);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), equalTo(quizID));
        assertThat(quiz.getQuizName(), equalTo("QuizRepositoryImplTestByName Quiz"));

        List<Quiz> quizList = quizRepository.findQuizNameLike("QuizRepositoryImplTestByName Quiz");
        assertNotNull(quizList);
        assertThat(quizList.size(), equalTo(1));
        assertThat(quizList.get(0).getQuizID(), equalTo(quizID));
        assertThat(quizList.get(0).getQuizName(), equalTo("QuizRepositoryImplTestByName Quiz"));

        quizList = quizRepository.findQuizNameLike("quizrepositoryimpltestbyname quiz");
        assertNotNull(quizList);
        assertThat(quizList.size(), equalTo(1));
        assertThat(quizList.get(0).getQuizID(), equalTo(quizID));
        assertThat(quizList.get(0).getQuizName(), equalTo("QuizRepositoryImplTestByName Quiz"));

        Quiz quizUpdate = new Quiz(quiz.getQuizID(),
                "QuizRepositoryImplTest Quiz Update",
                "QuizRepositoryImplTest Quiz Desc Update",
                quiz.getCategoryID(),
                quiz.getLikes(),
                quiz.getQuizHit(),
                quiz.getQuizMaxAttempts(),
                quiz.getQuizCreateDate(),
                quiz.getCreateUserID(),
                LocalDateTime.now(),
                1L);
        quizUpdate = quizRepository.update(quizUpdate, 1L);
        assertNotNull(quizUpdate);
        assertThat(quizUpdate.getQuizID(), equalTo(quizID));

        quiz = quizRepository.findById(quizID);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), equalTo(quizID));
        assertThat(quiz.getQuizName(), equalTo("QuizRepositoryImplTest Quiz Update"));

        quizList = quizRepository.findQuizNameLike("QuizRepositoryImplTest Quiz Update");
        assertNotNull(quizList);
        assertThat(quizList.size(), equalTo(1));
        assertThat(quizList.get(0).getQuizID(), equalTo(quizID));
        assertThat(quizList.get(0).getQuizName(), equalTo("QuizRepositoryImplTest Quiz Update"));

        quizList = quizRepository.findQuizNameLike("quizrepositoryimpltest quiz update");
        assertNotNull(quizList);
        assertThat(quizList.size(), equalTo(1));
        assertThat(quizList.get(0).getQuizID(), equalTo(quizID));
        assertThat(quizList.get(0).getQuizName(), equalTo("QuizRepositoryImplTest Quiz Update"));

        quizRepository.delete(quizID);
        quiz = quizRepository.findById(quizID);
        assertNull(quiz);
    }

    @Test
    public void testFindTop50ByOrderByQuizLikesDesc() throws QMeException {
        assertNotNull(quizRepository);

        Quiz quiz = new Quiz(
                "QuizRepositoryImplTestByLikes Quiz1", "QuizRepositoryImplTestByLikes Quiz1 Desc", 1L, 0, 1L);
        quiz = quizRepository.save(quiz);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), greaterThan(0L));
        final Long quizID1 = quiz.getQuizID();
        quiz = quizRepository.findById(quizID1);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), equalTo(quizID1));
        assertThat(quiz.getQuizName(), equalTo("QuizRepositoryImplTestByLikes Quiz1"));
        for (int i = 0; i < 100; i++) {
            Quiz quizUpdate = null;
            if (quiz.getLikes() == null) {
                quizUpdate = new Quiz(
                        quiz.getQuizID(),
                        quiz.getQuizName(),
                        quiz.getQuizDesc(),
                        quiz.getCategoryID(),
                        1L,
                        quiz.getQuizHit(),
                        quiz.getQuizMaxAttempts(),
                        quiz.getQuizCreateDate(),
                        quiz.getCreateUserID(),
                        LocalDateTime.now(),
                        1L);
            } else {
                quizUpdate = new Quiz(
                        quiz.getQuizID(),
                        quiz.getQuizName(),
                        quiz.getQuizDesc(),
                        quiz.getCategoryID(),
                        quiz.getLikes() + 1,
                        quiz.getQuizHit(),
                        quiz.getQuizMaxAttempts(),
                        quiz.getQuizCreateDate(),
                        quiz.getCreateUserID(),
                        LocalDateTime.now(),
                        1L);

            }
            quizUpdate = quizRepository.update(quizUpdate, 1L);
        }

        quiz = new Quiz(
                "QuizRepositoryImplTestByLikes Quiz2", "QuizRepositoryImplTestByLikes Quiz2 Desc", 1L, 0, 1L);
        quiz = quizRepository.save(quiz);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), greaterThan(0L));
        final Long quizID2 = quiz.getQuizID();
        quiz = quizRepository.findById(quizID2);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), equalTo(quizID2));
        assertThat(quiz.getQuizName(), equalTo("QuizRepositoryImplTestByLikes Quiz2"));
        for (int i = 0; i < 10; i++) {
            Quiz quizUpdate = null;
            if (quiz.getLikes() == null) {
                quizUpdate = new Quiz(
                        quiz.getQuizID(),
                        quiz.getQuizName(),
                        quiz.getQuizDesc(),
                        quiz.getCategoryID(),
                        1L,
                        quiz.getQuizHit(),
                        quiz.getQuizMaxAttempts(),
                        quiz.getQuizCreateDate(),
                        quiz.getCreateUserID(),
                        LocalDateTime.now(),
                        1L);
            } else {
                quizUpdate = new Quiz(
                        quiz.getQuizID(),
                        quiz.getQuizName(),
                        quiz.getQuizDesc(),
                        quiz.getCategoryID(),
                        quiz.getLikes() + 1,
                        quiz.getQuizHit(),
                        quiz.getQuizMaxAttempts(),
                        quiz.getQuizCreateDate(),
                        quiz.getCreateUserID(),
                        LocalDateTime.now(),
                        1L);

            }
            quizUpdate = quizRepository.update(quizUpdate, 1L);
        }

        quiz = new Quiz(
                "QuizRepositoryImplTestByLikes Quiz3", "QuizRepositoryImplTestByLikes Quiz3 Desc", 1L, 0, 1L);
        quiz = quizRepository.save(quiz);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), greaterThan(0L));
        final Long quizID3 = quiz.getQuizID();
        quiz = quizRepository.findById(quizID3);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), equalTo(quizID3));
        assertThat(quiz.getQuizName(), equalTo("QuizRepositoryImplTestByLikes Quiz3"));

        final List<Quiz> quizList = quizRepository.findByMostLiked();
        assertNotNull(quizList);
        assertThat(quizList.size(), greaterThan(0));
        assertThat((quizList.get(0)).getQuizID(), equalTo(quizID1));
        assertThat((quizList.get(0)).getQuizName(), equalTo("QuizRepositoryImplTestByLikes Quiz1"));

        quizRepository.delete(quizID1);
        quiz = quizRepository.findById(quizID1);
        assertNull(quiz);

        quizRepository.delete(quizID2);
        quiz = quizRepository.findById(quizID2);
        assertNull(quiz);

        quizRepository.delete(quizID3);
        quiz = quizRepository.findById(quizID3);
        assertNull(quiz);
    }

}
