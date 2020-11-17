package kr.or.connect.reservesystem.service;

import java.util.List;

import kr.or.connect.reservesystem.dto.ProductPrice;

public interface ProductPriceService {
	public List<ProductPrice> getProductPriceList(int displayInfoId);
	
}
