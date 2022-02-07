package com.user.service;

import com.user.dao.RoleDao;
import com.user.dao.UserDao;
import com.user.entity.Role;
import com.user.entity.User;
import com.user.exception.InvalidInputException;
import com.user.model.SignUp;
import com.user.model.UserRequest;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserManagerService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private JavaMailSender mailSender;

    public List<User> getUsers(){
        return userDao.viewAll();
    }

    public Optional<User> getUser(Long userId) {
        return userDao.viewById(userId);
    }

    public User addUser(UserRequest input) throws MessagingException, UnsupportedEncodingException {
        Role role = roleDao.fetchRoleByLevel(input.getRole());


        User user = new User();
        user.setUserName(input.getUsername());
        user.setFullName(input.getFullName());
        user.setEmailAddress(input.getEmailAddress());
        user.setRole(role);
        user.setStatus("Pending");

        String randomCode = RandomString.make(64);
        user.setVerificationCode(randomCode);

        User response = userDao.add(user);
        sendVerificationEmail(user, "http://localhost:8080/");

        return response;
    }

    private void sendVerificationEmail(User user, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmailAddress();
        String fromAddress = "dummytestacc999@gmail.com";
        String senderName = "Dummy User Manager";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Dummy User Manager.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFullName());
        String verifyURL = siteURL + "/verify/" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);
    }

    public Long verifyUser(String verificationCode){
        User user = userDao.findUserByCode(verificationCode);
        if(user != null){
            return user.getId();
        }
        return null;
    }

    public User updatePassword(SignUp input){
        Optional<User> user = userDao.viewById(input.getId());
        User updatedUser = user.get();
        updatedUser.setPassword(input.getPassword());
        updatedUser.setStatus("Active");
        return updateUser(updatedUser, input.getId());
    }

    public User updateUser(User user, Long userId){
        return userDao.update(user, userId);
    }

    public void deleteUser(Long userId) {
        userDao.delete(userId);
    }

    public Role getRoleForUser(SignUp input) throws InvalidInputException {
        try {

            User user = userDao.findUserByEmail(input.getUserName());
            if (user.getPassword().equals(input.getPassword())) {
                return user.getRole();
            } else {
                throw new InvalidInputException("Invalid Username or Password");
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public void addRoles(){
        List<Role> roles = new ArrayList<>();
        Role r1 = new Role("level-1", new String[]{"read"});
        Role r2 = new Role("level-2", new String[]{"read", "create"});
        Role r3 = new Role("level-3", new String[]{"read", "create", "delete"});
        roles.add(r1);
        roles.add(r2);
        roles.add(r3);
        for(Role r: roles){
            roleDao.add(r);
        }
    }

}
