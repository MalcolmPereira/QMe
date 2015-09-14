/**
 * Name      : com.malcolm.qme.config.QMeMailSenderTest.java
 * Date      : 9/14/15
 * Developer : Malcolm
 * Purpose   : Test Cases for QMeMailSender
 */
package com.malcolm.qme.config;

import com.malcolm.qme.rest.config.QMeMailSender;
import com.malcolm.qme.rest.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

/**
 * @author malcolm
 */
@RunWith(MockitoJUnitRunner.class)
public class QMeMailSenderTest {

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalExceptionForInvalidUserName(){
        QMeMailSender sender = new QMeMailSender(null,null,null);
        assertNotNull(sender);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalExceptionForInvalidPassword(){
        QMeMailSender sender = new QMeMailSender("username",null,null);
        assertNotNull(sender);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalExceptionForInvalidProperties(){
        QMeMailSender sender = new QMeMailSender("username","password",null);
        assertNotNull(sender);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalExceptionForInvalidPropertyKey(){
        QMeMailSender sender = new QMeMailSender("username","password",new Properties());
        assertNotNull(sender);
    }

    @Test
    public void testValidQMeMailConfig(){
        Properties prop = new Properties();
        prop.put(QMeMailSender.MAIL_SMTP_AUTH, "true");
        prop.put(QMeMailSender.MAIL_SMTP_HOST, "smtp.gmail.com");
        prop.put(QMeMailSender.MAIL_SMTP_PORT, "587");
        prop.put(QMeMailSender.MAIL_SMTP_START_TLS, "true");
        QMeMailSender sender = new QMeMailSender("someuser","somepassword",prop);
        assertNotNull(sender);
    }

    //Umcomment to run test
    //Enter valid Email Credentials Here
    //@Test
    public void testQMeMailSendEmail() throws MessagingException {
        Properties prop = new Properties();
        prop.put(QMeMailSender.MAIL_SMTP_AUTH, "true");
        prop.put(QMeMailSender.MAIL_SMTP_HOST, "smtp.gmail.com");
        prop.put(QMeMailSender.MAIL_SMTP_PORT, "587");
        prop.put(QMeMailSender.MAIL_SMTP_START_TLS, "true");

        //Update these values for testing
        String userName     = "";
        String password     = "";
        String toemail      = "";

        String appurl       = "http://some.application.user/sometoken123456789012345678";

        QMeMailSender sender = new QMeMailSender(userName,password,prop);
        assertNotNull(sender);


        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(sender.getUsername());

        helper.setSubject("QMe Application Password Reset Request");
        helper.setTo(toemail);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html><head><title>QMe Application Password Reset</title></head><body>");
        stringBuilder.append("<h2><b>Password Reset Requested : </b></h2>");

        stringBuilder.append("<br/>");
        LocalDateTime now = LocalDateTime.now();
        stringBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp; Requested Date: ");
        stringBuilder.append(now.format(DateTimeFormatter.ofPattern(UserService.TOKEN_VALIDITY_DATE_PATTERN)));
        stringBuilder.append("<br/>");
        LocalDateTime validUntil = now.plusMinutes(UserService.TOKEN_VALIDITY_MINUTES);
        stringBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;  Valid Until : ");
        stringBuilder.append(validUntil.format(DateTimeFormatter.ofPattern(UserService.TOKEN_VALIDITY_DATE_PATTERN)));
        stringBuilder.append("<br/>");
        stringBuilder.append("<br/>");

        stringBuilder.append("If you have not requested password reset, please ignore this email.");
        stringBuilder.append("<br/>");
        stringBuilder.append("<br/>");
        stringBuilder.append("To reset password, please click on link below or copy/paste the link into a new browser window.");
        stringBuilder.append("<br/>");
        stringBuilder.append("<br/>");
        stringBuilder.append("<a href=\"");
        stringBuilder.append(appurl );
        stringBuilder.append("\">");
        stringBuilder.append(appurl );
        stringBuilder.append("</a>");
        stringBuilder.append("<br/>");
        stringBuilder.append("</body></html");
        helper.setText(stringBuilder.toString(), true);

        sender.send(message);
    }

}





