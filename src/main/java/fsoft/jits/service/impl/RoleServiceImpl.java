package fsoft.jits.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fsoft.jits.mybatis.dao.RoleDao;
import fsoft.jits.mybatis.model.Role;
import fsoft.jits.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService{
	
	@Autowired
	private RoleDao roleDao;
	
	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public void insert(Role role) {
		role.setName(role.getName().toUpperCase());
		roleDao.insert(role);
	}
	
}
