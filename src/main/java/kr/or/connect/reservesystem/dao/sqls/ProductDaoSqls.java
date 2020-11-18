package kr.or.connect.reservesystem.dao.sqls;

public class ProductDaoSqls {
	public static final int LIMIT = 4;
	public static final String SELECT_BY_CATEGORYID = "select b.id as display_info_id, b.place_name as place_name, a.content as product_content ,a.description as product_description, a.id as product_id, d.save_file_name as product_Image_url "
			+ "from product a, display_info b, product_image c, file_info d "
			+ "where a.id=b.product_id and a.id = c.product_id and c.file_id = d.id and c.type='th' and a.category_id=:categoryId "
			+ "limit :start,:limit";
	public static final String CATEGORY_COUNT = "select count(*) as count from product a, display_info b where a.id=b.product_id and a.category_id =:categoryId";
	
	public static final String SELECT_BY_ALL_CATEGORY=
			"select b.id as display_info_id, b.place_name as place_name, a.content as product_content ,a.description as product_description, a.id as product_id, d.save_file_name as product_Image_url "
			+ "from product a, display_info b, product_image c, file_info d "
			+ "where a.id=b.product_id and a.id = c.product_id and c.file_id = d.id and c.type='th' limit :start,:limit";
	
	public static final String CATEGORY_ALL_COUNT="select count(*) as count from product a, display_info b where a.id=b.product_id";
			
			
}
