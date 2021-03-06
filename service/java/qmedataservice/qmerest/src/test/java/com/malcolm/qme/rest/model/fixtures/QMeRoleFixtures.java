/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeRoleFixtures.java
 * Date      : 9/23/2015
 * Developer : Malcolm
 * Purpose   : Test Fixtures for QMe Role
 **/
package com.malcolm.qme.rest.model.fixtures;

import com.malcolm.qme.rest.model.QMeRole;

/**
 * @author Malcolm
 */
public class QMeRoleFixtures extends QMeResourceFixture<QMeRole>{

    /**
     * Simple QMe Role
     *
     * @return  QMeRole
     */
    public static QMeRole simpleQMeRole(){
        QMeRole qmeRole = new QMeRole();
        qmeRole.setRoleID(1);
        qmeRole.setRoleName("role name");
        qmeRole.setRoleDesc("role desc");
        return qmeRole;
    }
}
