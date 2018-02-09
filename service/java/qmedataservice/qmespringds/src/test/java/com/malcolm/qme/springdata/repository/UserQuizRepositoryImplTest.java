/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizRepositoryImplTest.java
 * Date      : 5/18/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData UserQuiz Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Quiz;
import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserQuiz;
import com.malcolm.qme.core.repository.*;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.UserQuizEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 * @author malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
public class UserQuizRepositoryImplTest {

    /**
     * User Quiz Repository
     */
    @Autowired
    @Qualifier("UserQuizRepository")
    private UserQuizRepository userQuizRepository;

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepo;

    /**
     * Quiz Repository
     */
    @Autowired
    @Qualifier("QuizRepository")
    private QuizRepository quizRepository;

    @Mock
    private UserQuizSpringDataRepository userQuizSpringDataRepositoryMOCK;

    @InjectMocks
    private UserQuizRepository userQuizRepositoryWithMOCK;

    @Before
    public void initMocks() {
        userQuizRepositoryWithMOCK = new UserQuizRepositoryImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() throws QMeException {
        assertNotNull(userQuizRepository);
        List<UserQuiz> userQuizList = userQuizRepository.findAll();
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(userQuizRepository);
        UserQuiz userQuiz = userQuizRepository.findById(1L);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), equalTo(1L));
    }

    @Test
    public void testCRUD() throws QMeException {

        assertNotNull(userQuizRepository);

        assertNotNull(userRepo);

        assertNotNull(quizRepository);

        User user = new User("UserQuizRepositoryImplTest", "Test", "Test", "Test", "UserQuizRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        Quiz quiz = new Quiz(
                "UserQuizRepositoryImplTest Quiz", "UserQuizRepositoryImplTest Quiz Desc", 1L, 0, 1L);
        quiz = quizRepository.save(quiz);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), greaterThan(0L));
        final Long quizID = quiz.getQuizID();

        UserQuiz userQuiz = new UserQuiz(userID, quizID, 1L, 10, "Some token");
        userQuiz = userQuizRepository.save(userQuiz);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), greaterThan(0L));
        final Long userQuizID = userQuiz.getUserQuizID();

        userQuiz = userQuizRepository.findById(userQuizID);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuiz.getUserID(), equalTo(userID));
        assertThat(userQuiz.getQuizID(), equalTo(quizID));
        assertThat(userQuiz.getQuizMaxScore(), equalTo(10));


        UserQuiz userQuizUpdate = new UserQuiz(
                userQuiz.getUserQuizID(),
                userQuiz.getUserID(),
                userQuiz.getQuizID(),
                userQuiz.getCategoryID(),
                userQuiz.getQuizStartDate(),
                LocalDateTime.now(),
                userQuiz.getUserQuizToken(),
                10,
                userQuiz.getQuizMaxScore()
        );
        userQuizUpdate = userQuizRepository.update(userQuizUpdate, userID);
        assertNotNull(userQuizUpdate);
        assertThat(userQuizUpdate.getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuizUpdate.getUserID(), equalTo(userID));
        assertThat(userQuizUpdate.getQuizID(), equalTo(quizID));
        assertThat(userQuizUpdate.getQuizMaxScore(), equalTo(10));

        userQuiz = userQuizRepository.findById(userQuizID);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuiz.getUserID(), equalTo(userID));
        assertThat(userQuiz.getQuizID(), equalTo(quizID));
        assertThat(userQuiz.getQuizMaxScore(), equalTo(10));


        userQuizRepository.delete(userQuizID);
        userQuiz = userQuizRepository.findById(userQuizID);
        assertNull(userQuiz);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

        quizRepository.delete(quizID);
        quiz = quizRepository.findById(quizID);
        assertNull(quiz);
    }

    @Test
    public void testFindByUserId() throws QMeException {

        assertNotNull(userQuizRepository);

        assertNotNull(userRepo);

        assertNotNull(quizRepository);

        User user = new User("UserQuizRepositoryImplTest", "Test", "Test", "Test", "UserQuizRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        Quiz quiz = new Quiz(
                "UserQuizRepositoryImplTest Quiz", "UserQuizRepositoryImplTest Quiz Desc", 1L, 0, 1L);
        quiz = quizRepository.save(quiz);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), greaterThan(0L));
        final Long quizID = quiz.getQuizID();

        UserQuiz userQuiz = new UserQuiz(userID, quizID, 1L, 10, "Some token");
        userQuiz = userQuizRepository.save(userQuiz);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), greaterThan(0L));
        final Long userQuizID = userQuiz.getUserQuizID();

        userQuiz = userQuizRepository.findById(userQuizID);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuiz.getUserID(), equalTo(userID));
        assertThat(userQuiz.getQuizID(), equalTo(quizID));
        assertThat(userQuiz.getQuizMaxScore(), equalTo(10));


        List<UserQuiz> userQuizList = userQuizRepository.findByUserId(userID);
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(1));
        assertThat(userQuizList.get(0).getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuizList.get(0).getUserID(), equalTo(userID));
        assertThat(userQuizList.get(0).getQuizID(), equalTo(quizID));
        assertThat(userQuizList.get(0).getQuizMaxScore(), equalTo(10));


        UserQuiz userQuizUpdate = new UserQuiz(
                userQuiz.getUserQuizID(),
                userQuiz.getUserID(),
                userQuiz.getQuizID(),
                userQuiz.getCategoryID(),
                userQuiz.getQuizStartDate(),
                LocalDateTime.now(),
                userQuiz.getUserQuizToken(),
                10,
                userQuiz.getQuizMaxScore()
        );
        userQuizUpdate = userQuizRepository.update(userQuizUpdate, userID);
        assertNotNull(userQuizUpdate);
        assertThat(userQuizUpdate.getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuizUpdate.getUserID(), equalTo(userID));
        assertThat(userQuizUpdate.getQuizID(), equalTo(quizID));
        assertThat(userQuizUpdate.getQuizMaxScore(), equalTo(10));

        userQuiz = userQuizRepository.findById(userQuizID);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuiz.getUserID(), equalTo(userID));
        assertThat(userQuiz.getQuizID(), equalTo(quizID));
        assertThat(userQuiz.getQuizMaxScore(), equalTo(10));


        userQuizList = userQuizRepository.findByUserId(userID);
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(1));
        assertThat(userQuizList.get(0).getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuizList.get(0).getUserID(), equalTo(userID));
        assertThat(userQuizList.get(0).getQuizID(), equalTo(quizID));
        assertThat(userQuizList.get(0).getQuizMaxScore(), equalTo(10));


        userQuizRepository.delete(userQuizID);
        userQuiz = userQuizRepository.findById(userQuizID);
        assertNull(userQuiz);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

        quizRepository.delete(quizID);
        quiz = quizRepository.findById(quizID);
        assertNull(quiz);
    }

    @Test
    public void testFindByQuizId() throws QMeException {

        assertNotNull(userQuizRepository);

        assertNotNull(userRepo);

        assertNotNull(quizRepository);

        User user = new User("UserQuizRepositoryImplTest", "Test", "Test", "Test", "UserQuizRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        Quiz quiz = new Quiz(
                "UserQuizRepositoryImplTest Quiz", "UserQuizRepositoryImplTest Quiz Desc", 1L, 0, 1L);
        quiz = quizRepository.save(quiz);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), greaterThan(0L));
        final Long quizID = quiz.getQuizID();

        UserQuiz userQuiz = new UserQuiz(userID, quizID, 1L, 10, "Some token");
        userQuiz = userQuizRepository.save(userQuiz);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), greaterThan(0L));
        final Long userQuizID = userQuiz.getUserQuizID();

        userQuiz = userQuizRepository.findById(userQuizID);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuiz.getUserID(), equalTo(userID));
        assertThat(userQuiz.getQuizID(), equalTo(quizID));
        assertThat(userQuiz.getQuizMaxScore(), equalTo(10));


        List<UserQuiz> userQuizList = userQuizRepository.findByQuizId(quizID);
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(1));
        assertThat(userQuizList.get(0).getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuizList.get(0).getUserID(), equalTo(userID));
        assertThat(userQuizList.get(0).getQuizID(), equalTo(quizID));
        assertThat(userQuizList.get(0).getQuizMaxScore(), equalTo(10));


        UserQuiz userQuizUpdate = new UserQuiz(
                userQuiz.getUserQuizID(),
                userQuiz.getUserID(),
                userQuiz.getQuizID(),
                userQuiz.getCategoryID(),
                userQuiz.getQuizStartDate(),
                LocalDateTime.now(),
                userQuiz.getUserQuizToken(),
                10,
                userQuiz.getQuizMaxScore()
        );
        userQuizUpdate = userQuizRepository.update(userQuizUpdate, userID);
        assertNotNull(userQuizUpdate);
        assertThat(userQuizUpdate.getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuizUpdate.getUserID(), equalTo(userID));
        assertThat(userQuizUpdate.getQuizID(), equalTo(quizID));
        assertThat(userQuizUpdate.getQuizMaxScore(), equalTo(10));


        userQuiz = userQuizRepository.findById(userQuizID);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuiz.getUserID(), equalTo(userID));
        assertThat(userQuiz.getQuizID(), equalTo(quizID));
        assertThat(userQuiz.getQuizMaxScore(), equalTo(10));


        userQuizList = userQuizRepository.findByQuizId(quizID);
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(1));
        assertThat(userQuizList.get(0).getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuizList.get(0).getUserID(), equalTo(userID));
        assertThat(userQuizList.get(0).getQuizID(), equalTo(quizID));
        assertThat(userQuizList.get(0).getQuizMaxScore(), equalTo(10));

        userQuizRepository.delete(userQuizID);
        userQuiz = userQuizRepository.findById(userQuizID);
        assertNull(userQuiz);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

        quizRepository.delete(quizID);
        quiz = quizRepository.findById(quizID);
        assertNull(quiz);
    }

    @Test
    public void testFindAllNullReturn() throws QMeException {
        when(userQuizSpringDataRepositoryMOCK.findAll()).thenReturn(null);
        List<UserQuiz> userQuizList = userQuizRepositoryWithMOCK.findAll();
        verify(userQuizSpringDataRepositoryMOCK).findAll();
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(0));
    }

    @Test(expected = QMeException.class)
    public void testFindAllQMeException() throws QMeException {
        when(userQuizSpringDataRepositoryMOCK.findAll()).thenThrow(new RuntimeException("some error"));
        userQuizRepositoryWithMOCK.findAll();
        verify(userQuizSpringDataRepositoryMOCK).findAll();
    }

    @Test(expected = QMeException.class)
    public void testFindByUserIDQMeException() throws QMeException {
        when(userQuizSpringDataRepositoryMOCK.findByUserId(1L)).thenThrow(new RuntimeException("some error"));
        userQuizRepositoryWithMOCK.findByUserId(1L);
        verify(userQuizSpringDataRepositoryMOCK).findByUserId(1L);
    }

    @Test(expected = QMeException.class)
    public void testFindByQuizIdQMeException() throws QMeException {
        when(userQuizSpringDataRepositoryMOCK.findByQuizId(1L)).thenThrow(new RuntimeException("some error"));
        userQuizRepositoryWithMOCK.findByQuizId(1L);
        verify(userQuizSpringDataRepositoryMOCK).findByQuizId(1L);
    }

    @Test(expected = QMeException.class)
    public void testFindByIDQMeException() throws QMeException {
        when(userQuizSpringDataRepositoryMOCK.findOne(1L)).thenThrow(new RuntimeException("some error"));
        userQuizRepositoryWithMOCK.findById(1L);
        verify(userQuizSpringDataRepositoryMOCK).findOne(1L);
    }

    @Test(expected = QMeException.class)
    public void testSaveQMeException() throws QMeException {
        when(userQuizSpringDataRepositoryMOCK.save(Matchers.<UserQuizEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userQuizRepositoryWithMOCK.save(new UserQuiz(1L, 1L, 1L, 1, "Some token"));
        verify(userQuizSpringDataRepositoryMOCK).save(Matchers.<UserQuizEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testUpdateQMeException() throws QMeException {
        when(userQuizSpringDataRepositoryMOCK.save(Matchers.<UserQuizEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userQuizRepositoryWithMOCK.update(new UserQuiz(1L, 1L, 1L, 10, "Some token"), 1L);
        verify(userQuizSpringDataRepositoryMOCK).save(Matchers.<UserQuizEntity>anyObject());
    }


    @Test(expected = QMeException.class)
    public void testDeleteQMeException() throws QMeException {
        doThrow(new RuntimeException("some error")).when(userQuizSpringDataRepositoryMOCK).delete(1L);
        userQuizRepositoryWithMOCK.delete(1L);
        verify(userQuizSpringDataRepositoryMOCK).delete(1L);
    }

    @Test
    public void testFindCompletedByUserId() throws QMeException {
        assertNotNull(userQuizRepository);

        assertNotNull(userRepo);

        assertNotNull(quizRepository);

        User user = new User("UserQuizRepositoryImplTest", "Test", "Test", "Test", "UserQuizRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        Quiz quiz = new Quiz("UserQuizRepositoryImplTest Quiz", "UserQuizRepositoryImplTest Quiz Desc", 1L, 0, 1L);
        quiz = quizRepository.save(quiz);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), greaterThan(0L));
        final Long quizID = quiz.getQuizID();

        UserQuiz userQuiz = new UserQuiz(userID, quizID, 1L, 10, "Some token");
        userQuiz = userQuizRepository.save(userQuiz);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), greaterThan(0L));
        final Long userQuizID = userQuiz.getUserQuizID();

        userQuiz = userQuizRepository.findById(userQuizID);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuiz.getUserID(), equalTo(userID));
        assertThat(userQuiz.getQuizID(), equalTo(quizID));
        assertThat(userQuiz.getQuizMaxScore(), equalTo(10));

        List<UserQuiz> userQuizList = userQuizRepository.findCompletedByUserId(userID, new PageSort(0,50, true, UserQuizRepository.USERQUIZSORTFIELDS.QUIZNAME.toString()));
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(0));

        userQuizList = userQuizRepository.findPendingByUserId(userID, new PageSort(0,50, true, UserQuizRepository.USERQUIZSORTFIELDS.QUIZNAME.toString()));
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(1));

        UserQuiz userQuizUpdate = new UserQuiz(
                userQuiz.getUserQuizID(),
                userQuiz.getUserID(),
                userQuiz.getQuizID(),
                userQuiz.getCategoryID(),
                userQuiz.getQuizStartDate(),
                LocalDateTime.now(),
                userQuiz.getUserQuizToken(),
                10,
                userQuiz.getQuizMaxScore()
        );
        userQuizUpdate = userQuizRepository.update(userQuizUpdate, userID);
        assertNotNull(userQuizUpdate);
        assertThat(userQuizUpdate.getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuizUpdate.getUserID(), equalTo(userID));
        assertThat(userQuizUpdate.getQuizID(), equalTo(quizID));
        assertThat(userQuizUpdate.getQuizMaxScore(), equalTo(10));

        userQuizList = userQuizRepository.findCompletedByUserId(userID, new PageSort(0,50, true, UserQuizRepository.USERQUIZSORTFIELDS.QUIZNAME.toString()));
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(1));

        userQuizList = userQuizRepository.findPendingByUserId(userID, new PageSort(0,50, true, UserQuizRepository.USERQUIZSORTFIELDS.QUIZNAME.toString()));
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(0));

        userQuiz = userQuizRepository.findById(userQuizID);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuiz.getUserID(), equalTo(userID));
        assertThat(userQuiz.getQuizID(), equalTo(quizID));
        assertThat(userQuiz.getQuizMaxScore(), equalTo(10));

        userQuizRepository.delete(userQuizID);
        userQuiz = userQuizRepository.findById(userQuizID);
        assertNull(userQuiz);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

        quizRepository.delete(quizID);
        quiz = quizRepository.findById(quizID);
        assertNull(quiz);
    }

    @Test
    public void testFindPendingByUserId() throws QMeException {

        assertNotNull(userQuizRepository);

        assertNotNull(userRepo);

        assertNotNull(quizRepository);

        User user = new User("UserQuizRepositoryImplTest", "Test", "Test", "Test", "UserQuizRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        Quiz quiz = new Quiz(
                "UserQuizRepositoryImplTest Quiz", "UserQuizRepositoryImplTest Quiz Desc", 1L, 0, 1L);
        quiz = quizRepository.save(quiz);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), greaterThan(0L));
        final Long quizID = quiz.getQuizID();

        UserQuiz userQuiz = new UserQuiz(userID, quizID, 1L, 10, "Some token");
        userQuiz = userQuizRepository.save(userQuiz);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), greaterThan(0L));
        final Long userQuizID = userQuiz.getUserQuizID();

        userQuiz = userQuizRepository.findById(userQuizID);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuiz.getUserID(), equalTo(userID));
        assertThat(userQuiz.getQuizID(), equalTo(quizID));
        assertThat(userQuiz.getQuizMaxScore(), equalTo(10));


        List<UserQuiz> userQuizList = userQuizRepository.findPendingByUserId(userID, new PageSort(0,50, true, UserQuizRepository.USERQUIZSORTFIELDS.QUIZNAME.toString()));
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(1));
        assertThat(userQuizList.get(0).getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuizList.get(0).getUserID(), equalTo(userID));
        assertThat(userQuizList.get(0).getQuizID(), equalTo(quizID));
        assertThat(userQuizList.get(0).getQuizMaxScore(), equalTo(10));


        UserQuiz userQuizUpdate = new UserQuiz(
                userQuiz.getUserQuizID(),
                userQuiz.getUserID(),
                userQuiz.getQuizID(),
                userQuiz.getCategoryID(),
                userQuiz.getQuizStartDate(),
                LocalDateTime.now(),
                userQuiz.getUserQuizToken(),
                10,
                userQuiz.getQuizMaxScore()
        );
        userQuizUpdate = userQuizRepository.update(userQuizUpdate, userID);
        assertNotNull(userQuizUpdate);
        assertThat(userQuizUpdate.getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuizUpdate.getUserID(), equalTo(userID));
        assertThat(userQuizUpdate.getQuizID(), equalTo(quizID));
        assertThat(userQuizUpdate.getQuizMaxScore(), equalTo(10));


        userQuiz = userQuizRepository.findById(userQuizID);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), equalTo(userQuizID));
        assertThat(userQuiz.getUserID(), equalTo(userID));
        assertThat(userQuiz.getQuizID(), equalTo(quizID));
        assertThat(userQuiz.getQuizMaxScore(), equalTo(10));

        userQuizList = userQuizRepository.findPendingByUserId(userID, new PageSort(0,50, true, UserQuizRepository.USERQUIZSORTFIELDS.QUIZNAME.toString()));
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(0));

        userQuizRepository.delete(userQuizID);
        userQuiz = userQuizRepository.findById(userQuizID);
        assertNull(userQuiz);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

        quizRepository.delete(quizID);
        quiz = quizRepository.findById(quizID);
        assertNull(quiz);
    }

    @Test(expected = QMeException.class)
    public void testFindCompletedByUserIdQMeException() throws QMeException {
        when(userQuizSpringDataRepositoryMOCK.findCompletedByUserId(1L,any())).thenThrow(new RuntimeException("some error"));
        userQuizRepositoryWithMOCK.findCompletedByUserId(1L,any());
        verify(userQuizSpringDataRepositoryMOCK).findCompletedByUserId(1L,any());
    }

    @Test(expected = QMeException.class)
    public void testFindPendingByUserIdQMeException() throws QMeException {
        when(userQuizSpringDataRepositoryMOCK.findPendingByUserId(1L,any())).thenThrow(new RuntimeException("some error"));
        userQuizRepositoryWithMOCK.findPendingByUserId(1L,any());
        verify(userQuizSpringDataRepositoryMOCK).findPendingByUserId(1L,any());
    }

    @Test
    public void testFindQuizzesForUser() throws QMeException {
        assertNotNull(userQuizRepository);

        assertNotNull(userRepo);

        assertNotNull(quizRepository);

        User user = new User("UserQuizRepositoryImplTest 1", "Test", "Test", "Test", "UserQuizRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        Quiz quiz = new Quiz(
                "UserQuizRepositoryImplTest 1 Quiz", "UserQuizRepositoryImplTest 1 Quiz Desc", 1L, 0, 1L);
        quiz = quizRepository.save(quiz);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), greaterThan(0L));
        final Long quizID = quiz.getQuizID();

        UserQuiz userQuiz = new UserQuiz(userID, quizID, 1L, 10, "Some token");
        userQuiz = userQuizRepository.save(userQuiz);
        assertNotNull(userQuiz);
        assertThat(userQuiz.getUserQuizID(), greaterThan(0L));
        final Long userQuizID = userQuiz.getUserQuizID();

        Quiz quizAnother = new Quiz(
                "UserQuizRepositoryImplTest Quiz Test 2", "UserQuizRepositoryImplTest Quiz Test 2 Desc", 1L, 0, 1L);
        quizAnother = quizRepository.save(quizAnother);
        assertNotNull(quizAnother);
        assertThat(quizAnother.getQuizID(), greaterThan(0L));
        final Long quizIDAnother = quizAnother.getQuizID();

        List<UserQuiz> userQuizList = userQuizRepository.findQuizzesForUser(userID, new PageSort(0,50, true, UserQuizRepository.USERQUIZSORTFIELDS.QUIZNAME.toString()));
        assertNotNull(userQuizList);

        userQuizRepository.delete(userQuizID);
        userQuiz = userQuizRepository.findById(userQuizID);
        assertNull(userQuiz);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

        quizRepository.delete(quizID);
        quiz = quizRepository.findById(quizID);
        assertNull(quiz);

        quizRepository.delete(quizIDAnother);
        quizAnother = quizRepository.findById(quizIDAnother);
        assertNull(quizAnother);
    }
}