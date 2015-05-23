/**
 * Name      : com.malcolm.qme.springdata.repository.QuestionHitSpringDataRepositoryTest.java
 * Date      : 5/14/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA QuestionHitEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.CategoryEntity;
import com.malcolm.qme.springdata.entity.QuestionEntity;
import com.malcolm.qme.springdata.entity.QuestionHitEntity;
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
public class QuestionHitSpringDataRepositoryTest {

    /**
     * QuestionHitEntity Repository
     */
    @Autowired
    private QuestionHitSpringDataRepository questionHitSpringDataRepo;

    /**
     * QuestionEntity Repository
     */
    @Autowired
    private QuestionSpringDataRepository questionSpringDataRepository;

    /**
     * CategoryEntity Repository
     */
    @Autowired
    private CategorySpringDataRepository categorySpringDataRepository;

    @Test
    public void testFindAll() {
        assertNotNull(questionHitSpringDataRepo);
        final List<QuestionHitEntity> questionHitEntities = questionHitSpringDataRepo.findAll();
        assertNotNull(questionHitEntities);
        assertThat(questionHitEntities.size(), greaterThan(0));
    }

    @Test
    public void testFindById() {
        assertNotNull(questionHitSpringDataRepo);
        final QuestionHitEntity questionHitEntity = questionHitSpringDataRepo.findOne(1L);
        assertNotNull(questionHitEntity);
        assertThat(questionHitEntity.getQuestionId(), equalTo(1L));
        assertThat(questionHitEntity.getCatId(), equalTo(1L));
    }

    @Test
    public void testCRUD() {

        assertNotNull(questionHitSpringDataRepo);

        assertNotNull(categorySpringDataRepository);

        assertNotNull(questionSpringDataRepository);

        CategoryEntity categoryEntity = new CategoryEntity("QuestionHitSpringDataRepositoryTest", 0L, LocalDateTime.now(), 1L);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), greaterThan(0L));
        final Long catID = categoryEntity.getCatId();

        QuestionEntity questionEntity = new QuestionEntity(catID,
                "QuestionHitSpringDataRepositoryTest Question",
                "QuestionHitSpringDataRepositoryTest Answer",
                1, 0L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
        questionEntity = questionSpringDataRepository.save(questionEntity);
        assertNotNull(questionEntity);
        assertThat(questionEntity.getQuestionId(), greaterThan(0L));
        final Long questionID = questionEntity.getQuestionId();

        QuestionHitEntity questionHitEntity = new QuestionHitEntity(questionID, catID, 0L, 0L, 0L);
        questionHitEntity = questionHitSpringDataRepo.save(questionHitEntity);
        assertNotNull(questionHitEntity);
        assertThat(questionHitEntity.getQuestionId(), equalTo(questionID));
        assertThat(questionHitEntity.getCatId(), equalTo(catID));

        questionHitEntity = questionHitSpringDataRepo.findOne(questionID);
        assertNotNull(questionHitEntity);
        assertThat(questionHitEntity.getQuestionId(), equalTo(questionID));
        assertThat(questionHitEntity.getCatId(), equalTo(catID));

        questionHitEntity.setQuestionHit(1L);
        questionHitEntity.setRightCount(1L);
        questionHitEntity = questionHitSpringDataRepo.save(questionHitEntity);
        assertNotNull(questionHitEntity);
        assertThat(questionHitEntity.getQuestionId(), equalTo(questionID));
        assertThat(questionHitEntity.getCatId(), equalTo(catID));
        assertThat(questionHitEntity.getQuestionHit(), equalTo(1L));
        assertThat(questionHitEntity.getRightCount(), equalTo(1L));

        questionHitSpringDataRepo.delete(questionID);
        questionHitEntity = questionHitSpringDataRepo.findOne(questionID);
        assertNull(questionHitEntity);

        questionSpringDataRepository.delete(questionID);
        questionEntity = questionSpringDataRepository.findOne(questionID);
        assertNull(questionEntity);

        categorySpringDataRepository.delete(catID);
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNull(categoryEntity);

    }

}
