/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuestionLikesRepositoryImplTest.java
 * Date      : 5/14/2015
 * Developer : Malcolm
 * Purpose   : Tests for User Question Likes Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Question;
import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserQuestionLikes;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.QuestionRepository;
import com.malcolm.qme.core.repository.UserQuestionLikesRepository;
import com.malcolm.qme.core.repository.UserRepository;
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
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class UserQuestionLikesRepositoryImplTest {

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepo;

    /**
     * Question Repository
     */
    @Autowired
    @Qualifier("QuestionRepository")
    private QuestionRepository questionRepo;


    /**
     * Question Repository
     */
    @Autowired
    @Qualifier("UserQuestionLikesRepository")
    private UserQuestionLikesRepository userQuestionLikesRepo;

    @Test
    public void testFindAll() throws QMeException {
        assertNotNull(userQuestionLikesRepo);
        List<UserQuestionLikes> userQuestionLikesList = userQuestionLikesRepo.findAll();
        assertNotNull(userQuestionLikesList);
        assertThat(userQuestionLikesList.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(userQuestionLikesRepo);
        UserQuestionLikes userQuestionLikes = new UserQuestionLikes(1L, 1L);
        userQuestionLikes = userQuestionLikesRepo.findById(userQuestionLikes);
        assertNotNull(userQuestionLikes);
        assertThat(userQuestionLikes.getUserID(), equalTo(1L));
        assertThat(userQuestionLikes.getQuestionID(), equalTo(1L));
    }

    @Test
    public void testCRUD() throws QMeException {

        assertNotNull(userQuestionLikesRepo);

        assertNotNull(userRepo);

        assertNotNull(questionRepo);

        User user = new User("UserQuestionLikesRepositoryImplTest", "Test", "Test", "Test", "UserQuestionLikesRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        Question question = new Question(1L, "UserQuestionLikesRepositoryImplTest Question", "UserQuestionLikesRepositoryImplTest Answer", 1L);
        question = questionRepo.save(question);
        assertNotNull(question);
        assertThat(question.getQuestionID(), greaterThan(0L));
        Long questionID = question.getQuestionID();

        UserQuestionLikes userQuestionLikes = new UserQuestionLikes(userID, questionID);
        userQuestionLikes = userQuestionLikesRepo.save(userQuestionLikes);
        assertNotNull(userQuestionLikes);
        assertThat(userQuestionLikes.getUserID(), equalTo(userID));
        assertThat(userQuestionLikes.getQuestionID(), equalTo(questionID));

        userQuestionLikes = userQuestionLikesRepo.findById(userQuestionLikes);
        assertNotNull(userQuestionLikes);
        assertThat(userQuestionLikes.getUserID(), equalTo(userID));
        assertThat(userQuestionLikes.getQuestionID(), equalTo(questionID));

        userQuestionLikesRepo.delete(userQuestionLikes);
        userQuestionLikes = userQuestionLikesRepo.findById(userQuestionLikes);
        assertNull(userQuestionLikes);

        questionRepo.delete(questionID);
        question = questionRepo.findById(questionID);
        assertNull(question);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    }

    @Test
    public void testFindByUserId() throws QMeException {

        assertNotNull(userQuestionLikesRepo);

        assertNotNull(userRepo);

        assertNotNull(questionRepo);

        User user = new User("UserQuestionLikesRepositoryImplTestByUser", "Test", "Test", "Test", "UserQuestionLikesRepositoryImplTestByUser@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        Question question = new Question(1L, "UserQuestionLikesRepositoryImplTestByUser Question", "UserQuestionLikesRepositoryImplTestByUser Answer", 1L);
        question = questionRepo.save(question);
        assertNotNull(question);
        assertThat(question.getQuestionID(), greaterThan(0L));
        Long questionID = question.getQuestionID();

        UserQuestionLikes userQuestionLikes = new UserQuestionLikes(userID, questionID);
        userQuestionLikes = userQuestionLikesRepo.save(userQuestionLikes);
        assertNotNull(userQuestionLikes);
        assertThat(userQuestionLikes.getUserID(), equalTo(userID));
        assertThat(userQuestionLikes.getQuestionID(), equalTo(questionID));

        userQuestionLikes = userQuestionLikesRepo.findById(userQuestionLikes);
        assertNotNull(userQuestionLikes);
        assertThat(userQuestionLikes.getUserID(), equalTo(userID));
        assertThat(userQuestionLikes.getQuestionID(), equalTo(questionID));

        List<UserQuestionLikes> userQuestionLikesList = userQuestionLikesRepo.findByUserId(userID);
        assertNotNull(userQuestionLikesList);
        assertThat(userQuestionLikesList.size(), equalTo(1));

        userQuestionLikesList = userQuestionLikesRepo.findByQuestionId(questionID);
        assertNotNull(userQuestionLikesList);
        assertThat(userQuestionLikesList.size(), equalTo(1));

        userQuestionLikesRepo.delete(userQuestionLikes);
        userQuestionLikes = userQuestionLikesRepo.findById(userQuestionLikes);
        assertNull(userQuestionLikes);

        questionRepo.delete(questionID);
        question = questionRepo.findById(questionID);
        assertNull(question);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

    }

    @Test
    public void testFindByQuestionId() throws QMeException {

        assertNotNull(userQuestionLikesRepo);

        assertNotNull(userRepo);

        assertNotNull(questionRepo);

        User user = new User("UserQuestionLikesRepositoryImplTestByQID", "Test", "Test", "Test", "UserQuestionLikesRepositoryImplTestByQID@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        Question question = new Question(1L, "UserQuestionLikesRepositoryImplTestByQID Question", "UserQuestionLikesRepositoryImplTestByQID Answer", 1L);
        question = questionRepo.save(question);
        assertNotNull(question);
        assertThat(question.getQuestionID(), greaterThan(0L));
        Long questionID = question.getQuestionID();

        UserQuestionLikes userQuestionLikes = new UserQuestionLikes(userID, questionID);
        userQuestionLikes = userQuestionLikesRepo.save(userQuestionLikes);
        assertNotNull(userQuestionLikes);
        assertThat(userQuestionLikes.getUserID(), equalTo(userID));
        assertThat(userQuestionLikes.getQuestionID(), equalTo(questionID));

        userQuestionLikes = userQuestionLikesRepo.findById(userQuestionLikes);
        assertNotNull(userQuestionLikes);
        assertThat(userQuestionLikes.getUserID(), equalTo(userID));
        assertThat(userQuestionLikes.getQuestionID(), equalTo(questionID));

        List<UserQuestionLikes> userQuestionLikesList = userQuestionLikesRepo.findByUserId(userID);
        assertNotNull(userQuestionLikesList);
        assertThat(userQuestionLikesList.size(), equalTo(1));

        userQuestionLikesList = userQuestionLikesRepo.findByQuestionId(questionID);
        assertNotNull(userQuestionLikesList);
        assertThat(userQuestionLikesList.size(), equalTo(1));

        userQuestionLikesRepo.delete(userQuestionLikes);
        userQuestionLikes = userQuestionLikesRepo.findById(userQuestionLikes);
        assertNull(userQuestionLikes);

        questionRepo.delete(questionID);
        question = questionRepo.findById(questionID);
        assertNull(question);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

    }

}
