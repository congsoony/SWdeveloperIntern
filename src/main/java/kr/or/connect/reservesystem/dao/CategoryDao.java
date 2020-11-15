package kr.or.connect.reservesystem.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import org.springframework.stereotype.Repository;
import kr.or.connect.reservesystem.dto.Category;
import static kr.or.connect.reservesystem.dao.CategoryDaoSqls.*;

@Repository
public class CategoryDao {
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);
	private NamedParameterJdbcTemplate jdbc;

	public CategoryDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Category> selectAll() {
		Map<String, Integer> params = new HashMap<>();
		return jdbc.query(SELECT_ALL, params, rowMapper);
	}
}
