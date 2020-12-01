package kr.or.connect.reservesystem.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservesystem.dto.CommentImage;
import static kr.or.connect.reservesystem.dao.sqls.CommentImageDaoSqls.*;

@Repository
public class CommentImageDao {
	private RowMapper<CommentImage> rowMapper = BeanPropertyRowMapper.newInstance(CommentImage.class);
	private NamedParameterJdbcTemplate jdbc;

	public CommentImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<CommentImage> getCommentImageList(int reservationUserCommentId) {
		Map<String, Integer> params = new HashMap<>();
		
		params.put("reservationUserCommentId", reservationUserCommentId);
		
		return jdbc.query(SELECT_BY_RESERVATION_USER_COMMENT_ID, params, rowMapper);
	}

}
