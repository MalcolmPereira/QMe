/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizLikesRepositoryImplTest.java
 * Date      : May 17, 2015
 * Developer : Malcolm
 * Purpose   : Tests for UserQuizLikes Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.domain.Quiz;
import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserQuizLikes;
import com.malcolm.qme.core.repository.*;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.UserQuizLikesEntity;
import com.malcolm.qme.springdata.entity.UserQuizLikesEntityId;
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
public class UserQuizLikesRepositoryImplTest {
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

    /**
     * Category Repository
     */
    @Autowired
    @Qualifier("CategoryRepository")
    private CategoryRepository categoryRepo;

    /**
     * UserQuizLikes Repository
     */
    @Autowired
    @Qualifier("UserQuizLikesRepository")
    private UserQuizLikesRepository userQuizLikesRepository;

    @Mock
    private UserQuizLikesSpringDataRepository userQuizLikesSpringDataRepositoryMOCK;

    @InjectMocks
    private UserQuizLikesRepository userQuizLikesRepositoryWithMock;


    @Before
    public void initMocks(){
        userQuizLikesRepositoryWithMock = new UserQuizLikesRepositoryImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() throws QMeException {
        assertNotNull(userQuizLikesRepository);
        final List<UserQuizLikes> userQuizLikesList = userQuizLikesRepository.findAll();
        assertNotNull(userQuizLikesList);
        assertThat(userQuizLikesList.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(userQuizLikesRepository);
        UserQuizLikes userQuizLikes = new UserQuizLikes(1L, 1L);
        userQuizLikes = userQuizLikesRepository.findById(userQuizLikes);
        assertNotNull(userQuizLikes);
        assertThat(userQuizLikes.getUserID(), equalTo(1L));
        assertThat(userQuizLikes.getQuizID(), equalTo(1L));
    }

    @Test
    public void testCRUD() throws QMeException {
        assertNotNull(userRepo);

        assertNotNull(categoryRepo);

        assertNotNull(quizRepository);

        assertNotNull(userQuizLikesRepository);

        User user = new User("UserQuizLikesRepositoryImplTest", "Test", "Test", "Test", "UserQuizLikesRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();


        Category category = new Category("UserQuizLikesRepositoryImplTest", 1L);
        category = categoryRepo.save(category);
        assertNotNull(category);
        assertThat(category.getCategoryID(), greaterThan(0L));
        Long catID = category.getCategoryID();

        Quiz quiz = new Quiz("UserQuizLikesRepositoryImplTest Quiz", "UserQuizLikesRepositoryImplTest Quiz Desc", catID, 0, userID);
        quiz = quizRepository.save(quiz);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), greaterThan(0L));
        final Long quizID = quiz.getQuizID();

        UserQuizLikes userQuizLikes = new UserQuizLikes(userID, quizID);
        userQuizLikes = userQuizLikesRepository.save(userQuizLikes);
        assertNotNull(userQuizLikes);
        assertThat(userQuizLikes.getUserID(), equalTo(userID));
        assertThat(userQuizLikes.getQuizID(), equalTo(quizID));

        userQuizLikes = userQuizLikesRepository.findById(userQuizLikes);
        assertNotNull(userQuizLikes);
        assertThat(userQuizLikes.getUserID(), equalTo(userID));
        assertThat(userQuizLikes.getQuizID(), equalTo(quizID));

        userQuizLikes = userQuizLikesRepository.update(userQuizLikes, userID);
        assertNotNull(userQuizLikes);
        assertThat(userQuizLikes.getUserID(), equalTo(userID));
        assertThat(userQuizLikes.getQuizID(), equalTo(quizID));

        userQuizLikesRepository.delete(userQuizLikes);
        userQuizLikes = userQuizLikesRepository.findById(userQuizLikes);
        assertNull(userQuizLikes);

        quizRepository.delete(quizID);
        quiz = quizRepository.findById(quizID);
        assertNull(quiz);

        categoryRepo.delete(catID);
        category = categoryRepo.findById(catID);
        assertNull(category);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    }

    @Test
    public void testFindByUserId() throws QMeException {
        assertNotNull(userRepo);

        assertNotNull(categoryRepo);

        assertNotNull(quizRepository);

        assertNotNull(userQuizLikesRepository);

        User user = new User("UserQuizLikesRepositoryImplTestByUserID", "Test", "Test", "Test", "UserQuizLikesRepositoryImplTestByUserID@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();


        Category category = new Category("UserQuizLikesRepositoryImplTestByUserID", 1L);
        category = categoryRepo.save(category);
        assertNotNull(category);
        assertThat(category.getCategoryID(), greaterThan(0L));
        Long catID = category.getCategoryID();

        Quiz quiz = new Quiz("UserQuizLikesRepositoryImplTestByUserID Quiz", "UserQuizLikesRepositoryImplTestByUserID Quiz Desc", catID, 0, userID);
        quiz = quizRepository.save(quiz);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), greaterThan(0L));
        final Long quizID = quiz.getQuizID();

        UserQuizLikes userQuizLikes = new UserQuizLikes(userID, quizID);
        userQuizLikes = userQuizLikesRepository.save(userQuizLikes);
        assertNotNull(userQuizLikes);
        assertThat(userQuizLikes.getUserID(), equalTo(userID));
        assertThat(userQuizLikes.getQuizID(), equalTo(quizID));

        userQuizLikes = userQuizLikesRepository.findById(userQuizLikes);
        assertNotNull(userQuizLikes);
        assertThat(userQuizLikes.getUserID(), equalTo(userID));
        assertThat(userQuizLikes.getQuizID(), equalTo(quizID));

        final List<UserQuizLikes> userQuizList = userQuizLikesRepository.findByUserId(userID);
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(1));
        assertThat(userQuizList.get(0).getUserID(), equalTo(userID));
        assertThat(userQuizList.get(0).getQuizID(), equalTo(quizID));

        userQuizLikesRepository.delete(userQuizLikes);
        userQuizLikes = userQuizLikesRepository.findById(userQuizLikes);
        assertNull(userQuizLikes);

        quizRepository.delete(quizID);
        quiz = quizRepository.findById(quizID);
        assertNull(quiz);

        categoryRepo.delete(catID);
        category = categoryRepo.findById(catID);
        assertNull(category);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    }

    @Test
    public void testFindByQuizId() throws QMeException {
        assertNotNull(userRepo);

        assertNotNull(categoryRepo);

        assertNotNull(quizRepository);

        assertNotNull(userQuizLikesRepository);

        User user = new User("UserQuizLikesRepositoryImplTestByUserID", "Test", "Test", "Test", "UserQuizLikesRepositoryImplTestByUserID@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();


        Category category = new Category("UserQuizLikesRepositoryImplTestByUserID", 1L);
        category = categoryRepo.save(category);
        assertNotNull(category);
        assertThat(category.getCategoryID(), greaterThan(0L));
        Long catID = category.getCategoryID();

        Quiz quiz = new Quiz("UserQuizLikesRepositoryImplTestByUserID Quiz", "UserQuizLikesRepositoryImplTestByUserID Quiz Desc", catID, 0, userID);
        quiz = quizRepository.save(quiz);
        assertNotNull(quiz);
        assertThat(quiz.getQuizID(), greaterThan(0L));
        final Long quizID = quiz.getQuizID();

        UserQuizLikes userQuizLikes = new UserQuizLikes(userID, quizID);
        userQuizLikes = userQuizLikesRepository.save(userQuizLikes);
        assertNotNull(userQuizLikes);
        assertThat(userQuizLikes.getUserID(), equalTo(userID));
        assertThat(userQuizLikes.getQuizID(), equalTo(quizID));

        userQuizLikes = userQuizLikesRepository.findById(userQuizLikes);
        assertNotNull(userQuizLikes);
        assertThat(userQuizLikes.getUserID(), equalTo(userID));
        assertThat(userQuizLikes.getQuizID(), equalTo(quizID));

        final List<UserQuizLikes> userQuizList = userQuizLikesRepository.findByQuizId(quizID);
        assertNotNull(userQuizList);
        assertThat(userQuizList.size(), equalTo(1));
        assertThat(userQuizList.get(0).getUserID(), equalTo(userID));
        assertThat(userQuizList.get(0).getQuizID(), equalTo(quizID));

        userQuizLikesRepository.delete(userQuizLikes);
        userQuizLikes = userQuizLikesRepository.findById(userQuizLikes);
        assertNull(userQuizLikes);

        quizRepository.delete(quizID);
        quiz = quizRepository.findById(quizID);
        assertNull(quiz);

        categoryRepo.delete(catID);
        category = categoryRepo.findById(catID);
        assertNull(category);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    }

    @Test
    public void testFindAllNullReturn() throws QMeException {
        when(userQuizLikesSpringDataRepositoryMOCK.findAll()).thenReturn(null);
        final List<UserQuizLikes> userQuizLikesList = userQuizLikesRepositoryWithMock.findAll();
        verify(userQuizLikesSpringDataRepositoryMOCK).findAll();
        assertNotNull(userQuizLikesList);
        assertThat(userQuizLikesList.size(), equalTo(0));
    }

    @Test(expected = QMeException.class)
    public void testFindAllQMeException() throws QMeException {
        when(userQuizLikesSpringDataRepositoryMOCK.findAll()).thenThrow(new RuntimeException("some error"));
        userQuizLikesRepositoryWithMock.findAll();
        verify(userQuizLikesSpringDataRepositoryMOCK).findAll();
    }

    @Test(expected = QMeException.class)
    public void testFindByUserIDQMeException() throws QMeException {
        when(userQuizLikesSpringDataRepositoryMOCK.findByUserId(1L)).thenThrow(new RuntimeException("some error"));
        userQuizLikesRepositoryWithMock.findByUserId(1L);
        verify(userQuizLikesSpringDataRepositoryMOCK).findByUserId(1L);
    }

    @Test(expected = QMeException.class)
    public void testFindByQuizIDQMeException() throws QMeException {
        when(userQuizLikesSpringDataRepositoryMOCK.findByQuizId(1L)).thenThrow(new RuntimeException("some error"));
        userQuizLikesRepositoryWithMock.findByQuizId(1L);
        verify(userQuizLikesSpringDataRepositoryMOCK).findByQuizId(1L);
    }

    @Test(expected = QMeException.class)
    public void testFindByIDQMeException() throws QMeException {
        when(userQuizLikesSpringDataRepositoryMOCK.findOne(Matchers.<UserQuizLikesEntityId>anyObject())).thenThrow(new RuntimeException("some error"));
        userQuizLikesRepositoryWithMock.findById(new UserQuizLikes(1L, 1L));
        verify(userQuizLikesSpringDataRepositoryMOCK).findOne(Matchers.<UserQuizLikesEntityId>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testSaveQMeException() throws QMeException {
        when(userQuizLikesSpringDataRepositoryMOCK.save(Matchers.<UserQuizLikesEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userQuizLikesRepositoryWithMock.save(new UserQuizLikes(1L, 1L));
        verify(userQuizLikesSpringDataRepositoryMOCK).save(Matchers.<UserQuizLikesEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testUpdateQMeException() throws QMeException {
        when(userQuizLikesSpringDataRepositoryMOCK.save(Matchers.<UserQuizLikesEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userQuizLikesRepositoryWithMock.update(new UserQuizLikes(1L, 1L), 1L);
        verify(userQuizLikesSpringDataRepositoryMOCK).save(Matchers.<UserQuizLikesEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testDeleteQMeException() throws QMeException {
        doThrow(new RuntimeException("some error")).when(userQuizLikesSpringDataRepositoryMOCK).delete(Matchers.<UserQuizLikesEntityId>anyObject());
        userQuizLikesRepositoryWithMock.delete(new UserQuizLikes(1L, 1L));
        verify(userQuizLikesSpringDataRepositoryMOCK).delete(Matchers.<UserQuizLikesEntityId>anyObject());
    }
}
