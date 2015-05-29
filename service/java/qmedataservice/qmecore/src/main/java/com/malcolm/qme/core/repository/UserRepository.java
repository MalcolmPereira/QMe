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
     * @throws QMeException
     */
    User findByUserName(String userName) throws QMeException;

    /**
     * Find User By User Email
     *
     * @param userEmail User Email
     * @return User for unique user email
     * @throws QMeException
     */
    User findByUserEmail(String userEmail) throws QMeException;
}
