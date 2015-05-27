/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeUserFixture.java
 * Date      : 5/27/2015
 * Developer : Malcolm
 * Purpose   : Test Fixtures for QMe User
 **/
package com.malcolm.qme.rest.model.fixtures;

import com.malcolm.qme.rest.model.QMeUser;

/**
 * @author Malcolm
 */
public class QMeUserFixture extends QMeResourceFixture<QMeUser>{

    /**
     * Simple QMe User
     *
     * @return  QMeUser
     */
    public static QMeUser simpleQMeUser(){
        QMeUser qmeUser = new QMeUser();
        qmeUser.setUserName("suser1");
        qmeUser.setUserPassword("spassword1");
        qmeUser.setUserFirstName("Simple 1");
        qmeUser.setUserLastName("Simple User 1");
        qmeUser.setUserEmail("SimpleUser1@User.com");
        return qmeUser;
    }
}
