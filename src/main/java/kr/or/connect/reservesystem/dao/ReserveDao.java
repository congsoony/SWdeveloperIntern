package kr.or.connect.reservesystem.dao;

import javax.sql.DataSource;


import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;


import kr.or.connect.reservesystem.dto.ReservationInfoPrice;
import kr.or.connect.reservesystem.dto.ReserveInfo;
import static kr.or.connect.reservesystem.dao.sqls.ReserveDaoSqls.*;

@Repository
public class ReserveDao {

	private NamedParameterJdbcTemplate jdbc;

	public ReserveDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int insertReservationInfo(ReserveInfo reserveInfo){
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(reserveInfo);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(INSERT_RESERVATION_INFO, params, keyHolder);
		return keyHolder.getKey().intValue();
	}
	public int insertReservationInfoPrice(ReservationInfoPrice reservationInfoPrice) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(reservationInfoPrice);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(INSERT_RESERVATION_INFO_PRICE, params, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	
}
