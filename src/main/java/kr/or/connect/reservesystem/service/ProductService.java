package kr.or.connect.reservesystem.service;

import java.util.List;
import kr.or.connect.reservesystem.dto.Product;

public interface ProductService {

	List<Product> getSelectByCategoryId(int start, int categoryId);
	List<Product> getSelectByAllCategory(int start);
	int getSelectCategoryCount(int categoryId);
	int getSelectALLCategoryCount();
}
