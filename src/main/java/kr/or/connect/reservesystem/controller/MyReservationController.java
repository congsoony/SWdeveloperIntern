package kr.or.connect.reservesystem.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservesystem.service.MyReservationInfoService;

@RestController
@RequestMapping(path = "/api/myreservation")
public class MyReservationController {
	@Autowired
	private MyReservationInfoService myReservationInfoService;
	@GetMapping
	public ResponseEntity<Object> getMyReservationInfoList(@RequestParam String email) {
		List<?> list=myReservationInfoService.getMyReservations(email);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	@PutMapping
	public ResponseEntity<Object> reservationInfoCancel(@RequestParam int reservationInfoId) throws Exception{
		try {
			myReservationInfoService.updateReservationInfoCancel(reservationInfoId);
			return new ResponseEntity<>("true",HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>("false",HttpStatus.OK);
			
		}
	}
}
