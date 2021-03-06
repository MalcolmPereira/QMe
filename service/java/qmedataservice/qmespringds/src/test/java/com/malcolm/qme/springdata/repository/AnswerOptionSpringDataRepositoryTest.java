/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerOptionSpringDataRepositoryTest.java
 * Date      : 5/11/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA AnswerOptionEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.AnswerOptionEntity;
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
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class AnswerOptionSpringDataRepositoryTest {

    /**
     * AnswerOptionEntity Repository
     */
    @Autowired
    private AnswerOptionSpringDataRepository answerOptionSpringDataRepo;

    /**
     * QuestionEntity Repository
     */
    @Autowired
    private QuestionSpringDataRepository questionSpringDataRepository;


    @Test
    public void testFindAll() {
        assertNotNull(answerOptionSpringDataRepo);
        final List<AnswerOptionEntity> answerOptionEntities = answerOptionSpringDataRepo.findAll();
        assertNotNull(answerOptionEntities);
        assertThat(answerOptionEntities.size(), greaterThan(0));
    }

    @Test
    public void testFindById() {
        assertNotNull(answerOptionSpringDataRepo);
        final AnswerOptionEntity answerOptionEntity = answerOptionSpringDataRepo.findOne(1L);
        assertNotNull(answerOptionEntity);
        assertThat(answerOptionEntity.getOptionId(), equalTo(1L));
    }

    @Test
    public void testCRUD() {

        assertNotNull(answerOptionSpringDataRepo);

        assertNotNull(questionSpringDataRepository);

        QuestionEntity questionEntity = new QuestionEntity(1L,
                "AnswerOptionSpringDataRepositoryTest Question",
                "AnswerOptionSpringDataRepositoryTest Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);

        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));

        final Long questionID = questionEntity.getQuestionId();

        AnswerOptionEntity answerOptionEntity = new AnswerOptionEntity(questionID, "Option 1", (byte) 0);
        answerOptionEntity = answerOptionSpringDataRepo.save(answerOptionEntity);
        assertNotNull(answerOptionEntity);
        assertThat(answerOptionEntity.getOptionId(), greaterThan(0L));
        final Long answerOptionID = answerOptionEntity.getOptionId();

        answerOptionEntity = answerOptionSpringDataRepo.findOne(answerOptionID);
        assertNotNull(answerOptionEntity);
        assertThat(answerOptionEntity.getOptionId(), equalTo(answerOptionID));

        answerOptionEntity.setOptionText("Option 1 Updated");
        answerOptionEntity = answerOptionSpringDataRepo.save(answerOptionEntity);
        assertNotNull(answerOptionEntity);
        assertThat(answerOptionEntity.getOptionId(), equalTo(answerOptionID));
        assertThat(answerOptionEntity.getOptionText(), equalTo("Option 1 Updated"));

        answerOptionSpringDataRepo.delete(answerOptionID);
        answerOptionEntity = answerOptionSpringDataRepo.findOne(answerOptionID);
        assertNull(answerOptionEntity);

        questionSpringDataRepository.delete(questionID);
        questionEntity = questionSpringDataRepository.findOne(questionID);
        assertNull(questionEntity);
    }

    @Test
    public void testFindByQuestionId() {

        assertNotNull(answerOptionSpringDataRepo);

        assertNotNull(questionSpringDataRepository);

        QuestionEntity questionEntity = new QuestionEntity(1L,
                "AnswerOptionSpringDataRepositoryTestByQID Question",
                "AnswerOptionSpringDataRepositoryTestByQID Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);

        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));

        final Long questionID = questionEntity.getQuestionId();

        AnswerOptionEntity answerOptionEntity = new AnswerOptionEntity(questionID, "Option 1", (byte) 0);
        answerOptionEntity = answerOptionSpringDataRepo.save(answerOptionEntity);
        assertNotNull(answerOptionEntity);
        assertThat(answerOptionEntity.getOptionId(), greaterThan(0L));
        final Long answerOptionID = answerOptionEntity.getOptionId();

        answerOptionEntity = answerOptionSpringDataRepo.findOne(answerOptionID);
        assertNotNull(answerOptionEntity);
        assertThat(answerOptionEntity.getOptionId(), equalTo(answerOptionID));

        final List<AnswerOptionEntity> answerOptionList = answerOptionSpringDataRepo.findByQuestionId(questionID);
        assertNotNull(answerOptionList);
        assertThat(answerOptionList.get(0).getOptionId(), equalTo(answerOptionID));
        assertThat(answerOptionList.get(0).getOptionText(), equalTo("Option 1"));

        answerOptionEntity.setOptionText("Option 1 Updated");
        answerOptionEntity = answerOptionSpringDataRepo.save(answerOptionEntity);
        assertNotNull(answerOptionEntity);
        assertThat(answerOptionEntity.getOptionId(), equalTo(answerOptionID));
        assertThat(answerOptionEntity.getOptionText(), equalTo("Option 1 Updated"));

        answerOptionSpringDataRepo.delete(answerOptionID);
        answerOptionEntity = answerOptionSpringDataRepo.findOne(answerOptionID);
        assertNull(answerOptionEntity);

        questionSpringDataRepository.delete(questionID);
        questionEntity = questionSpringDataRepository.findOne(questionID);
        assertNull(questionEntity);
    }

}
