package kr.or.connect.reservesystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservesystem.dao.BookingLoginDao;
import kr.or.connect.reservesystem.service.BookingLoginService;

@Service
public class BookingLoginServiceImpl implements BookingLoginService{
	
	@Autowired
	private BookingLoginDao bookingLoginDao;

	
	@Override
	@Transactional(readOnly = true)
	public boolean hasEmailId(String email) {
		return bookingLoginDao.hasEmailId(email);
	}
	
	
}
