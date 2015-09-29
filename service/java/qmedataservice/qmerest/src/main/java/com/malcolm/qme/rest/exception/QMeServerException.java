/**
 * Name      : com.malcolm.qme.rest.exception.QMeServerException.java
 * Date      : 8/31/15
 * Developer : Malcolm
 * Purpose   : Exception class for serevr errors
 */

package com.malcolm.qme.rest.exception;

/**
 * @author Malcolm
 */
public class QMeServerException  extends QMeResourceException {

    /**
     * Public Constructor
     *
     * @param errorMessage Error Message
     */
    public QMeServerException(String errorMessage) {
        super(errorMessage);
    }

    /**
     * Public Constructor
     *
     * @param errorMessage Error Message
     * @param error Error
     */
    public QMeServerException(String errorMessage, Throwable error) {
        super(errorMessage, error);
    }
}
