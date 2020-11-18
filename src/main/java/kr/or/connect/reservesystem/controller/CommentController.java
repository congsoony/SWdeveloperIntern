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
import kr.or.connect.reservesystem.service.CommentService;

@RestController
@RequestMapping(path = "/api/comment")
public class CommentController {

	@Autowired
	CommentService commentService;

	@GetMapping
	public Map<String, Object> getCategories(@RequestParam(name = "displayInfoId", required = true) int displayInfoId) {

		Map<String, Object> map = new HashMap<>();
		List<Comment> list = commentService.getCommentList(displayInfoId);

		map.put("items", list);

		return map;
	}
}
