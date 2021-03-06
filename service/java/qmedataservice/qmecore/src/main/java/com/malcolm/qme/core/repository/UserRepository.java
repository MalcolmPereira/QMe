/**
 * Name      : com.malcolm.qme.core.domain.AnswerOption.java
 * Date      : 5/2/15
 * Developer : Malcolm
 * Purpose   : QMe User Repository Interface
 */
package com.malcolm.qme.core.repository;

import com.malcolm.qme.core.domain.User;

import java.time.LocalDateTime;


/**
 * @author Malcolm
 */
public interface UserRepository extends QMeRepository<User, Long> {
    /**
     * Sort Fields Enum
     */
    enum USERSORTFIELDS {
        USERNAME("userName"),
        EMAIL("userEmail"),
        FIRSTNAME("userFirstName"),
        LASTNAME("userLastName"),
        REGISTERDATE("userRegisteredDate"),
        LOGINDATE("userLastLoginDate"),

        ;
        /**
         * Enum Constructor
         * @param fieldName Field Name
         */
        USERSORTFIELDS(String fieldName) {
            this.userSortField = fieldName;
        }

        /**
         * Sort Field Name
         */
        private final String userSortField;

        /**
         * Get User Sort Field Name
         * @return User Sort Field Name
         */
        public String getUserSortField() {
            return userSortField;
        }
    }


    /**
     * Find User By User Name
     *
     * @param userName User Name
     * @return User for unique user name
     * @throws QMeException
     */
    User findByUserName(String userName) throws QMeException;

    /**
     * Find User By User Email
     *
     * @param userEmail User Email
     * @return User for unique user email
     * @throws QMeException
     */
    User findByUserEmail(String userEmail) throws QMeException;

    /**
     * Add Password Reset Token
     *
     * @param resetToken Unique Generated Reset Token
     * @param userId User ID for whom password reset is requested
     * @throws QMeException
     */
     void addResetToken(String resetToken, Long userId) throws QMeException;

    /**
     * Get Create Time for Password Reset Token
     * @param resetToken Unique Generated Reset Token
     * @param userId User ID for whom password reset is requested
     * @return Reset Token Created Time
     * @throws QMeException
     */
    LocalDateTime getResetTokenCreateTime(String resetToken, Long userId) throws QMeException;

    /**
     * Delete Reset Token
     *
     * @param resetToken Unique Generated Reset Token
     * @param userId User ID for whom password reset is requested
     * @throws QMeException
     */
    void deleteResetToken(String resetToken, Long userId) throws QMeException;

    /**
     * Update Login Date for User
     *
     * @param userId for User
     * @return User
     * @throws QMeException
     */
    User updateLoginDate(Long userId) throws QMeException;

    /**
     * Find Any Staged User By User Name
     *
     * @param userName User Name
     * @return User for unique user name
     * @throws QMeException
     */
    User findStagedUserByUserName(String userName) throws QMeException;

    /**
     * Find Any Staged User By User Email
     *
     * @param userEmail User Email
     * @return User for unique user email
     * @throws QMeException
     */
    User findStagedUserByUserEmail(String userEmail) throws QMeException;
    
    /**
     * Stage User Registration
     *
     * @param user user attempting to register
     * @return staging token which needs to be confirmed
     * @throws QMeException
     */
    String stageUserRegistration(User user)throws QMeException;

    /**
     * Delete User Staging Token
     *
     * @param  stagingToken staging token to be deleted
     * @throws QMeException
     */
    void deleteStagingToken(String stagingToken)throws QMeException;

    /**
     * Confirm User Registration
     *
     * @param userRegistrationToken User Registration Token
     * @return User
     * @throws QMeException
     */
     User confirmUserRegistration(String userRegistrationToken) throws QMeException;

    /**
     * Reset User Password for password reset token
     *
     * @param resetToken  Unique Generated Reset Token
     * @param userId Long userId
     * @param userPassword Encrypted Password
     * @return User
     * @throws QMeException
     */
    User resetUserPassword(String resetToken, Long userId, String userPassword) throws QMeException;
}
