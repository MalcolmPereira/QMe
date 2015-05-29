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

/**
 * @author Malcolm
 */
interface UserPasswordResetSpringDataRepository extends JpaRepository<UserPasswordResetEntity, UserPasswordResetEntityId> {
}
