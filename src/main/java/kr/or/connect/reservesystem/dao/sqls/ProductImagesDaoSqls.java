package kr.or.connect.reservesystem.dao.sqls;

public class ProductImagesDaoSqls {
	public static final String SELECT_BY_DISPLAY_INFO_ID = "SELECT file_info.content_type AS content_type, "
			+ "file_info.create_date AS create_date , "
			+ "file_info.delete_flag AS delete_flag, "
			+ "file_info.id AS file_info_id, "
			+ "file_info.file_name AS file_name , "
			+ "file_info.modify_date AS modify_date, "
			+ "product.id AS product_id ,  "
			+ "product_image.id AS product_image_id , "
			+ "file_info.save_file_name AS save_file_name, "
			+ "product_image.type AS type  "
			+ "FROM file_info "
			+ "JOIN  product_image ON  file_info.id= product_image.file_id "
			+ "JOIN  product ON product.id=product_image.product_id "
			+ "JOIN display_info ON product.id= display_info.product_id "
			+ "WHERE product_image.type = 'ma' AND display_info.id=1";
}
