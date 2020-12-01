package kr.or.connect.reservesystem.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservesystem.dto.ProductImages;
import static kr.or.connect.reservesystem.dao.sqls.ProductImagesDaoSqls.*;

@Repository
public class ProductImagesDao {

	private RowMapper<ProductImages> rowMapper = BeanPropertyRowMapper.newInstance(ProductImages.class);
	private NamedParameterJdbcTemplate jdbc;

	public ProductImagesDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<ProductImages> getProductImagesList(int displayInfoId,String type) {
		Map<String, Object> params = new HashMap<>();
		
		params.put("displayInfoId", displayInfoId);
		params.put("type", type);
		return jdbc.query(SELECT_BY_DISPLAY_INFO_ID, params, rowMapper);
	}

}
