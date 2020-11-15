package kr.or.connect.reservesystem.dao;

public class PromotionDaoSqls {
	public static final String SELECT_ALL = "select a.id as id, b.id as product_id, e.save_file_name as product_image_url "
			+ "from promotion a, product b, display_info c ,product_image d,file_info e "
			+ "where a.product_id=b.id and b.id=c.product_id and a.id= d.product_id and d.file_id = e.id and d.type='th' "
			+ "group by b.id order by a.id";
	
}
