package fsoft.jits.service;

import java.util.List;
import java.util.Map;

import fsoft.jits.mybatis.model.Category;

public interface CategoryService {

	List<Category> findAll();

	void initCategory();

	void insertCategory(Category category);

	void deleteCategory(int id);

	Category findCategoryById(int id);

	void updateCategory(Category category);

	Map<Integer, Category> getMapCategory();

}
