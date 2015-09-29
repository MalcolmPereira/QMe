/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeUserRoleFixtures.java
 * Date      : 9/24/2015
 * Developer : Malcolm
 * Purpose   : Test Fixtures for QMe User Role
 **/
package com.malcolm.qme.rest.model.fixtures;

import com.malcolm.qme.rest.model.QMeUserRole;

/**
 * @author Malcolm
 */
public class QMeUserRoleFixtures extends QMeResourceFixture<QMeUserRole>{

    /**
     * Simple QMe User Role
     *
     * @return  QMeUserRole
     */
    public static QMeUserRole simpleQMeUserRole(){
        QMeUserRole qmeUserRole = new QMeUserRole();
        qmeUserRole.setUserRoleID(1L);
        qmeUserRole.setRoleID(1);
        qmeUserRole.setRoleName("role name");
        qmeUserRole.setUserID(1L);
        return qmeUserRole;
    }
}
