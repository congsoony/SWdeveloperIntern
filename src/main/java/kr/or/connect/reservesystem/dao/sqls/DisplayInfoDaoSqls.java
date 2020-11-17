package kr.or.connect.reservesystem.dao.sqls;

public class DisplayInfoDaoSqls {
	public static final String SELECT_BY_DISPLAY_INFO_ID="SELECT category.id as category_id , "
			+ "category.name as category_name, "
			+ "display_info.create_date as create_date, "
			+ "display_info.id as display_info_id, "
			+ "display_info.email as email, "
			+ "display_info.homepage as homepage, "
			+ "display_info.modify_date as modify_date, "
			+ "display_info.opening_hours as opening_hours, "
			+ "display_info.place_lot as place_lot, "
			+ "display_info.place_name as place_name, "
			+ "display_info.place_street as place_stree, "
			+ "product.content as product_content, "
			+ "product.description as product_description, "
			+ "product.event as product_event, "
			+ "product.id as product_id, "
			+ "display_info.tel as telephone "
			+ "FROM display_info "
			+ "JOIN product on display_info.product_id = product.id "
			+ "JOIN category on product.category_id = category.id "
			+ "WHERE display_info.id =1";
}
