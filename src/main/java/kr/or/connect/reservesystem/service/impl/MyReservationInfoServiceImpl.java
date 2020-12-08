package kr.or.connect.reservesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservesystem.dao.BookingLoginDao;
import kr.or.connect.reservesystem.dao.MyReservationInfoDao;
import kr.or.connect.reservesystem.dao.ProductPriceDao;
import kr.or.connect.reservesystem.dto.MyReservationInfo;
import kr.or.connect.reservesystem.dto.ProductPrice;
import kr.or.connect.reservesystem.service.MyReservationInfoService;

@Service
public class MyReservationInfoServiceImpl implements MyReservationInfoService {

	@Autowired
	private MyReservationInfoDao myReservationInfoDao;
	@Autowired
	private ProductPriceDao productPriceDao;
	@Autowired
	private BookingLoginDao bookingLoginDao;

	@Override
	@Transactional(readOnly = true)
	public List<MyReservationInfo> getMyReservations(String reservationEmail) {
		List<MyReservationInfo> list = myReservationInfoDao.getMyReservations(reservationEmail);
		for (MyReservationInfo item : list) {
			List<ProductPrice> prices = productPriceDao.getProductPrices(item.getReservationInfoId());
			item.setProductPriceList(prices);
		}
		return list;

	}

	@Override
	@Transactional
	public int updateReservationInfoCancel(int reservationInfoId, String email) throws Exception {
		boolean existEmail = bookingLoginDao.existEmailId(email);
		if (existEmail == false) {
			throw new RuntimeException();
		}

		int update = myReservationInfoDao.updateReservationInfoCancel(reservationInfoId);
		if (update < 0) {
			throw new Exception();
		}

		return update;
	}

}
