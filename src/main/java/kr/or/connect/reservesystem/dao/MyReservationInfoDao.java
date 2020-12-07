package kr.or.connect.reservesystem.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservesystem.dto.MyReservationInfo;
import static kr.or.connect.reservesystem.dao.sqls.ReserveDaoSqls.*;

@Repository
public class MyReservationInfoDao {
	private RowMapper<MyReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(MyReservationInfo.class);
	private NamedParameterJdbcTemplate jdbc;

	public MyReservationInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<MyReservationInfo> getMyReservations(String reservationEmail) {
		Map<String, String> params = new HashMap<>();

		params.put("reservationEmail", reservationEmail);

		return jdbc.query(SELECT_BY_EMAIL_ID, params, rowMapper);
	}
	
	public int updateReservationInfoCancel(int reservationInfoId) {
		Map<String, Object> params = new HashMap<>();
		params.put("reservationInfoId", reservationInfoId);
		return jdbc.update(UPDATE_RESERVATION_INFO_CANCEL,params);
	}
}
