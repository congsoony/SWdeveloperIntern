package kr.or.connect.reservesystem.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservesystem.dao.ReserveDao;
import kr.or.connect.reservesystem.dto.ReservationInfoPrice;
import kr.or.connect.reservesystem.dto.ReserveInfo;
import kr.or.connect.reservesystem.service.ReserveService;

@Service
public class ReserveServiceImpl implements ReserveService {

	@Autowired
	ReserveDao reserveDao;

	@Override
	@Transactional(readOnly = false)
	public ReserveInfo insertReservationInfo(ReserveInfo reserveInfo) throws Exception {

		int reservationInfoId = reserveDao.insertReservationInfo(reserveInfo);
		reserveInfo.setReservationInfoId(reservationInfoId);
		List<ReservationInfoPrice> list = reserveInfo.getReservationInfoPrices();
		if (list.size() == 0) {
			throw new RuntimeException();
		}

		for (ReservationInfoPrice item : list) {
			item.setReservationInfoId(reservationInfoId);//reservationinfoid 값 insert한것 넣어줘야함 
			int reservationInfoPriceId = reserveDao.insertReservationInfoPrice(item);
			item.setProductPriceId(reservationInfoPriceId);
		}
		reserveInfo.setReservationInfoPrices(list);
		return reserveInfo;
	}

}
