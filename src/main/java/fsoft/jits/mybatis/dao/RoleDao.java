package fsoft.jits.mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fsoft.jits.mybatis.model.Role;

@Repository
public class RoleDao {
	@Autowired
	private SqlSession sqlSession;
	
	private static final String roleMapper = "fsoft.jits.mapper.roleMapper";

	public List<Role> findAll() {
		return sqlSession.selectList(roleMapper + ".findAll");
	}

	public void insert(Role role) {
		sqlSession.insert(roleMapper + ".insert", role);
	}
	
	public Role findByName(String name) {
		Map<String, Object> params = new HashMap<>();
		params.put("name", name);
		return sqlSession.selectOne(roleMapper + ".findByName", name);
	}
}
