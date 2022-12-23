package fsoft.jits.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fsoft.jits.mybatis.model.SubCategory;
import fsoft.jits.service.SubCategoryService;

@RestController
@RequestMapping("/api")
public class APIController {

	@Autowired
	private SubCategoryService subCategoryService;
	
	@PostMapping("/get_all_by_cate_id")
	public List<SubCategory> apiGetAllByCateId(@RequestParam(value = "cateId") int cateId) {
		List<SubCategory> list = subCategoryService.findAllByCategoryId(cateId);
//		ModelAndView view = new ModelAndView();
//		view.addObject("list", list);
		return list;
	}
}
