package kr.or.connect.reservesystem.dao.sqls;

public class ProductPriceDaoSqls {
	
	public static final String SELECT_BY_DISPLAY_INFO_ID="SELECT product_price.create_date AS create_date, "
			+ "product_price.discount_rate AS discount_rate, "
			+ "product_price.modify_date AS modify_date, "
			+ "product_price.price AS price, "
			+ "product_price.price_type_name AS price_type_name, "
			+ "product_price.product_id as product_id, product_price.id AS product_price_id "
			+ "FROM display_info "
			+ "JOIN product_price ON display_info.product_id = product_price.product_id "
			+ "WHERE display_info.id=:displayInfoId";
	
}
