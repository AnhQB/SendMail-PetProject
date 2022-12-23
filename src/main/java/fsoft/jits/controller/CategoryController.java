package fsoft.jits.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fsoft.jits.mybatis.model.Category;
import fsoft.jits.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/")
	public String getAllCategory(Model model) {
		Authentication authen = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(authen.toString());
		List<Category> listCate = categoryService.findAll();
		model.addAttribute("listCate", listCate);
		
		return "category/index";
	}
	
	@GetMapping("/create-update")
	public String insertCategory(@RequestParam(required=false) String cateId, Model model) {
		
		if(cateId != null && cateId != "") {
			int cateIdInt = Integer.parseInt(cateId);
			
			Category cate =  categoryService.findCategoryById(cateIdInt);
			model.addAttribute("category", cate);
		}
		
		return "category/create_update";
	}
	
	@PostMapping("/save")
	public String storeCategory(@ModelAttribute("category") Category category) {
//		categoryService.insertCategory(category);
		if(category.getId() == 0) {
			categoryService.insertCategory(category);
		}else {
			categoryService.updateCategory(category);
		}
		
		return "redirect:/categories/";
	}
	
	@GetMapping("/delete")
	public String deleteCategory(@RequestParam int cateId) {
		categoryService.deleteCategory(cateId);
		return "redirect:/categories/";
		
	}
	
	@GetMapping("/initCategory")
	public String initCate() {
		categoryService.initCategory();
		return "redirect:/categories/";
	}
	
//	
//	@GetMapping("/edit")
//	public String updateCategory(@RequestParam int cateId, Model model) {
//		Category cate =  categoryService.findCategoryById(cateId);
//		model.addAttribute("category", cate);
//		
//		return "category/update";
//	}
//	
//	@PostMapping("/update")
//	public String updateCategory(@ModelAttribute("category") Category category) {
//		categoryService.updateCategory(category);
//		return "redirect:/categories/";
//	}
}
