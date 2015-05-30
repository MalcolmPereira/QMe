/**
 * Name      : com.malcolm.qme.rest.config.RestConfig.java
 * Date      : 5/20/15
 * Developer : Malcolm
 * Purpose   : Configuration for REST MVC
 */

package com.malcolm.qme.rest.config;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;
import com.malcolm.qme.rest.api.AtomicTokenGenerator;
import com.malcolm.qme.security.config.QMeSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author malcolm
 */
@Configuration
@PropertySource({"classpath:mail.properties"})
@Import(QMeSecurityConfig.class)
@ComponentScan({"com.malcolm.qme.rest"})
public class RestConfig {

    @Autowired
    private Environment environment;


    @Bean
    public Jackson2ObjectMapperBuilder jacksonBuilder() {
        final Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.indentOutput(true);
        builder.featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        builder.serializers(new LocalDateTimeSerializer());
        return builder;
    }

    @Bean
    public AtomicTokenGenerator atomicTokenGenerator() {

        return new AtomicTokenGenerator(){

            private final AtomicLong LAST_TIME_MS = new AtomicLong();

            @Override
            public Long generateUniqueResetToken() {

                Long now = System.currentTimeMillis();

                while(true) {

                    Long lastTime = LAST_TIME_MS.get();

                    if (lastTime >= now)
                        now = lastTime+1;

                    if (LAST_TIME_MS.compareAndSet(lastTime, now))
                        return now;
                }
            }
        };
    }

    @Bean
    public JavaMailSenderImpl javaMailSender(){
        JavaMailSenderImpl sender = new JavaMailSenderImpl();

        String userName = environment.getProperty(MAIL_SMTP_USERNAME);
        if(userName == null || userName.trim().length() == 0){
            userName = environment.getProperty(MAIL_SMTP_USERNAME_SYS);
        }
        String passWord = environment.getProperty(MAIL_SMTP_PASSWORD);
        if(passWord == null || passWord.trim().length() == 0){
            passWord = environment.getProperty(MAIL_SMTP_PASSWORD_SYS);
        }
        sender.setUsername(userName);
        sender.setPassword(passWord);

        String authTypeStr = environment.getProperty(MAIL_SMTP_AUTH_TYPE);
        if(authTypeStr == null || authTypeStr.trim().length() == 0){
            authTypeStr = SMPT_AUTH_TYPE.TLS.getAuthType();
        }
        SMPT_AUTH_TYPE authType = SMPT_AUTH_TYPE.fromString(authTypeStr);
        if(authType == null){
            authType = SMPT_AUTH_TYPE.TLS;
        }
        if(SMPT_AUTH_TYPE.TLS == authType){
            sender.setJavaMailProperties(mailTLS());
        }else if(SMPT_AUTH_TYPE.SSL == authType){
            sender.setJavaMailProperties(mailSSL());
        }else{
            sender.setJavaMailProperties(mailTLS());
        }
        return sender;
    }

    /**
     * JSON LocalDateTime Serializer
     */
    private final class LocalDateTimeSerializer extends StdScalarSerializer<LocalDateTime> {
        public LocalDateTimeSerializer() {
            super(LocalDateTime.class);
        }
        @Override
        public void serialize(LocalDateTime dateTime,
                              JsonGenerator jsonGenerator,
                              SerializerProvider provider) throws IOException, JsonGenerationException {
            jsonGenerator.writeString( dateTime.format(DateTimeFormatter.ofPattern("YYYY-dd-MM HH:mm:ss")));
        }
    }

    /**
     * Return Mail TLS Properties
     *
     * @return Mail TLS Properties
     */
    private Properties mailTLS() {
        return new Properties() {
            private static final long serialVersionUID = 6067241928722086747L;
            {
                setProperty(MAIL_SMTP_AUTH,environment.getProperty(MAIL_SMTP_AUTH));
                setProperty(MAIL_SMTP_HOST,environment.getProperty(MAIL_SMTP_HOST));
                setProperty(MAIL_SMTP_PORT,environment.getProperty(MAIL_SMTP_PORT_TLS));
                setProperty(MAIL_SMTP_START_TLS,environment.getProperty(MAIL_SMTP_START_TLS));
            }
        };
    }

    /**
     * Return Mail SSL Properties
     *
     * @return Mail SSL Properties
     */
    private Properties mailSSL() {
        return new Properties() {
            private static final long serialVersionUID = 6067341928721086747L;
            {
                setProperty(MAIL_SMTP_AUTH,environment.getProperty(MAIL_SMTP_AUTH));
                setProperty(MAIL_SMTP_HOST,environment.getProperty(MAIL_SMTP_HOST));
                setProperty(MAIL_SMTP_PORT,environment.getProperty(MAIL_SMTP_PORT_SSL));
                setProperty(MAIL_SMTP_SSL_SOCKET_CLASS,environment.getProperty(MAIL_SMTP_SSL_SOCKET_CLASS));
                setProperty(MAIL_SMTP_SSL_SOCKET_PORT,environment.getProperty(MAIL_SMTP_SSL_SOCKET_PORT));
            }
        };
    }

    /**
     * Mail Properties Parameters
     */
    private static final String MAIL_SMTP_USERNAME = "mail.smtp.username";
    private static final String MAIL_SMTP_PASSWORD = "mail.smtp.password";
    private static final String MAIL_SMTP_USERNAME_SYS = "mail.smtp.username.sys";
    private static final String MAIL_SMTP_PASSWORD_SYS = "mail.smtp.password.sys";
    private static final String MAIL_SMTP_AUTH = "mail.smtp.auth";
    private static final String MAIL_SMTP_AUTH_TYPE = "mail.smtp.auth.type";
    private static final String MAIL_SMTP_HOST = "mail.smtp.host";
    private static final String MAIL_SMTP_PORT = "mail.smtp.port";
    private static final String MAIL_SMTP_PORT_TLS = "mail.smtp.port.tls";
    private static final String MAIL_SMTP_START_TLS = "mail.smtp.starttls.enable";
    private static final String MAIL_SMTP_PORT_SSL = "mail.smtp.port.ssl";
    private static final String MAIL_SMTP_SSL_SOCKET_PORT = "mail.smtp.socketFactory.port";
    private static final String MAIL_SMTP_SSL_SOCKET_CLASS = "mail.smtp.socketFactory.class";

    enum SMPT_AUTH_TYPE {
        TLS("TLS"),
        SSL("SSL")
        ;
        private String authType;

        /**
         * Enum Constructor
         * @param authType
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
         * @param text
         * @return
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
}
