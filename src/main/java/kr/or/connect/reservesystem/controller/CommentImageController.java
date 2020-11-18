package kr.or.connect.reservesystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservesystem.dto.CommentImage;
import kr.or.connect.reservesystem.service.CommentImageService;

@RestController
@RequestMapping(path = "/api/commentimage")
public class CommentImageController {

	@Autowired
	CommentImageService commentImageService;

	@GetMapping
	public Map<String, Object> getCommentImages(
			@RequestParam(name = "reservationUserCommentId", required = true) int reservationUserCommentId) {
		Map<String, Object> map = new HashMap<>();
		List<CommentImage> list = commentImageService.getCommentImageList(reservationUserCommentId);

		map.put("commentImages", list);

		return map;
	}
}
