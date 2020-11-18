package kr.or.connect.reservesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservesystem.dao.CommentDao;
import kr.or.connect.reservesystem.dto.Comment;
import kr.or.connect.reservesystem.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	@Transactional(readOnly = true)
	public List<Comment> getCommentList(int displayInfoId) {
		return commentDao.getCommentList(displayInfoId);
	}

}