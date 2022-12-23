package fsoft.jits.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fsoft.jits.mybatis.dao.CategoryDao;
import fsoft.jits.mybatis.model.Category;
import fsoft.jits.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public void initCategory() {
		List<Category> list = new ArrayList<>();
		list.add(new Category("kho100"));
		list.add(new Category("kho102"));
		list.add(new Category("kho103"));
		categoryDao.insertListCategory(list);
	}

	@Override
	public void insertCategory(Category category) {
		categoryDao.insertCategory(category);
		
	}

	@Override
	public void deleteCategory(int id) {
		categoryDao.deleteCategory(id);
	}

	@Override
	public Category findCategoryById(int id) {
		return categoryDao.findCategoryById(id);
	}

	@Override
	public void updateCategory(Category category) {
		categoryDao.updateCategory(category);
	}

	@Override
	public Map<Integer, Category> getMapCategory() {
		return categoryDao.findAllMap();
	}

}
