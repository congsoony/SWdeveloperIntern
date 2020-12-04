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
			RenameType type;
			try {
				type = RenameType.valueOf(item.getPriceTypeName());
				item.setPriceTypeName(type.getName());
			} catch (Exception e) {// 열거형에 없을경우 기존이름 +석
				item.setPriceTypeName(item.getPriceTypeName() + "석");
			}
		}
		return list;
	}

	private enum RenameType {
		A("성인"), Y("청소년"), B("유아"), S("셋트"), D("장애인"), C("지역주민"), E("어얼리버드"), V("VIP"), R("R석");

		private String name;

		public String getName() {
			return name;
		}

		private RenameType(String name) {
			this.name = name;
		}

	}
}
