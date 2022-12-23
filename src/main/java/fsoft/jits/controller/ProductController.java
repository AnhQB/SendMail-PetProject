package fsoft.jits.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import fsoft.jits.component.ExcelHelper;
import fsoft.jits.mybatis.dto.ProductDTO;
import fsoft.jits.mybatis.model.Category;
import fsoft.jits.mybatis.model.Product;
import fsoft.jits.mybatis.model.SubCategory;
import fsoft.jits.service.CategoryService;
import fsoft.jits.service.ProductService;
import fsoft.jits.service.SubCategoryService;

@Controller
@RequestMapping("/manager/products")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private SubCategoryService subCategoryService;
	
	@GetMapping("/")
	@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	public String index(@RequestParam(value = "searchByName",required = false) String searchByName
			, @RequestParam(value = "searchPriceFrom",required = false) String searchPriceFrom
			, @RequestParam(value = "searchPriceTo",required = false) String searchPriceTo
			, @RequestParam(value = "currentPage",required = false) String currentPage
			, Model model) {
		
		List<ProductDTO> listProduct = productService.findBySearch(searchByName, searchPriceFrom, searchPriceTo, currentPage);
		Map<Integer, Category> mapCategory = categoryService.getMapCategory();
		Map<Integer, SubCategory> mapSubCategory = subCategoryService.getMapAllSubCategory();
		int count = productService.countFindSearch(searchByName, searchPriceFrom, searchPriceTo);
		int numberPage =  count / ProductService.LIMIT + ((count % ProductService.LIMIT == 0) ? 0 : 1) ;
		model.addAttribute("listProduct", listProduct);
		model.addAttribute("mapCategory", mapCategory);
		model.addAttribute("mapSubCategory", mapSubCategory);
		model.addAttribute("searchByName", searchByName);
		model.addAttribute("searchPriceFrom", searchPriceFrom);
		model.addAttribute("searchPriceTo", searchPriceTo);
		model.addAttribute("currentPage", currentPage == null || currentPage == "" ? 1 : Integer.parseInt(currentPage));
		model.addAttribute("numberPage", numberPage);
		
		return "product/index";
	}
	
	@GetMapping("/create-update")
	public String createUpdate(@RequestParam(required = false) String id, Model model) {
		List<SubCategory> lstSubCate = new ArrayList<>();
		
		List<Category> lstCategory = categoryService.findAll();
		if(id != null && id != "") {
			int idInt = Integer.parseInt(id);
			Product product = productService.findProductById(idInt);
			lstSubCate = subCategoryService.findAllByCategoryId(product.getCategoryId());
			
			model.addAttribute("product", product);
		}else {
			lstSubCate = subCategoryService.findAllByCategoryId(lstCategory.get(0).getId());
		}
		
		
		model.addAttribute("lstCategory", lstCategory);
		model.addAttribute("lstSubCate",lstSubCate);
		return "product/create_update";
	}
	
	@PostMapping("/save")
	public String save(@ModelAttribute("product") ProductDTO product) {
		if(product.getId() <= 0) {
			productService.insertProduct(product);
		}else {
			productService.updateProduct(product);
		}
		return "redirect:/products/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
		productService.deleteProductById(id);
		return "redirect:/products/";
	}
	
	@GetMapping("/export")
	public ResponseEntity<Resource> exportFile(){
		String filename = "data" + Product.class.getSimpleName() + ExcelHelper.END;
		InputStreamResource file = new InputStreamResource(productService.exportExcel());
		return ResponseEntity.ok()
		        .header("Content-Disposition", "attachment; filename=" + filename)
		        .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
		        .body(file);
	}
	
	@PostMapping("/import")
	public String importFile(@RequestParam("file") MultipartFile file) {
		productService.importExcel(file);
		return "redirect:/products/";
	}
	
}
