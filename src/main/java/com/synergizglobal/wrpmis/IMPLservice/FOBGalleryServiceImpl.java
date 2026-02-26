package com.synergizglobal.wrpmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.wrpmis.Idao.FOBGalleryDao;
import com.synergizglobal.wrpmis.Iservice.FOBGalleryService;
import com.synergizglobal.wrpmis.model.FOBGallery;

@Service
public class FOBGalleryServiceImpl implements FOBGalleryService{
	@Autowired
	FOBGalleryDao dao;
	
	@Override
	public List<FOBGallery> getFOBGalleryList(String fob_id) throws Exception {
		return dao.getFOBGalleryList(fob_id);
	}

}
