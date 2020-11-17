package kr.or.connect.reservesystem.service;

import java.util.List;

import kr.or.connect.reservesystem.dto.Comment;

public interface CommentService {
	public List<Comment> getCommentList(int displayInfoId);
	
}
