/**
 * Name      : com.malcolm.qme.core.domain.AnswerOption.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.User;


/**
 * @author Malcolm
 */
public interface UserRepository extends QMeRepository<User, Long> {
    /**
     * Find User By User Name
     *
     * @param userName User Name
     * @return User for unique user name
     */
    User findByUserName(String userName);

    /**
     * Find User By User Email
     *
     * @param userEmail User Email
     * @return User for unique user email
     */
    User findByUserEmail(String userEmail);
}
