package kr.or.connect.reservesystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservesystem.dto.Promotion;
import kr.or.connect.reservesystem.service.PromotionService;

@RestController
@RequestMapping("api/promotions")
public class PromotionController {
	@Autowired
	PromotionService promotionService;
	
	@GetMapping
	public Map<String,Object> getPromotions(){
		Map<String,Object> map = new HashMap<>();
		List<Promotion> list=promotionService.getPromotions();
		map.put("items", list);
		return map;
	}
}
