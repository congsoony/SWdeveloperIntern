package kr.or.connect.reservesystem.controller;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservesystem.service.BookingLoginService;

@RestController
@RequestMapping(path = "/api/bookinglogin")
public class BookingLoginController {
	@Autowired
	private BookingLoginService bookingLoginService;

	@GetMapping
	public ResponseEntity<Object> BookingLogin(@RequestParam String emailId, HttpServletResponse response) {
		boolean isLogin = bookingLoginService.existEmailId(emailId);
		if (!isLogin) {
			// 없는 이메일 로그인 불가
			return new ResponseEntity<>("false", HttpStatus.OK);
		}
		// 쿠키를 전송한다.
		Cookie cookie = new Cookie("email", emailId);
		cookie.setPath("/"); // / 경로 이하에 모두 쿠키 적용.
		response.addCookie(cookie);
		return new ResponseEntity<>("true", HttpStatus.OK);
	}

}
