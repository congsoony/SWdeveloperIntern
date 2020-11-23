package kr.or.connect.reservesystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageMoveController {
	@RequestMapping(path = "/mainpage",method = RequestMethod.GET)
	public String moveMainPage() {
		return "mainpage";
	}
	
	@RequestMapping(path = "/detail",method = RequestMethod.GET)
	public String moveDtatil() {
		return "detail";
	}
	
	@RequestMapping(path = "/review",method = RequestMethod.GET)
	public String moveReview() {
		return "review";
	}
}
