package kr.or.connect.reservesystem.dao;

public class CategoryDaoSqls {
	public static final String SELECT_ALL ="select b.category_id as cagegory_id, a.name as name, count(*)"
			+ "from category a, product b , display_info c,product_image d, file_info f"
			+ "where a.id=b.category_id and b.id=c.product_id and a.id=d.product_id"
			+ "and d.file_id=f.id"
			+ "group by (b.category_id) order by b.category_id;";
}
