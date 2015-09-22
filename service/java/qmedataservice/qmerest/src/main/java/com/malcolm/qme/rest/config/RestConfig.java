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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.keygen.KeyGenerators;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

/**
 * @author malcolm
 */
@Configuration
@PropertySource({"classpath:mail.properties"})
@Import(QMeSecurityConfig.class)
@ComponentScan({"com.malcolm.qme.rest"})
public class RestConfig {

    /**
     * Logger
     */
    private static Logger LOG = LoggerFactory.getLogger(RestConfig.class);

    /**
     * Token Key Length
     */
    private static final int TOKEN_KEY_LENGTH = 32;

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
            @Override
            public String generateUniqueResetToken() {
                return new String(Hex.encode(KeyGenerators.secureRandom(TOKEN_KEY_LENGTH).generateKey()));
            }
        };
    }

    @Bean
    public JavaMailSenderImpl javaMailSender(){
        String userName = environment.getProperty(QMeMailSender.MAIL_SMTP_USERNAME);
        if(userName == null || userName.trim().length() == 0){
            LOG.debug("Getting User Name from System Properties ");
            userName = environment.getProperty(QMeMailSender.MAIL_SMTP_USERNAME_SYS);
        }
        String passWord = environment.getProperty(QMeMailSender.MAIL_SMTP_PASSWORD);
        if(passWord == null || passWord.trim().length() == 0){
            LOG.debug("Getting User Password from System Properties ");
            passWord = environment.getProperty(QMeMailSender.MAIL_SMTP_PASSWORD_SYS);
        }
        String authTypeStr = environment.getProperty(QMeMailSender.MAIL_SMTP_AUTH_TYPE);
        if(authTypeStr == null || authTypeStr.trim().length() == 0){
            authTypeStr = QMeMailSender.SMPT_AUTH_TYPE.TLS.getAuthType();
        }
        QMeMailSender.SMPT_AUTH_TYPE authType = QMeMailSender.SMPT_AUTH_TYPE.fromString(authTypeStr);
        if(authType == null){
            authType = QMeMailSender.SMPT_AUTH_TYPE.TLS;
        }
        LOG.debug("Got Mail auth type "+authType);
        Properties mailProperties = null;
        if(QMeMailSender.SMPT_AUTH_TYPE.TLS == authType){
            mailProperties = mailTLS();
        }else if(QMeMailSender.SMPT_AUTH_TYPE.SSL == authType){
            mailProperties = mailSSL();
        }else{
            mailProperties = mailTLS();
        }
        return new QMeMailSender(userName,passWord,mailProperties);
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
                setProperty(QMeMailSender.MAIL_SMTP_AUTH,environment.getProperty(QMeMailSender.MAIL_SMTP_AUTH));
                setProperty(QMeMailSender.MAIL_SMTP_HOST,environment.getProperty(QMeMailSender.MAIL_SMTP_HOST));
                setProperty(QMeMailSender.MAIL_SMTP_PORT,environment.getProperty(QMeMailSender.MAIL_SMTP_PORT_TLS));
                setProperty(QMeMailSender.MAIL_SMTP_START_TLS,environment.getProperty(QMeMailSender.MAIL_SMTP_START_TLS));
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
                setProperty(QMeMailSender.MAIL_SMTP_AUTH,environment.getProperty(QMeMailSender.MAIL_SMTP_AUTH));
                setProperty(QMeMailSender.MAIL_SMTP_HOST,environment.getProperty(QMeMailSender.MAIL_SMTP_HOST));
                setProperty(QMeMailSender.MAIL_SMTP_PORT,environment.getProperty(QMeMailSender.MAIL_SMTP_PORT_SSL));
                setProperty(QMeMailSender.MAIL_SMTP_SSL_SOCKET_CLASS,environment.getProperty(QMeMailSender.MAIL_SMTP_SSL_SOCKET_CLASS));
                setProperty(QMeMailSender.MAIL_SMTP_SSL_SOCKET_PORT,environment.getProperty(QMeMailSender.MAIL_SMTP_SSL_SOCKET_PORT));
            }
        };
    }
}
