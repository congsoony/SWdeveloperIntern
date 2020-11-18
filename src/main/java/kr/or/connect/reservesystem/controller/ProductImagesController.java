package kr.or.connect.reservesystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservesystem.dto.ProductImages;
import kr.or.connect.reservesystem.service.ProductImagesService;

@RestController
@RequestMapping(path = "/api/productimages")
public class ProductImagesController {

	@Autowired
	private ProductImagesService productImagesService;

	@GetMapping
	public Map<String, Object> getProductImages(@RequestParam(name = "displayInfoId", required = true) int displayInfoId) {

		Map<String, Object> map = new HashMap<>();
		List<ProductImages> list = productImagesService.getProductImagesList(displayInfoId);

		map.put("productImages", list);

		return map;
	}

}
