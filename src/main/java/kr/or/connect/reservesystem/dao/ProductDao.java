package kr.or.connect.reservesystem.dao;

import static kr.or.connect.reservesystem.dao.sqls.ProductDaoSqls.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservesystem.dto.Product;

@Repository
public class ProductDao {

	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private NamedParameterJdbcTemplate jdbc;

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> getProductsList(int start, int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("categoryId", categoryId);
		params.put("limit", LIMIT);
		return jdbc.query(SELECT_BY_CATEGORYID, params, rowMapper);
	}
	
	public List<Product> getAllProductsList(int start) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", LIMIT);
		return jdbc.query(SELECT_BY_ALL_CATEGORY, params, rowMapper);
	}
	public int getProductsTotalCount(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(CATEGORY_COUNT, params, Integer.class);
	}
	
	public int getALLProductsTotalCount() {
		Map<String, Integer> params = new HashMap<>();
		return jdbc.queryForObject(CATEGORY_ALL_COUNT, params, Integer.class);
	}
	
}
