package kr.or.connect.reservesystem.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservesystem.dto.DisplayInfo;
import static kr.or.connect.reservesystem.dao.sqls.DisplayInfoDaoSqls.*;

@Repository
public class DisplayInfoDao {

	private RowMapper<DisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	private NamedParameterJdbcTemplate jdbc;

	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public DisplayInfo getDisplayInfo(int displayInfoId) {
		Map<String, Integer> params = new HashMap<>();

		params.put("displayInfoId", displayInfoId);

		return jdbc.queryForObject(SELECT_BY_DISPLAY_INFO_ID, params, rowMapper);
	}
}
