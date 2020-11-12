package kr.or.connect.reservesystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservesystem.dto.Category;
import kr.or.connect.reservesystem.dto.Product;
import kr.or.connect.reservesystem.service.CategoryService;
import kr.or.connect.reservesystem.service.ProductService;

@RestController
@RequestMapping(path = "/api/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public Map<String, Object> getCategories(@RequestParam(name = "categoryId", defaultValue = "0") int categoryId,
			@RequestParam(name = "start", defaultValue = "0") int start) {
	
		Map<String, Object> map = new HashMap<>();
		List<Product> list = null;
		int totalCount = -1;
		
		if (categoryId > 0) {
			list = productService.getSelectByCategoryId(start, categoryId);
			totalCount = productService.getSelectCategoryCount(categoryId);
		} else {
			list = productService.getSelectByAllCategory(start);
			totalCount = productService.getSelectALLCategoryCount();
		}
		
		map.put("items", list);
		map.put("totalCount", totalCount);
		map.put("categoryId", categoryId);
		
		return map;
	}

}
