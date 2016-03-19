/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeQuestionDetailFixture.java
 * Date      : 3/16/15
 * Developer : Malcolm
 * Purpose   : Test Fixtures for QMe Question Details
 */
package com.malcolm.qme.rest.model.fixtures;

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
        qmeQuestionDetail.setUpdateUserID(1L);
        return qmeQuestionDetail;
    }
}
