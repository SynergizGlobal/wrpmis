package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.FOBGalleryDao;
import com.synergizglobal.pmis.Iservice.FOBGalleryService;
import com.synergizglobal.pmis.model.FOBGallery;

@Service
public class FOBGalleryServiceImpl implements FOBGalleryService{
	@Autowired
	FOBGalleryDao dao;
	
	@Override
	public List<FOBGallery> getFOBGalleryList(String fob_id) throws Exception {
		return dao.getFOBGalleryList(fob_id);
	}

}
