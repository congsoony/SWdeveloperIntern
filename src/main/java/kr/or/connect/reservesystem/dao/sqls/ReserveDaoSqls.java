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
	
	public static final String SELECT_BY_EMAIL_ID="select display_info.tel as display_info_tel,"
			+ "reservation_info.id as reservation_info_id,place_name,reservation_info.display_info_id as display_info_id,"
			+ "reservation_info.cancel_flag as cancel_flag, "
			+ "reservation_info.product_id as product_id, "
			+ "DATE_FORMAT(reservation_info.reservation_date, '%Y.%c.%e') as reservation_date, "
			+ "DATE_FORMAT(DATE_ADD(reservation_info.reservation_date,INTERVAL 3 DAY), '%Y.%c.%e') as until_date,"
			+ "CASE WHEN cancel_flag =TRUE THEN FALSE ELSE (CASE WHEN DATE_ADD(reservation_info.reservation_date,INTERVAL 3 DAY)<now() THEN TRUE ELSE FALSE END) END AS used,"
			+ "dayofweek(reservation_date) as start_day,"
			+ "dayofweek(DATE_ADD(reservation_info.reservation_date,INTERVAL 3 DAY)) as until_day,"
			+ "reservation_info.reservation_email as reservation_email, "
			+ "reservation_info.reservation_tel as reservation_tel,"
			+ "reservation_info.reservation_name as reservation_name,"
			+ "ROUND(sum((100-discount_rate)/100*count*product_price.price),0) as total_price,"
			+ "product.description as description "
			+ "from reservation_info "
			+ "join reservation_info_price on reservation_info_price.reservation_info_id = reservation_info.id "
			+ "join product_price on product_price.id = reservation_info_price.product_price_id "
			+ "join product on product.id=product_price.product_id "
			+ "join display_info on display_info.id= reservation_info.display_info_id "
			+ "where reservation_info.reservation_email=:reservationEmail "
			+ "group by reservation_info.display_info_id";
	public static final String UPDATE_RESERVATION_INFO_CANCEL="UPDATE reservation_info SET cancel_flag =TRUE, modify_date =NOW() WHERE id=:reservationInfoId";
}
