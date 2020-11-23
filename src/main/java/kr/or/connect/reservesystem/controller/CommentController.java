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
import kr.or.connect.reservesystem.dto.CommentImage;
import kr.or.connect.reservesystem.dto.DisplayInfo;
import kr.or.connect.reservesystem.service.CommentImageService;
import kr.or.connect.reservesystem.service.CommentService;
import kr.or.connect.reservesystem.service.DisplayInfoService;

@RestController
@RequestMapping(path = "/api/comment")
public class CommentController {

	@Autowired
	CommentService commentService;
	@Autowired
	private CommentImageService commentImageService;
	@Autowired
	private DisplayInfoService displayInfoService;
	
	@GetMapping
	public Map<String, Object> getCategories(@RequestParam int displayInfoId) {

		Map<String, Object> map = new HashMap<>();
		List<Comment> list = commentService.getCommentList(displayInfoId);
		
		DisplayInfo item = displayInfoService.getDisplayInfo(displayInfoId);
		double averageScore=commentService.getCommentAverage(displayInfoId);
		List<Comment> comments = commentService.getCommentList(displayInfoId);
		int totalCount=commentService.getCommentTotalCount(displayInfoId);
		
		for(Comment it:comments) {
			
			List<CommentImage> imglist=commentImageService.getCommentImageList(it.getCommentId());
			it.setCommentImages(imglist);	
		}
		
		
		map.put("averageScore",averageScore);
		map.put("totalCount",totalCount);
		map.put("comments", comments);
		map.put("item", item);
		return map;
	}
}
