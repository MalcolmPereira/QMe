/**
 * Name      : com.malcolm.qme.springdata.repository.UserSpringDataRepository.java
 * Date      : 5/3/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: Malcolm
 */
public interface UserSpringDataRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Find By User Name
     * @param userName
     * @return
     */
    public UserEntity findByUserNameIgnoreCase(String userName);

    /**
     * Find By User Email
     * @param userName
     * @return
     */
    public UserEntity findByUserEmailIgnoreCase(String userName);
}
