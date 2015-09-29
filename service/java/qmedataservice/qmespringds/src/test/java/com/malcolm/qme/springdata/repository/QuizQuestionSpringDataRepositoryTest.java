/**
 * Name      : com.malcolm.qme.springdata.repository.QuizQuestionSpringDataRepositoryTest.java
 * Date      : May 17, 2015
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA QuizQuestionEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.CategoryEntity;
import com.malcolm.qme.springdata.entity.QuestionEntity;
import com.malcolm.qme.springdata.entity.QuizEntity;
import com.malcolm.qme.springdata.entity.QuizQuestionEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class QuizQuestionSpringDataRepositoryTest {

    /**
     * QuizQuestionEntity Repository
     */
    @Autowired
    private QuizQuestionSpringDataRepository quizQuestionSpringDataRepository;

    /**
     * CategoryEntity Repository
     */
    @Autowired
    private CategorySpringDataRepository categorySpringDataRepository;

    /**
     * QuizEntity Repository
     */
    @Autowired
    private QuizSpringDataRepository quizSpringDataRepository;

    /**
     * QuestionEntity Repository
     */
    @Autowired
    private QuestionSpringDataRepository questionSpringDataRepository;


    @Test
    public void testFindAll() {
        assertNotNull(quizQuestionSpringDataRepository);
        final List<QuizQuestionEntity> quizQuestionEntities = quizQuestionSpringDataRepository.findAll();
        assertNotNull(quizQuestionEntities);
        assertThat(quizQuestionEntities.size(), greaterThan(0));
    }

    @Test
    public void testFindById() {
        assertNotNull(quizQuestionSpringDataRepository);
        final QuizQuestionEntity quizQuestionEntity = quizQuestionSpringDataRepository.findOne(1L);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), equalTo(1L));
    }

    @Test
    public void testCRUD() {

        assertNotNull(categorySpringDataRepository);

        assertNotNull(questionSpringDataRepository);

        assertNotNull(quizSpringDataRepository);

        assertNotNull(quizQuestionSpringDataRepository);

        CategoryEntity categoryEntity = new CategoryEntity("QuizQuestionSpringDataRepositoryTest", 0L, LocalDateTime.now(), 1L);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), greaterThan(0L));
        final Long catID = categoryEntity.getCatId();

        QuestionEntity questionEntity = new QuestionEntity(catID,
                "QuizQuestionSpringDataRepositoryTest Question1",
                "QuizQuestionSpringDataRepositoryTest Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        final Long question1ID = questionEntity.getQuestionId();

        questionEntity = new QuestionEntity(catID,
                "QuizQuestionSpringDataRepositoryTest Question2",
                "QuestionSpringDataRepositoryTest Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        final Long question2ID = questionEntity.getQuestionId();

        questionEntity = new QuestionEntity(catID,
                "QuizQuestionSpringDataRepositoryTest Question3",
                "QuestionSpringDataRepositoryTest Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        final Long question3ID = questionEntity.getQuestionId();

        QuizEntity quizEntity = new QuizEntity(
                "QuizQuestionSpringDataRepositoryTest Quiz",
                "QuizQuestionSpringDataRepositoryTest Quiz Desc", catID, 0L, 0L,
                0, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L
        );

        quizEntity = quizSpringDataRepository.save(quizEntity);
        assertNotNull(quizEntity);
        assertThat(quizEntity.getQuizId(), greaterThan(0L));
        final Long quizID = quizEntity.getQuizId();

        QuizQuestionEntity quizQuestionEntity = new QuizQuestionEntity(quizID, question1ID);
        quizQuestionEntity = quizQuestionSpringDataRepository.save(quizQuestionEntity);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), greaterThan(0L));
        final Long quizQuestionID1 = quizQuestionEntity.getQuizQuestionId();
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID1);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), equalTo(quizQuestionID1));

        quizQuestionEntity = new QuizQuestionEntity(quizID, question2ID);
        quizQuestionEntity = quizQuestionSpringDataRepository.save(quizQuestionEntity);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), greaterThan(0L));
        final Long quizQuestionID2 = quizQuestionEntity.getQuizQuestionId();
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID2);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), equalTo(quizQuestionID2));

        quizQuestionEntity = new QuizQuestionEntity(quizID, question3ID);
        quizQuestionEntity = quizQuestionSpringDataRepository.save(quizQuestionEntity);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), greaterThan(0L));
        final Long quizQuestionID3 = quizQuestionEntity.getQuizQuestionId();
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID3);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), equalTo(quizQuestionID3));

        quizQuestionSpringDataRepository.delete(quizQuestionID1);
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID1);
        assertNull(quizQuestionEntity);

        quizQuestionSpringDataRepository.delete(quizQuestionID2);
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID2);
        assertNull(quizQuestionEntity);

        quizQuestionSpringDataRepository.delete(quizQuestionID3);
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID3);
        assertNull(quizQuestionEntity);

        quizSpringDataRepository.delete(quizID);
        quizEntity = quizSpringDataRepository.findOne(quizID);
        assertNull(quizEntity);

        questionSpringDataRepository.delete(question1ID);
        questionEntity = questionSpringDataRepository.findOne(question1ID);
        assertNull(questionEntity);

        questionSpringDataRepository.delete(question2ID);
        questionEntity = questionSpringDataRepository.findOne(question2ID);
        assertNull(questionEntity);

        questionSpringDataRepository.delete(question3ID);
        questionEntity = questionSpringDataRepository.findOne(question3ID);
        assertNull(questionEntity);

        categorySpringDataRepository.delete(catID);
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNull(categoryEntity);
    }

    @Test
    public void testFindByQuizId() {

        assertNotNull(categorySpringDataRepository);

        assertNotNull(questionSpringDataRepository);

        assertNotNull(quizSpringDataRepository);

        assertNotNull(quizQuestionSpringDataRepository);

        CategoryEntity categoryEntity = new CategoryEntity("QuizQuestionSpringDataRepositoryTestByQuiz", 0L, LocalDateTime.now(), 1L);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), greaterThan(0L));
        final Long catID = categoryEntity.getCatId();

        QuestionEntity questionEntity = new QuestionEntity(catID,
                "QuizQuestionSpringDataRepositoryTestByQuiz Question1",
                "QuizQuestionSpringDataRepositoryTestByQuiz Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        final Long question1ID = questionEntity.getQuestionId();

        questionEntity = new QuestionEntity(catID,
                "QuizQuestionSpringDataRepositoryTestByQuiz Question2",
                "QuizQuestionSpringDataRepositoryTestByQuiz Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        final Long question2ID = questionEntity.getQuestionId();

        questionEntity = new QuestionEntity(catID,
                "QuizQuestionSpringDataRepositoryTestByQuiz Question3",
                "QuizQuestionSpringDataRepositoryTestByQuiz Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        final Long question3ID = questionEntity.getQuestionId();

        QuizEntity quizEntity = new QuizEntity(
                "QuizQuestionSpringDataRepositoryTestByQuiz Quiz", "QuizQuestionSpringDataRepositoryTestByQuiz Quiz Desc", catID, 0L, 0L,
                0, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L
        );

        quizEntity = quizSpringDataRepository.save(quizEntity);
        assertNotNull(quizEntity);
        assertThat(quizEntity.getQuizId(), greaterThan(0L));
        final Long quizID = quizEntity.getQuizId();

        QuizQuestionEntity quizQuestionEntity = new QuizQuestionEntity(quizID, question1ID);
        quizQuestionEntity = quizQuestionSpringDataRepository.save(quizQuestionEntity);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), greaterThan(0L));
        final Long quizQuestionID1 = quizQuestionEntity.getQuizQuestionId();
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID1);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), equalTo(quizQuestionID1));

        quizQuestionEntity = new QuizQuestionEntity(quizID, question2ID);
        quizQuestionEntity = quizQuestionSpringDataRepository.save(quizQuestionEntity);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), greaterThan(0L));
        final Long quizQuestionID2 = quizQuestionEntity.getQuizQuestionId();
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID2);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), equalTo(quizQuestionID2));

        quizQuestionEntity = new QuizQuestionEntity(quizID, question3ID);
        quizQuestionEntity = quizQuestionSpringDataRepository.save(quizQuestionEntity);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), greaterThan(0L));
        final Long quizQuestionID3 = quizQuestionEntity.getQuizQuestionId();
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID3);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), equalTo(quizQuestionID3));

        final List<QuizQuestionEntity> quizQuestionList = quizQuestionSpringDataRepository.findByQuizId(quizID);
        assertNotNull(quizQuestionList);
        assertThat(quizQuestionList.size(), equalTo(3));

        quizQuestionSpringDataRepository.delete(quizQuestionID1);
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID1);
        assertNull(quizQuestionEntity);

        quizQuestionSpringDataRepository.delete(quizQuestionID2);
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID2);
        assertNull(quizQuestionEntity);

        quizQuestionSpringDataRepository.delete(quizQuestionID3);
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID3);
        assertNull(quizQuestionEntity);

        quizSpringDataRepository.delete(quizID);
        quizEntity = quizSpringDataRepository.findOne(quizID);
        assertNull(quizEntity);

        questionSpringDataRepository.delete(question1ID);
        questionEntity = questionSpringDataRepository.findOne(question1ID);
        assertNull(questionEntity);

        questionSpringDataRepository.delete(question2ID);
        questionEntity = questionSpringDataRepository.findOne(question2ID);
        assertNull(questionEntity);

        questionSpringDataRepository.delete(question3ID);
        questionEntity = questionSpringDataRepository.findOne(question3ID);
        assertNull(questionEntity);

        categorySpringDataRepository.delete(catID);
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNull(categoryEntity);
    }


    @Test
    public void testFindByQuestionId() {

        assertNotNull(categorySpringDataRepository);

        assertNotNull(questionSpringDataRepository);

        assertNotNull(quizSpringDataRepository);

        assertNotNull(quizQuestionSpringDataRepository);

        CategoryEntity categoryEntity = new CategoryEntity("QuizQuestionSpringDataRepositoryTestByQuestion", 0L, LocalDateTime.now(), 1L);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), greaterThan(0L));
        final Long catID = categoryEntity.getCatId();

        QuestionEntity questionEntity = new QuestionEntity(catID,
                "QuizQuestionSpringDataRepositoryTestByQuestion Question1",
                "QuizQuestionSpringDataRepositoryTestByQuestion Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        final Long question1ID = questionEntity.getQuestionId();

        questionEntity = new QuestionEntity(catID,
                "QuizQuestionSpringDataRepositoryTestByQuestion Question2",
                "QuizQuestionSpringDataRepositoryTestByQuestion Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        final Long question2ID = questionEntity.getQuestionId();

        questionEntity = new QuestionEntity(catID,
                "QuizQuestionSpringDataRepositoryTestByQuestion Question3",
                "QuizQuestionSpringDataRepositoryTestByQuestion Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        final Long question3ID = questionEntity.getQuestionId();

        QuizEntity quizEntity = new QuizEntity(
                "QuizQuestionSpringDataRepositoryTestByQuestion Quiz", "QuizQuestionSpringDataRepositoryTestByQuestion Quiz Desc", catID, 0L, 0L,
                0, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L
        );

        quizEntity = quizSpringDataRepository.save(quizEntity);
        assertNotNull(quizEntity);
        assertThat(quizEntity.getQuizId(), greaterThan(0L));
        final Long quizID = quizEntity.getQuizId();

        QuizQuestionEntity quizQuestionEntity = new QuizQuestionEntity(quizID, question1ID);
        quizQuestionEntity = quizQuestionSpringDataRepository.save(quizQuestionEntity);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), greaterThan(0L));
        final Long quizQuestionID1 = quizQuestionEntity.getQuizQuestionId();
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID1);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), equalTo(quizQuestionID1));

        quizQuestionEntity = new QuizQuestionEntity(quizID, question2ID);
        quizQuestionEntity = quizQuestionSpringDataRepository.save(quizQuestionEntity);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), greaterThan(0L));
        final Long quizQuestionID2 = quizQuestionEntity.getQuizQuestionId();
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID2);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), equalTo(quizQuestionID2));

        quizQuestionEntity = new QuizQuestionEntity(quizID, question3ID);
        quizQuestionEntity = quizQuestionSpringDataRepository.save(quizQuestionEntity);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), greaterThan(0L));
        final Long quizQuestionID3 = quizQuestionEntity.getQuizQuestionId();
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID3);
        assertNotNull(quizQuestionEntity);
        assertThat(quizQuestionEntity.getQuizQuestionId(), equalTo(quizQuestionID3));

        List<QuizQuestionEntity> quizQuestionList = quizQuestionSpringDataRepository.findByQuestionId(question1ID);
        assertNotNull(quizQuestionList);
        assertThat(quizQuestionList.size(), equalTo(1));

        quizQuestionList = quizQuestionSpringDataRepository.findByQuestionId(question2ID);
        assertNotNull(quizQuestionList);
        assertThat(quizQuestionList.size(), equalTo(1));

        quizQuestionList = quizQuestionSpringDataRepository.findByQuestionId(question3ID);
        assertNotNull(quizQuestionList);
        assertThat(quizQuestionList.size(), equalTo(1));

        quizQuestionSpringDataRepository.delete(quizQuestionID1);
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID1);
        assertNull(quizQuestionEntity);

        quizQuestionSpringDataRepository.delete(quizQuestionID2);
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID2);
        assertNull(quizQuestionEntity);

        quizQuestionSpringDataRepository.delete(quizQuestionID3);
        quizQuestionEntity = quizQuestionSpringDataRepository.findOne(quizQuestionID3);
        assertNull(quizQuestionEntity);

        quizSpringDataRepository.delete(quizID);
        quizEntity = quizSpringDataRepository.findOne(quizID);
        assertNull(quizEntity);

        questionSpringDataRepository.delete(question1ID);
        questionEntity = questionSpringDataRepository.findOne(question1ID);
        assertNull(questionEntity);

        questionSpringDataRepository.delete(question2ID);
        questionEntity = questionSpringDataRepository.findOne(question2ID);
        assertNull(questionEntity);

        questionSpringDataRepository.delete(question3ID);
        questionEntity = questionSpringDataRepository.findOne(question3ID);
        assertNull(questionEntity);

        categorySpringDataRepository.delete(catID);
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNull(categoryEntity);
    }
}
