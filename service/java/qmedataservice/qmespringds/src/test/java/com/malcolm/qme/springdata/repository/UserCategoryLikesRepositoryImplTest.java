/**
 * Name      : com.malcolm.qme.springdata.repository.UserCategoryLikesRepositoryImplTest.java
 * Date      : 5/13/2015
 * Developer : Malcolm
 * Purpose   : Tests for User Category Likes Repository Implementation
 */
package com.malcolm.qme.springdata.repository;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserCategoryLikes;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.core.repository.UserCategoryLikesRepository;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;

/**
 * @Author: malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
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
    
    @Test
    public void testFindAll(){
        assertNotNull(userCategoryLikesRepo);
        List<UserCategoryLikes> userCategoryLikesList = userCategoryLikesRepo.findAll();
        assertNotNull(userCategoryLikesList);
        assertThat(userCategoryLikesList.size(), greaterThan(0));
    }

    @Test
    public void testFindById(){
        assertNotNull(userCategoryLikesRepo);
        UserCategoryLikes userCategoryLikes = new UserCategoryLikes(1L,1L);
        userCategoryLikes = userCategoryLikesRepo.findById(userCategoryLikes);
        assertNotNull(userCategoryLikes);
        assertThat(userCategoryLikes.getUserID(), equalTo(1L));
        assertThat(userCategoryLikes.getCategoryID(), equalTo(1L));
    }
    
    @Test
    public void testCRUD() {
    	
    	assertNotNull(userCategoryLikesRepo);

        assertNotNull(userRepo);

        assertNotNull(categoryRepo);
        
        User user = new User("UserCategoryLikesRepositoryImplTest", "Test", "Test", "Test","UserCategoryLikesRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));
        Long userID = user.getUserID();
        
        Category category = new Category("UserCategoryLikesRepositoryImplTest", userID);
        category = categoryRepo.save(category);
        assertNotNull(category);
        assertThat(category.getCategoryID(), greaterThan(0L));
        Long catID = category.getCategoryID();
       
        UserCategoryLikes userCategoryLikes = new UserCategoryLikes(userID,catID);
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
    public void testFindByUserId() {
    	
    	assertNotNull(userCategoryLikesRepo);

        assertNotNull(userRepo);

        assertNotNull(categoryRepo);
        
        User user = new User("UCatLikesRepositoryImplTestByUser", "Test", "Test", "Test","UCatLikesRepositoryImplTestByUser@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(Long.valueOf(user.getUserID()).intValue(), greaterThan(0));
        Long userID = user.getUserID();
        
        Category category = new Category("UCatLikesRepositoryImplTestByUser", userID);
        category = categoryRepo.save(category);
        assertNotNull(category);
        assertThat(category.getCategoryID(), greaterThan(0L));
        Long catID = category.getCategoryID();
       
        UserCategoryLikes userCategoryLikes = new UserCategoryLikes(userID,catID);
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

}
