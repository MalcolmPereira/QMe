/**
 * Name      : com.malcolm.qme.rest.api.QMeAPI.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QME REST API Interface for the REST Service
 */
package com.malcolm.qme.rest.api;

import com.malcolm.qme.security.service.QMeUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Malcolm
 */
public interface QMeAPI {
	/**
	 * App String
	 */
	String APP_STRING 		   = "/qme";
	/**
	 * Id Parameter
	 */
	String ID_PARAM_STRING 		= "id";
	/**
	 * Name Parameter
	 */
	String NAME_PARAM_STRING 	= "name";
    /**
	 * Email Parameter
	 */
	String EMAIL_PARAM_STRING 	= "email";

    /**
     * Get Current Logged in User
     * @return QMeUserDetails Current Logged in User
     */
    default QMeUserDetails getCurrentUser() {
		if(SecurityContextHolder.getContext() != null && SecurityContextHolder.getContext().getAuthentication() != null && SecurityContextHolder.getContext().getAuthentication() instanceof QMeUserDetails){
			return (QMeUserDetails) SecurityContextHolder.getContext().getAuthentication();
		}
        return null;
    }
}
