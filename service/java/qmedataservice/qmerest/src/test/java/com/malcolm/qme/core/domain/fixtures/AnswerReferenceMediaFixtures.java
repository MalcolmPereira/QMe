/**
 * Name      : com.malcolm.qme.core.domain.fixtures.AnswerReferenceMediaFixtures.java
 * Date      : 2/20/17
 * Developer : Malcolm
 * Purpose   : Test Fixtures for Answer Reference Media Fixtures
 */
package com.malcolm.qme.core.domain.fixtures;

import com.malcolm.qme.core.domain.AnswerReferenceMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
public class AnswerReferenceMediaFixtures {

    /**
     * Get Answer Reference Media Fixture
     * @param answerRefMediaID Answer Reference Media Id
     * @param questionID Question Id
     * @param mediaTypeID Media Type Id
     * @param media Media
     * @return AnswerReferenceMedia
     */
    public static AnswerReferenceMedia simpleAnswerReferenceMedia(Long answerRefMediaID, Long questionID, Integer mediaTypeID, byte[] media){
        return new AnswerReferenceMedia(answerRefMediaID, questionID, mediaTypeID, media);

    }

    /**
     * Get Answer Reference Media Fixture List
     * @param answerRefMediaID Answer Reference Media Id
     * @param questionID Question Id
     * @param mediaTypeID Media Type Id
     * @param media Media
     * @return AnswerReferenceMedia List
     */
    public static List<AnswerReferenceMedia> simpleAnswerReferenceMediaList(Long answerRefMediaID, Long questionID, Integer mediaTypeID, byte[] media){
        List<AnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        answerReferenceMediaList.add(new AnswerReferenceMedia(answerRefMediaID, questionID, mediaTypeID, media));
        return answerReferenceMediaList;
    }


}
