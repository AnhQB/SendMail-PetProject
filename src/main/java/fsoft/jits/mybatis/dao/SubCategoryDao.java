package fsoft.jits.mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fsoft.jits.mybatis.model.SubCategory;

@Repository
public class SubCategoryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String subCategoryMapper = "fsoft.jits.mapper.SubCategory";
	
	public List<SubCategory> findAll(){
		return sqlSession.selectList(subCategoryMapper + ".findAll");
	}
	

	public Map<Integer, SubCategory> findAllMap(){
		return sqlSession.selectMap(subCategoryMapper + ".findAll", "id");
	}
		
	public List<SubCategory> findAll(String searchByName){
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("searchName", searchByName);
		return sqlSession.selectList(subCategoryMapper + ".findAllSearch", parameters);
	}
	
	public void insert(SubCategory subCategory) {
		sqlSession.insert(subCategoryMapper + ".insert", subCategory);
	}
	
	public SubCategory findById(int id) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		return sqlSession.selectOne(subCategoryMapper + ".findById", parameters);
	}
	
	public void update(SubCategory subCategory) {
		sqlSession.update(subCategoryMapper + ".update" , subCategory);
	}
	
	public void delete(int id) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		sqlSession.delete(subCategoryMapper + ".deleteCategoryById", parameters);
	}
	
	public List<SubCategory> findAllByCategoryId(int id){
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("cateId", id);
		
		return sqlSession.selectList(subCategoryMapper + ".findAllByCateId", parameters);
	}
}
