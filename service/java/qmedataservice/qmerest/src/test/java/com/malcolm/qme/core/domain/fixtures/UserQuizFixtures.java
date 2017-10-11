/**
 * Name      : com.malcolm.qme.core.domain.fixtures.UserQuizFixtures.java
 * Date      : 9/26/2017
 * Developer : Malcolm
 * Purpose   : Test Fixtures for User Quiz
 **/
package com.malcolm.qme.core.domain.fixtures;

import com.malcolm.qme.core.domain.UserQuiz;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
public class UserQuizFixtures {

    /**
     * Return Simple User Quiz List
     *
     * @return User Quiz List
     */
    public static List<UserQuiz> simpleUserQuizList() {
        List<UserQuiz> userQuizList = new ArrayList<>();
        UserQuiz userQuiz = new UserQuiz(1L,1L, 1L, 1L, LocalDateTime.now(),LocalDateTime.now(), "somequiztoken", 10,10, true);
        userQuizList.add(userQuiz);
        userQuiz = new UserQuiz(2L,1L, 1L, 1L, LocalDateTime.now(),LocalDateTime.now(), "somequiztoken", 10,10, true);
        userQuizList.add(userQuiz);
        userQuiz = new UserQuiz(3L,1L, 1L, 1L, LocalDateTime.now(),LocalDateTime.now(), "somequiztoken", 10,10, true);
        userQuizList.add(userQuiz);
        userQuiz = new UserQuiz(4L,1L, 1L, 1L, LocalDateTime.now(),LocalDateTime.now(), "somequiztoken", 10,10, true);
        userQuizList.add(userQuiz);
        userQuiz = new UserQuiz(5L,1L, 1L, 1L, LocalDateTime.now(),LocalDateTime.now(), "somequiztoken", 10,10, true);
        userQuizList.add(userQuiz);
        return userQuizList;
    }

    /**
     * Return Simple User Quiz
     *
     * @return User Quiz
     */
    public static UserQuiz simpleUserQuiz() {
        return new UserQuiz(1L,1L, 1L, 1L, LocalDateTime.now(),LocalDateTime.now(), "somequiztoken", 10,10, true);
    }
}
