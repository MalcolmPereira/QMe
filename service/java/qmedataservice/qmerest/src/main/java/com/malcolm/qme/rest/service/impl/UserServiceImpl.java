/**
 * Name      : com.malcolm.qme.rest.service.impl.UserServiceImpl.java
 * Date      : 5/26/15
 * Developer : Malcolm
 * Purpose   : QMeUser Service Implementation
 */

package com.malcolm.qme.rest.service.impl;

import com.malcolm.qme.core.domain.User;
import com.malcolm.qme.core.domain.UserRole;
import com.malcolm.qme.core.repository.QMeException;
import com.malcolm.qme.core.repository.UserRepository;
import com.malcolm.qme.core.repository.UserRoleRepository;
import com.malcolm.qme.rest.api.AtomicTokenGenerator;
import com.malcolm.qme.rest.exception.*;
import com.malcolm.qme.rest.model.QMeResetPassword;
import com.malcolm.qme.rest.model.QMeUser;
import com.malcolm.qme.rest.model.QMeUserDetail;
import com.malcolm.qme.rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Malcolm
 */
@Service
public final class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("UserRepository")
    private UserRepository userRepo;

    @Autowired
    @Qualifier("UserRoleRepository")
    private UserRoleRepository userRoleRepo;

    @Autowired
    private PasswordEncoder passcodeEncoder;

    @Autowired
    private AtomicTokenGenerator atomicTokenGenerator;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    @Override
    public QMeUserDetail searchByUser(String userName) throws QMeResourceNotFoundException,QMeServerException {
        try{
            User user = userRepo.findByUserName(userName);
            if(user == null){
                throw new QMeResourceNotFoundException("User with User  Name "+userName+" not found");
            }
            QMeUserDetail qMeUserDetail =  getQMeUserDetail(user);
            setUserRoles(qMeUserDetail);
            return  qMeUserDetail;

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeUserDetail searchByEmail(String userEmail) throws QMeResourceNotFoundException,QMeServerException {
        try{
            User user = userRepo.findByUserEmail(userEmail);
            if(user == null){
                throw new QMeResourceNotFoundException("User with User Email "+userEmail+" not found");
            }
            QMeUserDetail qMeUserDetail =  getQMeUserDetail(user);
            setUserRoles(qMeUserDetail);
            return  qMeUserDetail;

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public List<QMeUserDetail> list() throws QMeServerException {
        try{
            return  getQMeUserDetail(userRepo.findAll());

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeUserDetail searchById(Long id) throws QMeResourceNotFoundException,QMeServerException {
        try {
            User user = userRepo.findById(id);
            if(user == null){
                throw new QMeResourceNotFoundException("User with User Id "+id+" not found");
            }
            QMeUserDetail qMeUserDetail =  getQMeUserDetail(user);
            setUserRoles(qMeUserDetail);
            return  qMeUserDetail;
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeUserDetail save(QMeUser qMeUser, Long userId) throws QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException{
        try {
            User user = getUser(qMeUser);
            user = userRepo.save(user);
            return getQMeUserDetail(user);

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeUserDetail update(QMeUser qMeUser, Long id, Long userId) throws QMeResourceNotFoundException,QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException{
        try {
            User user = getUser(qMeUser,id,userId);
            user = userRepo.update(user, userId);
            return getQMeUserDetail(user);
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public void delete(Long id) throws QMeResourceNotFoundException,QMeServerException {
        try {
            User user = userRepo.findById(id);
            if(user == null){
                throw new QMeResourceNotFoundException("User with User Id "+id+" not found");
            }
            userRepo.delete(id);

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);

        }
    }

    @Override
    public void forgotPassword(String userEmail, String url) throws QMeInvalidResourceDataException,QMeResourceNotFoundException,QMeServerException {
        try{
            if(url == null || url.trim().length() == 0){
                throw new QMeInvalidResourceDataException("Invalid application url redirect parameter ");
            }
            if(userEmail == null || userEmail.trim().length() == 0){
                throw new QMeInvalidResourceDataException("Invalid user email address ");
            }

            userEmail = userEmail.trim();
            url = url.trim();

            User user =  userRepo.findByUserEmail(userEmail);
            if(user == null){
                throw new QMeResourceNotFoundException("User with User email "+userEmail+" not found");
            }

            String resetToken = atomicTokenGenerator.generateUniqueResetToken();
            userRepo.addResetToken(resetToken,user.getUserID());

            sendEmail(user.getUserName(), user.getUserEmail(), resetToken, url + "?token=" + resetToken);

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    @Override
    public QMeUserDetail resetPassword(String userEmail, QMeResetPassword qMeResetPassword) throws QMeInvalidResourceDataException,QMeResourceNotFoundException,QMeServerException  {
        try{
            if(userEmail == null || userEmail.trim().length() == 0){
                throw new QMeInvalidResourceDataException("Invalid user email address ");
            }
            userEmail = userEmail.trim();

            User user =  userRepo.findByUserEmail(userEmail);
            if(user == null){
                throw new QMeResourceNotFoundException("User with User email "+userEmail+" not found");
            }

            String userName = qMeResetPassword.getUserName();
            if(userName == null || userName.trim().length() == 0){
                throw new QMeInvalidResourceDataException("Invalid user name provided");
            }
            userName = userName.trim();
            if(!user.getUserName().equalsIgnoreCase(userName)){
                throw new QMeInvalidResourceDataException("User name does not match for  "+userEmail);
            }

            String resetToken = qMeResetPassword.getToken();

            LocalDateTime tokenCreatedTime = userRepo.getResetTokenCreateTime(resetToken, user.getUserID());
            if(tokenCreatedTime == null){
                throw new QMeInvalidResourceDataException("Reset Token not found for "+userEmail);
            }
            tokenCreatedTime = tokenCreatedTime.plusMinutes(TOKEN_VALIDITY_MINUTES);

            if(  LocalDateTime.now().isAfter(tokenCreatedTime)) {
                userRepo.deleteResetToken(resetToken,user.getUserID());
                throw new QMeInvalidResourceDataException("Reset Token expired for  "+userEmail);
            }

            user = userRepo.resetUserPassword(resetToken,user.getUserID(),passcodeEncoder.encode(qMeResetPassword.getUserPassword()));

            return getQMeUserDetail(user);

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);

        }
    }

    /**
     * Get User for Create
     *
     * @param qMeuser QMe User
     * @return User
     * @throws QMeResourceException
     */
    private User getUser(QMeUser qMeuser) throws QMeInvalidResourceDataException,QMeResourceConflictException, QMeServerException {
        try{
            if(qMeuser.getUserName() == null || qMeuser.getUserName().trim().length() == 0){
                throw new QMeInvalidResourceDataException("Valid User Name is required");
            }
            if(qMeuser.getUserPassword() == null || qMeuser.getUserPassword().trim().length() == 0){
                throw new QMeInvalidResourceDataException("Valid User Password is required");
            }
            if(qMeuser.getUserEmail() == null || qMeuser.getUserEmail().trim().length() == 0){
                throw new QMeInvalidResourceDataException("Valid User Email is required");
            }
            if(qMeuser.getUserFirstName() == null || qMeuser.getUserFirstName().trim().length() == 0){
                throw new QMeInvalidResourceDataException("Valid User First Name is required");
            }
            User user = userRepo.findByUserName(qMeuser.getUserName());
            if(user != null){
                throw new QMeResourceConflictException("User with username already exists, please use valid user name");
            }
            user = userRepo.findByUserEmail(qMeuser.getUserEmail());
            if(user != null){
                throw new QMeResourceConflictException("User with email address already exists, please use valid unique user email address");
            }
            return new User(
                    qMeuser.getUserName(),
                    passcodeEncoder.encode(qMeuser.getUserPassword()),
                    qMeuser.getUserFirstName(),
                    qMeuser.getUserLastName(),
                    qMeuser.getUserEmail()
            );

        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    /**
     * Set User Roles
     * @param qMeUserDetail QMe User Details
     * @throws QMeException
     */
    private void setUserRoles(QMeUserDetail qMeUserDetail) throws QMeException {
        List<UserRole> userRoles = userRoleRepo.findByUserId(qMeUserDetail.getUserId());
        List<String> userRoleList = new ArrayList<>();
        for(UserRole userRole : userRoles){
            userRoleList.add(userRole.getRoleName());
        }
        qMeUserDetail.setUserRoles(userRoleList);
    }

    /**
     * Get User for Update
     *
     * @param qMeuser QMe User
     * @return User
     * @throws QMeResourceException
     */
    private User getUser(QMeUser qMeuser, Long userId, Long updateUserId) throws QMeResourceNotFoundException,QMeServerException {
        try{
            User currentUser = userRepo.findById(userId);
            if(currentUser == null){
                throw new QMeResourceNotFoundException("User with User  ID "+userId+" not found");
            }
            String firstName = currentUser.getUserFirstName();
            if(qMeuser.getUserFirstName() != null && qMeuser.getUserFirstName().trim().length() > 0){
                firstName = qMeuser.getUserFirstName();
            }
            String lastName = currentUser.getUserLastName();
            if(qMeuser.getUserLastName() != null){
                lastName = qMeuser.getUserLastName();
            }
            return new User(
                    currentUser.getUserID(),
                    currentUser.getUserName(),
                    currentUser.getUserPassword(),
                    firstName,
                    lastName,
                    currentUser.getUserEmail(),
                    currentUser.getUserRegisteredDate(),
                    LocalDateTime.now(),
                    updateUserId
            );
        }catch(QMeException err){
            throw new QMeServerException(err.getMessage(),err);
        }
    }

    /**
     * Map User Domain Object to REST Model
     *
     * @param userList List of User
     * @return QMeUser Detail List
     */
    private List<QMeUserDetail> getQMeUserDetail(List<User> userList) {
        List<QMeUserDetail> qMeUsers = new ArrayList<>();
        if (userList == null) {
            return qMeUsers;
        }
        qMeUsers.addAll(
                userList.stream().map
                        (this::getQMeUserDetail).collect(Collectors.toList())
        );
        return qMeUsers;
    }

    /**
     * Map User Domain Object to REST Model
     *
     * @param user User
     * @return QMeUserDetail QMeUser Detail
     */
    private QMeUserDetail getQMeUserDetail(User user) {
        QMeUserDetail qmeUserDetail = new QMeUserDetail();
        qmeUserDetail.setUserId(user.getUserID());
        qmeUserDetail.setUserName(user.getUserName());
        qmeUserDetail.setUserFirstName(user.getUserFirstName());
        qmeUserDetail.setUserLastName(user.getUserLastName());
        qmeUserDetail.setUserEmail(user.getUserEmail());
        qmeUserDetail.setUserRegisteredDate(user.getUserRegisteredDate());
        qmeUserDetail.setUserUpdateDate(user.getUserUpdateDate());
        qmeUserDetail.setUpdateUserID(user.getUpdateUserID());
        //Fixme: Need to add updated  user name
        qmeUserDetail.setUpdateUserName("");

        return qmeUserDetail;
    }

    /**
     * Send Email Link for Password Reset
     *
     * @param userName User Name for whom password reset is requested
     * @param userEmail User Email for whom password reset is requested
     * @param resetToken Reset Token for password request
     * @param url URL for password reset form when users clicks on the email link
     * @throws QMeResourceException
     */
    private void sendEmail(String userName, String userEmail, String resetToken, String url)  throws QMeServerException {
        if(javaMailSender.getUsername() == null || javaMailSender.getUsername().trim().length() == 0 ||
                javaMailSender.getPassword() == null || javaMailSender.getPassword().trim().length() == 0){
            throw new QMeServerException("System Configuration Error, Please configue mail server details correctly");
        }
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message);
            helper.setFrom(javaMailSender.getUsername());

            helper.setSubject("QMe Application Password Reset Request");
            helper.setTo(userEmail);

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("<html><head><title>QMe Application Password Reset</title></head><body>");
            stringBuilder.append("<h2><b>Password Reset Requested : </b></h2>");

            stringBuilder.append("<br/>");
            LocalDateTime now = LocalDateTime.now();
            stringBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp; Requested Date: ");
            stringBuilder.append(now.format(DateTimeFormatter.ofPattern(TOKEN_VALIDITY_DATE_PATTERN)));
            stringBuilder.append("<br/>");
            LocalDateTime validUntil = now.plusMinutes(TOKEN_VALIDITY_MINUTES);
            stringBuilder.append("&nbsp;&nbsp;&nbsp;&nbsp;  Valid Until : ");
            stringBuilder.append(validUntil.format(DateTimeFormatter.ofPattern(TOKEN_VALIDITY_DATE_PATTERN)));
            stringBuilder.append("<br/>");
            stringBuilder.append("<br/>");

            stringBuilder.append("If you have not requested password reset, please ignore this email.");
            stringBuilder.append("<br/>");
            stringBuilder.append("<br/>");
            stringBuilder.append("To reset password, please click on link below or copy/paste the link into a new browser window.");
            stringBuilder.append("<br/>");
            stringBuilder.append("<br/>");
            stringBuilder.append("<a href=\"");
            stringBuilder.append(url);
            stringBuilder.append("\">");
            stringBuilder.append(url);
            stringBuilder.append("</a>");
            stringBuilder.append("<br/>");
            stringBuilder.append("</body></html");

            helper.setText(stringBuilder.toString(),true);


            javaMailSender.send(message);

        }catch (MessagingException messagingErr){
            throw new QMeServerException("System  Error, Error Sending Email Message",messagingErr);
        }
    }
}