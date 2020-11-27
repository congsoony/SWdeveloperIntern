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
		for (ProductPrice item : list) {
			item.setPriceTypeName(setRenameType(item.getPriceTypeName()));
		}
		return list;
	}

	private String setRenameType(String name) {
		String type = "";
		switch (name) {
		case "A":
			type = "성인";
			break;
		case "Y":
			type = "청소년";
			break;
		case "B":
			type = "유아";
			break;
		case "S":
			type = "셋트";
			break;
		case "D":
			type = "장애인";
			break;
		case "C":
			type = "지역주민";
			break;
		case "E":
			type = "어얼리버드";
			break;
		case "V":
			type = "VIP";
			break;
		case "R":
			type = "R석";
			break;
		default:
			type = name + "석";
			break;
		}
		return type;
	}

}
