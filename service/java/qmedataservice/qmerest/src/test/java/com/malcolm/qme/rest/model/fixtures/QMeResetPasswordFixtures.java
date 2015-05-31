/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeResetPasswordFixtures.java
 * Date      : 5/31/15
 * Developer : malcolm
 * Purpose   : TODO:
 */

package com.malcolm.qme.rest.model.fixtures;/**
 * Created by malcolm on 5/31/15.
 */

import com.malcolm.qme.rest.model.QMeResetPassword;

/**
 * @Author: malcolm
 */
public class QMeResetPasswordFixtures extends QMeResourceFixture<QMeResetPassword>{

    /**
     * Return Simple QMe Reset Password
     * @return
     */
    public static QMeResetPassword simpleQMeResetPassword(){
        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setToken(1L);
        qMeResetPassword.setUserName("suser1");
        qMeResetPassword.setUserPassword("somepassword");
        return qMeResetPassword;
    }
}
