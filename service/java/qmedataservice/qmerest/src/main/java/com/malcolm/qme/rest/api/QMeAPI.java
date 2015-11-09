/**
 * Name      : com.malcolm.qme.rest.api.QMeAPI.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : QME REST API Interface for the REST Service
 */
package com.malcolm.qme.rest.api;

import com.malcolm.qme.security.service.QMeUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Malcolm
 */
@SuppressWarnings("ALL")
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
	 * Page Parameter
	 */
	String PAGE_PARAM_STRING 	= "page";
    /**
     * Page Size Parameter
     */
    String PAGE_SIZE_PARAM_STRING 	= "pagesize";
    /**
     * Sort Type
     */
    String SORT_PARAM_STRING 	= "sorttype";
    /**
     * Sort Fields
     */
    String SORT_FIELDS 	= "sortfields";
    /**
     * Sort Field Separator
     */
    String SORT_FIELDS_SEPARATOR = ",";
    /**
     * Admin Role
     */
    String ADMIN_ROLE = "ADMIN";

	/**
	 * Logger
	 */
	Logger LOG = LoggerFactory.getLogger(QMeAPI.class);

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

	/**
	 * Log Rest Call
	 * @param user Current Logged in User
	 * @param methodName Method Name
	 */
	default void log(QMeUserDetails user, String methodName){
		if(user != null){
			LOG.debug("User "+methodName+" called by User ID: "+user.getUserID()+" User Name: "+user.getUsername()+" User Roles"+user.getAuthorities());
		}else{
			LOG.debug("User "+methodName+" called with no security context ");
		}
	}

    /**
     * Get Page Number
     * @param page Page Number String
     * @return Integer Page Number
     */
	default Integer getPageNumber(String page){
        Integer     pageNumber      = null;
        try{
            if(page != null && page.trim().length() > 0 ) {
                pageNumber = Integer.valueOf(page);
            }
        }catch(NumberFormatException numErr){
            pageNumber      = null;
        }
        return pageNumber;
    }

    /**
     * Get Page Size Number
     * @param pageSize Page Size Number String
     * @return Integer Page Size Number
     */
    default Integer getPageSizeNumber(String pageSize){
        Integer     pageSizeNumber      = null;
        try{
            if(pageSize != null && pageSize.trim().length() > 0) {
                pageSizeNumber = Integer.valueOf(pageSize);
            }
        }catch(NumberFormatException numErr){
            pageSizeNumber      = null;
        }
        return pageSizeNumber;
    }

    /**
     * Get Sort Ascending
     * @param sortAsc Sort Ascending True/False
     * @return Boolean Sort Ascending
     */
    default Boolean getSortAsc(String sortAsc){
        if(sortAsc != null && sortAsc.trim().length() > 0){
            return Boolean.valueOf(sortAsc);
        }
        return Boolean.TRUE;
    }

    /**
     * Get Sort Fields
     * @param sortFields Sort Fields String
     * @return String[] Sort Fields Array
     */
    default String[] getSortOrderFields(String sortFields){
        String[]    sortOrderFields = null;
        if(sortFields != null && sortFields.trim().length() > 0){
            sortOrderFields = sortFields.split(SORT_FIELDS_SEPARATOR);
        }
        return sortOrderFields;
    }
}
