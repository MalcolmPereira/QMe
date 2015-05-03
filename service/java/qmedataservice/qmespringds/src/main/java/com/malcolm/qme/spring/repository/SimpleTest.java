/**
 * Name      : com.malcolm.qme.spring.repository.SimpleTest.java
 * Date      : 5/2/15
 * Developer : malcolm
 * Purpose   : TODO:
 */

package com.malcolm.qme.spring.repository;/**
 * Created by malcolm on 5/2/15.
 */

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.repository.UserRepository;

import java.util.List;

/**
 * @Author: malcolm
 */
public class SimpleTest implements UserRepository {
    @java.lang.Override
    public User findByUserName(String userName) {
        return null;
    }

    @java.lang.Override
    public User findByUserEmail(String userEmail) {
        return null;
    }

    @java.lang.Override
    public List<User> findAll() {
        return null;
    }

    @java.lang.Override
    public User findById(long id) {
        return null;
    }

    @java.lang.Override
    public User save(User user) {
        return null;
    }

    @java.lang.Override
    public User update(User user, long updateUserId) {
        return null;
    }

    @java.lang.Override
    public void delete(long id) {

    }
}
