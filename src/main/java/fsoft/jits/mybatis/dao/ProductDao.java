package fsoft.jits.mybatis.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fsoft.jits.mybatis.model.Product;

@Repository
public class ProductDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	private static final String productMapper = "fsoft.jits.mapper.productMapper";
	
	public List<Product> findAll(){
		return sqlSession.selectList(productMapper + ".findAll");
	}
	
	public List<Product> findSearch(String name, float from, float to, int limit, int offset){
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", name);
		parameters.put("from", from );
		parameters.put("to", to);
		parameters.put("limit", limit);
		parameters.put("offset", offset);
		return sqlSession.selectList(productMapper + ".findSearch", parameters);
	}
	
	public void insert(Product product) {
		sqlSession.insert(productMapper + ".insertProduct", product);
	}
	
	public void deleteById(int id) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		sqlSession.delete(productMapper + ".deleteById", parameters);
	}
	
	public void update(Product product) {
		sqlSession.update(productMapper + ".updateProduct", product);
	}
	
	public Product findProductById(int id) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("id", id);
		return sqlSession.selectOne(productMapper + ".findById", parameters);
	}
	
	public void insertListProduct(List<Product> pList) {
		Map<String, Object> params = new HashMap<>();
		params.put("list", pList);
		sqlSession.insert(productMapper + ".insertListProduct", params);
	}

	public int countFindSearch(String name, float from, float to) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("name", name);
		parameters.put("from", from );
		parameters.put("to", to);
		return sqlSession.selectOne(productMapper + ".countFindSearch", parameters);
	}
}
