package kr.or.connect.reservesystem.service;

import java.util.List;
import kr.or.connect.reservesystem.dto.Product;

public interface ProductService {
	
	List<Product> getProductsList(int start, int categoryId);
	List<Product> getAllProductsList(int start);
	int getProductsTotalCount(int categoryId);
	int getALLProductsTotalCount();
	
}
