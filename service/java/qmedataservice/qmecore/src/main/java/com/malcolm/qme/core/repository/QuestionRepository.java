/**
 * Name      : com.malcolm.qme.core.repository.QuestionRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Question Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.Question;

import java.util.List;

/**
 * @author Malcolm
 */
public interface QuestionRepository extends QMeRepository<Question,Long> {

    /**
     * Sort Fields Enum
     */
    enum QUESTIONSORTFIELDS {
        QUESTION("question"),
        USERNAME("userName"),
        QUESTIONCREATEDATE("questionCreateDate"),
        QUESTIONUPDATEDATE("questionUpdateDate"),
        ;

        /**
         * Enum Constructor
         * @param fieldName Field Name
         */
        QUESTIONSORTFIELDS(String fieldName) {
            this.questionSortField = fieldName;
        }

        /**
         * Sort Field Name
         */
        private final String questionSortField;

        /**
         * Get Question Sort Field Name
         * @return Question Sort Field Name
         */
        public String getQuestionSortField() {
            return questionSortField;
        }
    }

    /**
     * Find By Category
     *
     * @param categoryID Category ID
     * @return Question List for Category ID
     * @throws QMeException
     */
    List<Question> findByCategoryId(Long categoryID) throws QMeException;

    /**
     * Find By Most Liked
     *
     * @return Questions sorted by Likes
     * @throws QMeException
     */
    List<Question> findByMostLiked() throws QMeException;
}
