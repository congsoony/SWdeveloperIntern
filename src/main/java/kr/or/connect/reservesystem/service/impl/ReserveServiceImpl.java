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
	public ReserveInfo insertReservationInfo(ReserveInfo reserveInfo) throws Exception{
		List<ReservationInfoPrice> list=reserveInfo.getReservationInfoPrices();
		if(list.size()==0) {
			throw new RuntimeException();
		}
		for(ReservationInfoPrice item:list) {
			int reservationInfoPriceId=reserveDao.insertReservationInfoPrice(item);
			item.setProductPriceId(reservationInfoPriceId);
		}
		
		
		int reserveInfoId = reserveDao.insertReservationInfo(reserveInfo);
		reserveInfo.setDisplayInfoId(reserveInfoId);
		reserveInfo.setReservationInfoPrices(list);
		
		return reserveInfo;
	}

	

}
