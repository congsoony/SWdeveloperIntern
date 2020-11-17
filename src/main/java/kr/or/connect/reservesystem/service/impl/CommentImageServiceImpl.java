package kr.or.connect.reservesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservesystem.dao.CommentImageDao;
import kr.or.connect.reservesystem.dto.CommentImage;
import kr.or.connect.reservesystem.service.CommentImageService;

@Service
public class CommentImageServiceImpl implements CommentImageService {

	@Autowired
	private CommentImageDao commentImageDao;

	@Override
	@Transactional(readOnly = true)
	public List<CommentImage> getCommentImageList(int reservationUserCommentId) {
		return commentImageDao.getCommentImageList(reservationUserCommentId);
	}

}
