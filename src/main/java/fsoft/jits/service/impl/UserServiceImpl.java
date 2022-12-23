package fsoft.jits.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.internal.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import fsoft.jits.component.ConfigProperties;
import fsoft.jits.mybatis.dao.RoleDao;
import fsoft.jits.mybatis.dao.UserDao;
import fsoft.jits.mybatis.dao.UserRoleDao;
import fsoft.jits.mybatis.dto.ChangePassDTO;
import fsoft.jits.mybatis.model.AbstractAuditing;
import fsoft.jits.mybatis.model.User;
import fsoft.jits.mybatis.model.UserRole;
import fsoft.jits.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	private static final String roleUserName = "USER";
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	ConfigProperties configProp;
	
	@Autowired
    private JavaMailSender mailSender;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findByUserName(String userName) {
		return userDao.findByUserName(userName);
	}

	@Override
	public void register(User user, String siteURL) {
		//check username exist
		//check email exist or not
		//check DOB with condition
		//check accept term or not
		
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
		String pwd = RandomStringUtils.random( 10, characters );
		
//		passwordSalt = RandomStringUtils.randomAscii(20);
//		this.password = DigestUtils.shaHex(password + passwordSalt);
		
		user.setPassword(pwd);
		
		String randomCode = RandomString.make(64);
	    user.setVerificationCode(randomCode);
	    user.setEnabled(false);
		
		int result = userDao.insert(user);
		
		if(result < 0) {
			// return exception	
		}
		
		UserRole userRole = new UserRole(
				user.getId(), 
				roleDao.findByName(roleUserName).getRoleId()
				);
		userRoleDao.insert(userRole);
		
		try {
			sendVerificationEmail(user, siteURL);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			// 
			System.out.println("mail ko ton tai");
			e.printStackTrace();
		}
	}

	private void sendVerificationEmail(User user, String siteURL) throws UnsupportedEncodingException, MessagingException {
		String toAddress = user.getEmail();
	    String fromAddress = configProp.getConfigValue("spring.mail.username");
	    String senderName = "Demo Project";
	    String subject = "Please verify your registration";
	    String content = "Dear [[name]],<br>"
	    		+ "Please click the link below to verify your registration:<br>"
	            + "Password:      <b> [[pass]] </b><br>"
	            + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
	            + "Thank you,<br>"
	            + "Bai Bai company.";
	     
	    MimeMessage message = mailSender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom(fromAddress, senderName);
	    helper.setTo(toAddress);
	    helper.setSubject(subject);
	    
	    content = content.replace("[[name]]", user.getName());
	    content = content.replace("[[pass]]", user.getPassword());
	    String verifyURL = siteURL + "/verify?verificationCode=" + user.getVerificationCode();
	     
	    content = content.replace("[[URL]]", verifyURL);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
		
	}

	@Override
	public User verifyAccount(String code) {
		User user = userDao.findByVerificationCode(code);
		
		if(user == null || user.isEnabled()) {
			// return account verified
			return null;
		}else {
			user.setVerificationCode(null);
			user.setEnabled(true);
			user.setUpdatedDateTime(AbstractAuditing.getDateTimeNow());
			userDao.update(user);
			return user;
		}
	}

	@Override
	public void changePassword(ChangePassDTO chPassDTO) {
		if(!chPassDTO.getNewPassword().equalsIgnoreCase(chPassDTO.getConfirmPassword())) {
			//return exception pass not same
			return;
		}
		
		User user = userDao.findById(chPassDTO.getId());
		if(user != null) {
			if(!user.getPassword().equalsIgnoreCase(chPassDTO.getOldPassword())) {
				// return exception
				return;
			}
			user.setPassword(passwordEncoder.encode(chPassDTO.getNewPassword()));
			user.setUpdatedDateTime(AbstractAuditing.getDateTimeNow());
			userDao.changePassword(user);	
		}
	}
	
}
