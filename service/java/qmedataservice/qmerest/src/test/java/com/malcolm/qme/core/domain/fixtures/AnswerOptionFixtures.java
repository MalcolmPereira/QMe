/**
 * Name      : com.malcolm.qme.core.domain.fixtures.AnswerOptionFixtures.java
 * Date      : 2/20/17
 * Developer : Malcolm
 * Purpose   : Test Fixtures for Answer Option
 */
package com.malcolm.qme.core.domain.fixtures;

import com.malcolm.qme.core.domain.AnswerOption;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Simple Answer Option List
     * @param answerOptionID Answer Option Id
     * @param questionID Question Id
     * @param optionText Option Text
     * @param correct Correct
     * @return List of Answer Options
     */
    public static List<AnswerOption> simpleAnswerOptionList (Long answerOptionID, Long questionID, String optionText, Boolean correct){
        List<AnswerOption> answerOptionList = new ArrayList<>();
        answerOptionList.add(new AnswerOption(answerOptionID, questionID, optionText, correct));
        return answerOptionList;
    }

}
