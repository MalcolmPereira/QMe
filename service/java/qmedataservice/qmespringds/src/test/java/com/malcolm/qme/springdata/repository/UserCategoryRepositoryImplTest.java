/**
 * Name      : com.malcolm.qme.springdata.repository.UserCategoryRepositoryImplTest.java
 * Date      : 5/12/15
 * Developer : Malcolm
 * Purpose   : Tests for UserCategory Repository
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.core.domain.Category;
import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserCategory;
import com.malcolm.qme.core.repository.CategoryRepository;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserCategoryRepository;
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
public class UserCategoryRepositoryImplTest {

    /**
     * Spring Data UserEntity Repository
     */
    @Autowired
    @Qualifier("UserCategoryRepository")
    private UserCategoryRepository userCategoryRepo;

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
    public void testFindAll() throws QMeException {
        assertNotNull(userCategoryRepo);
        List<UserCategory> userCategoryList = userCategoryRepo.findAll();
        assertNotNull(userCategoryList);
        assertThat(userCategoryList.size(), greaterThan(0));
    }

    @Test
    public void testFindById() throws QMeException {
        assertNotNull(userCategoryRepo);
        UserCategory userCategory = userCategoryRepo.findById(1L);
        assertNotNull(userCategory);
        assertThat(userCategory.getCategoryID(), equalTo(1L));
    }

    @Test
    public void testCRUD() throws QMeException {

        assertNotNull(userCategoryRepo);

        assertNotNull(userRepo);

        assertNotNull(categoryRepo);

        User user = new User("UserCategoryRepositoryImplTest", "Test", "Test", "Test", "UserCategoryRepositoryImplTest@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        Category category = new Category("UserCategoryRepositoryImplTest", 1L);
        category = categoryRepo.save(category);
        assertNotNull(category);
        assertThat(category.getCategoryID(), greaterThan(0L));
        Long catID = category.getCategoryID();

        UserCategory userCategory = new UserCategory(userID, catID);
        userCategory = userCategoryRepo.save(userCategory);
        Long userCatID = userCategory.getUserCategoryID();

        userCategory = userCategoryRepo.findById(userCatID);
        assertNotNull(userCategory);
        assertThat(userCategory.getUserCategoryID(), equalTo(userCatID));

        userCategoryRepo.delete(userCatID);
        userCategory = userCategoryRepo.findById(userCatID);
        assertNull(userCategory);

        categoryRepo.delete(catID);
        category = categoryRepo.findById(catID);
        assertNull(category);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);
    }

    @Test
    public void testFindByUserId() throws QMeException {

        assertNotNull(userCategoryRepo);

        assertNotNull(userRepo);

        assertNotNull(categoryRepo);

        User user = new User("UserCategoryRepositoryImplTestByUserID", "Test", "Test", "Test", "UserCategoryRepositoryImplTestByUserID@test.com");
        user = userRepo.save(user);
        assertNotNull(user);
        assertThat(user.getUserID(), greaterThan(0L));
        Long userID = user.getUserID();

        Category category = new Category("UserCategoryRepositoryImplTestByUserID", 1L);
        category = categoryRepo.save(category);
        assertNotNull(category);
        assertThat(category.getCategoryID(), greaterThan(0L));
        Long catID = category.getCategoryID();

        UserCategory userCategory = new UserCategory(userID, catID);
        userCategory = userCategoryRepo.save(userCategory);
        Long userCatID = userCategory.getUserCategoryID();


        userCategory = userCategoryRepo.findById(userCatID);
        assertNotNull(userCategory);
        assertThat(userCategory.getUserCategoryID(), equalTo(userCatID));

        List<UserCategory> userCategoryList = userCategoryRepo.findByUserID(userID);
        assertNotNull(userCategoryList);
        assertThat(userCategoryList.size(), equalTo(1));

        userCategoryRepo.delete(userCatID);
        userCategory = userCategoryRepo.findById(userCatID);
        assertNull(userCategory);

        categoryRepo.delete(catID);
        category = categoryRepo.findById(catID);
        assertNull(category);

        userRepo.delete(userID);
        user = userRepo.findById(userID);
        assertNull(user);

    }
}
