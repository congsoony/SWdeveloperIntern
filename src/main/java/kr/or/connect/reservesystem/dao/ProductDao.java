package kr.or.connect.reservesystem.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservesystem.dto.Product;
import static kr.or.connect.reservesystem.dao.ProductDaoSqls.*;

@Repository
public class ProductDao {

	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private NamedParameterJdbcTemplate jdbc;

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> selectByCategoryId(int start, int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("categoryId", categoryId);
		params.put("limit", LIMIT);
		return jdbc.query(SELECT_BY_CATEGORYID, params, rowMapper);
	}
	
	public List<Product> selectByALLCategory(int start) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", LIMIT);
		return jdbc.query(SELECT_BY_ALL_CATEGORY, params, rowMapper);
	}
	public int selectCategoryCount(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(CATEGORY_COUNT, params, Integer.class);
	}
	
	public int selectCategoryAllCount() {
		Map<String, Integer> params = new HashMap<>();
		return jdbc.queryForObject(CATEGORY_ALL_COUNT, params, Integer.class);
	}
	
}