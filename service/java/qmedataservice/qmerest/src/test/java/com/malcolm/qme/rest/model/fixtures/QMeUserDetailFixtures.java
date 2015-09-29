/**
 * Name      : com.malcolm.qme.rest.model.fixtures.QMeUserDetailFixtures.java
 * Date      : 5/27/2015
 * Developer : Malcolm
 * Purpose   : Test Fixtures for QMe User Details
 **/
package com.malcolm.qme.rest.model.fixtures;

import com.malcolm.qme.rest.model.QMeUserDetail;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Malcolm
 */
public class QMeUserDetailFixtures extends QMeResourceFixture<QMeUserDetail>{

    /**
     * Simple QMe User Detail
     *
     * @return  QMeUserDetail
     */
    public static QMeUserDetail simpleQMeUserDetail(){
        QMeUserDetail userDetail = new QMeUserDetail();
        userDetail.setUserId(1L);
        userDetail.setUserName("suser1");
        userDetail.setUserPassword("spassword1");
        userDetail.setUserFirstName("Simple 1");
        userDetail.setUserLastName("Simple User 1");
        userDetail.setUserEmail("SimpleUser1@User.com");
        userDetail.setUserRegisteredDate(LocalDateTime.now());
        userDetail.setUserUpdateDate(LocalDateTime.now());
        userDetail.setUpdateUserID(1L);
        return userDetail;
    }

    /**
     * Simple QMe User Detail List
     *
     * @return  QMeUserDetail List
     */
    public static List<QMeUserDetail> simpleQMeUserDetailList(){
        List<QMeUserDetail> userDetailList = new ArrayList<>();

        QMeUserDetail userDetail = new QMeUserDetail();
        userDetail.setUserId(1L);
        userDetail.setUserName("suser1");
        userDetail.setUserPassword("spassword1");
        userDetail.setUserFirstName("Simple 1");
        userDetail.setUserLastName("Simple User 1");
        userDetail.setUserEmail("SimpleUser1@User.com");
        userDetail.setUserRegisteredDate(LocalDateTime.now());
        userDetail.setUserUpdateDate(LocalDateTime.now());
        userDetail.setUpdateUserID(1L);
        userDetailList.add(userDetail);

        userDetail = new QMeUserDetail();
        userDetail.setUserId(2L);
        userDetail.setUserName("suser2");
        userDetail.setUserPassword("spassword2");
        userDetail.setUserFirstName("Simple 2");
        userDetail.setUserLastName("Simple User 2");
        userDetail.setUserEmail("SimpleUser2@User.com");
        userDetail.setUserRegisteredDate(LocalDateTime.now());
        userDetail.setUserUpdateDate(LocalDateTime.now());
        userDetail.setUpdateUserID(1L);
        userDetailList.add(userDetail);

        userDetail = new QMeUserDetail();
        userDetail.setUserId(3L);
        userDetail.setUserName("suser3");
        userDetail.setUserPassword("spassword3");
        userDetail.setUserFirstName("Simple 3");
        userDetail.setUserLastName("Simple User 3");
        userDetail.setUserEmail("SimpleUser3@User.com");
        userDetail.setUserRegisteredDate(LocalDateTime.now());
        userDetail.setUserUpdateDate(LocalDateTime.now());
        userDetail.setUpdateUserID(1L);
        userDetailList.add(userDetail);

        userDetail = new QMeUserDetail();
        userDetail.setUserId(4L);
        userDetail.setUserName("suser4");
        userDetail.setUserPassword("spassword4");
        userDetail.setUserFirstName("Simple 4");
        userDetail.setUserLastName("Simple User 4");
        userDetail.setUserEmail("SimpleUser4@User.com");
        userDetail.setUserRegisteredDate(LocalDateTime.now());
        userDetail.setUserUpdateDate(LocalDateTime.now());
        userDetail.setUpdateUserID(1L);
        userDetailList.add(userDetail);

        userDetail = new QMeUserDetail();
        userDetail.setUserId(5L);
        userDetail.setUserName("suser5");
        userDetail.setUserPassword("spassword5");
        userDetail.setUserFirstName("Simple 5");
        userDetail.setUserLastName("Simple User 5");
        userDetail.setUserEmail("SimpleUser5@User.com");
        userDetail.setUserRegisteredDate(LocalDateTime.now());
        userDetail.setUserUpdateDate(LocalDateTime.now());
        userDetail.setUpdateUserID(1L);
        userDetailList.add(userDetail);

        return userDetailList;
    }
}
