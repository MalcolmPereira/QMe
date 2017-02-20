/**
 * Name      : com.malcolm.qme.core.domain.fixtures.AnswerOptionFixtures.java
 * Date      : 2/20/17
 * Developer : Malcolm
 * Purpose   : Test Fixtures for Answer Option
 */
package com.malcolm.qme.core.domain.fixtures;

import com.malcolm.qme.core.domain.AnswerOption;

/**
 * @author Malcolm
 */
public class AnswerOptionFixtures {

    /**
     * Simple Answer Option Fixture
     * @param answerOptionID Answer Option ID
     * @param questionID Question ID
     * @param optionText Option Text
     * @param correct Option Correct
     * @return AnswerOption
     */
    public static AnswerOption simpleAnswerOption (Long answerOptionID, Long questionID, String optionText, Boolean correct){
        return new AnswerOption(answerOptionID, questionID, optionText, correct);
    }
}
