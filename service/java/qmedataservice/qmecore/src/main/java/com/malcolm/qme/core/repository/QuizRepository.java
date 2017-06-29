/**
 * Name      : com.malcolm.qme.core.repository.QuizRepository.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe Quiz Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.Quiz;

import java.util.List;

/**
 * @author Malcolm
 */
public interface QuizRepository extends QMeRepository<Quiz,Long> {

    /**
     * Sort Fields Enum
     */
    enum QUIZSORTFIELDS {
        QUIZ("quizName"),
        QUIZLIKES("quizLikes"),
        USERNAME("userName"),
        CATEGORY("catId"),
        QUIZHITS("quizHits"),
        QUIZMAXATTEMPTS("quizMaxAttempts"),
        QUIZCREATEDATE("quizCreateDate"),
        QUIZUPDATEDATE("quizUpdateDate")
        ;

        /**
         * Enum Constructor
         * @param fieldName Field Name
         */
        QUIZSORTFIELDS(String fieldName) {
            this.quizSortField = fieldName;
        }

        /**
         * Sort Field Name
         */
        private final String quizSortField;

        /**
         * Get Question Sort Field Name
         * @return Question Sort Field Name
         */
        public String getQuizSortField() {
            return quizSortField;
        }
    }

    /**
     * Find By Category
     *
     * @param categoryID Category ID
     * @return Quiz List for Category ID
     * @throws QMeException
     */
    List<Quiz> findByCategoryId(Long categoryID) throws QMeException;

    /**
     * Find By Most Liked
     *
     * @return Quiz List sorted by likes
     * @throws QMeException
     */
    List<Quiz> findByMostLiked() throws QMeException;

    /**
     * Find By Quiz Name Like
     * @param quizName Quiz Name
     * @return Quiz List matching name like
     * @throws QMeException
     */
    List<Quiz> findQuizNameLike(String quizName) throws QMeException;
}
