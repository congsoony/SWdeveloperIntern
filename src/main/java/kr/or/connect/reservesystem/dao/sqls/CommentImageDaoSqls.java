package kr.or.connect.reservesystem.dao.sqls;

public class CommentImageDaoSqls {
	public static final String SELECT_BY_RESERVATION_USER_COMMENT_ID ="SELECT file_info.content_type AS content_type, "
			+ "file_info.create_date AS create_date , "
			+ "file_info.delete_flag AS delete_flag, "
			+ "file_info.id AS file_info_id, "
			+ "file_info.file_name AS file_name ,"
			+ "reservation_user_comment_image.id AS image_id, "
			+ "file_info.modify_date AS modify_date, "
			+ "reservation_info.id AS reservation_info_id, "
			+ "reservation_user_comment.id AS reservation_user_comment_id, "
			+ "file_info.save_file_name AS save_file_name "
			+ "FROM reservation_user_comment_image "
			+ "JOIN file_info ON reservation_user_comment_image.file_id = file_info.id "
			+ "JOIN reservation_user_comment ON reservation_user_comment_image.reservation_user_comment_id = reservation_user_comment.id "
			+ "JOIN reservation_info ON reservation_info.id= reservation_user_comment.reservation_info_id "
			+ "JOIN product ON reservation_info.product_id = product.id "
			+ "JOIN display_info ON product.id= display_info.product_id "
			+ "WHERE reservation_user_comment.id = :reservationUserCommentId";
	
}
