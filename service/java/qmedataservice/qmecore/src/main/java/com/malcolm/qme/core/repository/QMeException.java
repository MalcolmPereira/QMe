/**
 * Name      : com.malcolm.qme.core.repository.QMeException.java
 * Date      : 5/28/15
 * Developer : Malcolm
 * Purpose   : Exception Class for QMe Repository
 */

package com.malcolm.qme.core.repository;

/**
 * @author malcolm
 */
public class QMeException extends Exception {

    /**
     * Error Message
     */
    private final String errorMessage;

    /**
     * Error
     */
    private Throwable error;

    /**
     * Public Constructor
     *
     * @param errorMessage Error Message
     */
    public QMeException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Public Constructor
     *
     * @param cause Throwable Error
     */
    public QMeException(Throwable cause) {
        super(cause);
        this.error = cause;
        if(cause != null){
            this.errorMessage = cause.getLocalizedMessage();
        }else{
            this.errorMessage = null;
        }
    }

    /**
     *  Public Constructor
     *
     * @param errorMessage Error Message
     * @param error Throwable Error
     */
    public QMeException(String errorMessage, Throwable error) {
        super(errorMessage,error);
        this.errorMessage = errorMessage;
        this.error = error;
    }

    /**
     * Get Error Message
     *
     * @return Error Message
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Get Error Cause
     *
     * @return Throwable Error
     */
    public Throwable getError() {
        return error;
    }
}
