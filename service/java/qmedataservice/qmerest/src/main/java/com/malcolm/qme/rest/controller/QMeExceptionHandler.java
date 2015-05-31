/**
 * Name      : com.malcolm.qme.rest.controller.QMeExceptionHandler.java
 * Date      : 5/23/15
 * Developer : Malcolm
 * Purpose   : Exception Handler for QMe REST resources
 */

package com.malcolm.qme.rest.controller;

import com.malcolm.qme.rest.exception.QMeInvalidResourceDataException;
import com.malcolm.qme.rest.exception.QMeResourceConflictException;
import com.malcolm.qme.rest.exception.QMeResourceException;
import com.malcolm.qme.rest.exception.QMeResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: malcolm
 */
@ControllerAdvice
public class QMeExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(QMeExceptionHandler.class);

    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Requested resource not found. please make sure resource id is valid and available for query or update.")
    @ExceptionHandler(QMeResourceNotFoundException.class)
    public void handleResourceNotFoundException(HttpServletRequest request, QMeResourceNotFoundException ex){
        if(ex.getCause() != null){
            ex.getCause().printStackTrace();
        }else{
            ex.printStackTrace();
        }
        logger.info("QMeResourceNotFoundException Occurred:: URL="+request.getRequestURL());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid data for resource. please make sure valid parameters are submitted for create or update.")
     @ExceptionHandler(QMeInvalidResourceDataException.class)
     public void handleResourceDataException(HttpServletRequest request, QMeInvalidResourceDataException ex){
        if(ex.getCause() != null){
            ex.getCause().printStackTrace();
        }else{
            ex.printStackTrace();
        }
        logger.info("QMeInvalidResourceDataException Occurred:: URL="+request.getRequestURL());
    }

    @ResponseStatus(value = HttpStatus.CONFLICT, reason = "Duplicate data for resource. please make sure valid parameters are submitted for create or update.")
    @ExceptionHandler(QMeResourceConflictException.class)
    public void handleResourceConflictException(HttpServletRequest request, QMeResourceConflictException ex){
        if(ex.getCause() != null){
            ex.getCause().printStackTrace();
        }else{
            ex.printStackTrace();
        }
        logger.info("QMeResourceConflictException Occurred:: URL="+request.getRequestURL());
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "QMe Service Exception occured. Please retry request or contact system admin")
    @ExceptionHandler(QMeResourceException.class)
    public void handleResourceException(HttpServletRequest request, QMeResourceException ex){
        if(ex.getCause() != null){
            ex.getCause().printStackTrace();
        }else{
            ex.printStackTrace();
        }
        logger.info("QMeResourceException Occurred:: URL="+request.getRequestURL());
    }

}
