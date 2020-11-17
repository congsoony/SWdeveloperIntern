package kr.or.connect.reservesystem.dao.sqls;

public class CommentDaoSqls {
	public static final String SELECT_BY_DISPLAY_INFO_ID="SELECT reservation_user_comment.comment AS comment, "
			+ "reservation_user_comment.id as comment_id, "
			+ "reservation_user_comment.create_date as create_date, "
			+ "reservation_user_comment.modify_date as modify_date, "
			+ "product.id as product_id ,"
			+ "reservation_info.reservation_date as reservation_date, "
			+ "reservation_info.reservation_email as reservation_email, "
			+ "reservation_info.id as reservation_info_id, "
			+ "reservation_info.reservation_name as reservation_name, "
			+ "reservation_info.reservation_tel as reservation_telephone, "
			+ "reservation_user_comment.score as score "
			+ "FROM reservation_user_comment "
			+ "JOIN reservation_info ON reservation_info.id=reservation_user_comment.reservation_info_id "
			+ "JOIN product ON product.id = reservation_info.product_id "
			+ "JOIN display_info ON product.id= display_info.product_id "
			+ "WHERE display_info.id= :displayInfoId";
	
}
