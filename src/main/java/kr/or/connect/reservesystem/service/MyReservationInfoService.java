package kr.or.connect.reservesystem.service;

import java.util.List;

import kr.or.connect.reservesystem.dto.MyReservationInfo;

public interface MyReservationInfoService {
	
	public List<MyReservationInfo> getMyReservations(String reservationEmail);
	public int updateReservationInfoCancel(int reservationInfoId,String email) throws Exception;
	
	
}
