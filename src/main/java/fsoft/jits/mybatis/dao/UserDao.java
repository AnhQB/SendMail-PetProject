package fsoft.jits.mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fsoft.jits.mybatis.dto.ChangePassDTO;
import fsoft.jits.mybatis.model.User;

@Repository
public class UserDao {
	@Autowired
	private SqlSession sqlSession;
	
	private static final String userMapper = "fsoft.jits.mapper.userMapper";
	
	public List<User> findAll(){
		// Map<String,Object> parameters = new HashMap<String, Object>();
		// parameters.put("id", "1");
		return sqlSession.selectList(userMapper +".findAll");
	}

	public User findByUserName(String userName) {
		Map<String, Object> params = new HashMap<>();
		params.put("userName", userName);
		return sqlSession.selectOne(userMapper + ".findByUserName", params);
	}

	public int insert(User user) {
		
		return sqlSession.insert(userMapper + ".insert", user);
	}

	public User findByVerificationCode(String code) {
		Map<String, Object> params = new HashMap<>();
		params.put("code", code);
		return sqlSession.selectOne(userMapper + ".findByVerificationCode", params);
	}

	public void update(User user) {
		sqlSession.update(userMapper + ".update", user);
	}

	public User findById(int id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return sqlSession.selectOne(userMapper + ".findById", params);
	}

	public void changePassword(User user) {
		sqlSession.update(userMapper + ".changePassword", user);
	}

}
