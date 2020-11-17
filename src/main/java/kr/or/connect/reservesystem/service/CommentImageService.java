package kr.or.connect.reservesystem.service;

import java.util.List;

import kr.or.connect.reservesystem.dto.CommentImage;

public interface CommentImageService {
	public List<CommentImage> getCommentImageList(int reservationUserCommentId);
	
}
