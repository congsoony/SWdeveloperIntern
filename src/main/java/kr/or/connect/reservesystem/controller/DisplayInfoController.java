package kr.or.connect.reservesystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservesystem.dto.Comment;
import kr.or.connect.reservesystem.dto.DisplayInfo;
import kr.or.connect.reservesystem.dto.DisplayInfoImage;
import kr.or.connect.reservesystem.dto.ProductImages;
import kr.or.connect.reservesystem.dto.ProductPrice;
import kr.or.connect.reservesystem.service.CommentService;
import kr.or.connect.reservesystem.service.DisplayInfoImageService;
import kr.or.connect.reservesystem.service.DisplayInfoService;
import kr.or.connect.reservesystem.service.ProductImagesService;
import kr.or.connect.reservesystem.service.ProductPriceService;

@RestController
@RequestMapping(path = "/api/displayinfo")
public class DisplayInfoController {

	@Autowired
	private DisplayInfoService displayInfoService;
	@Autowired
	private ProductImagesService productImagesService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ProductPriceService productPriceService;
	@Autowired
	private DisplayInfoImageService displayInfoImageService;
	
	@GetMapping
	public Map<String, Object> getDisplayInfo(
			@RequestParam int displayInfoId) {
		
		DisplayInfo item = displayInfoService.getDisplayInfo(displayInfoId);
		List<ProductImages> mainImages=productImagesService.getProductImagesList(displayInfoId,"ma");
		List<ProductImages> etcImages=productImagesService.getProductImagesList(displayInfoId,"et");
		double averageScore=commentService.getCommentAverage(displayInfoId);
		List<Comment> comments = commentService.getCommentList(displayInfoId);
		List<ProductPrice> productPrices= productPriceService.getProductPriceList(displayInfoId);
		DisplayInfoImage displayInfoImage = displayInfoImageService.getDisplayInfoImage(displayInfoId);
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("displayInfo", item);
		map.put("mainImages", mainImages);
		map.put("etcImages", etcImages);
		map.put("averageScore", averageScore);
		map.put("comments", comments);
		map.put("productPrices", productPrices);
		map.put("displayInfoImage", displayInfoImage);
		
		return map;
	}
}
