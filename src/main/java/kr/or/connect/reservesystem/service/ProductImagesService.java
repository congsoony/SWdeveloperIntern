package kr.or.connect.reservesystem.service;

import java.util.List;

import kr.or.connect.reservesystem.dto.ProductImages;

public interface ProductImagesService {
	public List<ProductImages> getProductImagesList(int displayInfoId);
	
}
