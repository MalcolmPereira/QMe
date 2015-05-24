/**
 * Name      : com.malcolm.qme.rest.service.QMeResourceException.java
 * Date      : 5/23/15
 * Developer : Malcolm
 * Purpose   : QMe Resource Exception
 */

package com.malcolm.qme.rest.service;

/**
 * @author Malcolm
 */
public class QMeResourceException extends Exception {

    /**
     * Error Message
     */
    private String errorMessage;

    /**
     * Error
     */
    private Throwable error;

    /**
     * Public Constructor
     *
     * @param errorMessage Error Message
     */
    public QMeResourceException(String errorMessage){
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    /**
     * Public Constructor
     * @param errorMessage Error Message
     * @param error error
     */
    public QMeResourceException(String errorMessage,Throwable error){
        super(errorMessage,error);
        this.errorMessage = errorMessage;
        this.error = error;
    }
}
