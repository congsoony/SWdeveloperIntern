package kr.or.connect.reservesystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservesystem.dto.Comment;
import kr.or.connect.reservesystem.dto.CommentImage;
import kr.or.connect.reservesystem.dto.DisplayInfo;
import kr.or.connect.reservesystem.dto.DisplayInfoImage;
import kr.or.connect.reservesystem.dto.ProductImages;
import kr.or.connect.reservesystem.dto.ProductPrice;
import kr.or.connect.reservesystem.service.CommentImageService;
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
	private CommentImageService commentImageService;
	@Autowired
	private ProductPriceService productPriceService;
	@Autowired
	private DisplayInfoImageService displayInfoImageService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getDisplayInfo(@RequestParam int displayInfoId) {

		DisplayInfo item = displayInfoService.getDisplayInfo(displayInfoId);

		if (item == null) {// 잘못된 요청 처리
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<ProductImages> mainImages = productImagesService.getProductImagesList(displayInfoId, "ma");
		List<ProductImages> etcImages = productImagesService.getProductImagesList(displayInfoId, "et");

		double averageScore = commentService.getCommentAverage(displayInfoId);
		List<Comment> comments = commentService.getCommentLimitList(displayInfoId);
		int totalCount = commentService.getCommentTotalCount(displayInfoId);
		
		for (Comment it : comments) {

			List<CommentImage> imgList = commentImageService.getCommentImageList(it.getCommentId());
			it.setCommentImages(imgList);
		}

		List<ProductPrice> productPrices = productPriceService.getProductPriceList(displayInfoId);
		DisplayInfoImage displayInfoImage = displayInfoImageService.getDisplayInfoImage(displayInfoId);

		Map<String, Object> map = new HashMap<>();

		map.put("displayInfo", item);
		map.put("mainImages", mainImages);
		map.put("etcImages", etcImages);
		map.put("averageScore", averageScore);
		map.put("comments", comments);
		map.put("productPrices", productPrices);
		map.put("displayInfoImage", displayInfoImage);
		map.put("totalCount", totalCount);

		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}
