/**
 * Name      : com.malcolm.qme.rest.exception.QMeInvalidResourceDataException.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : Exception class for invalid resource data
 */

package com.malcolm.qme.rest.exception;

/**
 * @author Malcolm
 */
public class QMeInvalidResourceDataException extends QMeResourceException {

    /**
     * Public Constructor
     *
     * @param errorMessage Error Message
     */
    public QMeInvalidResourceDataException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Public Constructor
     *
     * @param errorMessage Error Message
     * @param error Error
     */
    public QMeInvalidResourceDataException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
