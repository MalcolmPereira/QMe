/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeUserFixtures.java
 * Date      : 5/27/2015
 * Developer : Malcolm
 * Purpose   : Test Fixtures for QMe User
 **/
package com.malcolm.qme.rest.model.fixtures;

import com.malcolm.qme.rest.model.QMeStageUser;
import com.malcolm.qme.rest.model.QMeUser;
import com.malcolm.qme.rest.model.QMeUserDetail;

/**
 * @author Malcolm
 */
public class QMeUserFixtures extends QMeResourceFixture<QMeUser>{

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

    /**
     * Simple QMe Staged User
     *
     * @return  QMeUser
     */
    public static QMeStageUser simpleQMeStagedUser(){
        QMeStageUser qmeUser = new QMeStageUser();
        qmeUser.setUserName("suser1");
        qmeUser.setUserPassword("spassword1");
        qmeUser.setUserFirstName("Simple 1");
        qmeUser.setUserLastName("Simple User 1");
        qmeUser.setUserEmail("SimpleUser1@User.com");
        qmeUser.setConfirmURL("some url");
        return qmeUser;
    }

    /**
     * Simple QMe User
     *
     * @return  QMeUser
     */
    public static QMeUserDetail simpleQMeUserDetails(){
        QMeUserDetail qmeUser = new QMeUserDetail();
        qmeUser.setUserName("suser1");
        qmeUser.setUserPassword("spassword1");
        qmeUser.setUserFirstName("Simple 1");
        qmeUser.setUserLastName("Simple User 1");
        qmeUser.setUserEmail("SimpleUser1@User.com");
        return qmeUser;
    }

    /**
     * Simple QMe User With Id
     *
     * @return  QMeUser
     */
    public static QMeUserDetail simpleQMeUserDetailsWithId(){
        QMeUserDetail qmeUser = simpleQMeUserDetails();
        qmeUser.setUserId(1L);
        return qmeUser;
    }
}
