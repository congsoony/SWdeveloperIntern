package kr.or.connect.reservesystem.controller;

import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservesystem.dto.DisplayInfo;
import kr.or.connect.reservesystem.service.DisplayInfoService;

@RestController
@RequestMapping(path = "/api/displayinfo")
public class DisplayInfoController {

	@Autowired
	private DisplayInfoService displayInfoService;

	@GetMapping
	public Map<String, Object> getDisplayInfo(
			@RequestParam(name = "displayInfoId", required = true) int displayInfoId) {

		Map<String, Object> map = new HashMap<>();
		DisplayInfo item = displayInfoService.getDisplayInfo(displayInfoId);

		map.put("displayInfo", item);

		return map;
	}
}
