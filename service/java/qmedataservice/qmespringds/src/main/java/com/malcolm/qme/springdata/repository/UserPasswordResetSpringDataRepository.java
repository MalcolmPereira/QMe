/**
 * Name      : com.malcolm.qme.springdata.repository.UserPasswordResetSpringDataRepository.java
 * Date      : 5/28/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserPasswordResetEntity Repository
 */

package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.UserPasswordResetEntity;
import com.malcolm.qme.springdata.entity.UserPasswordResetEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Malcolm
 */
@Repository
interface UserPasswordResetSpringDataRepository extends JpaRepository<UserPasswordResetEntity, UserPasswordResetEntityId> {

    /**
     * Find all previous user token By User ID
     *
     * @param userId User ID
     * @return UserPasswordResetEntity List
     */
    @Query(value = "SELECT * FROM USER_PASSWORD_RESET WHERE USER_ID = ?1", nativeQuery = true)
    List<UserPasswordResetEntity> findByUserId(Long userId);

}
