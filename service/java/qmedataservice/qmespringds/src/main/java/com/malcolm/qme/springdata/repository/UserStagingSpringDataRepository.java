/**
 * Name      : com.malcolm.qme.springdata.repository.UserSpringDataRepository.java
 * Date      : 9/21/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserStagingEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.UserStagingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

/**
 * @author Malcolm
 */
public interface UserStagingSpringDataRepository extends JpaRepository<UserStagingEntity, Long> {
    /**
     * Find By Staging Token
     *
     * @param stagingToken User Staging Token
     * @return User Staging Entity
     */
    UserStagingEntity findByStagingTokenIgnoreCase(String stagingToken);

    /**
     * Find By User Name
     * @param userName User Name
     * @return UserEntity List
     */
    UserStagingEntity findByUserNameIgnoreCase(String userName);

    /**
     * Find By User Email
     * @param userEmail User Email
     * @return UserEntity List
     */
    UserStagingEntity findByUserEmailIgnoreCase(String userEmail);

    /**
     * Delete Staging Users Staged more than 5 days ago
     * @param userStagingDate User Staging Date
     */
    void deleteByUserStagingDateLessThan(LocalDateTime userStagingDate);

}
