/**
 * Name      : com.malcolm.qme.springdata.repository.UserCategoryLikesSpringDataRepositoryTest.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : Tests for SpringData JPA UserCategoryLikes Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.config.QMeSpringDataJPAConfig;
import com.malcolm.qme.springdata.entity.CategoryEntity;
import com.malcolm.qme.springdata.entity.UserCategoryLikesEntity;
import com.malcolm.qme.springdata.entity.UserCategoryLikesEntityId;
import com.malcolm.qme.springdata.entity.UserEntity;
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
public class UserCategoryLikesSpringDataRepositoryTest {

    /**
     * UserCategoryLikesEntity Repository
     */
    @Autowired
    private UserCategoryLikesSpringDataRepository userCategoryLikesSpringDataRepo;

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
    public void testFindAll() {
        assertNotNull(userCategoryLikesSpringDataRepo);
        List<UserCategoryLikesEntity> userCategoryLikesEntities = userCategoryLikesSpringDataRepo.findAll();
        assertNotNull(userCategoryLikesEntities);
        assertThat(userCategoryLikesEntities.size(), greaterThan(0));
    }

    @Test
    public void testFindById() {
        assertNotNull(userCategoryLikesSpringDataRepo);

        UserCategoryLikesEntityId userCategoryLikesEntityId = new UserCategoryLikesEntityId();
        userCategoryLikesEntityId.setUserId(1L);
        userCategoryLikesEntityId.setCatId(1L);

        UserCategoryLikesEntity userCategoryLikesEntity = userCategoryLikesSpringDataRepo.findOne(userCategoryLikesEntityId);
        assertNotNull(userCategoryLikesEntity);
        assertThat(userCategoryLikesEntity.getId().getUserId(), equalTo(1L));
        assertThat(userCategoryLikesEntity.getId().getCatId(), equalTo(1L));
    }

    @Test
    public void testCRUD() {
        assertNotNull(userCategoryLikesSpringDataRepo);

        assertNotNull(userSpringDataRepo);

        assertNotNull(categorySpringDataRepository);

        UserEntity userEntity = new UserEntity("UserCategoryLikesSpringDataRepositoryTest", "Test", "Test", "UserCategoryLikesSpringDataRepositoryTest@test.com", "Test", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        Long userID = userEntity.getUserId();

        CategoryEntity categoryEntity = new CategoryEntity("UserCategoryLikesSpringDataRepositoryTest", 0L, LocalDateTime.now(), userID);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), greaterThan(0L));
        Long catID = categoryEntity.getCatId();

        UserCategoryLikesEntity userCategoryLikesEntity = new UserCategoryLikesEntity();
        UserCategoryLikesEntityId userCategoryLikesEntityId = new UserCategoryLikesEntityId();
        userCategoryLikesEntityId.setUserId(userID);
        userCategoryLikesEntityId.setCatId(catID);
        userCategoryLikesEntity.setId(userCategoryLikesEntityId);

        userCategoryLikesEntity = userCategoryLikesSpringDataRepo.save(userCategoryLikesEntity);


        userCategoryLikesEntity = userCategoryLikesSpringDataRepo.findOne(userCategoryLikesEntityId);
        assertNotNull(userCategoryLikesEntity);
        assertThat(userCategoryLikesEntity.getId().getUserId(), equalTo(userID));
        assertThat(userCategoryLikesEntity.getId().getCatId(), equalTo(catID));

        userCategoryLikesSpringDataRepo.delete(userCategoryLikesEntityId);
        userCategoryLikesEntity = userCategoryLikesSpringDataRepo.findOne(userCategoryLikesEntityId);
        assertNull(userCategoryLikesEntity);

        categorySpringDataRepository.delete(catID);
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNull(categoryEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);

    }

    @Test
    public void testFindByUserId() {
        assertNotNull(userCategoryLikesSpringDataRepo);

        assertNotNull(userSpringDataRepo);

        assertNotNull(categorySpringDataRepository);

        UserEntity userEntity = new UserEntity("UCatLikesSpringDataRepositoryTestByUSERID", "Test", "Test", "UserCategoryLikesSpringDataRepositoryTestByUSERID@test.com", "Test", LocalDateTime.now(), LocalDateTime.now(),LocalDateTime.now());
        userEntity = userSpringDataRepo.save(userEntity);
        assertNotNull(userEntity);
        assertThat(userEntity.getUserId(), greaterThan(0L));
        Long userID = userEntity.getUserId();

        CategoryEntity categoryEntity = new CategoryEntity("UCatLikesSpringDataRepositoryTestByUSERID", 0L, LocalDateTime.now(), userID);
        categoryEntity = categorySpringDataRepository.save(categoryEntity);
        assertNotNull(categoryEntity);
        assertThat(categoryEntity.getCatId(), greaterThan(0L));
        Long catID = categoryEntity.getCatId();

        UserCategoryLikesEntity userCategoryLikesEntity = new UserCategoryLikesEntity();
        UserCategoryLikesEntityId userCategoryLikesEntityId = new UserCategoryLikesEntityId();
        userCategoryLikesEntityId.setUserId(userID);
        userCategoryLikesEntityId.setCatId(catID);
        userCategoryLikesEntity.setId(userCategoryLikesEntityId);

        userCategoryLikesEntity = userCategoryLikesSpringDataRepo.save(userCategoryLikesEntity);

        userCategoryLikesEntity = userCategoryLikesSpringDataRepo.findOne(userCategoryLikesEntityId);
        assertNotNull(userCategoryLikesEntity);
        assertThat(userCategoryLikesEntity.getId().getUserId(), equalTo(userID));
        assertThat(userCategoryLikesEntity.getId().getCatId(), equalTo(catID));

        List<UserCategoryLikesEntity> userCategoryLikesEntityList = userCategoryLikesSpringDataRepo.findByUserId(userID);
        assertNotNull(userCategoryLikesEntityList);
        assertThat(userCategoryLikesEntityList.size(), equalTo(1));

        userCategoryLikesSpringDataRepo.delete(userCategoryLikesEntityId);
        userCategoryLikesEntity = userCategoryLikesSpringDataRepo.findOne(userCategoryLikesEntityId);
        assertNull(userCategoryLikesEntity);

        categorySpringDataRepository.delete(catID);
        categoryEntity = categorySpringDataRepository.findOne(catID);
        assertNull(categoryEntity);

        userSpringDataRepo.delete(userID);
        userEntity = userSpringDataRepo.findOne(userID);
        assertNull(userEntity);

    }
}

