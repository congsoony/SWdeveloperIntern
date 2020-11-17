package kr.or.connect.reservesystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservesystem.dto.Category;
import kr.or.connect.reservesystem.service.CategoryService;

@RestController
@RequestMapping(path = "/api/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public Map<String, Object> getCategories() {

		Map<String, Object> map = new HashMap<>();
		List<Category> list = categoryService.getCategories();

		map.put("items", list);

		return map;

	}

}
