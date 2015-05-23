/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerReferenceMediaSpringDataRepositoryTest.java
 * Date      : 5/15/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA AnswerReferenceMediaEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.AnswerReferenceMediaEntity;
import com.malcolm.qme.springdata.entity.QuestionEntity;
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
 * @author Malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class AnswerReferenceMediaSpringDataRepositoryTest {

    /**
     * AnswerOptionMediaEntity Repository
     */
    @Autowired
    private AnswerReferenceMediaSpringDataRepository answerReferenceMediaSpringDataRepo;

    /**
     * QuestionEntity Repository
     */
    @Autowired
    private QuestionSpringDataRepository questionSpringDataRepository;


    @Test
    public void testFindAll() {
        assertNotNull(answerReferenceMediaSpringDataRepo);
        final List<AnswerReferenceMediaEntity> answerReferenceMediaEntities = answerReferenceMediaSpringDataRepo.findAll();
        assertNotNull(answerReferenceMediaEntities);
        assertThat(answerReferenceMediaEntities.size(), greaterThan(0));
    }

    @Test
    public void testFindById() {
        assertNotNull(answerReferenceMediaSpringDataRepo);
        final AnswerReferenceMediaEntity answerReferenceMediaEntity = answerReferenceMediaSpringDataRepo.findOne(1L);
        assertNotNull(answerReferenceMediaEntity);
        assertThat(answerReferenceMediaEntity.getAnswerRefMediaId(), equalTo(1L));
    }

    @Test
    public void testCRUD() {

        assertNotNull(answerReferenceMediaSpringDataRepo);

        assertNotNull(questionSpringDataRepository);

        QuestionEntity questionEntity = new QuestionEntity(1L,
                "AnswerReferenceMediaSpringDataRepositoryTest Question",
                "AnswerReferenceMediaSpringDataRepositoryTest Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        final Long questionID = questionEntity.getQuestionId();

        AnswerReferenceMediaEntity answerReferenceMediaEntity = new AnswerReferenceMediaEntity();
        answerReferenceMediaEntity.setQuestionId(questionID);
        answerReferenceMediaEntity.setMediaTypeId(1);
        answerReferenceMediaEntity.setRefMedia(new String("Testing").getBytes());
        answerReferenceMediaEntity = answerReferenceMediaSpringDataRepo.save(answerReferenceMediaEntity);
        assertNotNull(answerReferenceMediaEntity);
        assertThat(answerReferenceMediaEntity.getAnswerRefMediaId(), greaterThan(0L));

        final Long answerReferenceMediaID = answerReferenceMediaEntity.getAnswerRefMediaId();

        answerReferenceMediaEntity = answerReferenceMediaSpringDataRepo.findOne(answerReferenceMediaID);
        assertNotNull(answerReferenceMediaEntity);
        assertThat(answerReferenceMediaEntity.getAnswerRefMediaId(), equalTo(answerReferenceMediaID));
        assertThat(new String(answerReferenceMediaEntity.getRefMedia()), equalTo("Testing"));

        answerReferenceMediaEntity.setRefMedia(new String("TestingNEW").getBytes());
        answerReferenceMediaEntity = answerReferenceMediaSpringDataRepo.save(answerReferenceMediaEntity);
        assertNotNull(answerReferenceMediaEntity);
        assertThat(answerReferenceMediaEntity.getAnswerRefMediaId(), equalTo(answerReferenceMediaID));

        answerReferenceMediaEntity = answerReferenceMediaSpringDataRepo.findOne(answerReferenceMediaID);
        assertNotNull(answerReferenceMediaEntity);
        assertThat(answerReferenceMediaEntity.getAnswerRefMediaId(), equalTo(answerReferenceMediaID));
        assertThat(new String(answerReferenceMediaEntity.getRefMedia()), equalTo("TestingNEW"));

        answerReferenceMediaSpringDataRepo.delete(answerReferenceMediaID);
        answerReferenceMediaEntity = answerReferenceMediaSpringDataRepo.findOne(answerReferenceMediaID);
        assertNull(answerReferenceMediaEntity);

        questionSpringDataRepository.delete(questionID);
        questionEntity = questionSpringDataRepository.findOne(questionID);
        assertNull(questionEntity);
    }

    @Test
    public void testFindByQuestionId() {

        assertNotNull(answerReferenceMediaSpringDataRepo);

        assertNotNull(questionSpringDataRepository);

        QuestionEntity questionEntity = new QuestionEntity(1L,
                "AnswerReferenceMediaSpringDataRepositoryTestQuesID Question",
                "AnswerReferenceMediaSpringDataRepositoryTestQuesID Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        final Long questionID = questionEntity.getQuestionId();

        AnswerReferenceMediaEntity answerReferenceMediaEntity = new AnswerReferenceMediaEntity();
        answerReferenceMediaEntity.setQuestionId(questionID);
        answerReferenceMediaEntity.setMediaTypeId(1);
        answerReferenceMediaEntity.setRefMedia(new String("Testing").getBytes());
        answerReferenceMediaEntity = answerReferenceMediaSpringDataRepo.save(answerReferenceMediaEntity);
        assertNotNull(answerReferenceMediaEntity);
        assertThat(answerReferenceMediaEntity.getAnswerRefMediaId(), greaterThan(0L));

        final Long answerReferenceMediaID = answerReferenceMediaEntity.getAnswerRefMediaId();

        answerReferenceMediaEntity = answerReferenceMediaSpringDataRepo.findOne(answerReferenceMediaID);
        assertNotNull(answerReferenceMediaEntity);
        assertThat(answerReferenceMediaEntity.getAnswerRefMediaId(), equalTo(answerReferenceMediaID));
        assertThat(new String(answerReferenceMediaEntity.getRefMedia()), equalTo("Testing"));

        answerReferenceMediaEntity.setRefMedia(new String("TestingNEW").getBytes());
        answerReferenceMediaEntity = answerReferenceMediaSpringDataRepo.save(answerReferenceMediaEntity);
        assertNotNull(answerReferenceMediaEntity);
        assertThat(answerReferenceMediaEntity.getAnswerRefMediaId(), equalTo(answerReferenceMediaID));

        answerReferenceMediaEntity = answerReferenceMediaSpringDataRepo.findOne(answerReferenceMediaID);
        assertNotNull(answerReferenceMediaEntity);
        assertThat(answerReferenceMediaEntity.getAnswerRefMediaId(), equalTo(answerReferenceMediaID));
        assertThat(new String(answerReferenceMediaEntity.getRefMedia()), equalTo("TestingNEW"));

        final List<AnswerReferenceMediaEntity> answerReferenceMediaEntityList = answerReferenceMediaSpringDataRepo.findByQuestionId(questionID);
        assertNotNull(answerReferenceMediaEntityList);
        assertThat(answerReferenceMediaEntityList.size(), equalTo(1));
        assertThat(answerReferenceMediaEntityList.get(0).getAnswerRefMediaId(), equalTo(answerReferenceMediaID));
        assertThat(new String(answerReferenceMediaEntityList.get(0).getRefMedia()), equalTo("TestingNEW"));

        answerReferenceMediaSpringDataRepo.delete(answerReferenceMediaID);
        answerReferenceMediaEntity = answerReferenceMediaSpringDataRepo.findOne(answerReferenceMediaID);
        assertNull(answerReferenceMediaEntity);

        questionSpringDataRepository.delete(questionID);
        questionEntity = questionSpringDataRepository.findOne(questionID);
        assertNull(questionEntity);
    }

}
