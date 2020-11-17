package kr.or.connect.reservesystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservesystem.dao.DisplayInfoDao;
import kr.or.connect.reservesystem.dto.DisplayInfo;
import kr.or.connect.reservesystem.service.DisplayInfoService;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {

	@Autowired
	private DisplayInfoDao displayInfoDao;

	@Override
	@Transactional(readOnly = true)
	public DisplayInfo getDisplayInfo(int displayInfoId) {
		return displayInfoDao.getDisplayInfo(displayInfoId);
	}

}
