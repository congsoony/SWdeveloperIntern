package kr.or.connect.reservesystem.service;

import kr.or.connect.reservesystem.dto.ReserveInfo;

public interface ReserveService {
	public ReserveInfo insertReservationInfo(ReserveInfo reserveInfo) throws Exception;
}
