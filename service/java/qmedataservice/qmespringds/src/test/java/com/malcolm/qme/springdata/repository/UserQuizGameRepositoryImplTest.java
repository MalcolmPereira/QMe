/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizGameRepositoryImplTest.java
 * Date      : 5/18/15
 * Developer : Malcolm
 * Purpose   : Tests for UserQuizGame Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserQuizGame;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserQuizGameRepository;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.UserQuizGameEntity;
import com.malcolm.qme.springdata.entity.UserQuizGameEntityId;
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
public class UserQuizGameRepositoryImplTest {
    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserQuizGameRepository")
    private UserQuizGameRepository userQuizGameRepo;

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepo;

    @Mock
    private UserQuizGameSpringDataRepository userQuizGameSpringDataRepositoryMOCK;

    @InjectMocks
    private UserQuizGameRepository userQuizGameRepositoryWithMock;

    @Before
    public void initMocks(){
        userQuizGameRepositoryWithMock = new UserQuizGameRepositoryImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() throws QMeException {
        assertNotNull(userQuizGameRepo);
        final List<UserQuizGame> userQuizGameList = userQuizGameRepo.findAll();
        assertNotNull(userQuizGameList);
        assertThat(userQuizGameList.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(userQuizGameRepo);
        UserQuizGame userQuizGame = new UserQuizGame(1L, 1L, 1L);
        userQuizGame = userQuizGameRepo.findById(userQuizGame);
        assertNotNull(userQuizGame);
        assertThat(userQuizGame.getUserID(), equalTo(1L));
        assertThat(userQuizGame.getCategoryID(), equalTo(1L));
        assertThat(userQuizGame.getUserGameToken(), equalTo(1L));
    }

    @Test
    public void testCRUD() throws QMeException {

        assertNotNull(userRepo);

        assertNotNull(userQuizGameRepo);

        User user = new User("UserQuizGameRepositoryImplTest", "Test", "Test", "Test", "UserQuizGameRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        final Long userID = user.getUserID();

        UserQuizGame userQuizGame = new UserQuizGame(21234L, userID, 1L);
        userQuizGame = userQuizGameRepo.save(userQuizGame);
        assertNotNull(userQuizGame);
        assertThat(userQuizGame.getUserID(), equalTo(userID));
        assertThat(userQuizGame.getCategoryID(), equalTo(1L));
        assertThat(userQuizGame.getUserGameToken(), equalTo(21234L));

        userQuizGame = userQuizGameRepo.findById(userQuizGame);
        assertNotNull(userQuizGame);
        assertThat(userQuizGame.getUserID(), equalTo(userID));
        assertThat(userQuizGame.getCategoryID(), equalTo(1L));
        assertThat(userQuizGame.getUserGameToken(), equalTo(21234L));

        UserQuizGame userQuizGameUpdate = new UserQuizGame(userQuizGame.getUserGameToken(), userQuizGame.getUserID(), userQuizGame.getCategoryID(), 10, userQuizGame.getQuizStartDate(), LocalDateTime.now());
        userQuizGameUpdate = userQuizGameRepo.update(userQuizGameUpdate, userID);
        assertNotNull(userQuizGameUpdate);
        assertThat(userQuizGameUpdate.getUserID(), equalTo(userID));
        assertThat(userQuizGameUpdate.getCategoryID(), equalTo(1L));
        assertThat(userQuizGameUpdate.getUserGameToken(), equalTo(21234L));

        userQuizGame = userQuizGameRepo.findById(userQuizGame);
        assertNotNull(userQuizGame);
        assertThat(userQuizGame.getUserID(), equalTo(userID));
        assertThat(userQuizGame.getCategoryID(), equalTo(1L));
        assertThat(userQuizGame.getUserGameToken(), equalTo(21234L));

        userQuizGameRepo.delete(userQuizGame);
        userQuizGame = userQuizGameRepo.findById(userQuizGame);
        assertNull(userQuizGame);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

    }

    @Test
    public void testFindByUserId() throws QMeException {

        assertNotNull(userRepo);

        assertNotNull(userQuizGameRepo);

        User user = new User("UserQuizGameRepositoryImplTestByUserID", "Test", "Test", "Test", "UserQuizGameRepositoryImplTestByUserID@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        final Long userID = user.getUserID();

        UserQuizGame userQuizGame = new UserQuizGame(212345L, userID, 1L);
        userQuizGame = userQuizGameRepo.save(userQuizGame);
        assertNotNull(userQuizGame);
        assertThat(userQuizGame.getUserID(), equalTo(userID));
        assertThat(userQuizGame.getCategoryID(), equalTo(1L));
        assertThat(userQuizGame.getUserGameToken(), equalTo(212345L));

        userQuizGame = userQuizGameRepo.findById(userQuizGame);
        assertNotNull(userQuizGame);
        assertThat(userQuizGame.getUserID(), equalTo(userID));
        assertThat(userQuizGame.getCategoryID(), equalTo(1L));
        assertThat(userQuizGame.getUserGameToken(), equalTo(212345L));

        List<UserQuizGame> userQuizGameList = userQuizGameRepo.findByUserId(userID);
        assertNotNull(userQuizGameList);
        assertThat(userQuizGameList.size(), equalTo(1));
        assertThat(userQuizGameList.get(0).getUserID(), equalTo(userID));
        assertThat(userQuizGameList.get(0).getCategoryID(), equalTo(1L));
        assertThat(userQuizGameList.get(0).getUserGameToken(), equalTo(212345L));

        UserQuizGame userQuizGameUpdate = new UserQuizGame(userQuizGame.getUserGameToken(), userQuizGame.getUserID(), userQuizGame.getCategoryID(), 10, userQuizGame.getQuizStartDate(), LocalDateTime.now());
        userQuizGameUpdate = userQuizGameRepo.save(userQuizGameUpdate);
        assertNotNull(userQuizGameUpdate);
        assertThat(userQuizGameUpdate.getUserID(), equalTo(userID));
        assertThat(userQuizGameUpdate.getCategoryID(), equalTo(1L));
        assertThat(userQuizGameUpdate.getUserGameToken(), equalTo(212345L));

        userQuizGame = userQuizGameRepo.findById(userQuizGame);
        assertNotNull(userQuizGame);
        assertThat(userQuizGame.getUserID(), equalTo(userID));
        assertThat(userQuizGame.getCategoryID(), equalTo(1L));
        assertThat(userQuizGame.getUserGameToken(), equalTo(212345L));

        userQuizGameList = userQuizGameRepo.findByUserId(userID);
        assertNotNull(userQuizGameList);
        assertThat(userQuizGameList.size(), equalTo(1));
        assertThat(userQuizGameList.get(0).getUserID(), equalTo(userID));
        assertThat(userQuizGameList.get(0).getCategoryID(), equalTo(1L));
        assertThat(userQuizGameList.get(0).getUserGameToken(), equalTo(212345L));

        userQuizGameRepo.delete(userQuizGame);
        userQuizGame = userQuizGameRepo.findById(userQuizGame);
        assertNull(userQuizGame);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

    }

    @Test
    public void testFindByGameToken() throws QMeException {

        assertNotNull(userRepo);

        assertNotNull(userQuizGameRepo);

        User user = new User("UserQuizGameRepositoryImplTestByTK", "Test", "Test", "Test", "UserQuizGameRepositoryImplTestByTK@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        final Long userID = user.getUserID();

        UserQuizGame userQuizGame = new UserQuizGame(2123456L, userID, 1L);
        userQuizGame = userQuizGameRepo.save(userQuizGame);
        assertNotNull(userQuizGame);
        assertThat(userQuizGame.getUserID(), equalTo(userID));
        assertThat(userQuizGame.getCategoryID(), equalTo(1L));
        assertThat(userQuizGame.getUserGameToken(), equalTo(2123456L));

        userQuizGame = userQuizGameRepo.findById(userQuizGame);
        assertNotNull(userQuizGame);
        assertThat(userQuizGame.getUserID(), equalTo(userID));
        assertThat(userQuizGame.getCategoryID(), equalTo(1L));
        assertThat(userQuizGame.getUserGameToken(), equalTo(2123456L));

        userQuizGame = userQuizGameRepo.findByGameToken(2123456L);
        assertNotNull(userQuizGame);
        assertThat(userQuizGame.getUserID(), equalTo(userID));
        assertThat(userQuizGame.getCategoryID(), equalTo(1L));
        assertThat(userQuizGame.getUserGameToken(), equalTo(2123456L));

        UserQuizGame userQuizGameUpdate = new UserQuizGame(userQuizGame.getUserGameToken(), userQuizGame.getUserID(), userQuizGame.getCategoryID(), 10, userQuizGame.getQuizStartDate(), LocalDateTime.now());
        userQuizGameUpdate = userQuizGameRepo.save(userQuizGameUpdate);
        assertNotNull(userQuizGameUpdate);
        assertThat(userQuizGameUpdate.getUserID(), equalTo(userID));
        assertThat(userQuizGameUpdate.getCategoryID(), equalTo(1L));
        assertThat(userQuizGameUpdate.getUserGameToken(), equalTo(2123456L));

        userQuizGame = userQuizGameRepo.findById(userQuizGame);
        assertNotNull(userQuizGame);
        assertThat(userQuizGame.getUserID(), equalTo(userID));
        assertThat(userQuizGame.getCategoryID(), equalTo(1L));
        assertThat(userQuizGame.getUserGameToken(), equalTo(2123456L));

        userQuizGame = userQuizGameRepo.findByGameToken(2123456L);
        assertNotNull(userQuizGame);
        assertThat(userQuizGame.getUserID(), equalTo(userID));
        assertThat(userQuizGame.getCategoryID(), equalTo(1L));
        assertThat(userQuizGame.getUserGameToken(), equalTo(2123456L));

        userQuizGameRepo.delete(userQuizGame);
        userQuizGame = userQuizGameRepo.findById(userQuizGame);
        assertNull(userQuizGame);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    }

    @Test
    public void testFindAllNullReturn() throws QMeException {
        when(userQuizGameSpringDataRepositoryMOCK.findAll()).thenReturn(null);
        List<UserQuizGame> userQuizGameList = userQuizGameRepositoryWithMock.findAll();
        verify(userQuizGameSpringDataRepositoryMOCK).findAll();
        assertNotNull(userQuizGameList);
        assertThat(userQuizGameList.size(), equalTo(0));
    }

    @Test(expected = QMeException.class)
    public void testFindAllQMeException() throws QMeException {
        when(userQuizGameSpringDataRepositoryMOCK.findAll()).thenThrow(new RuntimeException("some error"));
        userQuizGameRepositoryWithMock.findAll();
        verify(userQuizGameSpringDataRepositoryMOCK).findAll();
    }

    @Test(expected = QMeException.class)
    public void testFindByUserIdQMeException() throws QMeException {
        when(userQuizGameSpringDataRepositoryMOCK.findByUserId(1L)).thenThrow(new RuntimeException("some error"));
        userQuizGameRepositoryWithMock.findByUserId(1L);
        verify(userQuizGameSpringDataRepositoryMOCK).findByUserId(1L);
    }

    @Test(expected = QMeException.class)
    public void testFindByGameTokenQMeException() throws QMeException {
        when(userQuizGameSpringDataRepositoryMOCK.findByGameToken(1L)).thenThrow(new RuntimeException("some error"));
        userQuizGameRepositoryWithMock.findByGameToken(1L);
        verify(userQuizGameSpringDataRepositoryMOCK).findByGameToken(1L);
    }

    @Test(expected = QMeException.class)
    public void testFindByIDQMeException() throws QMeException {
        when(userQuizGameSpringDataRepositoryMOCK.findOne(Matchers.<UserQuizGameEntityId>anyObject())).thenThrow(new RuntimeException("some error"));
        userQuizGameRepositoryWithMock.findById(new UserQuizGame(2123456L, 1L, 1L));
        verify(userQuizGameSpringDataRepositoryMOCK).findOne(Matchers.<UserQuizGameEntityId>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testSaveQMeException() throws QMeException {
        when(userQuizGameSpringDataRepositoryMOCK.save(Matchers.<UserQuizGameEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userQuizGameRepositoryWithMock.save(new UserQuizGame(2123456L, 1L, 1L));
        verify(userQuizGameSpringDataRepositoryMOCK).save(Matchers.<UserQuizGameEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testUpdateQMeException() throws QMeException {
        when(userQuizGameSpringDataRepositoryMOCK.save(Matchers.<UserQuizGameEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userQuizGameRepositoryWithMock.update(new UserQuizGame(2123456L, 1L, 1L), 1L);
        verify(userQuizGameSpringDataRepositoryMOCK).save(Matchers.<UserQuizGameEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testDeleteQMeException() throws QMeException {
        doThrow(new RuntimeException("some error")).when(userQuizGameSpringDataRepositoryMOCK).delete(Matchers.<UserQuizGameEntityId>anyObject());
        userQuizGameRepositoryWithMock.delete(new UserQuizGame(2123456L, 1L, 1L));
        verify(userQuizGameSpringDataRepositoryMOCK).delete(Matchers.<UserQuizGameEntityId>anyObject());
    }
}
