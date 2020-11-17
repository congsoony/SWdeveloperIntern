package kr.or.connect.reservesystem.dao.sqls;

public class ProductPriceDaoSqls {
	public static final String SELECT_BY_DISPLAY_INFO_ID="SELECT product_price.create_date AS create_date, "
			+ "product_price.discount_rate AS discount_rate, "
			+ "product_price.modify_date AS modify_date, "
			+ "product_price.price AS price, "
			+ "product_price.price_type_name AS price_type_name, "
			+ "product.id as product_id, product_price.id AS product_price_id "
			+ "FROM product_price "
			+ "JOIN product ON product.id = product_price.product_id "
			+ "JOIN display_info ON product.id = display_info.product_id "
			+ "WHERE display_info.id=:displayInfoId";
}
