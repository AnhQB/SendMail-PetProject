package fsoft.jits.service;

import java.util.List;

import fsoft.jits.mybatis.model.Role;

public interface RoleService {

	List<Role> findAll();

	void insert(Role role);


}
