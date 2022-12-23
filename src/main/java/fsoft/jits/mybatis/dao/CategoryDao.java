package fsoft.jits.mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fsoft.jits.mybatis.model.Category;

@Repository
public class CategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String categoryMapper = "fsoft.jits.mapper.categoryMapper";
	
	public List<Category> findAll(){
		return sqlSession.selectList(categoryMapper + ".findAll" );
	}
	
	public Map<Integer, Category> findAllMap(){
		return sqlSession.selectMap(categoryMapper + ".findAll", "id");
	}
	
	public void insertListCategory(List<Category> lstCategory) {
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("list", lstCategory);
		sqlSession.insert(categoryMapper + ".insertListCategory", parameters);
	}
	
	public void insertCategory(Category category) {
		sqlSession.insert(categoryMapper + ".insertCategory", category);
	}
	
	public void deleteCategory(int id) {
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		sqlSession.delete(categoryMapper + ".deleteCategory", parameters);
	}
	
	public Category findCategoryById(int id) {
		Map<String,Object> parameters = new HashMap<String, Object>();
		parameters.put("id", id);
		return sqlSession.selectOne(categoryMapper + ".findCategoryById", parameters);
	}
	
	public void updateCategory(Category category) {
		sqlSession.insert(categoryMapper + ".updateCategory", category);
	}
	
}
