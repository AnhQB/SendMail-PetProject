package fsoft.jits.controller;

import java.lang.annotation.Repeatable;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import fsoft.jits.mybatis.model.Category;
import fsoft.jits.mybatis.model.SubCategory;
import fsoft.jits.service.CategoryService;
import fsoft.jits.service.SubCategoryService;

@Controller
@RequestMapping("/subcategory")
public class SubCategoryController {
	@Autowired
	private SubCategoryService subCategoryService;
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/")
	public String index(@RequestParam(required=false) String searchByName, Model model) {
		List<SubCategory> lstSubCategory = subCategoryService.findAll(searchByName);
		Map<Integer, Category> mapCate = categoryService.getMapCategory();
		
		model.addAttribute("listSubCategory", lstSubCategory);
		model.addAttribute("mapCate", mapCate);
		model.addAttribute("searchByName", searchByName);
		return "subcategory/index";
	}
	

	@GetMapping("/create-update")
	public String createUpdate(@RequestParam(required=false) String id, Model model) {
		if(id != null && id != "") {
			int idInt = Integer.parseInt(id);
			SubCategory subCategory = subCategoryService.findById(idInt);
			model.addAttribute("subCategory", subCategory);
		}
		List<Category> listCate = categoryService.findAll();
		model.addAttribute("listCate", listCate);
		return "subcategory/create_update";
	}
	
	@PostMapping("/save")
	public String saveCategory(@ModelAttribute("subCategory") SubCategory subCategory) {
		if(subCategory.getId() == 0) {
			//insert
			subCategoryService.insert(subCategory);
		}else {
			//update
			subCategoryService.update(subCategory);
		}
		return "redirect:/subcategory/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		subCategoryService.delete(id);
		return "redirect:/subcategory/";
	}
	
	
	
	
}
