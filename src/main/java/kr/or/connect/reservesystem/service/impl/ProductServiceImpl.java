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
	public List<Product> getSelectByCategoryId(int start, int categoryId) {
		return productDao.selectByCategoryId(start, categoryId);
	}

	@Override
	@Transactional(readOnly = true)
	public int getSelectCategoryCount(int categoryId) {
		return productDao.selectCategoryCount(categoryId);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Product> getSelectByAllCategory(int start) {
		return productDao.selectByALLCategory(start);
	}

	@Override
	@Transactional(readOnly = true)
	public int getSelectALLCategoryCount() {
		return productDao.selectCategoryAllCount();
	}

}
