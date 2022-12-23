package fsoft.jits;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import fsoft.jits.mybatis.model.Category;
import fsoft.jits.service.CategoryService;
import fsoft.jits.service.SubCategoryService;

@SpringBootApplication
@EnableScheduling
public class DemoProjectApplication implements CommandLineRunner {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SubCategoryService subCategoryService;

	public static void main(String[] args) {
		SpringApplication.run(DemoProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category cate = new Category("kho1");
		if(categoryService.findAll().isEmpty()) {
			categoryService.insertCategory(cate);
		}
		
		if(subCategoryService.findAll().isEmpty()) {
			subCategoryService.initSubCategory();
		}
//		categoryService.initCategory();
//		if(categoryService.findAll().isEmpty()) {
//			categoryService.initCategory();
//		}
		
	}
	
	
	

}
