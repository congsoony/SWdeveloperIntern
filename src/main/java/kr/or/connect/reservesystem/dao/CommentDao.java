package kr.or.connect.reservesystem.dao;

import static kr.or.connect.reservesystem.dao.sqls.CommentDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservesystem.dto.Comment;

@Repository
public class CommentDao {
	private RowMapper<Comment> rowMapper = BeanPropertyRowMapper.newInstance(Comment.class);
	private NamedParameterJdbcTemplate jdbc;

	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Comment> getCommentList(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();

		params.put("displayInfoId", displayInfoId);

		return jdbc.query(SELECT_BY_DISPLAY_INFO_ID, params, rowMapper);
	}

	public List<Comment> getCommentLimitList(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();

		params.put("displayInfoId", displayInfoId);
		params.put("limit", LIMIT);

		return jdbc.query(SELECT_BY_DISPLAY_INFO_ID_LIMIT, params, rowMapper);
	}

	public int getCommentTotalCount(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();

		params.put("displayInfoId", displayInfoId);

		return jdbc.queryForObject(SELECT_COUNT_BY_DISPLAY_INFO_ID, params, Integer.class);

	}

	public Double getCommentAverage(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();

		params.put("displayInfoId", displayInfoId);

		return jdbc.queryForObject(SELECT_AVG_BY_DISPLAY_INFO_ID, params, Double.class);
	}

	public int insertUserComment(Comment userComment) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userComment);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(INSERT_RESERVATION_USER_COMMENT, params, keyHolder);
		return keyHolder.getKey().intValue();
	}
}
