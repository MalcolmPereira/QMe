/**
 * Name      : com.malcolm.qme.springdata.repository.UserCategorySpringDataRepositoryTest.java
 * Date      : 5/12/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA UserCategoryEntity Repository
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.CategoryEntity;
import com.malcolm.qme.springdata.entity.UserCategoryEntity;
import com.malcolm.qme.springdata.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @Author: malcolm
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {QMeSpringDataJPAConfig.class})
@TestExecutionListeners(listeners = {
        DependencyInjectionTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class UserCategorySpringDataRepositoryTest {

    /**
     * UserCategoryEntity Repository
     */
    @Autowired
    private UserCategorySpringDataRepository userCategorySpringDataRepo;


    /**
     * UserEntity Repository
     */
    @Autowired
    private UserSpringDataRepository userSpringDataRepo;


    /**
     * CategoryEntity Repository
     */
    @Autowired
    private CategorySpringDataRepository categorySpringDataRepository;

    @Test
    public void testFindAll(){
        assertNotNull(userCategorySpringDataRepo);
        List<UserCategoryEntity> userCategoryEntities = userCategorySpringDataRepo.findAll();
        assertNotNull(userCategoryEntities);
        assertThat(userCategoryEntities.size(), greaterThan(0));
    }

    @Test
    public void testFindById(){
        assertNotNull(userCategorySpringDataRepo);
        UserCategoryEntity userCategoryEntity = userCategorySpringDataRepo.findOne(1L);
        assertNotNull(userCategoryEntity);
        assertThat(userCategoryEntity.getCatId(), equalTo(1L));
    }

    @Test
    public void testCRUD() {

        assertNotNull(userCategorySpringDataRepo);

        assertNotNull(userSpringDataRepo);

        assertNotNull(categorySpringDataRepository);

        UserEntity userEntity = new UserEntity("UserCategorySpringDataRepositoryTest", "Test", "Test", "UserCategorySpringDataRepositoryTest@test.com", "Test", new Date(), new Date());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        Long userID = userEntity.getUserId();

        CategoryEntity categoryEntity = new CategoryEntity("UserCategorySpringDataRepositoryTest", 0L, new Date(), userID);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), greaterThan(0L));
        Long catID = categoryEntity.getCatId();


        UserCategoryEntity userCategoryEntity = new UserCategoryEntity(userID,catID);
        userCategoryEntity = userCategorySpringDataRepo.save(userCategoryEntity);
        Long userCatID = userCategoryEntity.getUserCatId();

        userCategoryEntity = userCategorySpringDataRepo.findOne(userCatID);
        assertNotNull(userCategoryEntity);
        assertThat(userCategoryEntity.getUserCatId(), equalTo(userCatID));

        userCategorySpringDataRepo.delete(userCatID);
        userCategoryEntity = userCategorySpringDataRepo.findOne(userCatID);
        assertNull(userCategoryEntity);

        categorySpringDataRepository.delete(catID);
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNull(categoryEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }

    @Test
    public void testFindByUserId() {

        assertNotNull(userCategorySpringDataRepo);

        assertNotNull(userSpringDataRepo);

        assertNotNull(categorySpringDataRepository);

        UserEntity userEntity = new UserEntity("UserCategorySpringDataRepositoryTestByUserID", "Test", "Test", "UserCategorySpringDataRepositoryTestByUserID@test.com", "Test", new Date(), new Date());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        Long userID = userEntity.getUserId();

        CategoryEntity categoryEntity = new CategoryEntity("UserCategorySpringDataRepositoryTestByUserID", 0L, new Date(), userID);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), greaterThan(0L));
        Long catID = categoryEntity.getCatId();


        UserCategoryEntity userCategoryEntity = new UserCategoryEntity(userID,catID);
        userCategoryEntity = userCategorySpringDataRepo.save(userCategoryEntity);
        Long userCatID = userCategoryEntity.getUserCatId();

        userCategoryEntity = userCategorySpringDataRepo.findOne(userCatID);
        assertNotNull(userCategoryEntity);
        assertThat(userCategoryEntity.getUserCatId(), equalTo(userCatID));

        List<UserCategoryEntity> userCategoryEntityList = userCategorySpringDataRepo.findByUserId(userID);
        assertNotNull(userCategoryEntityList);
        assertThat(userCategoryEntityList.size(), equalTo(1));

        userCategorySpringDataRepo.delete(userCatID);
        userCategoryEntity = userCategorySpringDataRepo.findOne(userCatID);
        assertNull(userCategoryEntity);

        categorySpringDataRepository.delete(catID);
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNull(categoryEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);
    }
}


