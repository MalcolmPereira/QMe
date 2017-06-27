/**
 * Name      : com.malcolm.qme.core.domain.fixtures.QuizQuestionFixtures.java
 * Date      : 6/27/2017
 * Developer : Malcolm
 * Purpose   : Test Fixtures for Quiz Question
 */
package com.malcolm.qme.core.domain.fixtures;

import com.malcolm.qme.core.domain.QuizQuestion;

/**
 * Test Fixtures for Quiz Question
 *
 * @author Malcolm
 */
public class QuizQuestionFixtures {

    /**
     * Simple Quiz
     *
     * @return Quiz
     */
    public static QuizQuestion simpleQuizQuestion(){
        return new QuizQuestion(1L, 1L, 1L);
    }
}
