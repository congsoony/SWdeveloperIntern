package kr.or.connect.reservesystem.dao.sqls;

public class DisplayInfoImageDaoSqls {
	public static final String SELECT_BY_DISPLAY_INFO_ID="SELECT file_info.content_type AS content_type, "
			+ "file_info.create_date AS create_date, "
			+ "file_info.delete_flag AS delete_flag, "
			+ "display_info_image.display_info_id AS display_info_id, "
			+ "display_info_image.id AS display_info_image_id, "
			+ "display_info_image.file_id AS file_id, "
			+ "file_info.file_name AS file_name, "
			+ "file_info.modify_date AS modify_date ,"
			+ "file_info.save_file_name AS save_file_name "
			+ "FROM display_info_image "
			+ "JOIN file_info ON display_info_image.file_id= file_info.id "
			+ "JOIN display_info ON display_info_image.display_info_id = display_info.id "
			+ "WHERE display_info.id=2";
	
}
