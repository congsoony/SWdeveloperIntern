package kr.or.connect.reservesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservesystem.dao.CategoryDao;
import kr.or.connect.reservesystem.dto.Category;
import kr.or.connect.reservesystem.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	@Transactional(readOnly = true)
	public List<Category> getCategories() {
		List<Category> list = categoryDao.selectAll();
		return list;
	}

}
