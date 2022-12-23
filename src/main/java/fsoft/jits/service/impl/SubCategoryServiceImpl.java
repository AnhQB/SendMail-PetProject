package fsoft.jits.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fsoft.jits.mybatis.dao.CategoryDao;
import fsoft.jits.mybatis.dao.SubCategoryDao;
import fsoft.jits.mybatis.model.Category;
import fsoft.jits.mybatis.model.SubCategory;
import fsoft.jits.service.SubCategoryService;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{

	@Autowired
	private SubCategoryDao subCategoryDao;
	
	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<SubCategory> findAll() {
		return subCategoryDao.findAll();
	}

	@Override
	public void insert(SubCategory subCategory) {
		subCategoryDao.insert(subCategory);
		
	}

	@Override
	public void initSubCategory() {
		List<Category> listCate = categoryDao.findAll();
		int i = 0;
		for(Category cate: listCate){
			SubCategory subCategory = new SubCategory(cate.getId(), "anh" + i);
			i++;
			subCategoryDao.insert(subCategory);
			
			if(i==10) break;
		}
	}

	@Override
	public SubCategory findById(int id) {
		return subCategoryDao.findById(id);
	}

	@Override
	public void update(SubCategory subCategory) {
		subCategoryDao.update(subCategory);
	}

	@Override
	public void delete(int id) {
		subCategoryDao.delete(id);
	}

	@Override
	public List<SubCategory> findAll(String searchByName) {
		return subCategoryDao.findAll(searchByName);
	}

	@Override
	public Map<Integer, SubCategory> getMapAllSubCategory() {
		return subCategoryDao.findAllMap();
	}

	@Override
	public List<SubCategory> findAllByCategoryId(int id) {
		return subCategoryDao.findAllByCategoryId(id);
	}
}
