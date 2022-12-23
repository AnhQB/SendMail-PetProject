package fsoft.jits.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import fsoft.jits.mybatis.dto.ProductDTO;
import fsoft.jits.mybatis.model.Product;

public interface ProductService {
	
	public static final int LIMIT = 10;
		
	List<ProductDTO> findAll();

	void insertProduct(ProductDTO product);

	Product findProductById(int id);

	void deleteProductById(int id);

	void updateProduct(ProductDTO product);

	List<ProductDTO> findBySearch(String name, String from, String to, String currentPage);
	
	ByteArrayInputStream exportExcel();

	void importExcel(MultipartFile file);
	
	int countFindSearch(String name, String from, String to);

}
