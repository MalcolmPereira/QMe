/**
 * Name      : com.malcolm.qme.rest.config.QMeMailSender.java
 * Date      : 9/14/15
 * Developer : Malcolm
 * Purpose   : QMe Application Mail Sender
 */
package com.malcolm.qme.rest.config;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * @author malcolm
 */
public final class QMeMailSender extends JavaMailSenderImpl{

   enum SMPT_AUTH_TYPE {
        TLS("TLS"),
        SSL("SSL")
        ;

        private final String authType;

        /**
         * Enum Constructor
         * @param authType Authentication Type
         */
        SMPT_AUTH_TYPE(String authType){
            this.authType = authType;
        }

        /**
         * Get Auth Type
         * @return Auth Type String
         */
        public String getAuthType(){
            return authType;
        }

        /**
         * Get Auth Type Enum
         * @param text Authentication Type Text
         * @return SMTP_AUTH_TYPE
         */
        public static SMPT_AUTH_TYPE fromString(String text) {
            if (text != null) {
                for (SMPT_AUTH_TYPE authType : SMPT_AUTH_TYPE.values()) {
                    if (text.equalsIgnoreCase(authType.getAuthType())) {
                        return authType;
                    }
                }
            }
            return null;
        }
    }
    /**
     * SMTP User Name Property
     */
    public  static final String MAIL_SMTP_USERNAME = "mail.smtp.username";
    /**
     * SMTP User Password Property
     */
    public  static final String MAIL_SMTP_PASSWORD = "mail.smtp.password";
    /**
     * SMTP User Name Property from System Variable
     */
    public  static final String MAIL_SMTP_USERNAME_SYS = "mail.smtp.username.sys";
    /**
     * SMTP User Password Property from System Variable
     */
    public static final String MAIL_SMTP_PASSWORD_SYS = "mail.smtp.password.sys";
    /**
     * SMTP Authentication
     */
    public static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    /**
     * SMTP Authentication Type
     */
    public static final String MAIL_SMTP_AUTH_TYPE = "mail.smtp.auth.type";
    /**
     * SMTP Host
     */
    public static final String MAIL_SMTP_HOST  = "mail.smtp.host";

    /**
     * SMTP Port
     */
    public static final String MAIL_SMTP_PORT = "mail.smtp.port";

    /**
     * SMTP Port TLS
     */
    public static final String MAIL_SMTP_PORT_TLS = "mail.smtp.port.tls";

    /**
     * SMTP Enable TLS
     */
    public static final String MAIL_SMTP_START_TLS = "mail.smtp.starttls.enable";

    /**
     * SMTP Port SSL
     */
    public static final String MAIL_SMTP_PORT_SSL = "mail.smtp.port.ssl";

    /**
     * SMTP SSL Socket Port
     */
    public static final String MAIL_SMTP_SSL_SOCKET_PORT       = "mail.smtp.socketFactory.port";

    /**
     * SMTP SSL Socket Class
     */
    public static final String MAIL_SMTP_SSL_SOCKET_CLASS      = "mail.smtp.socketFactory.class";

    /**
     * SMTP Timeout
     */
    public static final String MAIL_SMTP_TIMEOUT              = "mail.smtp.connectiontimeout";


    /**
     * Public Constructor
     *
     * @param userName User Name
     * @param password Password
     * @param mailProperties MailProperties
     */
    public QMeMailSender(String userName, String password, Properties mailProperties) {
        if(userName == null || userName.trim().length() == 0){
            throw new IllegalArgumentException("Invalid user name for QMe Mail Sender, please specify valid email user name");
        }
        if(password == null || password.trim().length() == 0){
            throw new IllegalArgumentException("Invalid user password for QMe Mail Sender, please specify valid email user password ");
        }
        if(mailProperties == null || mailProperties.size() == 0){
            throw new IllegalArgumentException("Invalid mail properties for QMe Mail Sender, please specify valid smtp mail properties ");
        }
        if(!mailProperties.containsKey(MAIL_SMTP_AUTH)){
            throw new IllegalArgumentException("Invalid mail properties for QMe Mail Sender, please specify valid smtp mail properties with authentication key");
        }
        if(!mailProperties.containsKey(MAIL_SMTP_HOST)){
            throw new IllegalArgumentException("Invalid mail properties for QMe Mail Sender, please specify valid smtp mail properties with host name key");
        }
        if(!mailProperties.containsKey(MAIL_SMTP_PORT)){
            throw new IllegalArgumentException("Invalid mail properties for QMe Mail Sender, please specify valid smtp mail properties with port key");
        }
        this.setUsername(userName);
        this.setPassword(password);
        this.setJavaMailProperties(mailProperties);
    }
}