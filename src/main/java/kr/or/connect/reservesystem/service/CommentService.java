package kr.or.connect.reservesystem.service;

import java.util.List;

import kr.or.connect.reservesystem.dto.Comment;

public interface CommentService {
	public List<Comment> getCommentList(int displayInfoId);
	public List<Comment> getCommentLimitList(int displayInfoId);
	public double getCommentAverage(int displayInfoId);
	public int getCommentTotalCount(int displayInfoId);
	public int insertUserComment(Comment userComment);
	
}
