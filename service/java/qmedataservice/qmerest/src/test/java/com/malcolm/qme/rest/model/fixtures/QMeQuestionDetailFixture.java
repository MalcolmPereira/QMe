/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeQuestionDetailFixture.java
 * Date      : 3/16/15
 * Developer : Malcolm
 * Purpose   : Test Fixtures for QMe Question Details
 */
package com.malcolm.qme.rest.model.fixtures;

import com.malcolm.qme.core.domain.MediaTypeEnum;
import com.malcolm.qme.rest.model.QMeAnswerOption;
import com.malcolm.qme.rest.model.QMeAnswerOptionMedia;
import com.malcolm.qme.rest.model.QMeAnswerReferenceMedia;
import com.malcolm.qme.rest.model.QMeQuestionDetail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
public class QMeQuestionDetailFixture extends QMeResourceFixture<QMeQuestionDetail>{

    /**
     * Get QMe Question Detail
     * @return QMeQuestionDetail
     */
    public static QMeQuestionDetail simpleQMeQuestionDetail(){
        return getQMeQuestionDetail();
    }


    /**
     * Return Question Detail List
     * @return QMeQuestionDetail List
     */
    public static List<QMeQuestionDetail> simpleQMeQuestionDetailList(){
        List<QMeQuestionDetail> qmeQuestionList = new ArrayList<>();

        QMeQuestionDetail qmeQuestionDetail = getQMeQuestionDetail();
        qmeQuestionList.add(qmeQuestionDetail);

        qmeQuestionDetail = getQMeQuestionDetail();
        qmeQuestionDetail.setQuestionId(qmeQuestionDetail.getQuestionId()+1);
        qmeQuestionDetail.setQuestionText(qmeQuestionDetail.getQuestionText()+ " "+1);
        qmeQuestionList.add(qmeQuestionDetail);

        qmeQuestionDetail = getQMeQuestionDetail();
        qmeQuestionDetail.setQuestionId(qmeQuestionDetail.getQuestionId()+2);
        qmeQuestionDetail.setQuestionText(qmeQuestionDetail.getQuestionText()+ " "+2);
        qmeQuestionList.add(qmeQuestionDetail);

        qmeQuestionDetail = getQMeQuestionDetail();
        qmeQuestionDetail.setQuestionId(qmeQuestionDetail.getQuestionId()+3);
        qmeQuestionDetail.setQuestionText(qmeQuestionDetail.getQuestionText()+ " "+3);
        qmeQuestionList.add(qmeQuestionDetail);

        qmeQuestionDetail = getQMeQuestionDetail();
        qmeQuestionDetail.setQuestionId(qmeQuestionDetail.getQuestionId()+4);
        qmeQuestionDetail.setQuestionText(qmeQuestionDetail.getQuestionText()+ " "+4);
        qmeQuestionList.add(qmeQuestionDetail);

        return qmeQuestionList;
    }

    /**
     * Get QMeQuestionDetail
     * @return QMeQuestionDetail
     */
    private static QMeQuestionDetail getQMeQuestionDetail() {
        QMeQuestionDetail qmeQuestionDetail = new QMeQuestionDetail();
        qmeQuestionDetail.setQuestionId(1L);
        qmeQuestionDetail.setCategoryId(1L);
        qmeQuestionDetail.setQuestionText("Some Question");
        qmeQuestionDetail.setAnswer("Some Answer");
        qmeQuestionDetail.setQuestionPoint(1);
        qmeQuestionDetail.setLikes(1L);
        qmeQuestionDetail.setQuestionCreateDate(LocalDateTime.now());
        qmeQuestionDetail.setCreateUserID(1L);
        qmeQuestionDetail.setQuestionUpdateDate(LocalDateTime.now());

        List<QMeAnswerOption> answerOptionList = new ArrayList<>();
        QMeAnswerOption qMeAnswerOption = new QMeAnswerOption();
        qMeAnswerOption.setQuestionID(1L);
        qMeAnswerOption.setAnswerOptionID(1L);
        qMeAnswerOption.setOptionText("Some Option Text");
        qMeAnswerOption.setCorrect(Boolean.TRUE);

        List<QMeAnswerOptionMedia> answerOptionMediaList = new ArrayList<>();
        QMeAnswerOptionMedia qMeAnswerOptionMedia = new QMeAnswerOptionMedia();
        qMeAnswerOptionMedia.setAnswerOptionID(1L);
        qMeAnswerOptionMedia.setAnswerOptionMediaID(1L);
        qMeAnswerOptionMedia.setMediaType(MediaTypeEnum.LINK.getMediaType());
        qMeAnswerOptionMedia.setMedia("http://www.google.com".getBytes());
        answerOptionMediaList.add(qMeAnswerOptionMedia);
        qMeAnswerOption.setAnswerOptionMediaList(answerOptionMediaList);

        answerOptionList.add(qMeAnswerOption);
        qmeQuestionDetail.setAnswerOptionList(answerOptionList);

        List<QMeAnswerReferenceMedia> answerReferenceMediaList = new ArrayList<>();
        QMeAnswerReferenceMedia qMeAnswerReferenceMedia = new QMeAnswerReferenceMedia();
        qMeAnswerReferenceMedia.setQuestionID(1L);
        qMeAnswerReferenceMedia.setAnswerRefMediaID(1L);
        qMeAnswerReferenceMedia.setMediaType(MediaTypeEnum.LINK.getMediaType());
        qMeAnswerReferenceMedia.setMedia("http://www.google.com".getBytes());
        answerReferenceMediaList.add(qMeAnswerReferenceMedia);
        qmeQuestionDetail.setAnswerReferenceMediaList(answerReferenceMediaList);

        qmeQuestionDetail.setUpdateUserID(1L);
        return qmeQuestionDetail;
    }
}
