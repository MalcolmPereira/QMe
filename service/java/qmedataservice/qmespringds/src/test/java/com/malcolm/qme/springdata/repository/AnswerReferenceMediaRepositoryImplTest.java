/**
 * Name      : com.malcolm.qme.springdata.repository.AnswerReferenceMediaRepositoryImplTest.java
 * Date      : May 16, 2015
 * Developer : Malcolm
 * Purpose   : Tests for Answer Reference Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.AnswerReferenceMedia;
import com.malcolm.qme.core.domain.Question;
import com.malcolm.qme.core.repository.AnswerReferenceMediaRepository;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;


/**
 * @author Malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
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
    public void testFindAll() throws QMeException {
        assertNotNull(answerReferenceMediaRepository);
        final List<AnswerReferenceMedia> answerReferenceMediaList = answerReferenceMediaRepository.findAll();
        assertNotNull(answerReferenceMediaList);
        assertThat(answerReferenceMediaList.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(answerReferenceMediaRepository);
        final AnswerReferenceMedia answerReferenceMedia = answerReferenceMediaRepository.findById(1L);
        assertNotNull(answerReferenceMedia);
        assertThat(answerReferenceMedia.getAnswerRefMediaID(), equalTo(1L));
    }

    @Test
    public void testCRUD() throws QMeException {

        assertNotNull(answerReferenceMediaRepository);

        assertNotNull(questionRepository);

        Question question = new Question(1L, "AnswerReferenceMediaRepositoryImplTest Question", "AnswerReferenceMediaRepositoryImplTest Answer", 1L);

        question = questionRepository.save(question);
        assertNotNull(question);
        assertThat(question.getQuestionID(), greaterThan(0L));
        final Long questionID = question.getQuestionID();


        AnswerReferenceMedia answerReferenceMedia = new AnswerReferenceMedia(questionID, 1, new String("Testing").getBytes());
        answerReferenceMedia = answerReferenceMediaRepository.save(answerReferenceMedia);
        assertNotNull(answerReferenceMedia);
        assertThat(answerReferenceMedia.getAnswerRefMediaID(), greaterThan(0L));
        final Long answerReferenceMediaID = answerReferenceMedia.getAnswerRefMediaID();

        answerReferenceMedia = answerReferenceMediaRepository.findById(answerReferenceMediaID);
        assertNotNull(answerReferenceMedia);
        assertThat(answerReferenceMedia.getAnswerRefMediaID(), equalTo(answerReferenceMediaID));

        AnswerReferenceMedia answerReferenceMediaUpdate = new AnswerReferenceMedia(
                answerReferenceMedia.getAnswerRefMediaID(),
                questionID,
                1, new String("TestingNEW").getBytes());
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
    public void testFindByQuestionId() throws QMeException {

        assertNotNull(answerReferenceMediaRepository);

        assertNotNull(questionRepository);

        Question question = new Question(1L, "AnswerReferenceMediaRepositoryImplTest Question", "AnswerReferenceMediaRepositoryImplTest Answer", 1L);

        question = questionRepository.save(question);
        assertNotNull(question);
        assertThat(question.getQuestionID(), greaterThan(0L));
        final Long questionID = question.getQuestionID();


        AnswerReferenceMedia answerReferenceMedia = new AnswerReferenceMedia(questionID, 1, new String("Testing").getBytes());
        answerReferenceMedia = answerReferenceMediaRepository.save(answerReferenceMedia);
        assertNotNull(answerReferenceMedia);
        assertThat(answerReferenceMedia.getAnswerRefMediaID(), greaterThan(0L));
        final Long answerReferenceMediaID = answerReferenceMedia.getAnswerRefMediaID();

        answerReferenceMedia = answerReferenceMediaRepository.findById(answerReferenceMediaID);
        assertNotNull(answerReferenceMedia);
        assertThat(answerReferenceMedia.getAnswerRefMediaID(), equalTo(answerReferenceMediaID));

        List<AnswerReferenceMedia> answerReferenceMediaList = answerReferenceMediaRepository.findByQuestionId(questionID);
        assertNotNull(answerReferenceMediaList);
        assertThat(answerReferenceMediaList.size(), equalTo(1));
        assertThat(answerReferenceMediaList.get(0).getAnswerRefMediaID(), equalTo(answerReferenceMediaID));
        assertThat(new String(answerReferenceMediaList.get(0).getMedia()), equalTo("Testing"));


        assertNotNull(answerReferenceMedia);
        assertThat(answerReferenceMedia.getAnswerRefMediaID(), equalTo(answerReferenceMediaID));

        AnswerReferenceMedia answerReferenceMediaUpdate = new AnswerReferenceMedia(
                answerReferenceMedia.getAnswerRefMediaID(),
                questionID,
                1, new String("TestingNEW").getBytes());
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
