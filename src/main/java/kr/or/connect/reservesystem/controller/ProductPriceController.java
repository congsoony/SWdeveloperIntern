package kr.or.connect.reservesystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservesystem.dto.ProductPrice;
import kr.or.connect.reservesystem.service.ProductPriceService;

@RestController
@RequestMapping(path = "/api/productprices")
public class ProductPriceController {

	@Autowired
	private ProductPriceService productPriceService;

	@GetMapping
	public Map<String, Object> getProductPrices(@RequestParam(name = "displayInfoId", required = true) int displayInfoId) {

		Map<String, Object> map = new HashMap<>();
		List<ProductPrice> list = productPriceService.getProductPriceList(displayInfoId);

		map.put("productPrices", list);

		return map;
	}
}
