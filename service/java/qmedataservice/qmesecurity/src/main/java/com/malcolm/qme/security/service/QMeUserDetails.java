/**
 * Name      : com.malcolm.qme.security.service.QMeUserDetails.java
 * Date      : 5/25/15
 * Developer : Malcolm
 * Purpose   : QMe User Details
 */

package com.malcolm.qme.security.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Malcolm
 */
class QMeUserDetails implements UserDetails {
    /**
     * User Id
     */
    private final Long userID;
    /**
     * User Name
     */
    private final String userName;
    /**
     * User Password
     */
    private final String userPassword;
    /**
     * User First Name
     */
    private String userFirstName;
    /**
     * User Last Name
     */
    private String userLastName;
    /**
     * User Email
     */
    private String userEmail;
    /**
     * User Registered Date
     */
    private LocalDateTime userRegisteredDate;
    /**
     * User Update Date
     */
    private LocalDateTime userUpdateDate;
    /**
     * User Last Login Date
     */
    private LocalDateTime userLastLoginDate;
    /**
     * Update User Id
     */
    private Long updateUserID;
    /**
     * Granted Authority or User Roles
     */
    private final Collection<GrantedAuthority> authorities;

    /**
     * Created New QMeUserDetails
     *
     * @param userId User ID
     * @param username User Name
     * @param password Encrypted Password
     * @return User Details
     */
    public static UserDetails create(Long userId, String username, String password) {
        return new QMeUserDetails(userId, username, password);
    }

    /**
     * Created New QMeUserDetails
     *
     * @param userId User ID
     * @param username User Name
     * @param password Encrypted Password
     * @param authorities Authorities Array
     * @return User Details
     */
    public static UserDetails create(Long userId, String username, String password, String...authorities) {
        return new QMeUserDetails(userId, username, password, authorities);
    }

    /**
     * Created New QMeUserDetails
     *
     * @param userId User ID
     * @param username User Name
     * @param password Encrypted Password
     * @param authorities Authorities Array
     * @return User Details
     */
    public static UserDetails create(Long userId, String username, String password, Collection<GrantedAuthority> authorities) {
        return new QMeUserDetails(userId, username, password, authorities);
    }

    /**
     * Private Constructor Create New QMeUser
     *
     * @param userId User Id
     * @param username User Name
     * @param password Password
     */
    @SuppressWarnings("unchecked")
    private QMeUserDetails(Long userId, String username, String password) {
        this(userId, username, password, Collections.EMPTY_LIST);
    }

    /**
     * Private Constructor Create New QMeUser
     *
     * @param userId         User ID
     * @param username       User Name
     * @param password       Encrypted Password
     * @param authoritiesArr Authorities Array
     */
    private QMeUserDetails(Long userId, String username, String password, String... authoritiesArr) {
        userID = userId;
        userName = username;
        userPassword = password;
        authorities = AuthorityUtils.createAuthorityList(authoritiesArr);
    }

    /**
     * Private Constructor Create New QMeUser
     *
     * @param userId         User ID
     * @param username       User Name
     * @param password       Encrypted Password
     * @param authoritiesList    AuthoritiesList
     */
    private QMeUserDetails(Long userId, String username, String password, Collection<GrantedAuthority> authoritiesList) {
        super();
        userID	  = userId;
        userName = username;
        userPassword = password;
        authorities = authoritiesList;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userPassword;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * Get User ID
     * @return User ID
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Get User First Name
     * @return User First Name
     */
    public String getUserFirstName() {
        return userFirstName;
    }

    /**
     * Set User First Name
     *
     * @param userFirstName User First Name
     */
    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    /**
     * Get User Last Name
     *
     * @return User Last Name
     */
    public String getUserLastName() {
        return userLastName;
    }

    /**
     * Set User Last Name
     *
     * @param userLastName User Last Name
     */
    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    /**
     * Get User Email
     *
     * @return User Email
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Set User Email
     *
     * @param userEmail User Email
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * User Registered Date
     *
     * @return User Registered Date
     */
    public LocalDateTime getUserRegisteredDate() {
        return userRegisteredDate;
    }

    /**
     * Set User Registered Date
     *
     * @param userRegisteredDate User Registered Date
     */
    public void setUserRegisteredDate(LocalDateTime userRegisteredDate) {
        this.userRegisteredDate = userRegisteredDate;
    }

    /**
     * User Update Date
     *
     * @return User Update Date
     */
    public LocalDateTime getUserUpdateDate() {
        return userUpdateDate;
    }

    /**
     * Set User Updated Date
     *
     * @param userUpdateDate Set User Updated Date
     */
    public void setUserUpdateDate(LocalDateTime userUpdateDate) {
        this.userUpdateDate = userUpdateDate;
    }

    /**
     * User Last Logging Date
     *
     * @return User Last Login Date
     */
    public LocalDateTime getUserLastLoginDate() {
        return userLastLoginDate;
    }

    /**
     * Set User Last Login Date
     *
     * @param userLastLoginDate Set User Last Login Date Date
     */
    public void setUserLastLoginDate(LocalDateTime userLastLoginDate) {
        this.userLastLoginDate = userLastLoginDate;
    }

    /**
     * Get Update User ID
     * @return Update User ID
     */
    public Long getUpdateUserID() {
        return updateUserID;
    }

    /**
     *  Set Update User ID
     *
     * @param updateUserID Update User ID
     */
    public void setUpdateUserID(Long updateUserID) {
        this.updateUserID = updateUserID;
    }
}