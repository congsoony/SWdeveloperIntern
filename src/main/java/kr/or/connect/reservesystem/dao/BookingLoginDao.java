package kr.or.connect.reservesystem.dao;



import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import static kr.or.connect.reservesystem.dao.sqls.ReserveDaoSqls.*;

@Repository
public class BookingLoginDao {
	private NamedParameterJdbcTemplate jdbc;

	public BookingLoginDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public boolean existEmailId(String email) {
		Map<String, String> params = new HashMap<>();
		params.put("reservationEmail", email);
		return jdbc.queryForObject(SELECT_EXIST_BY_EMAIL, params, Boolean.class);
	}
}
