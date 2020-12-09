package kr.or.connect.reservesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservesystem.dto.Comment;
import kr.or.connect.reservesystem.service.CommentService;
import kr.or.connect.reservesystem.service.DisplayInfoService;

@RestController
@RequestMapping(path = "/api/reviewWrite")
public class reviewWriteController {
	@Autowired
	CommentService commentService;
	@Autowired
	DisplayInfoService displayInfoService;
	
	@GetMapping
	public ResponseEntity<Object> displayInfo(@RequestParam int displayInfoId) {
		return new ResponseEntity<>(displayInfoService.getDisplayInfo(displayInfoId),HttpStatus.OK);
	}
	
	
	@PostMapping("/onlyComment")//사진없는 댓글
	public ResponseEntity<Object> userCommentInfo(@RequestBody Comment userComment) throws Exception{
		commentService.insertUserComment(userComment);
		return new ResponseEntity<>("mainpage",HttpStatus.OK);
	}
	
	
}
