package kr.or.connect.reservesystem.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.reservesystem.dto.DisplayInfoImage;
import kr.or.connect.reservesystem.service.DisplayInfoImageService;

@RestController
@RequestMapping(path = "/api/displayinfoimage")
public class DisplayInfoImageController {

	@Autowired
	private DisplayInfoImageService displayInfoImageService;

	@GetMapping
	public Map<String, Object> getDisplayInfoImages(@RequestParam(name = "displayInfoId", required = true) int displayInfoId) {

		Map<String, Object> map = new HashMap<>();
		DisplayInfoImage item = displayInfoImageService.getDisplayInfoImage(displayInfoId);

		map.put("displayInfoImage", item);

		return map;
	}

}
