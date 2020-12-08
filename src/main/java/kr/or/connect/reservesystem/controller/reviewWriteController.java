package kr.or.connect.reservesystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservesystem.dto.Comment;
import kr.or.connect.reservesystem.service.CommentService;

@RestController
@RequestMapping(path = "/api/reviewWrite")
public class reviewWriteController {
	@Autowired
	CommentService commentService;
	
	
	
	@PostMapping("/onlyComment")//사진없는 댓글
	public ResponseEntity<Object> userCommentInfo(@RequestBody Comment userComment) throws Exception{
		System.out.println(userComment);
		return new ResponseEntity<>("mainpage", HttpStatus.OK);
	}
	
	
}
