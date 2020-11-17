package kr.or.connect.reservesystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservesystem.dao.DisplayInfoImageDao;
import kr.or.connect.reservesystem.dto.DisplayInfoImage;
import kr.or.connect.reservesystem.service.DisplayInfoImageService;

@Service
public class DisplayInfoImageServiceImpl implements DisplayInfoImageService {

	@Autowired
	private DisplayInfoImageDao displayInfoImageDao;

	@Override
	@Transactional(readOnly = true)
	public DisplayInfoImage getDisplayInfoImage(int displayInfoId) {
		return displayInfoImageDao.getDisplayInfoImage(displayInfoId);
	}

}
