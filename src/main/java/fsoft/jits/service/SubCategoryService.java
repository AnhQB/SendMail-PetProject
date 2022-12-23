package fsoft.jits.service;

import java.util.List;
import java.util.Map;

import fsoft.jits.mybatis.model.SubCategory;

public interface SubCategoryService {

	List<SubCategory> findAll();

	void insert(SubCategory subCategory);

	SubCategory findById(int id);

	void initSubCategory();

	void update(SubCategory subCategory);

	void delete(int id);

	List<SubCategory> findAll(String searchByName);

	Map<Integer, SubCategory> getMapAllSubCategory();

	List<SubCategory> findAllByCategoryId(int id);

}
