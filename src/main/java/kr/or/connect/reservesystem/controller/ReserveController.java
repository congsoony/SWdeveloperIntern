package kr.or.connect.reservesystem.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservesystem.dto.DisplayInfo;
import kr.or.connect.reservesystem.dto.ReservationInfo;
import kr.or.connect.reservesystem.dto.ReserveInfo;
import kr.or.connect.reservesystem.service.DisplayInfoService;
import kr.or.connect.reservesystem.service.ProductImagesService;
import kr.or.connect.reservesystem.service.ProductPriceService;
import kr.or.connect.reservesystem.service.ReserveService;

@RestController
@RequestMapping(path = "/api/reserve")
public class ReserveController {
	@Autowired
	private DisplayInfoService displayInfoService;
	@Autowired
	private ProductPriceService productPriceService;
	@Autowired
	private ProductImagesService productImagesService;
	@Autowired
	private ReserveService reserveService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getDisplayInfo(@RequestParam int displayInfoId) {
		DisplayInfo displayInfo = displayInfoService.getDisplayInfo(displayInfoId);

		if (displayInfo == null) {// 잘못된 요청 처리
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("displayInfo", displayInfo);
		map.put("productPrice", productPriceService.getProductPriceList(displayInfoId));
		map.put("productImages", productImagesService.getProductImagesList(displayInfoId, "ma"));
		return new ResponseEntity<>(map, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Object> reserveInfo(@RequestBody ReserveInfo reserveInfo) throws Exception{
		try {
			reserveService.insertReservationInfo(reserveInfo);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("mainpage", HttpStatus.OK);
	}
}
