package kr.or.connect.reservesystem.dao.sqls;

public class ReserveDaoSqls {
	public static final String INSERT_RESERVATION_INFO="INSERT INTO reservation_info "
			+ "(product_id, display_info_id, reservation_name,reservation_tel,reservation_email,reservation_date, create_date,modify_date) "
			+ "VALUES(:productId, :displayInfoId,:reservationName,:reservationTel,:reservationEmail,now(),now(),now())";
	public static final String INSERT_RESERVATION_INFO_PRICE="INSERT INTO reservation_info_price "
			+ "(reservation_info_id, "
			+ "product_price_id, "
			+ "count) "
			+ "VALUES(:reservationInfoId,:productPriceId,:count)";
	public static final String SELECT_EXIST_BY_EMAIL="SELECT EXISTS "
			+ "(SELECT * FROM  reservation_info "
			+ "WHERE reservation_email=:reservationEmail) as count";
			
	
}
