package kr.or.connect.reservesystem.dao;

public class CategoryDaoSqls {
	public static final String SELECT_ALL = "SELECT a.id as id, a.name as name,count(*) as count "
			+ "FROM category a, product b,display_info c "
			+ "where a.id=b.category_id and b.id=c.product_id group  by a.id";

}
