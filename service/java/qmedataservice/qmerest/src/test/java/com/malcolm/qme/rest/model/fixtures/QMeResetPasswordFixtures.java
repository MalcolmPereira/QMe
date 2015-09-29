/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeResetPasswordFixtures.java
 * Date      : 5/31/2015
 * Developer : Malcolm
 * Purpose   : QMe Reset Password Fixture
 */
package com.malcolm.qme.rest.model.fixtures;

import com.malcolm.qme.rest.model.QMeResetPassword;

/**
 * @author Malcolm
 */
public class QMeResetPasswordFixtures extends QMeResourceFixture<QMeResetPassword>{

    /**
     * Return Simple QMe Reset Password
     * @return QMeResetPassword
     */
    public static QMeResetPassword simpleQMeResetPassword(){
        QMeResetPassword qMeResetPassword = new QMeResetPassword();
        qMeResetPassword.setToken("somerandomtoken");
        qMeResetPassword.setUserName("suser1");
        qMeResetPassword.setUserPassword("somepassword");
        return qMeResetPassword;
    }
}
