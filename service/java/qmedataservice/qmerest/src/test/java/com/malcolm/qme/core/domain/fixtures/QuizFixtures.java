/**
 * Name      : com.malcolm.qme.core.domain.fixtures.QuizFixtures.java
 * Date      : 6/26/2017
 * Developer : Malcolm
 * Purpose   : Test Fixtures for Quiz
 */
package com.malcolm.qme.core.domain.fixtures;

import com.malcolm.qme.core.domain.Quiz;
import com.malcolm.qme.core.domain.QuizQuestion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
public class QuizFixtures {

    /**
     * Simple Quiz
     *
     * @return Quiz
     */
    public static Quiz simpleQuiz(){
        return new Quiz(1L, "Some simple quiz", "Some simple quiz", 1l, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L);
    }

    /**
     * Simple Quiz List
     * @return Quiz List
     */
    public static List<Quiz> simpleQuizList(){
        List<Quiz> quizList = new ArrayList<>();
        quizList.add(new Quiz(1L, "Some quiz - 1", "Some quiz - 1", 1l, 1L, 1L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L));
        quizList.add(new Quiz(2L, "Some quiz - 2", "Some quiz - 2", 1l, 2L, 2L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L));
        quizList.add(new Quiz(3L, "Some quiz - 3", "Some quiz - 3", 1l, 3L, 3L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L));
        quizList.add(new Quiz(4L, "Some quiz - 4", "Some quiz - 4", 1l, 4L, 4L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L));
        quizList.add(new Quiz(5L, "Some quiz - 5", "Some quiz - 5", 1l, 5L, 5L, 3, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L));
        return quizList;
    }

    /**
     * Simple Quiz Queszion List
     * @return Quiz List
     */
    public static List<QuizQuestion> simpleQuizQuestionList() {
        List<QuizQuestion> quizQuestionList = new ArrayList<>();
        quizQuestionList.add(new QuizQuestion(1L, 1L, 1L));
        quizQuestionList.add(new QuizQuestion(2L, 1L, 2L));
        quizQuestionList.add(new QuizQuestion(3L, 1L, 3L));
        quizQuestionList.add(new QuizQuestion(4L, 1L, 4L));
        quizQuestionList.add(new QuizQuestion(5L, 1L, 5L));
        return quizQuestionList;
    }
}
