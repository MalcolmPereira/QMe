/**
 * Name      : com.malcolm.qme.springdata.repository.MediaTypeSpringDataRepository.java
 * Date      : 5/7/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA MediaTypeEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.malcolm.qme.springdata.entity.MediaTypeEntity;

/**
 * @author Malcolm
 */
interface MediaTypeSpringDataRepository extends JpaRepository<MediaTypeEntity, Integer> {
}
