/**
 * Name      : com.malcolm.qme.core.domain.AnswerOption.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Repository Class
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.User;


/**
 * @Author: Malcolm
 */
public interface UserRepository extends QMeRepository<User>{
    /**
     * Find User By User Name
     * @param userName
     * @return
     */
    public User findByUserName(String userName);

    /**
     * Find User By User Email
     * @param userEmail
     * @return
     */
    public User findByUserEmail(String userEmail);
}
