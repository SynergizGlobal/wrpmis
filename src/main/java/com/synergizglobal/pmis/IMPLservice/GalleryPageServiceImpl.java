package com.synergizglobal.pmis.IMPLservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergizglobal.pmis.Idao.GalleryPageDao;
import com.synergizglobal.pmis.Iservice.GalleryPageService;
import com.synergizglobal.pmis.model.Structure;

@Service
public class GalleryPageServiceImpl implements GalleryPageService{

	@Autowired
	GalleryPageDao dao;

	@Override
	public List<Structure> getGalleryList(Structure obj) throws Exception {
		return dao.getGalleryList(obj);
	}

	@Override
	public List<Structure> getMonthList(Structure obj) throws Exception {
		return dao.getMonthList(obj);
	}

	@Override
	public List<Structure> getStructuresList(Structure obj) throws Exception {
		return dao.getStructuresList(obj);
	}

	@Override
	public List<Structure> getStructureIdList(Structure obj) throws Exception {
		return dao.getStructureIdList(obj);
	}
}
