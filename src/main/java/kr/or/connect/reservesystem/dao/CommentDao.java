package kr.or.connect.reservesystem.dao;

import static kr.or.connect.reservesystem.dao.sqls.CommentDaoSqls.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
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
		return jdbc.query(SELECT_BY_DISPLAY_INFO_ID, params, rowMapper);
	}
	
}
