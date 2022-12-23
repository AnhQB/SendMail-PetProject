package fsoft.jits.service;

import java.util.List;

import fsoft.jits.mybatis.dto.ChangePassDTO;
import fsoft.jits.mybatis.model.User;

public interface UserService {

	List<User> findAll();
	
	User findByUserName(String userName);

	void register(User user, String siteURL);

	User verifyAccount(String code);

	void changePassword(ChangePassDTO chPassDTO);

}
