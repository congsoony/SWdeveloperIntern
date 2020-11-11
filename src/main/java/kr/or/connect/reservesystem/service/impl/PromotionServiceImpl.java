package kr.or.connect.reservesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservesystem.dao.PromotionDao;
import kr.or.connect.reservesystem.dto.Promotion;
import kr.or.connect.reservesystem.service.PromotionService;

@Service
public class PromotionServiceImpl implements PromotionService {
	@Autowired
	private PromotionDao promotionDao;

	@Override
	@Transactional(readOnly = true)
	public List<Promotion> getPromotions() {

		List<Promotion> list = promotionDao.selectAll();
		return list;
	}

}
