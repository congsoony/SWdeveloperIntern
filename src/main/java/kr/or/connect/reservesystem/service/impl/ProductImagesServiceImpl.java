package kr.or.connect.reservesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservesystem.dao.ProductImagesDao;
import kr.or.connect.reservesystem.dto.ProductImages;
import kr.or.connect.reservesystem.service.ProductImagesService;

@Service
public class ProductImagesServiceImpl implements ProductImagesService {

	@Autowired
	private ProductImagesDao productImagesDao;

	@Override
	@Transactional(readOnly = true)
	public List<ProductImages> getProductImagesList(int displayInfoId,String type) {
		return productImagesDao.getProductImagesList(displayInfoId,type);
	}

}
