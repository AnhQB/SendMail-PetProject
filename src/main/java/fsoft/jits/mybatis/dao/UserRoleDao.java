package fsoft.jits.mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fsoft.jits.mybatis.model.Role;
import fsoft.jits.mybatis.model.UserRole;

@Repository
public class UserRoleDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String userRoleMapper = "fsoft.jits.mapper.userRoleMapper";
	
	public List<UserRole> findByUserId(int userId){
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		return sqlSession.selectList(userRoleMapper + ".findByUserId", params);
	}
	
	public List<Role> findRoleByUserId(int userId){
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		return sqlSession.selectList(userRoleMapper + ".findRoleByUserId", params);
	}
	
	public void insert(UserRole userRole) {
		sqlSession.insert(userRoleMapper + ".insert", userRole);
	}
}
