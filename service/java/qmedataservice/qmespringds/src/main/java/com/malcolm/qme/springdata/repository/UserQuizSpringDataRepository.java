/**
 * Name      : com.malcolm.qme.springdata.repository.UserQuizSpringDataRepository.java
 * Date      : 5/8/15
 * Developer : Malcolm
 * Purpose   : SpringData JPA UserQuizEntity Repository
 */
package com.malcolm.qme.springdata.repository;

import com.malcolm.qme.springdata.entity.UserQuizEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Malcolm
 */
@Repository
public interface UserQuizSpringDataRepository extends JpaRepository<UserQuizEntity, Long> {
	/**
     * Find By User ID
     * @param userId User ID
     * @return UserQuizEntity List
     */
    List<UserQuizEntity> findByUserId(Long userId);

    /**
     * Find By User ID and Quiz ID
     * @param userId
     * @param quizId
     * @return
     */
    List<UserQuizEntity> findByUserIdAndQuizId(Long userId, Long quizId);

    /**
     * Find Quizzes f0r User ID
     * @param userId User ID
     * @return UserQuizEntity List
     */
    @Query( value = "SELECT COALESCE(userQuiz.USER_QUIZ_ID, - SUBSTRING((RAND() * -1),4)) AS USER_QUIZ_ID , COALESCE(userQuiz.USER_ID, - SUBSTRING((RAND() * -1),4)) AS USER_ID,quiz.QUIZ_ID,quiz.QUIZ_NAME,quiz.QUIZ_DESC,quiz.CAT_ID,quiz.QUIZ_LIKES, quiz.QUIZ_HITS, quiz.MAX_ATTEMPTS, quiz.QUIZ_CREATE_DATE, quiz.QUIZ_CREATE_USER, quiz.QUIZ_UPDATE_DATE, quiz.QUIZ_UPDATE_USER, userQuiz.QUIZ_START_DATE,userQuiz.QUIZ_END_DATE,userQuiz.QUIZ_USER_SCORE,userQuiz.QUIZ_MAX_SCORE,userQuiz.QUIZ_TOKEN FROM USER_QUIZ userQuiz RIGHT JOIN QUIZ quiz ON userQuiz.QUIZ_ID = quiz.QUIZ_ID AND userQuiz.USER_ID = :userId \n#pageable\n",
            countQuery = "SELECT count(*) FROM USER_QUIZ userQuiz RIGHT JOIN QUIZ quiz ON userQuiz.QUIZ_ID = quiz.QUIZ_ID AND userQuiz.USER_ID = :userId",
            nativeQuery = true)
    Page<UserQuizEntity> findQuizzesForUser(@Param("userId") Long userId, Pageable pageable);

    /**
     * Find Completed By User Id
     * @param userId User ID
     * @return UserQuizEntity List
     */
    @Query(value = "SELECT userQuiz.USER_QUIZ_ID, userQuiz.USER_ID, quiz.QUIZ_ID,quiz.QUIZ_NAME,quiz.QUIZ_DESC,quiz.QUIZ_LIKES, quiz.QUIZ_HITS, quiz.MAX_ATTEMPTS, quiz.QUIZ_CREATE_DATE, quiz.QUIZ_CREATE_USER, quiz.QUIZ_UPDATE_DATE, quiz.QUIZ_UPDATE_USER,userQuiz.CAT_ID,userQuiz.QUIZ_START_DATE,userQuiz.QUIZ_END_DATE,userQuiz.QUIZ_USER_SCORE,userQuiz.QUIZ_MAX_SCORE,userQuiz.QUIZ_TOKEN FROM USER_QUIZ userQuiz RIGHT JOIN QUIZ quiz ON userQuiz.QUIZ_ID = quiz.QUIZ_ID WHERE userQuiz.USER_ID  = :userId AND userQuiz.QUIZ_END_DATE IS NOT NULL \n#pageable\n",
           countQuery = "SELECT userQuiz.USER_QUIZ_ID,userQuiz.USER_ID, quiz.QUIZ_ID,quiz.QUIZ_NAME,quiz.QUIZ_DESC,userQuiz.CAT_ID,userQuiz.QUIZ_START_DATE,userQuiz.QUIZ_END_DATE,userQuiz.QUIZ_USER_SCORE,userQuiz.QUIZ_MAX_SCORE,userQuiz.QUIZ_TOKEN FROM USER_QUIZ userQuiz RIGHT JOIN QUIZ quiz ON userQuiz.QUIZ_ID = quiz.QUIZ_ID WHERE userQuiz.USER_ID  = :userId AND userQuiz.QUIZ_END_DATE IS NOT NULL",
           nativeQuery = true)
    Page<UserQuizEntity> findCompletedByUserId(@Param("userId")Long userId, Pageable pageable);

    /**
     * Find Pending By User Id
     * @param userId User ID
     * @return UserQuizEntity List
     */
    @Query(value = "SELECT userQuiz.USER_QUIZ_ID, userQuiz.USER_ID,quiz.QUIZ_ID,quiz.QUIZ_NAME,quiz.QUIZ_DESC,quiz.QUIZ_LIKES, quiz.QUIZ_HITS, quiz.MAX_ATTEMPTS, quiz.QUIZ_CREATE_DATE, quiz.QUIZ_CREATE_USER, quiz.QUIZ_UPDATE_DATE, quiz.QUIZ_UPDATE_USER,userQuiz.CAT_ID,userQuiz.QUIZ_START_DATE,userQuiz.QUIZ_END_DATE,userQuiz.QUIZ_USER_SCORE,userQuiz.QUIZ_MAX_SCORE,userQuiz.QUIZ_TOKEN FROM USER_QUIZ userQuiz RIGHT JOIN QUIZ quiz ON userQuiz.QUIZ_ID = quiz.QUIZ_ID WHERE userQuiz.USER_ID  = :userId AND userQuiz.QUIZ_END_DATE IS NULL \n#pageable\n",
           countQuery = "SELECT userQuiz.USER_QUIZ_ID,userQuiz.USER_ID,quiz.QUIZ_ID,quiz.QUIZ_NAME,quiz.QUIZ_DESC,userQuiz.CAT_ID,userQuiz.QUIZ_START_DATE,userQuiz.QUIZ_END_DATE,userQuiz.QUIZ_USER_SCORE,userQuiz.QUIZ_MAX_SCORE,userQuiz.QUIZ_TOKEN FROM USER_QUIZ userQuiz RIGHT JOIN QUIZ quiz ON userQuiz.QUIZ_ID = quiz.QUIZ_ID WHERE userQuiz.USER_ID  = :userId AND userQuiz.QUIZ_END_DATE IS NULL",
           nativeQuery = true)
    Page<UserQuizEntity> findPendingByUserId(@Param("userId")Long userId,Pageable pageable);

    /**
     * Find By Quiz ID
     * @param quizId Quiz ID
     * @return UserQuizEntity List
     */
    List<UserQuizEntity> findByQuizId(Long quizId);
}
