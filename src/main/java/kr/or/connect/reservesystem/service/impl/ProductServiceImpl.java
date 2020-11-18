package kr.or.connect.reservesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservesystem.dao.ProductDao;
import kr.or.connect.reservesystem.dto.Product;
import kr.or.connect.reservesystem.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Override
	@Transactional(readOnly = true)
	public List<Product> getProductsList(int start, int categoryId) {
		return productDao.getProductsList(start, categoryId);
	}

	@Override
	@Transactional(readOnly = true)
	public int getProductsTotalCount(int categoryId) {
		return productDao.getProductsTotalCount(categoryId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> getAllProductsList(int start) {
		return productDao.getAllProductsList(start);
	}

	@Override
	@Transactional(readOnly = true)
	public int getALLProductsTotalCount() {
		return productDao.getALLProductsTotalCount();
	}

}
