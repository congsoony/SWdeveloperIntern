package kr.or.connect.reservesystem.dao.sqls;

public class CommentDaoSqls {
	public static final int LIMIT = 3;
	public static final String SELECT_BY_DISPLAY_INFO_ID="SELECT reservation_user_comment.comment AS comment, "
			+ "reservation_user_comment.id as comment_id, "
			+ "date_format(reservation_user_comment.create_date,'%Y.%c.%e') as create_date, "
			+ "date_format(reservation_user_comment.modify_date,'%Y.%c.%e') as modify_date, "
			+ "display_info.product_id as product_id ,"
			+ "date_format(reservation_info.reservation_date,'%Y.%c.%e') as reservation_date, "
			+ "reservation_info.reservation_email as reservation_email, "
			+ "reservation_info.id as reservation_info_id, "
			+ "reservation_info.reservation_name as reservation_name, "
			+ "reservation_info.reservation_tel as reservation_telephone, "
			+ "reservation_user_comment.score as score "
			+ "FROM display_info "
			+ "JOIN reservation_info ON display_info.product_id = reservation_info.product_id "
			+ "JOIN reservation_user_comment ON reservation_info.id=reservation_user_comment.reservation_info_id "
			+ "WHERE display_info.id=:displayInfoId";
	
	public static final String SELECT_BY_DISPLAY_INFO_ID_LIMIT="SELECT reservation_user_comment.comment AS comment, "
			+ "reservation_user_comment.id as comment_id, "
			+ "date_format(reservation_user_comment.create_date,'%Y.%c.%e') as create_date, "
			+ "date_format(reservation_user_comment.modify_date,'%Y.%c.%e') as modify_date, "
			+ "display_info.product_id as product_id ,"
			+ "date_format(reservation_info.reservation_date,'%Y.%c.%e') as reservation_date, "
			+ "reservation_info.reservation_email as reservation_email, "
			+ "reservation_info.id as reservation_info_id, "
			+ "reservation_info.reservation_name as reservation_name, "
			+ "reservation_info.reservation_tel as reservation_telephone, "
			+ "reservation_user_comment.score as score "
			+ "FROM display_info "
			+ "JOIN reservation_info ON display_info.product_id = reservation_info.product_id "
			+ "JOIN reservation_user_comment ON reservation_info.id=reservation_user_comment.reservation_info_id "
			+ "WHERE display_info.id=:displayInfoId "
			+ "LIMIT :limit";
	
	public static final String SELECT_COUNT_BY_DISPLAY_INFO_ID="SELECT COUNT(*) AS count "
			+ "FROM display_info "
			+ "JOIN reservation_info ON display_info.product_id = reservation_info.product_id "
			+ "JOIN reservation_user_comment ON reservation_info.id=reservation_user_comment.reservation_info_id "
			+ "WHERE display_info.id=:displayInfoId";
	
	
	public static final String SELECT_AVG_BY_DISPLAY_INFO_ID="SELECT IFNULL(ROUND(AVG(score),2),0) average_score "
			+ "FROM display_info "
			+ "JOIN reservation_info ON display_info.product_id = reservation_info.product_id "
			+ "JOIN reservation_user_comment ON reservation_info.id=reservation_user_comment.reservation_info_id "
			+ "WHERE display_info.id=:displayInfoId";
	
	public static final String INSERT_RESERVATION_USER_COMMENT="INSERT INTO reservation_user_comment"
			+ "(product_id, reservation_info_id, score, comment, create_date,modify_date) "
			+ "VALUES(:productId,:reservationInfoId,:score,:comment,NOW(),NOW())";
}
