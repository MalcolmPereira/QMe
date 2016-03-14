/**
 * Name      : com.malcolm.qme.core.domain.fixtures.QuestionFixtures.java
 * Date      : 3/14/16
 * Developer : Malcolm
 * Purpose   : Test Fixtures for Question
 */
package com.malcolm.qme.core.domain.fixtures;

import com.malcolm.qme.core.domain.Question;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
public class QuestionFixtures {

    /**
     * Simple Question
     *
     * @return Question
     */
    public static Question simpleQuestion(){
        return new  Question(1L, 1L, "Some question text",
                "Some question answer", 1, 1L,
                LocalDateTime.now(), 1L,
                LocalDateTime.now(), 1L) ;
    }

    /**
     * Simple Question List
     * @return Question List
     */
    public static List<Question> simpleQuestionList(){
        List<Question> questionList = new ArrayList<>();
        questionList.add(new Question(1L, 1L, "Some question text - 1", "Some question answer - 1", 1, 1L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L));
        questionList.add(new Question(2L, 1L, "Some question text - 2", "Some question answer - 2", 1, 1L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L));
        questionList.add(new Question(3L, 1L, "Some question text - 3", "Some question answer - 3", 1, 1L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L));
        questionList.add(new Question(4L, 1L, "Some question text - 4", "Some question answer - 4", 1, 1L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L));
        questionList.add(new Question(5L, 1L, "Some question text - 5", "Some question answer - 5", 1, 1L, LocalDateTime.now(), 1L, LocalDateTime.now(), 1L));
        return questionList;
    }
}
