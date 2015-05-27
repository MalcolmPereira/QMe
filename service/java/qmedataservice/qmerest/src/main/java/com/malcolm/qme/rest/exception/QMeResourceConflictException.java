/**
 * Name      : com.malcolm.qme.rest.exception.QMeResourceConflictException.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : Exception class for duplicate resource data
 */

package com.malcolm.qme.rest.exception;

/**
 * @author Malcolm
 */
public class QMeResourceConflictException extends QMeResourceException {

    /**
     * Public Constructor
     *
     * @param errorMessage Error Message
     */
    public QMeResourceConflictException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Public Constructor
     *
     * @param errorMessage Error Message
     * @param error Error
     */
    public QMeResourceConflictException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
