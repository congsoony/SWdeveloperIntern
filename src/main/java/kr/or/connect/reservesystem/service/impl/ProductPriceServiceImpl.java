package kr.or.connect.reservesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservesystem.dao.ProductPriceDao;
import kr.or.connect.reservesystem.dto.ProductPrice;
import kr.or.connect.reservesystem.service.ProductPriceService;

@Service
public class ProductPriceServiceImpl implements ProductPriceService {

	@Autowired
	private ProductPriceDao productPriceDao;

	@Override
	@Transactional(readOnly = true)
	public List<ProductPrice> getProductPriceList(int displayInfoId) {
		List<ProductPrice> list = productPriceDao.getProductPriceList(displayInfoId);
		return list;
	}
}
