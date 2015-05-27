/**
 * Name      : com.malcolm.qme.rest.exception.QMeResourceNotFoundException.java
 * Date      : 5/23/15
 * Developer : Malcolm
 * Purpose   : Exception class for resource not found
 */

package com.malcolm.qme.rest.exception;

/**
 * @author Malcolm
 */
public class QMeResourceNotFoundException extends QMeResourceException {

    /**
     * Public Constructor
     *
     * @param errorMessage Error Message
     */
    public QMeResourceNotFoundException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Public Constructor
     *
     * @param errorMessage Error Message
     * @param error Error
     */
    public QMeResourceNotFoundException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
