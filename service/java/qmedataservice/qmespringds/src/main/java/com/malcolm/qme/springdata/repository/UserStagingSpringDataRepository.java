/**
 * Name      : com.malcolm.qme.springdata.repository.UserSpringDataRepository.java
 * Date      : 9/21/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserStagingEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.UserStagingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Malcolm
 */
public interface UserStagingSpringDataRepository extends JpaRepository<UserStagingEntity, Long> {
    /**
     * Find By Staging Token
     *
     * @param stagingToken
     * @return
     */
    UserStagingEntity findByStagingTokenIgnoreCase(String stagingToken);
}
