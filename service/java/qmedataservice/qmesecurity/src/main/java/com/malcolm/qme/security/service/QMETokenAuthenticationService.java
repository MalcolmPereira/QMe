/**
 * Name      : com.malcolm.qme.security.service.QMETokenAuthenticationService.java
 * Date      : 9/30/15
 * Developer : Malcolm
 * Purpose   : QMe Token Authentication Service Interface
 */
package com.malcolm.qme.security.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Malcolm
 */
public interface QMETokenAuthenticationService {
    /**
     * QME Authentication Token
     */
    String QME_AUTH_HEADER_NAME = "QME-AUTH-TOKEN";

    /**
     * Add Authentication Token to Response
     *
     * @param response Response
     * @param authentication QMe Authentication USer
     */
    String addAuthToken(HttpServletResponse response, QMeUserDetails authentication);

    /**
     * Get Authenticated Used from Authentication Token String
     * @param request HttpServletRequest
     * @return QMe User Details
     */
    QMeUserDetails getAuthenticatedUser(HttpServletRequest request);
}
