/**
 * Name      : com.malcolm.qme.rest.service.QuestionService.java
 * Date      : 3/12/16
 * Developer : Malcolm
 * Purpose   : QMeQuestion Service Interface
 */
package com.malcolm.qme.rest.service;

import com.malcolm.qme.rest.exception.QMeServerException;
import com.malcolm.qme.rest.model.QMeQuestion;
import com.malcolm.qme.rest.model.QMeQuestionDetail;

import java.util.List;

/**
 * @author malcolm
 */
public interface QuestionService extends QMeService<QMeQuestionDetail,QMeQuestion,Long> {
    /**
     * Count By Category Id
     *
     * @param categoryId
     * @return
     * @throws QMeServerException
     */
    Long countByCategoryId(Long categoryId) throws QMeServerException;

    /**
     * Return List of Questions by Category Id
     *
     * @param categoryId
     * @return
     * @throws QMeServerException
     */
    List<QMeQuestionDetail> list(Long categoryId) throws QMeServerException;

    /**
     * Return List of Questions by Category Id
     *
     * @param categoryId
     * @param pageIndex
     * @param maxRows
     * @param sortAscending
     * @param sortFields
     * @return
     * @throws QMeServerException
     */
    List<QMeQuestionDetail> list(Long categoryId, Integer pageIndex, Integer maxRows, boolean sortAscending, String... sortFields) throws QMeServerException;
}
