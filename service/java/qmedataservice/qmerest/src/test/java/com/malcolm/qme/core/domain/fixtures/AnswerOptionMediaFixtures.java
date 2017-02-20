/**
 * Name      : com.malcolm.qme.core.domain.fixtures.AnswerOptionMediaFixtures.java
 * Date      : 2/20/17
 * Developer : Malcolm
 * Purpose   : Test Fixtures for Answer Option Media
 */
package com.malcolm.qme.core.domain.fixtures;

import com.malcolm.qme.core.domain.AnswerOptionMedia;

/**
 * @author Malcolm
 */
public class AnswerOptionMediaFixtures {

    /**
     * Simple Answer Option Media Fixture
     * @param answerOptionMediaID Answer Option Media Id
     * @param answerOptionID Answer Option Id
     * @param mediaTypeID Answer Option Media Type
     * @param media Answer Option MEdia
     * @return AnswerOptionMedia
     */
    public static AnswerOptionMedia simpleAnswerOptionMedia(Long answerOptionMediaID, Long answerOptionID, Integer mediaTypeID, byte[] media){
        return new AnswerOptionMedia(answerOptionMediaID, answerOptionID, mediaTypeID,media);
    }

}
