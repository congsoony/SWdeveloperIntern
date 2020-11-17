package kr.or.connect.reservesystem.dao;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservesystem.dto.DisplayInfoImage;
import static kr.or.connect.reservesystem.dao.sqls.DisplayInfoImageDaoSqls.*;

@Repository
public class DisplayInfoImageDao {

	private RowMapper<DisplayInfoImage> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	private NamedParameterJdbcTemplate jdbc;

	public DisplayInfoImageDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public DisplayInfoImage getDisplayInfoImage(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();

		params.put("displayInfoId", displayInfoId);

		return jdbc.queryForObject(SELECT_BY_DISPLAY_INFO_ID, params, rowMapper);
	}

}
