/**
 * Name      : com.malcolm.qme.springdata.repository.UserCategoryLikesRepositoryImplTest.java
 * Date      : 5/13/2015
 * Developer : Malcolm
 * Purpose   : Tests for User Category Likes Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserCategoryLikes;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserCategoryLikesRepository;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.UserCategoryLikesEntity;
import com.malcolm.qme.springdata.entity.UserCategoryLikesEntityId;
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
public class UserCategoryLikesRepositoryImplTest {


    /**
     * Spring Data UserCategoryLikesEntity Repository
     */
    @Autowired
    @Qualifier("UserCategoryLikesRepository")
    private UserCategoryLikesRepository userCategoryLikesRepo;

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepo;

    /**
     * Category Repository
     */
    @Autowired
    @Qualifier("CategoryRepository")
    private CategoryRepository categoryRepo;

    @Mock
    private UserCategoryLikesSpringDataRepository userCategoryLikesSpringDataRepositoryMOCK;

    @InjectMocks
    private UserCategoryLikesRepository userCategoryLikesRepositoryWithMock;

    @Before
    public void initMocks(){
        userCategoryLikesRepositoryWithMock = new UserCategoryLikesRepositoryImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() throws QMeException {
        assertNotNull(userCategoryLikesRepo);
        List<UserCategoryLikes> userCategoryLikesList = userCategoryLikesRepo.findAll();
        assertNotNull(userCategoryLikesList);
        assertThat(userCategoryLikesList.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(userCategoryLikesRepo);
        UserCategoryLikes userCategoryLikes = new UserCategoryLikes(1L, 1L);
        userCategoryLikes = userCategoryLikesRepo.findById(userCategoryLikes);
        assertNotNull(userCategoryLikes);
        assertThat(userCategoryLikes.getUserID(), equalTo(1L));
        assertThat(userCategoryLikes.getCategoryID(), equalTo(1L));
    }

    @Test
    public void testCRUD() throws QMeException {

        assertNotNull(userCategoryLikesRepo);

        assertNotNull(userRepo);

        assertNotNull(categoryRepo);

        User user = new User("UserCategoryLikesRepositoryImplTest", "Test", "Test", "Test", "UserCategoryLikesRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        Category category = new Category("UserCategoryLikesRepositoryImplTest", userID);
        category = categoryRepo.save(category);
        assertNotNull(category);
        assertThat(category.getCategoryID(), greaterThan(0L));
        Long catID = category.getCategoryID();

        UserCategoryLikes userCategoryLikes = new UserCategoryLikes(userID, catID);
        userCategoryLikes = userCategoryLikesRepo.save(userCategoryLikes);

        userCategoryLikes = userCategoryLikesRepo.findById(userCategoryLikes);
        assertNotNull(userCategoryLikes);
        assertThat(userCategoryLikes.getUserID(), equalTo(userID));
        assertThat(userCategoryLikes.getCategoryID(), equalTo(catID));

        userCategoryLikesRepo.delete(userCategoryLikes);
        userCategoryLikes = userCategoryLikesRepo.findById(userCategoryLikes);
        assertNull(userCategoryLikes);

        categoryRepo.delete(catID);
        category = categoryRepo.findById(catID);
        assertNull(category);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

    }

    @Test
    public void testFindByUserId() throws QMeException {

        assertNotNull(userCategoryLikesRepo);

        assertNotNull(userRepo);

        assertNotNull(categoryRepo);

        User user = new User("UCatLikesRepositoryImplTestByUser", "Test", "Test", "Test", "UCatLikesRepositoryImplTestByUser@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        Category category = new Category("UCatLikesRepositoryImplTestByUser", userID);
        category = categoryRepo.save(category);
        assertNotNull(category);
        assertThat(category.getCategoryID(), greaterThan(0L));
        Long catID = category.getCategoryID();

        UserCategoryLikes userCategoryLikes = new UserCategoryLikes(userID, catID);
        userCategoryLikes = userCategoryLikesRepo.save(userCategoryLikes);

        userCategoryLikes = userCategoryLikesRepo.findById(userCategoryLikes);
        assertNotNull(userCategoryLikes);
        assertThat(userCategoryLikes.getUserID(), equalTo(userID));
        assertThat(userCategoryLikes.getCategoryID(), equalTo(catID));

        List<UserCategoryLikes> userCategoryLikesList = userCategoryLikesRepo.findByUserId(userID);
        assertNotNull(userCategoryLikesList);
        assertThat(userCategoryLikesList.size(), equalTo(1));

        userCategoryLikesRepo.delete(userCategoryLikes);
        userCategoryLikes = userCategoryLikesRepo.findById(userCategoryLikes);
        assertNull(userCategoryLikes);

        categoryRepo.delete(catID);
        category = categoryRepo.findById(catID);
        assertNull(category);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

    }

    @Test
    public void testFindAllNullReturn() throws QMeException {
        when(userCategoryLikesSpringDataRepositoryMOCK.findAll()).thenReturn(null);
        List<UserCategoryLikes> userCategoryLikesList = userCategoryLikesRepositoryWithMock.findAll();
        verify(userCategoryLikesSpringDataRepositoryMOCK).findAll();
        assertNotNull(userCategoryLikesList);
        assertThat(userCategoryLikesList.size(), equalTo(0));
    }

    @Test(expected = QMeException.class)
    public void testFindAllQMeException() throws QMeException {
        when(userCategoryLikesSpringDataRepositoryMOCK.findAll()).thenThrow(new RuntimeException("some error"));
        userCategoryLikesRepositoryWithMock.findAll();
        verify(userCategoryLikesSpringDataRepositoryMOCK).findAll();
    }

    @Test(expected = QMeException.class)
    public void testFindByUserIDQMeException() throws QMeException {
        when(userCategoryLikesSpringDataRepositoryMOCK.findByUserId(1L)).thenThrow(new RuntimeException("some error"));
        userCategoryLikesRepositoryWithMock.findByUserId(1L);
        verify(userCategoryLikesSpringDataRepositoryMOCK).findByUserId(1L);
    }

    @Test(expected = QMeException.class)
    public void testFindByIDQMeException() throws QMeException {
        when(userCategoryLikesSpringDataRepositoryMOCK.findOne(Matchers.<UserCategoryLikesEntityId>anyObject())).thenThrow(new RuntimeException("some error"));
        UserCategoryLikes userCategoryLikes = new UserCategoryLikes(1L,1L);
        userCategoryLikesRepositoryWithMock.findById(userCategoryLikes);
        verify(userCategoryLikesSpringDataRepositoryMOCK).findOne(Matchers.<UserCategoryLikesEntityId>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testSaveQMeException() throws QMeException {
        when(userCategoryLikesSpringDataRepositoryMOCK.save(Matchers.<UserCategoryLikesEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userCategoryLikesRepositoryWithMock.save(new UserCategoryLikes(1L, 1L));
        verify(userCategoryLikesSpringDataRepositoryMOCK).save(Matchers.<UserCategoryLikesEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testUpdateQMeException() throws QMeException {
        when(userCategoryLikesSpringDataRepositoryMOCK.save(Matchers.<UserCategoryLikesEntity>anyObject())).thenThrow(new RuntimeException("some error"));
        userCategoryLikesRepositoryWithMock.update(new UserCategoryLikes(1L, 1L), 1L);
        verify(userCategoryLikesSpringDataRepositoryMOCK).save(Matchers.<UserCategoryLikesEntity>anyObject());
    }

    @Test(expected = QMeException.class)
    public void testDeleteQMeException() throws QMeException {
        doThrow(new RuntimeException("some error")).when(userCategoryLikesSpringDataRepositoryMOCK).delete(Matchers.<UserCategoryLikesEntityId>anyObject());
        userCategoryLikesRepositoryWithMock.delete(new UserCategoryLikes(1L, 1L));
        verify(userCategoryLikesSpringDataRepositoryMOCK).delete(Matchers.<UserCategoryLikesEntityId>anyObject());
    }
}
