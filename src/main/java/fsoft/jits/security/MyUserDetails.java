package fsoft.jits.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fsoft.jits.exception.CustomException;
import fsoft.jits.mybatis.dao.RoleDao;
import fsoft.jits.mybatis.dao.UserDao;
import fsoft.jits.mybatis.dao.UserRoleDao;
import fsoft.jits.mybatis.model.Role;
import fsoft.jits.mybatis.model.User;
import fsoft.jits.mybatis.model.UserRole;

@Service
public class MyUserDetails implements UserDetailsService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public UserDetails loadUserByUsername(String input) throws UsernameNotFoundException {
		
		final User user;
		if(input.contains("@")) {
			//find by email
			user = userDao.findByUserName(input);
		}else {
			//find by user name
			user = userDao.findByUserName(input);
		}
		
		if(user == null) {
			throw new UsernameNotFoundException("User '" + input + "' not found");
		}
		
		List<Role> listRole = userRoleDao.findRoleByUserId(user.getId());
		List<GrantedAuthority> authorities = buildUserAuthority(listRole);
		
		return buildUserForAuthentication(user, authorities);
	}
	
	private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, 
		List<GrantedAuthority> authorities) {
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(), 
				user.isEnabled(), 
				true, true, true, 
				authorities);
	}	
	
	private List<GrantedAuthority> buildUserAuthority(List<Role> listRole) {
		Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

		// Build user's authorities
		for (Role role : listRole) {
			setAuths.add(new SimpleGrantedAuthority(role.getName()));
		}

		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

		return Result;
	}
}
