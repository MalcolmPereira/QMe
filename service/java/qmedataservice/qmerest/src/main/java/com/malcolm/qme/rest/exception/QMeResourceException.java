/**
 * Name      : com.malcolm.qme.rest.exception.QMeResourceException.java
 * Date      : 5/23/15
 * Developer : Malcolm
 * Purpose   : QMe Resource Exception
 */

package com.malcolm.qme.rest.exception;

/**
 * @author Malcolm
 */
public class QMeResourceException extends Exception {

    /**
     * Error Message
     */
    private final String errorMessage;

    /**
     * Throwable Error
     */
    private Throwable error;

    /**
     * Public Constructor
     *
     * @param errorMessage Error Message
     */
    QMeResourceException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Public Constructor
     * @param errorMessage Error Message
     * @param error error
     */
    QMeResourceException(String errorMessage, Throwable error){
        super(errorMessage,error);
        this.errorMessage = errorMessage;
        this.error = error;
    }

    /**
     * Get Error Message
     * @return String Error Message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Get Error
     *
     * @return Throwable
     */
    public Throwable getError() {
        return error;
    }
}
